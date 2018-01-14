package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.collections.PStream;
import com.persistentbit.javacodegen.*;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.string.UString;

import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class TableTypeDef implements TypeDef{

	private final CgTableName       tableName;
	private final PList<TableField> fields;
	private final StructureTypeDef  structureTypeDef;


	public TableTypeDef(CgTableName tableName,
						PList<TableField> fields
	) {
		this.tableName = tableName;
		this.fields = fields;
		this.structureTypeDef = new StructureTypeDef(tableName, fields, true);
	}


	@Override
	public void init(CgContext context) {
		structureTypeDef.init(context);
	}

	private TypeRef getTableRef(CgContext context) {
		return context.createTableTypeRef(tableName);
	}

	@Override
	public TypeRef getRef(CgContext context) {
		return context.createExprTypeRef(tableName);
	}

	public TypeRef getJavaRef(CgContext context) {
		return context.createValueTypeRef(tableName);
	}

	private TypeRef getFactoryRef(CgContext context) {
		return context.createTypeFactoryTypeRef(tableName);
	}


	@Override
	public DbGenContext generateDb(CgContext context, DbGenContext db) {
		TypeRef tableRef     = getTableRef(context);
		String  instanceName = context.tableNameToJavaInstanceName(tableName);
		String  exprContext  = db.getExprContext();
		db = db.withCls(db.getCls().addField(
			new JField(instanceName, tableRef.getClassName())
				.addImports(tableRef.getImports(context))
				.asStatic()
				.asFinal()
				.initValue("new " + tableRef.getClassName() + "(" + exprContext + ")")
				.withAccessLevel(AccessLevel.Public)
		));
		db = db.addInitCode(pw -> {
			//pw.println(instanceName + " = new " + tableRef.getClassName() + "(" + exprContext + ");");
			//pw.println(exprContext + ".addTable(" + instanceName + ");");
		});
		db = structureTypeDef.generateDb(context, db);
		return db;
	}

	@Override
	public Result<PList<JJavaFile>> generate(CgContext context) {
		String packageName = getTableRef(context).getFullPackage(context);
		String packageNameInsert = context.createInsertTypeRef(tableName).getFullPackage(context);
		return Result.function(getTableRef(context))
			.code(log ->
					  buildTableClass(context)
						  .map(cls -> new JJavaFile(packageName)
							  .addClass(cls))
			).flatMap(tc -> buildInsertClass(context)
				.map(cls -> new JJavaFile(packageNameInsert).addClass(cls))
				.map(ic -> PList.val(tc, ic))
			).flatMap(res ->
						  structureTypeDef.generate(context)
							  .map(st -> res.plusAll(st))
			);
	}

	private PStream<SimpleTableField> getExpandedFields(CgContext context) {
		return fields.map(f -> f.expand(context, null)).flatten();
	}

	private PStream<SimpleTableField> getPrimKeys(CgContext context) {
		return getExpandedFields(context).filter(sf -> sf.isPrimKey());
	}


	private String createPrimKeyEq(CgContext context, Function<String, String> rightInstance) {
		String where = null;
		for(SimpleTableField stf : getPrimKeys(context)) {
			String exprCode = stf.getExprCode(context);
			String thisWhere = "this." + exprCode + ".eq(" + rightInstance.apply(
				exprCode) + ")";
			if(where == null) {
				where = thisWhere;
			}
			else {
				where = where + ".and(" + thisWhere + ")";
			}
		}
		return where;
	}

	private Result<JClass> buildTableClass(CgContext context) {
		return Result.function(this.getTableRef(context))
			.code(log -> {
				TypeRef tableRef = getTableRef(context);
				TypeRef exprRef  = getRef(context);
				TypeRef javaRef  = getJavaRef(context);


				JClass  cls        = new JClass(getTableRef(context).getClassName());
				TypeRef extendsRef = TypeRef.create(AbstractTable.class).withGenerics(exprRef, javaRef);

				cls = cls.addImports(extendsRef.getImports(context));
				cls = cls.addImports(javaRef.getImports(context));


				cls = cls.asFinal().extendsDef(extendsRef.getClassName());
				//ADD TABLENAME
				cls = cls.addField(
					new JField("_tableName", TableName.class)
						.initValue("new " + TableName.class.getSimpleName()
									   + "(" + sep(esc(tableName.getCatalogName().orElse(null)), esc(tableName
																										 .getSchemaName()
																										 .orElse(null)), esc(tableName
																																 .getTableName()
																																 .orElse(null))) + ")")
						.addImport(TableName.class)
				);
				PStream<SimpleTableField> expandedFields = getExpandedFields(context);
				PStream<SimpleTableField> primKeyFields  = getPrimKeys(context);
				boolean                   hasPrimKey     = primKeyFields.isEmpty() == false;

				String selectByIdWorkClassName = null;


				if(hasPrimKey) {
					if(primKeyFields.isEmpty() == false) {
						selectByIdWorkClassName = "DbWorkP" + primKeyFields.size();
						cls = cls.addImport(DbWorkP1.class.getPackageName() + "." + selectByIdWorkClassName);
						selectByIdWorkClassName +=
							"<" + primKeyFields.map(tf -> tf.getJavaTypeRef(context).getClassName())
								.toString(", ") + "," + javaRef
								.getClassName() + ">";
					}
					else {
						selectByIdWorkClassName = DbWork.class.getSimpleName();
					}
					//ADD SELECTBYID

					cls = cls.addField(
						new JField("_selectById", selectByIdWorkClassName)
					);
				}

				//ADD _ALL
				cls = cls.addField(new JField("_all", exprRef.getClassName()));

				//ADD FIELDS
				for(TableField field : fields) {
					cls = cls.addField(field.createExprField(context));
				}
				TypeRef refTypeFactory = context.createTypeFactoryTypeRef(tableName);
				//CREATE CONSTRUCTORS
				cls = cls.addMethod(
					new JMethod(tableRef.getClassName())
						.withAccessLevel(AccessLevel.Private)
						.addArg(new JArgument(ExprContext.class.getSimpleName(), "context"))
						.addArg(new JArgument("String", "alias"))
						.addImport(Param.class)
						.addImports(refTypeFactory.getImports(context))
						.withCode(pw -> {
							pw.println("super(context, alias);");
							pw.println("context.registerType(" + exprRef.getClassName() + ".class," + refTypeFactory
								.getClassName() + ".class);");
							pw.println("this._all = context");
							pw.println("\t.getTypeFactory(" + exprRef.getClassName() + ".class)");
							pw.println("\t.buildTableField(createFullTableNameOrAlias() + \".\",\"\",\"\");");
							for(TableField tf : fields) {
								String jn = tf.getJavaName(context);
								pw.println("this." + jn + " = _all." + jn + ";");
							}
							if(hasPrimKey) {
								pw.println("this._selectById = query(p -> q -> {");
								pw.indent(pi -> {

									String allParams = "";
									for(SimpleTableField stf : primKeyFields) {
										String expName   = stf.getTypeRef(context).getClassName();
										String fieldName = stf.getJavaName(context);
										pi.println("Param<" + expName + "> param" + fieldName + " = context.param(" + expName + ".class, " + esc(fieldName) + ");");

										if(allParams.isEmpty() == false) {
											allParams += ", ";
										}
										allParams += "param" + fieldName;
									}
									pi.println("return q");
									pi.println("\t.where(" + createPrimKeyEq(context, s -> "param" + s + ".getExpr()") + ")");
									pi.println("\t.selection(all())");
									pi.println("\t.one(" + allParams + ");");
								});

								pw.println("});");

							}
						})
				).addImport(ExprContext.class);

				cls = cls.addMethod(
					new JMethod(tableRef.getClassName())
						.withAccessLevel(AccessLevel.Public)
						.addArg(new JArgument(ExprContext.class.getSimpleName(), "context"))
						.withCode(pw -> {
							pw.println("this(context,null);");
						})
				);
				//CREATE TYPECLASS GETTER
				cls = cls.addMethod(
					new JMethod("getTypeClass", "Class<" + exprRef.getClassName() + ">")
						.withAccessLevel(AccessLevel.Public)
						.overrides()
						.withCode(pw -> {
							pw.println("return " + exprRef.getClassName() + ".class;");
						})
						.addImport(JImport.forClass(Table.class))
				);
				//CREATE TableName GETTER
				cls = cls.addMethod(
					new JMethod("getTableName", TableName.class.getSimpleName())
						.withAccessLevel(AccessLevel.Protected)
						.overrides()
						.withCode(pw -> {
							pw.println("return _tableName;");
						})
				);

				//CREATE as
				cls = cls.addMethod(
					new JMethod("as", tableRef.getClassName())
						.withAccessLevel(AccessLevel.Public)
						.addArg(new JArgument("String", "aliasName"))
						.overrides()
						.withCode(pw -> {
							pw.println("return new " + tableRef.getClassName() + "(context, aliasName);");
						})
				);
				//CREATE all
				cls = cls.addMethod(
					new JMethod("all", exprRef.getClassName())
						.withAccessLevel(AccessLevel.Public)
						.overrides()
						.withCode(pw -> {
							pw.println("return _all;");
						})
				);
				//CREATE QUERY
				cls = cls.addMethod(
					new JMethod("query", context.getQueryInterfaceClass().getSimpleName())
						.withAccessLevel(AccessLevel.Public)
						.withCode(pw -> {
							pw.println("return new " + context.getQueryImplClass()
								.getSimpleName() + "(context, PList.val(this));");
						})
						.addImport(context.getQueryImplClass())
						.addImport(context.getQueryInterfaceClass())
						.addImport(PList.class)
				);
				cls = cls.addMethod(
					new JMethod("query", "<R> R")
						.withAccessLevel(AccessLevel.Public)
						.addArg(new JArgument("Function<" + exprRef
							.getClassName() + ", Function<Query, R>>", "builder"))
						.withCode(pw -> {
							pw.println("return builder.apply(all()).apply(query());");
						})
						.addImport(Query.class)
						.addImport(QueryImpl.class)
						.addImport(Function.class)
						.withCode(pw -> {
							pw.println("return builder.apply(all()).apply(query());");
						})
				);
				//CREATE INSERT
				TypeRef insertRef = context.createInsertTypeRef(tableName);
				cls = cls.addMethod(
					new JMethod("insert", insertRef.getClassName())
						.withAccessLevel(AccessLevel.Public)
						.addImports(insertRef.getImports(context))
						.addImport(QueryImpl.class)
						.addImport(Function.class)
						.withCode(pw -> {
							pw.println("return new " + insertRef.getClassName() + "(context, this);");
						})
				);
				PStream<JField>
					insertFields =
					fields.map(f -> f.expand(context, null)).<TableField>flatten()
						.map(f -> f.createJavaField(context, true).orElseThrow());
				cls = cls.addImports(insertFields.map(JField::getAllImports).flatten());
				String autoGenClass = getPrimKeys(context).find(stf -> stf.isAutoGenKey())
					.map(stf -> stf.getJavaTypeRef(context).getClassName()).orElse("Object");
				cls = cls.addMethod(
					new JMethod("insert", DbWork.class.getSimpleName() + "<" + autoGenClass + ">")
						.withAccessLevel(AccessLevel.Public)
						.addArgs(insertFields.map(JField::asArgument))
						.addImport(DbWork.class)
						.addImport(Result.class)
						.addImport(InsertResult.class)
						.withCode(pw -> {
							pw.println("return insert()");
							pw.println(
								".add(" + insertFields.map(JField::getName).toString(", ") + ")"
							);
							pw.println(".flatMap(irList-> " + Result.class
								.getSimpleName() + ".fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));");
						})
				);
				cls = cls.addMethod(
					new JMethod("insert", DbWork.class.getSimpleName() + "<" + javaRef.getClassName() + ">")
						.withAccessLevel(AccessLevel.Public)
						.addArg(new JArgument(javaRef.getClassName(), "p"))
						.withCode(pw -> {
							pw.println("return insert().add(p)");
							pw.println("\t.flatMap(irList -> Result.fromOpt(irList.headOpt())");
							pw.println("\t\t.flatMap(ir ->");
							pw.println("\t\t\tir.getUpdateCount() == 0");
							pw.println("\t\t\t\t? Result.empty(\"No record inserted for \" + p)");
							pw.println("\t\t\t\t: Result.success(p)));");
						})
				);

				//CREATE val
				cls = cls.addMethod(
					new JMethod("val", exprRef.getClassName())
						.addArg(new JArgument(javaRef.getClassName(), "value"))
						.withAccessLevel(AccessLevel.Public)
						.withCode(pw -> {
							pw.println(
								"return context.getTypeFactory(" + exprRef.getClassName() + ".class).buildVal(value);"
							);
						})
				);
				//CREATE UPDATE
				cls = cls.addMethod(
					new JMethod("update", Update.class.getSimpleName())
						.withAccessLevel(AccessLevel.Public)
						.addImport(Update.class)
						.withCode(pw -> {
							pw.println("return new Update(context, this);");
						})
				);
				if(hasPrimKey) {
					cls = cls.addMethod(
						new JMethod("update", DbWork.class.getSimpleName() + "<Integer>")
							.addArg(new JArgument(javaRef.getClassName(), "value"))
							.withAccessLevel(AccessLevel.Public)
							.withCode(pw -> {
								pw.println(exprRef.getClassName() + " e = val(value);");
								pw.println("return update()");
								pw.println("\t.set(all(), e)");
								pw.println(".where(" + createPrimKeyEq(context, s -> "e." + s) + ");");
							})
					);
					//CREATE select
					cls = cls.addMethod(
						new JMethod("selectById", DbWork.class.getSimpleName() + "<" + javaRef.getClassName() + ">")
							.withAccessLevel(AccessLevel.Public)
							.addArgs(primKeyFields
										 .map(sf -> sf.createJavaField(context, false).orElseThrow().asArgument()))
							.withCode(pw -> {
								pw.println("return _selectById.with(" +
											   primKeyFields.map(sf -> sf.createJavaField(context, false).orElseThrow()
												   .getName())
												   .toString(", ")
											   + ");");
							})
					);

				}
				//CREATE DELETE
				cls = cls.addMethod(
					new JMethod("delete", Delete.class.getSimpleName())
						.withAccessLevel(AccessLevel.Public)
						.withCode(pw -> {
							pw.println("return new Delete(context, this); ");
						})
						.addImport(Delete.class)
				);
				if(hasPrimKey) {
					cls = cls.addMethod(
						new JMethod("deleteById", DbWork.class.getSimpleName() + "<Integer>")
							.withAccessLevel(AccessLevel.Public)
							.addArgs(primKeyFields
										 .map(sf -> sf.createJavaField(context, false).orElseThrow().asArgument()))
							.withCode(pw -> {
								pw.println("return delete()");
								pw.println("\t.where(" + createPrimKeyEq(context, s -> s) + ");");
							})
					);
				}
				return Result.success(cls);
			});


	}

	private Result<JClass> buildInsertClass(CgContext context) {
		return Result.function().code(log -> {
			TypeRef                    insertRef    = context.createInsertTypeRef(tableName);
			TypeRef                    tableRef     = context.createTableTypeRef(tableName);
			Optional<SimpleTableField> autoGenField = getPrimKeys(context).find(pk -> pk.isAutoGenKey());
			JClass cls = new JClass(insertRef.getClassName())
				.extendsDef(context.getInsertImplClass().getSimpleName() + "<" + tableRef
					.getClassName() + ", " + autoGenField
					.map(tf -> tf.getJavaTypeRef(context).getClassName()).orElse("Void") + ">")
				.addImport(context.getInsertImplClass())
				.addImports(tableRef.getImports(context));

			PStream<SimpleTableField> expanded = getExpandedFields(context);
			//ADD COLUMN NAMES
			cls = cls.addField(
				new JField("columnNames", "PList<String>")
					.addImport(PList.class)
					.withAccessLevel(AccessLevel.Private)
					.asStatic()
					.initValue(
						"PList.val(" + expanded.map(sf -> esc(sf.getColumnName(context))).toString(", ") + ")"
					)
			);
			//ADD CONSTRUCTOR
			cls = cls.addMethod(
				new JMethod(insertRef.getClassName())
					.addArg(ExprContext.class, "context")
					.addArg(tableRef.getClassName(), "into")
					.addArg("PList<String>", "columnNames")
					.addArg("PSet<String>", "withDefaults")
					.addImport(PSet.class)
					.addArg("String", "autoGenKeyName")
					.addArg("PList<Object[]>", "rows")
					.withCode(pw -> {
						pw.println("super(context,into,columnNames,withDefaults, autoGenKeyName,rows);");
					})
			);
			cls = cls.addMethod(
				new JMethod(insertRef.getClassName())
					.addArg(ExprContext.class, "context")
					.addArg(tableRef.getClassName(), "into")
					.withCode(pw -> {
						pw.println("this(context,into,columnNames,PSet.empty(), "
									   + autoGenField.map(sf -> esc(sf.getColumnName(context))).orElse("null")
									   + ",PList.empty());");
					})
			);

			//ADD ADD-METHOD
			cls = cls.addMethod(
				new JMethod("add", insertRef.getClassName())
					.addArgs(expanded.map(sf -> sf.createJavaField(context, true).orElseThrow().asArgument()))
					.withCode(pw -> {
						pw.println("Object[] row = new Object[]{");
						boolean first = true;
						for(SimpleTableField f : expanded) {
							if(first == false) {
								pw.print(", ");
							}
							first = false;
							pw.println("\t" + f.getJavaName(context));
						}
						pw.println("};");
						pw.println("return new " + insertRef.getClassName() + "(");
						pw.println("\tthis.context,this.into,this.columnNames,this.withDefaults, this.autoGenKeyName,this.rows.plus(row));");
					})
			);

			cls = cls.addMethod(
				new JMethod("add", insertRef.getClassName())
					.addArg(getJavaRef(context).getClassName(), "value")
					.addImports(getJavaRef(context).getImports(context))
					.withCode(pw -> {
						pw.println("return add(");
						pw.indent(pi -> {
							pi.println(expanded.map(sf -> sf.getJavaGetter(context))
										   .map(s -> "value." + s)
										   .toString(", "));
						});
						pw.println(");");
					})

			);

			return Result.success(cls);
		});

	}


	private final String esc(String str) {

		return str == null
			? "null"
			: "\"" + UString.escapeToJavaString(str) + "\"";
	}

	private final String sep(Object... val) {
		return sepWith(", ", val);
	}

	private final String sepWith(String sep, Object... val) {
		String res = "";
		for(Object obj : val) {
			if(res.isEmpty() == false) {
				res += sep;
			}
			res += obj.toString();
		}
		return res;
	}
}
