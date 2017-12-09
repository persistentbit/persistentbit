package com.persistentbit.sql.dsl.codegen.posgresql;

import com.persistentbit.collections.*;
import com.persistentbit.javacodegen.*;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.annotations.DbColumnName;
import com.persistentbit.sql.dsl.codegen.DbJavaGen;
import com.persistentbit.sql.dsl.codegen.DbNameTransformer;
import com.persistentbit.sql.dsl.codegen.JavaGenTableSelection;
import com.persistentbit.sql.dsl.codegen.dbjavafields.*;
import com.persistentbit.sql.dsl.codegen.generic.DbCustomType;
import com.persistentbit.sql.dsl.codegen.generic.DbEnumType;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.sql.dsl.generic.inserts.InsertResult;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.dsl.postgres.rt.DbPostgres;
import com.persistentbit.sql.dsl.postgres.rt.PostgresDbContext;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.*;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.meta.data.*;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.string.UString;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.utils.exceptions.ToDo;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/07/17
 */
public class PostgresJavaGen implements DbJavaGen{
	private final JavaGenTableSelection selection;
	private final DbNameTransformer nameTransformer;
	private final Supplier<DbTransaction> transactionSupplier;
	private final String rootPackage;

	public PostgresJavaGen(JavaGenTableSelection selection, String rootPackage,DbNameTransformer nameTransformer) {
		this.selection = selection;
		this.rootPackage = rootPackage;
		this.nameTransformer = nameTransformer;
		this.transactionSupplier = selection.getTransactionSupplier();
	}
	public PostgresJavaGen(JavaGenTableSelection selection,String rootPackage){
		this(selection, rootPackage,new DbNameTransformer(name -> UString.firstUpperCase(UString.snake_toCamelCase(name))));

	}

	public JavaGenTableSelection	getSelection() {
		return selection;
	}

