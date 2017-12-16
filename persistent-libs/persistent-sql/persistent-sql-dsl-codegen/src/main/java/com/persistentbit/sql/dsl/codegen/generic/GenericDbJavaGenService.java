package com.persistentbit.sql.dsl.codegen.generic;

import com.persistentbit.collections.*;
import com.persistentbit.javacodegen.*;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.annotations.DbColumnName;
import com.persistentbit.sql.dsl.codegen.*;
import com.persistentbit.sql.dsl.codegen.dbjavafields.DbJavaField;
import com.persistentbit.sql.dsl.codegen.dbjavafields.DbJavaTable;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.exprcontext.impl.GenericDbContext;
import com.persistentbit.sql.dsl.generic.DbGeneric;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.sql.dsl.generic.inserts.InsertResult;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.meta.data.DbMetaDatabase;
import com.persistentbit.sql.meta.data.DbMetaSchema;
import com.persistentbit.sql.meta.data.DbMetaUDT;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.string.UString;
import com.persistentbit.tuples.Tuple2;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
public class GenericDbJavaGenService implements DbJavaGenService{

	@Override
	public DbHandlingLevel getHandlingLevel(DbMetaDatabase db) {
		return DbHandlingLevel.onlyGeneric;
	}

	@Override
	public String getDescription() {
		return "Generic Database Code generator";
	}

	@Override
	public Result<PList<GeneratedJavaSource>> generate(DbJavaGenOptions options, DbDefinition data) {
		return Result.function().code(log -> {

			String rootPackage = options.getRootPackage();
			DbNameTransformer nameTransformer = options.getNameTransformer();


			//CREATE STATE CLASSES SOURCE CODE
			Result<PList<GeneratedJavaSource>> genSourceEnums = UPStreams.fromSequence(
				data.getEnumTypes().map(e -> generateEnumSource(rootPackage,nameTransformer,e))
			).map(PStream::plist);


			Result<PList<GeneratedJavaSource>> genSourceCustomTypes =
				UPStreams.fromSequence(data.getCustomTypes().map(ct -> generateStateClass(rootPackage,nameTransformer,ct))).map(PStream::plist);
			Result<PList<GeneratedJavaSource>> genSourceTableRecords =
				UPStreams.fromSequence(data.getTables().map(this::generateStateClass)).map(PStream::plist);
			Result<PList<GeneratedJavaSource>> genSourceDomains =
				UPStreams.fromSequence(data.getDomainObjects().map(d -> generateDomainSource(rootPackage,nameTransformer,d))).map(PStream::plist);
			Result<PList<GeneratedJavaSource>> genSourceTables =
				UPStreams.fromSequence(data.getTables().map(t -> generateTableClassSource(t))).map(PStream::plist);
			Result<PList<GeneratedJavaSource>> genSourceTableExpr =
				UPStreams.fromSequence(data.getTables().map(t -> generateTableExprSource(t))).map(PStream::plist);

			Result<GeneratedJavaSource> dbSource = generateDbSource(options.getDbJavaName(),rootPackage,data.getTables(),options.getFullDbSupport());

			Result<PList<GeneratedJavaSource>> result =
				genSourceEnums.flatMap(res -> genSourceCustomTypes.map(res::plusAll));
			result = result.flatMap(res -> genSourceTableRecords.map(res::plusAll));
			result = result.flatMap(res -> genSourceTableExpr.map(res::plusAll));
			result = result.flatMap(res -> genSourceTables.map(res::plusAll));
			result = result.flatMap(res -> genSourceDomains.map(res::plusAll));
			result = result.flatMap(res -> dbSource.map(res::plus));

			return result;
		});
	}

	protected Class<? extends DbGeneric>	getDbClass(boolean isFullDb){
		return DbGeneric.class;
	}
	protected Class<? extends DbContext> getContextClass(boolean isFullDb){
		return GenericDbContext.class;
	}

