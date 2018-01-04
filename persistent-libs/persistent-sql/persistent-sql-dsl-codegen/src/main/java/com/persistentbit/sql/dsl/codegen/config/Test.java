package com.persistentbit.sql.dsl.codegen.config;

import com.persistentbit.collections.PList;
import com.persistentbit.logging.ModuleLogging;

import java.sql.Driver;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
public class Test{
	public  static void	main(String[] args){
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		//JJMapper mapper = new JJMapper();

		Connector conH2 = Connector.build(b -> b
			.setDriverClass(Driver.class.getName())
			.setUrl("jdbc:h2:mem:db1")
		);
		SchemaDef schema = SchemaDef.build(b -> b
			.setSchemaName("persistentbittest")
			.setJavaName("TestSchema")
		).addExcludeTables("SchemaHistory")
			.addTable(TableDef.build(b -> b
				.setTableName("person")
				.setJavaName("APerson")
			));



		CodeGen codeGenGeneric = CodeGen.build(b -> b
		);

		Instance instance = Instance.build(b -> b
			.setSchemas(PList.val(schema))
			.setCodeGen(codeGenGeneric)
			.setConnector(conH2)
		);
		DbCodeGenConfig config = new DbCodeGenConfig(PList.val(instance));

		//System.out.println(JJPrinter.toJson(config));
/*
		PList<DbJavaGenOptions> options = DbCodeGenConfigLoader.load(config).orElseThrow();
		for(DbJavaGenOptions opt : options){
			System.out.println(opt);
		}*/
	}
}