	public Result<PList<GeneratedJavaSource>>	generate(){
		return Result.function().code(log -> {
			PList<DbMetaSchema> allSchemas = DbMetaDataImporter.getAllSchemas().run(transactionSupplier.get()).orElseThrow();
			PList<DbEnumType>   enumTypes  = loadEnumTypes(allSchemas).run(transactionSupplier.get()).orElseThrow();
			log.info("ALL ENUMS: " + enumTypes);

			PList<DbMetaTable> tables = selection.getTablesAndViews();

			PList<DbCustomType> customTypes = selection
				.getSchemas()
				.map(schema -> DbMetaDataImporter.getTypes(schema).run(transactionSupplier.get()).orElseThrow()
												 .map(metaTable -> new DbCustomType(
													 metaTable
													 //metaTable.withColumns(metaTable.getColumns().map(col -> col.withType(col.getType().withIsNullable(false))))

													 ,nameTransformer.toJavaName(metaTable),PList.empty()))
				)
				.<DbCustomType>flatten()
				.plist();
			log.info("ALL CUSTOM TYPES: " + customTypes);
			PList<DbMetaUDT> udts = selection
				.getSchemas()
				.map(schema -> DbMetaDataImporter.getUDT(schema,null,new int[] {Types.DISTINCT}).run(transactionSupplier.get()).orElseThrow())
				.<DbMetaUDT>flatten()
				.plist();
			udts.forEach(udt -> {
				log.info("UDT: " + udt);
			});

			PList<DbCustomType> customTypesWithFields = addCustomTypesFields(enumTypes, customTypes, udts);




			PList<DbJavaTable> javaTables = tables.map(table -> generateJavaTable(table,customTypes,enumTypes,udts));



			// FIND USED STRUCTURES
			PSet<DbCustomType> usedTypes = PSet.empty();
			for(DbJavaTable table : javaTables){
				for(DbJavaField field : table.getJavaFields()){
					for(DbJavaFieldStruct structField : field.getStructures()){
						usedTypes = usedTypes.plus(
							customTypesWithFields.find(ct -> ct.getDefinition().equals(structField.getCustomTypeDbMeta())).get()
						);
					}
				}
			}
			for(DbCustomType ct : customTypesWithFields){
				for(DbJavaField field : ct.getFields()){
					for(DbJavaFieldStruct structField : field.getStructures()){
						usedTypes = usedTypes.plus(
							customTypesWithFields.find(uct -> uct.getDefinition().equals(structField.getCustomTypeDbMeta())).get()
						);
					}
				}
			}
			log.info("USED CUSTOM TYPES: " + usedTypes.map(DbCustomType::getJavaClassName).toString(", "));

			//FIND USED ENUMS
			PSet<DbEnumType> usedEnums = PSet.empty();
			for(DbJavaTable table : javaTables){
				for(DbJavaField field : table.getJavaFields()){
					for(DbJavaFieldEnum enumField : field.getUsedEnums()){
						usedEnums = usedEnums.plus(
							enumTypes.find(et -> enumField.getEnumType().equals(et)).get()
						);
					}
				}
			}
			for(DbCustomType ct : usedTypes){
				for(DbJavaField field : ct.getFields()){
					for(DbJavaFieldEnum enumField : field.getUsedEnums()){
						usedEnums = usedEnums.plus(
							enumTypes.find(et -> enumField.getEnumType().equals(et)).get()
						);
					}
				}
			}
			log.info("USED ENUMS: " + usedEnums.map(DbEnumType::getName).toString(", "));

			//FIND USED DOMAINS...
			PSet<DbMetaUDT> usedDomains = PSet.empty();
			for(DbJavaTable table : javaTables){
				for(DbJavaField field : table.getJavaFields()){
					for(DbJavaFieldDomain element : field.getDomains()){
						usedDomains = usedDomains.plus(
							element.getUdtMeta()
						);
					}
				}
			}
			for(DbCustomType customType : usedTypes){
				for(DbJavaField field : customType.getFields()){
					for(DbJavaFieldDomain element : field.getDomains()){
						usedDomains = usedDomains.plus(
							element.getUdtMeta()
						);
					}
				}
			}
			log.info("USED DOMAINS: " + usedDomains.map(DbMetaUDT::getName).toString(", "));

			//CREATE STATE CLASSES SOURCE CODE
			Result<PList<GeneratedJavaSource>> genSourceEnums = UPStreams.fromSequence(
				usedEnums.map(this::generateEnumSource)
			).map(PStream::plist);


			Result<PList<GeneratedJavaSource>> genSourceCustomTypes =
				UPStreams.fromSequence(usedTypes.map(this::generateStateClass)).map(PStream::plist);
			Result<PList<GeneratedJavaSource>> genSourceTableRecords =
				UPStreams.fromSequence(javaTables.map(this::generateStateClass)).map(PStream::plist);
			Result<PList<GeneratedJavaSource>> genSourceDomains =
				UPStreams.fromSequence(usedDomains.map(this::generateDomainSource)).map(PStream::plist);
			Result<PList<GeneratedJavaSource>> genSourceTables =
				UPStreams.fromSequence(javaTables.map(this::generateTableClassSource)).map(PStream::plist);
			Result<PList<GeneratedJavaSource>> genSourceTableExpr =
				UPStreams.fromSequence(javaTables.map(this::generateTableExprSource)).map(PStream::plist);

			Result<GeneratedJavaSource> dbSource = generateDbSource(javaTables);

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

	private PList<DbCustomType> addCustomTypesFields(PList<DbEnumType> enumTypes, PList<DbCustomType> customTypes,
													 PList<DbMetaUDT> udts
	) {
		return customTypes.map(dbCustomType -> {
			DbMetaTable  ctTable = dbCustomType.getDefinition();
			DbCustomType res     = dbCustomType;
			for(DbMetaColumn col : res.getDefinition().getColumns()){
				DbJavaField newField = getDbJavaField(ctTable,col.withType(col.getType().withIsNullable(false)),customTypes,enumTypes,udts);
				res = res.withFields(res.getFields().plus(newField));
			}
			return res;
		});
	}

	private Result<GeneratedJavaSource> generateDbSource(PList<DbJavaTable> tables){
		return Result.function().code(l -> {
			JClass cls = new JClass("Db").extendsDef(DbPostgres.class.getSimpleName());
			cls = cls.addImport(DbPostgres.class);
			cls = cls.addImport(PostgresDbContext.class);

			for(DbJavaTable table : tables){
				JField field = new JField(UString.firstLowerCase(table.getJavaClassName()),"T" + table.getJavaClassName() + "Table")
					.asFinal()
					.withAccessLevel(AccessLevel.Public)
					.addImport(new JImport(table.getPackName() + ".T" + table.getJavaClassName() + "Table"));
				cls = cls.addField(field);


			}
			JMethod constructor = new JMethod("Db")
				.withAccessLevel(AccessLevel.Public)
				.addArg(new JArgument(PostgresDbContext.class.getSimpleName(), "context"))
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

			JMethod emptyConstructor = new JMethod("Db")
				.withAccessLevel(AccessLevel.Public);
			emptyConstructor = emptyConstructor.withCode(pw -> {
				pw.println("this(new PostgresDbContext());");
			});
			cls = cls.addMethod(emptyConstructor);
/*
			for(DbJavaTable table : tables){
				JMethod val = new JMethod("val","T" + table.getJavaClassName())
						.withAccessLevel(AccessLevel.Public)
						.addImport(new JImport(table.getPackName() + ".T" + table.getJavaClassName()))
						.addImport(new JImport(table.getPackName() + "." + table.getJavaClassName()))
						.addArg(new JArgument(table.getJavaClassName(),"v"))
						.withCode(pw -> {
							pw.println("return new T" + table.getJavaClassName() + "(");
							pw.indent(pt -> {
								pt.println(table.getJavaFields().map(jf ->
									"val(v.get" + UString.firstUpperCase(jf.getJavaName()) + "()" +
										(jf.isNullable() ? ".orElse(null)" : "")
										+ ")").toString(", "));
							});
							pw.println(");");
						});

				cls = cls.addMethod(val);
			}
			*/


			JJavaFile file = new JJavaFile(rootPackage)
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}

	private Result<GeneratedJavaSource> generateTableExprSource(DbJavaTable table){
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

	private Result<GeneratedJavaSource> generateTableClassSource(DbJavaTable table){
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
						PList<String> autoGenNames = table.getTable().getPrimKey().map(metaCol -> metaCol.getName());
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

	private DbWork<PList<DbEnumType>> loadEnumTypes(PList<DbMetaSchema> allSchemas){
		return DbWork.function().code(trans -> con -> log -> {
			String sql = "SELECT pg_type.typname AS enumtype,\n" +
				"     pg_enum.enumlabel AS enumlabel,\n" +
				"     pg_enum.enumsortorder as sortorder,\n" +
				"     n.nspname as schema\n" +
				" FROM pg_type\n" +
				" JOIN pg_enum ON pg_enum.enumtypid = pg_type.oid\n" +
				" join pg_catalog.pg_namespace n on n.oid = pg_type.typnamespace\n" +
				"order by schema,enumtype,sortorder";
			PMap<String,DbEnumType> enumTypes = PMap.empty();
			try(PreparedStatement stat = con.prepareStatement(sql)){

				try(ResultSet rs = stat.executeQuery()){
					while(rs.next()){
						String name = rs.getString("enumtype");
						String value = rs.getString("enumlabel");
						float sortOrder = rs.getFloat("sortorder");
						String schemaName = rs.getString("schema");
						DbMetaSchema schema = allSchemas.find(s -> s.getName().orElse("").equals(schemaName)).orElse(null);
						if(schema == null){
							throw new RuntimeException("Can't find schema " + schemaName + " for enum type " + name);
						}
						String fullName = schemaName + "." + name;
						DbEnumType type = enumTypes.getOrDefault(fullName,null);
						if(type == null){
							//A new type
							String packName = toJavaPackage(schema);
							String clsName = nameTransformer.toJavaName(name);
							type = new DbEnumType(schema,name,packName,clsName);
						}
						type = type.addValue(value,UString.toJavaIdentifier(value));
						enumTypes = enumTypes.put(fullName,type);
					}

				}
			}
			return Result.success(enumTypes.values().plist());
		});
	}

	private Result<DbEnumType> findEnumType(String enumDbName){
		throw new ToDo();
	}


	private Optional<DbMetaTable> getCustomTypeMetaTable(DbMetaSchema usedInSchema,String dbTypeName){
		//FIRST, CHECK THE CURRENT SCHEMA
		for(DbMetaTable custom : DbMetaDataImporter.getTypes(usedInSchema).run(transactionSupplier.get()).orElseThrow()){
			if(custom.getName().equals(dbTypeName)){
				return Optional.of(custom);
			}
		}
		//NOW CHECK THE REST
		PList<DbMetaSchema> allschemas = selection.getSchemas().filter(schema -> usedInSchema.equals(schema) == false);
		for(DbMetaSchema schemaToCheck : allschemas){
			for(DbMetaTable custom : DbMetaDataImporter.getTypes(schemaToCheck).run(transactionSupplier.get()).orElseThrow()){
				if(custom.getName().equals(dbTypeName)){
					return Optional.of(custom);
				}
			}
		}
		return Optional.empty();
	}

	private DbJavaTable	generateJavaTable(DbMetaTable table,PList<DbCustomType> customTypes,PList<DbEnumType> enumTypes,PList<DbMetaUDT> domains){
		PList<DbJavaField> fields = table.getColumns().map(col -> getDbJavaField(table, col,customTypes,enumTypes,domains));
		String javaClassName = nameTransformer.toJavaName(table);
		String packName = rootPackage
			+ "." + nameTransformer.toJavaName(table.getSchema().getCatalog())
			+ "." + nameTransformer.toJavaName(table.getSchema());
		return new DbJavaTable(table,fields, javaClassName,packName);
	}

	private Result<GeneratedJavaSource> generateDomainSource(DbMetaUDT udt){
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
					field = new JField(fname, Xml.class);
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
			JJavaFile file = new JJavaFile(toJavaPackage(udt.getSchema()))
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}

	private Result<GeneratedJavaSource> generateEnumSource(DbEnumType enumType){
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
				j = j.addInstance(new JEnumInstance(dbAndJava._2,PList.val("\"" +UString.escapeToJavaString(dbAndJava._1) + "\"" )));
			}
			JJavaFile file = new JJavaFile(toJavaPackage(enumType.getSchema()))
				.addEnum(j);
			return Result.success(file.toJavaSource());
		});
	}

	private Result<GeneratedJavaSource> generateStateClass(DbCustomType customType){
		return Result.function(customType).code(l -> {
			JClass cls = new JClass(customType.getJavaClassName());
			for(DbJavaField field : customType.getFields()){
				cls = cls.addField(field.createJField(true));
			}
			cls = cls.makeCaseClass();




			JJavaFile file = new JJavaFile(toJavaPackage(customType.getDefinition().getSchema()))
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}

	private Result<GeneratedJavaSource> generateStateClass(DbJavaTable table){
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
	private String toJavaPackage(DbMetaSchema schema){
		return rootPackage
			+ "." + nameTransformer.toJavaName(schema.getCatalog())
			+ "." + nameTransformer.toJavaName(schema);

	}

	private DbJavaField	getDbJavaField(DbMetaTable table, DbMetaColumn column,PList<DbCustomType> customTypes, PList<DbEnumType> enumTypes, PList<DbMetaUDT> domains){
		DbMetaDataType mt       = column.getType();
		String         javaName = nameTransformer.toJavaName(table,column);
		switch(mt.getSqlType()){
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BINARY:
			case Types.BLOB:
				return new DbJavaFieldCustomObject(column,javaName,PByteList.class);
			case Types.BOOLEAN:
				return new DbJavaFieldPrimitiveType(column, javaName, boolean.class);

			case Types.BIGINT:
				return new DbJavaFieldPrimitiveType(column, javaName, long.class);
			case Types.BIT: {
				if(mt.getColumnSize() == 1){
					return new DbJavaFieldPrimitiveType(column, javaName, boolean.class);
				}
				return new DbJavaFieldCustomObject(column, javaName, PBitList.class);
			}
			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCLOB:
				DbEnumType enumType = enumTypes.find(e -> e.getName().equals(mt.getDbTypeName().get())).orElse(null);
				if(enumType != null){
					return new DbJavaFieldEnum(column, javaName, enumType,nameTransformer.toJavaName(enumType.getName()),toJavaPackage(enumType.getSchema()));
				}
				return new DbJavaFieldCustomObject(column, javaName, String.class);
			case Types.DATE:
				return new DbJavaFieldCustomObject(column, javaName, LocalDate.class);

			case Types.NUMERIC:
			case Types.DECIMAL:
				return new DbJavaFieldCustomObject(column, javaName, BigDecimal.class);
			case Types.DOUBLE:
				if(mt.getDbTypeName().orElse("").equals("money")){
					return new DbJavaFieldCustomObject(column, javaName, Money.class);
				}
				return new DbJavaFieldPrimitiveType(column, javaName, double.class);
			case Types.REAL:
			case Types.FLOAT:
				return new DbJavaFieldPrimitiveType(column, javaName, float.class);
			case Types.INTEGER:
				return new DbJavaFieldPrimitiveType(column, javaName, int.class);

			case Types.SMALLINT:
				return new DbJavaFieldPrimitiveType(column, javaName, short.class);
			case Types.TIMESTAMP:
				if(mt.getDbTypeName().get().equals("timestamp")) {
					return new DbJavaFieldCustomObject(column, javaName, LocalDateTime.class);
				} else {
					return new DbJavaFieldCustomObject(column, javaName, ZonedDateTime.class);
				}

			case Types.TIME:
				return new DbJavaFieldCustomObject(column, javaName, Time.class);

			case Types.TINYINT:
				return new DbJavaFieldPrimitiveType(column, javaName, byte.class);
			case Types.STRUCT:
				String pack = toJavaPackage(table.getSchema());
				String clsName = nameTransformer.toJavaName(mt.getDbTypeName().get());
				return new DbJavaFieldStruct(column,javaName,getCustomTypeMetaTable(table.getSchema(),mt.getDbTypeName().get()).get(),clsName,pack);
			case Types.JAVA_OBJECT:
				throw new RuntimeException("Not Implemented: JAVA_OBJECT for " + column );
			case Types.SQLXML:
				return new DbJavaFieldCustomObject(column, javaName, Xml.class);
			case Types.DISTINCT:
				DbMetaUDT udt = domains.find(u -> u.getName().equals(mt.getDbTypeName().orElse(null))).get();
				return new DbJavaFieldDomain(column,udt,javaName,nameTransformer.toJavaName(udt.getName()),this.toJavaPackage(udt.getSchema()));



			case Types.ARRAY:
				return createArrayField(javaName, table, column,customTypes,enumTypes,domains);

			case Types.OTHER:
				switch(column.getType().getDbTypeName().orElse(null)){
					case "interval":
						return new DbJavaFieldCustomObject(column, javaName, Interval.class);
					case "cidr":
						return new DbJavaFieldCustomObject(column, javaName, Cidr.class);
					case "inet":
						return new DbJavaFieldCustomObject(column, javaName, Inet.class);
					case "macaddr":
						return new DbJavaFieldCustomObject(column, javaName, Macaddr.class);
					case "uuid":
						return new DbJavaFieldCustomObject(column, javaName, UUID.class);
					case "varbit":
						return new DbJavaFieldCustomObject(column, javaName, PBitList.class);
					case "tsvector":
						return new DbJavaFieldCustomObject(column, javaName, Tsvector.class);
					case "tsquery":
						return new DbJavaFieldCustomObject(column, javaName, Tsquery.class);
					case "json":
						return new DbJavaFieldCustomObject(column, javaName, Json.class);
					default:
						throw new RuntimeException("Don't know other type " + column.getType().getDbTypeName() + " for " + column);
				}

			default:
				throw new RuntimeException("Unknown: " + column);
		}
	}

	private DbJavaFieldArray createArrayField(String javaName, DbMetaTable table, DbMetaColumn column, PList<DbCustomType> customTypes, PList<DbEnumType> enumTypes, PList<DbMetaUDT> udts){
		String dbTypeName = column.getType().getDbTypeName().orElse(null);
		DbJavaField element;
		switch(dbTypeName){
			case "_numeric":
				element= new DbJavaFieldCustomObject(column, javaName, BigDecimal.class);
				break;
			case "_int8":
				element = new DbJavaFieldPrimitiveType(column, javaName, long.class);
				break;
			case "_int4":
				element = new DbJavaFieldPrimitiveType(column, javaName, int.class);
				break;
			case "_int2":
				element = new DbJavaFieldPrimitiveType(column, javaName, short.class);
				break;
			case "_float4":
				element = new DbJavaFieldPrimitiveType(column, javaName, float.class);
				break;
			case "_float8":
				element = new DbJavaFieldPrimitiveType(column, javaName, double.class);
				break;
			case "_money":
				element = new DbJavaFieldCustomObject(column, javaName, Money.class);
				break;
			case "_varchar":
			case "_text":
			case "_bpchar":
				element = new DbJavaFieldCustomObject(column, javaName, String.class);
				break;
			case "_bytea":
				element = new DbJavaFieldCustomObject(column,javaName,PByteList.class);
				break;
			case "_timestamp":
				element = new DbJavaFieldCustomObject(column, javaName, LocalDateTime.class);
				break;
			case "_timestamptz":
				element = new DbJavaFieldCustomObject(column, javaName, ZonedDateTime.class);
				break;
			case "_date":
				element = new DbJavaFieldCustomObject(column, javaName, LocalDate.class);
				break;
			case "_time":
				element = new DbJavaFieldCustomObject(column, javaName, Time.class);
				break;
			case "_timetz":
				element = new DbJavaFieldCustomObject(column, javaName, Time.class);
				break;
			case "_interval":
				element = new DbJavaFieldCustomObject(column, javaName, Interval.class);
				break;
			case "_bool":
				element = new DbJavaFieldPrimitiveType(column, javaName, boolean.class);
				break;
			case "_cidr":
				element = new DbJavaFieldCustomObject(column, javaName, Cidr.class);
				break;
			case "_inet":
				element = new DbJavaFieldCustomObject(column, javaName, Inet.class);
				break;
			case "_macaddr":
				element = new DbJavaFieldCustomObject(column, javaName, Macaddr.class);
				break;
			case "_bit":
			case "_varbit":
				element = new DbJavaFieldCustomObject(column,javaName, PBitList.class);
				break;
			case "_tsvector":
				element = new DbJavaFieldCustomObject(column, javaName, Tsvector.class);
				break;

			case "_tsquery":
				element = new DbJavaFieldCustomObject(column, javaName, Tsquery.class);
				break;

			case "_uuid":
				element = new DbJavaFieldCustomObject(column, javaName, UUID.class);
				break;
			case "_xml":
				element = new DbJavaFieldCustomObject(column, javaName, Xml.class);
				break;

			case "_json":
				element = new DbJavaFieldCustomObject(column, javaName, Json.class);
				break;

			//case "_enum_test":
			//case "_full_name":
			//case "us_postal_code":

			default:
				DbEnumType enumType = enumTypes.find(et -> ("_" + et.getName()).equals(dbTypeName)).orElse(null);
				if(enumType != null){

					element = new DbJavaFieldEnum(column,javaName,enumType,nameTransformer.toJavaName(enumType.getName()),toJavaPackage(enumType.getSchema()));
					break;
				}
				DbCustomType customType = customTypes.find(ct -> ("_" + ct.getDefinition().getName()).equals(dbTypeName)).orElse(null);
				if(customType != null){
					String pack = toJavaPackage(customType.getDefinition().getSchema());
					String clsName = nameTransformer.toJavaName(customType.getDefinition().getName());
					element = new DbJavaFieldStruct(column,javaName,customType.getDefinition(),clsName,pack);
					break;
				}
				/*if(dbTypeName.startsWith("_")){
					DbMetaTable customTypeMeta = getCustomTypeMetaTable(table.getSchema(),dbTypeName).orElse(null);
					if(customTypeMeta != null){
						return new DbJavaFieldArray(column,javaName,new DbJavaFieldStruct(column,javaName,customTypeMeta));
					}
					//MUST BE AN ENUM
				}


				if(true) return null;*/

				throw new RuntimeException("Don't know array element type " + dbTypeName + " for " + column);
		}

		return new DbJavaFieldArray(column,javaName, element);
	}


}