	protected Result<GeneratedJavaSource> generateDbSource(String dbName, String rootPackage, PList<DbJavaTable> tables, boolean isFullDb){
		return Result.function().code(l -> {
			JClass cls = new JClass(dbName).extendsDef(getDbClass(isFullDb).getSimpleName());
			cls = cls.addImport(getDbClass(isFullDb));
			cls = cls.addImport(getContextClass(isFullDb));

			for(DbJavaTable table : tables){
				JField field = new JField(UString.firstLowerCase(table.getJavaClassName()),"T" + table.getJavaClassName() + "Table")
					.asFinal()
					.withAccessLevel(AccessLevel.Public)
					.addImport(new JImport(table.getPackName() + ".T" + table.getJavaClassName() + "Table"));
				cls = cls.addField(field);


			}
			JMethod constructor = new JMethod(dbName)
				.withAccessLevel(AccessLevel.Public)
				.addArg(new JArgument(getContextClass(isFullDb).getSimpleName(), "context"))
				.addImport(JImport.forClass(DbContext.class));
			constructor = constructor.withCode(pw -> {
				pw.println("super(context);");
				//this.company = new TCompany(context.forTable("schemaName","tableName"));
				for(DbJavaTable table : tables){

					pw.println("this." + UString.firstLowerCase(table.getJavaClassName()) + " = new T" + table.getJavaClassName() + "Table"
						+ "(context.forTable(\"" + table.getTable().getSchema().getName().orElse("null") + "\", \"" + table.getTable().getName()	+ "\"));"
					);
				}
			});
			cls = cls.addMethod(constructor);

			JMethod emptyConstructor = new JMethod(dbName)
				.withAccessLevel(AccessLevel.Public);
			emptyConstructor = emptyConstructor.withCode(pw -> {
				pw.println("this(new " + getContextClass(isFullDb).getSimpleName()+ "());");
			});
			cls = cls.addMethod(emptyConstructor);



			JJavaFile file = new JJavaFile(rootPackage)
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}

	protected Result<GeneratedJavaSource> generateTableExprSource(DbJavaTable table){
		return Result.function(table).code(l -> {
			String clsName = "T" + table.getJavaClassName();
			JClass cls = new JClass(clsName);
			cls = cls.addImport(DTableExprImpl.class);
			cls = cls.extendsDef(DTableExprImpl.class.getSimpleName() + "<" + table.getJavaClassName() +">");
			cls = cls.addImport(new JImport(table.getPackName() + "." + table.getJavaClassName()));
			cls = cls.addImport(DbTableContext.class);

			for(DbJavaField field : table.getJavaFields()){
				JField jfield = field.createTableColumnField().withAccessLevel(AccessLevel.Public);
				cls = cls.addField(jfield);
			}
			cls = cls.addImport(PList.class);
			JMethod constructor = new JMethod(clsName)
				.withAccessLevel(AccessLevel.Public);
			//.addArg(new JArgument(DbTableContext.class.getSimpleName(),"context"))
			//.addImport(JImport.forClass(DbTableContext.class));
			constructor = constructor.withCode(pw -> {
				pw.println("super(");
				pw.indent(ps -> {
					ps.println("PList.val(" + table.getJavaFields().map(jf-> jf.getJavaName()).toString(", ") + "),");
					ps.println("_scon -> _rr -> {");
					ps.indent(pt -> {
						for(DbJavaField field : table.getJavaFields()){
							JField jf = field.createJField(false);

							pt.println(jf.getDefinition() + "\t_" + jf.getName() + " = DImpl._get(" + jf.getName() + ")._read(_scon,_rr);");
						}
						String cond = table.getJavaFields().map(jf -> "_" + jf.getJavaName() + "== null").toString(" && ");
						pt.println("if(" + cond + ") { return null; }");
						pt.println("return new " + table.getJavaClassName() + "(" + table.getJavaFields().map(f -> "_" + f.getJavaName()).toString(", ") + ");");
					});
					ps.println("}");
				});



				pw.println(");");
				for(DbJavaField field : table.getJavaFields()){
					JField jfield = field.createTableColumnField().withAccessLevel(AccessLevel.Public);
					pw.println("this." + jfield.getName() + "\t=\t" + jfield.getName() +";");
				}
			});
			for(DbJavaField field : table.getJavaFields()){
				JField jfield = field.createTableColumnField().withAccessLevel(AccessLevel.Public);
				constructor = constructor.addArg(jfield.asArgument());
			}
			for(DbJavaField field : table.getJavaFields()){
				JField jf = field.createJField(false);
				for(JImport ji : jf.getAllImports()){
					cls = cls.addImport(ji);
				}
			}
			cls = cls.addMethod(constructor);

			JMethod doWithAlias = new JMethod("_doWithAlias")
				.withResultType(clsName)
				.withAccessLevel(AccessLevel.Protected)
				.addAnnotation("@Override")
				.addImport(JImport.forClass(Override.class))
				.addImport(JImport.forClass(DImpl.class))
				.addArg("String","alias",false)
				.withCode(pw -> {
					pw.println("return new " + clsName +"(");
					pw.indent(pt -> {
						String args = table.getJavaFields().map(field -> {
							JField jf = field.createTableColumnField();
							return "(" + jf.getDefinition() + ")" +
								"DImpl._get(" + jf.getName() + ")._withAlias(alias)";
						}).toString(", " + System.lineSeparator());
						pt.println(args);
					});
					pw.println(");");

				});
			cls = cls.addMethod(doWithAlias);

			JMethod methodCast = new JMethod("cast")
				.withAccessLevel(AccessLevel.Public)
				.asStatic()
				.withResultType(clsName)
				.addArg(DExpr.class.getSimpleName() + "<" + table.getJavaClassName() + ">","expr",false)
				.withCode(pw -> {
					pw.println("return (" + clsName + ")expr;");
				}).addImport(JImport.forClass(DExpr.class));
			cls = cls.addMethod(methodCast);

			JJavaFile file = new JJavaFile(table.getPackName())
				.addClass(cls);

			return Result.success(file.toJavaSource());
		});
	}

	protected Result<GeneratedJavaSource> generateTableClassSource(DbJavaTable table){
		return Result.function(table).code(l -> {
			String clsName = "T" + table.getJavaClassName() + "Table";
			JClass cls = new JClass(clsName);
			cls = cls.addImport(DExprTable.class);
			cls = cls.addImport(DbTableContext.class);
			cls = cls.extendsDef("T" + table.getJavaClassName() + " implements DExprTable<" + table.getJavaClassName() +">");
			cls = cls.addImport(new JImport(table.getPackName() + "." + table.getJavaClassName()));

			//cls = cls.addField(new JField("_tableContext",DbTableContext.class));

			JMethod constructor = new JMethod(clsName)
				.withAccessLevel(AccessLevel.Public)
				.addArg(new JArgument(DbTableContext.class.getSimpleName(),"tableContext"))
				.addImport(JImport.forClass(DbTableContext.class))
				.addImport(JImport.forClass(PList.class));
			constructor = constructor.withCode(pw -> {
				pw.println("super(" +
					table.getJavaFields().map(field -> field.createTableColumnFieldInitializer("tableContext")).toString(",")+");");
				pw.println("this._tableContext = tableContext;");
				pw.println("this._insertFieldNames = PList.val(" + table.getJavaFields().map(jf -> "\"" + jf.getDbMetaColumn().getName() + "\"").toString(", ") + ");");
				pw.println("this._autoGenKeyFieldNames = PList.val(" +
					table.getTable().getPrimKey().filter(mc -> mc.getType().getIsAutoIncrement())
						 .map(fc -> "\"" + fc.getName() + "\"").toString(", ")
					+");");
			});

			for(DbJavaField field : table.getJavaFields()){
				JField jf = field.createJField(false);
				for(JImport imp : jf.getAllImports()){
					cls = cls.addImport(imp);
				}
			}
			//cls = cls.addImport(DImpl.class);
			//cls = cls.addImport(PList.class);
			//cls = cls.addImport(Tuple2.class);
			cls = cls.addMethod(constructor);

			/*JMethod selAlias = new JMethod("asTableExpr")
					.withAccessLevel(AccessLevel.Public)
					.withResultType(clsName)
					.addArg("String","selectionAliasName",false)
					.withCode(pw -> {
						pw.println("return new " + clsName + "(_tableContext.withAlias(selectionAliasName));");
					});
			cls = cls.addMethod(selAlias);*/
			JMethod tableAlias = new JMethod("alias")
				.withAccessLevel(AccessLevel.Public)
				.withResultType(clsName)
				.addArg("String","tableAlias",false)
				.withCode(pw -> {
					pw.println("return new " + clsName + "(_tableContext.withTableAlias(tableAlias));");
				});
			cls = cls.addMethod(tableAlias);

			JMethod query = new JMethod("query")
				.withAccessLevel(AccessLevel.Public)
				.addImport(JImport.forClass(Override.class))
				.addAnnotation("@" + Override.class.getSimpleName())
				.withResultType(Query.class.getSimpleName())
				.addImport(JImport.forClass(Query.class))
				.withCode(pw -> {
					pw.println("return _tableContext.createQuery(this);");
				});
			cls = cls.addMethod(query);

			JMethod all = new JMethod("all")
				.withAccessLevel(AccessLevel.Public)
				.withResultType("T" + table.getJavaClassName())
				.withCode(pw -> pw.println("return this;"))
				.addAnnotation("@" + Override.class.getSimpleName());
			cls = cls.addMethod(all);

			JMethod val = new JMethod("val","T" + table.getJavaClassName())
				.withAccessLevel(AccessLevel.Public)
				.addImport(JImport.forClass(DbContext.class))
				.addArg(new JArgument(table.getJavaClassName(),"v"))
				.withCode(pw -> {
					pw.println(DbContext.class.getSimpleName() + " db = _tableContext.getDbContext();");
					pw.println("return new T" + table.getJavaClassName() + "(");
					pw.indent(pt -> {
						pt.println(table.getJavaFields().map(jf ->
							"db.val(v.get" + UString.firstUpperCase(jf.getJavaName()) + "()" +
								(jf.isNullable() ? ".orElse(null)" : "")
								+ ")").toString(", "));
					});
					pw.println(");");
				});

			cls = cls.addMethod(val);

			/*JMethod insert = new JMethod("insert","DbWork<"+ table.getJavaClassName() + ">")
					.addImport(JImport.forClass(DbWork.class))
					.addImport(JImport.forClass(Insert.class))
					.addImport(JImport.forClass(Result.class))
					.addArg(new JArgument(table.getJavaClassName(),"record"))
					.withAccessLevel(AccessLevel.Public)
					.withCode(pw -> {
						pw.println("DbWork<Integer> count = new Insert<>(this._tableContext.getDbContext(), this, val(record));");
						pw.println("return count.flatMap(c -> c==0 ? Result.empty() : Result.success(record));");
					})
			;
			*/
			JMethod insert = new JMethod("insert","DbWork<"+ table.getJavaClassName() + ">")
				.addImport(JImport.forClass(DbWork.class))
				.addImport(JImport.forClass(Insert.class))
				.addImport(JImport.forClass(InsertResult.class))
				.addImport(JImport.forClass(Result.class))
				.addArg(new JArgument(table.getJavaClassName(),"record"))
				.withAccessLevel(AccessLevel.Public)
				.withCode(pw -> {
					pw.println("return new Insert(_tableContext.getDbContext(),this)");
					pw.indent(pg -> {
						pg.println(".row(val(record))");
						PList<String> autoGenNames = table.getTable()
														  .getPrimKey()
														  .filter(c -> c.getType().getIsAutoIncrement())
														  .map(metaCol -> metaCol.getName());
						if(autoGenNames.isEmpty() == false){
							PList<DbJavaField> jfl = table.getJavaFields().filter(jf -> autoGenNames.contains(jf.getDbMetaColumn().getName()));

							pg.println(".withAutoGenerated(" +
								jfl.map(jf->jf.getJavaName()).toString(", ")
								+ ")"
							);
							pg.println(".map(insertResult -> {");
							pg.println("\tPList keys = insertResult.getAutoGenKeys().head();");
							pg.println("\treturn record");
							int index = 0;
							for(DbJavaField jf : jfl){
								pg.println("\t\t.with" + UString.firstUpperCase(jf.getJavaName())
									+ "((" + jf.createJField(true).getDefinition() + ")" + "keys.get(" + index++ + "))"
								);
							}
							pg.println("\t;");
							pg.println("});");

						} else {
							pg.println(".map(ir -> record);");
						}

					});
				})
				;


			cls = cls.addMethod(insert);

			JJavaFile file = new JJavaFile(table.getPackName())
				.addClass(cls);

			return Result.success(file.toJavaSource());
		});
	}

	protected Result<GeneratedJavaSource> generateDomainSource(String rootPackage, DbNameTransformer nameTransformer, DbMetaUDT udt){
		return Result.function(udt).code(l -> {
			JClass cls   = new JClass(nameTransformer.toJavaName(udt.getName()));
			JField field;
			String fname = "value";
			switch(udt.getBaseType()){
				case Types.BLOB:
				case Types.BINARY:
				case Types.LONGVARBINARY:
				case Types.VARBINARY:
					field = new JField(fname,PByteList.class);
					break;
				case Types.BOOLEAN:
					field = new JField(fname,Boolean.class);
					break;
				case Types.BIGINT:
					field = new JField(fname,Long.class);
					break;
				case Types.BIT:
					field = new JField(fname,PBitList.class);
					break;
				case Types.CHAR:
				case Types.CLOB:
				case Types.LONGNVARCHAR:
				case Types.NCHAR:
				case Types.NCLOB:
				case Types.NVARCHAR:
				case Types.VARCHAR:
				case Types.LONGVARCHAR:
					field = new JField(fname,String.class);
					break;
				case Types.DATE:
					field = new JField(fname,LocalDate.class);
					break;
				case Types.NUMERIC:
				case Types.DECIMAL:
					field = new JField(fname,BigDecimal.class);
					break;
				case Types.DOUBLE:
					field = new JField(fname,Double.class);
					break;
				case Types.REAL:
				case Types.FLOAT:
					field = new JField(fname,Float.class);
					break;
				case Types.INTEGER:
					field = new JField(fname,Integer.class);
					break;
				case Types.SMALLINT:
					field = new JField(fname, Byte.class);
					break;
				case Types.SQLXML:
					field = createXmlJField(fname);
					break;
				case Types.TIME:
					field = new JField(fname,LocalTime.class);
					break;
				case Types.TIMESTAMP:
					field = new JField(fname,LocalDateTime.class);
					break;
				case Types.TINYINT:
					field = new JField(fname,Byte.class);
					break;
				case Types.OTHER:
					field = new JField(fname,Object.class);
					break;
				case Types.JAVA_OBJECT:
				case Types.DISTINCT:
				case Types.ARRAY:
				case Types.STRUCT:
				case Types.DATALINK:
				case Types.NULL:
				case Types.REF:
				case Types.REF_CURSOR:
				case Types.ROWID:
				case Types.TIME_WITH_TIMEZONE:
				case Types.TIMESTAMP_WITH_TIMEZONE:
				default:
					return Result.failure("Can't convert domain: " + udt);
			}
			field = field.asNullable();
			cls = cls.addField(field);
			cls = cls.addMainConstructor(AccessLevel.Public);
			cls = cls.addMethod(field.createGetter());
			JJavaFile file = new JJavaFile(toJavaPackage(rootPackage,nameTransformer,udt.getSchema()))
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}

	protected JField createXmlJField(String fname) {
		throw new UnsupportedOperationException("XML is not supported");
	}

	protected Result<GeneratedJavaSource> generateEnumSource(String rootPackage, DbNameTransformer nameTransformer, DbEnumType enumType){
		return Result.function(enumType).code(l-> {
			JClass cls = new JClass(enumType.getJavaClassName());
			JField dbValue = new JField("dbValue",String.class)
				.notFinal();
			cls = cls.addField(dbValue);
			cls = cls.addMainConstructor(AccessLevel.Private);
			cls = cls.addRequiredFieldsConstructor(AccessLevel.Private);
			cls = cls.addMethod(dbValue.createGetter());


			JEnum j = new JEnum(cls,PList.empty());



			for(Tuple2<String,String> dbAndJava : enumType.getValueAndJavaNameList()){
				j = j.addInstance(new JEnumInstance(dbAndJava._2,PList.val("\"" + UString.escapeToJavaString(dbAndJava._1) + "\"" )));
			}
			JJavaFile file = new JJavaFile(toJavaPackage(rootPackage,nameTransformer,enumType.getSchema()))
				.addEnum(j);
			return Result.success(file.toJavaSource());
		});
	}

	protected Result<GeneratedJavaSource> generateStateClass(String rootPackage, DbNameTransformer nameTransformer, DbCustomType customType){
		return Result.function(customType).code(l -> {
			JClass cls = new JClass(customType.getJavaClassName());
			for(DbJavaField field : customType.getFields()){
				cls = cls.addField(field.createJField(true));
			}
			cls = cls.makeCaseClass();



			JJavaFile file = new JJavaFile(toJavaPackage(rootPackage, nameTransformer, customType.getDefinition().getSchema()))
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}
	protected Result<GeneratedJavaSource> generateStateClass(DbJavaTable table){
		return Result.function(table).code(l -> {
			JClass cls = new JClass(table.getJavaClassName());

			for(DbJavaField field : table.getJavaFields()){
				JField jfield = field.createJField(true);
				jfield = jfield.addImport(JImport.forClass(DbColumnName.class));
				jfield = jfield.addAnnotation("@" + DbColumnName.class.getSimpleName() + "(\"" + field.getDbMetaColumn().getName()+ "\")");
				cls = cls.addField(jfield);
			}
			cls = cls.makeCaseClass();




			JJavaFile file = new JJavaFile(table.getPackName())
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}
	private String toJavaPackage(String rootPackage, DbNameTransformer nameTransformer, DbMetaSchema schema){
		return rootPackage
			+ "." + nameTransformer.toJavaName(schema.getCatalog())
			+ "." + nameTransformer.toJavaName(schema);

	}
}
