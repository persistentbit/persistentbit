package com.persistentbit.sql.dsl.codegen.config;

import com.persistentbit.collections.PList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
public class DbCodeGentConfigInitalEmpty{
	static public DbCodeGenConfig createInitialEmpty(){
		Connector conH2 = Connector.build(b -> b
			.setDriverClass("org.h2.Driver")
			.setUrl("jdbc:h2:mem:db1")
			.setUserName("sa")
			.setPassword("sa")
		);
		SchemaDef schema = SchemaDef.build(b -> b
			.setSchemaName("myschema")
			.setJavaName("myschema")
		).addExcludeTables("SchemaHistory")
									.addTable(TableDef.build(b -> b
										.setTableName("person")
										.setJavaName("APerson")
									));



		CodeGen codeGenGeneric = CodeGen.build(b -> b
			.setRootPackage("com.mycompany.db.generated")
			.setGeneric(true)
		);

		Instance instance = Instance.build(b -> b
			.setSchemas(PList.val(schema))
			.setCodeGen(codeGenGeneric)
			.setConnector(conH2)
		);
		DbCodeGenConfig config = new DbCodeGenConfig(PList.val(instance));

		//System.out.println(JJPrinter.toJson(config));

		/*PList<DbJavaGenOptions> options = DbCodeGenConfigLoader.load(config).orElseThrow();
		for(DbJavaGenOptions opt : options){
			System.out.println(opt);
		}*/
		return config;
	}
}
