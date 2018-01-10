package com.persistentbit.sql.javacodegen.mavenplugin;

import com.persistentbit.collections.PList;
import com.persistentbit.io.IO;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.json.mapping.JJMapper;
import com.persistentbit.json.nodes.JJParser;
import com.persistentbit.json.nodes.JJPrinter;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.logging.printing.LogPrintToString;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.dsl.codegen.config.DbCodeGenConfig;
import com.persistentbit.sql.dsl.codegen.config.DbCodeGenConfigInitalEmpty;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.dsl.codegen.service.DbCodeGenService;
import com.persistentbit.sql.dsl.codegen.service.DbHandlingLevel;
import com.persistentbit.sql.transactions.DbTransaction;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Supplier;

/**
 * Generate Sql db java classes from database.
 **/
@Mojo(
	name = "generate-db-code",
	defaultPhase = LifecyclePhase.GENERATE_SOURCES,
	requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME
)
public class DslCodeGenPlugin extends AbstractMojo{

	@Parameter(property = "project", required = true, readonly = true)
	MavenProject project;

	@Parameter(name="configfile",defaultValue = "dbimportconfig.json")
	private String configfile;

	public String getConfigFile() {
		return configfile;
	}

	public DslCodeGenPlugin setConfigFile(String configFile) {
		this.configfile = configFile;
		return this;
	}

	public void execute() throws MojoExecutionException, MojoFailureException {
		Result<OK> ok = doExecute();
		if(ok.isError() == false){
			return;
		}
		LogPrintToString lp = new LogPrintToString(ModuleLogging.createLogFormatter());
		lp.print(ok.getLog());
		lp.print(ok.getEmptyOrFailureException().get());
		getLog().error(lp.getLogString());
		throw new MojoFailureException("Error while generating db code");
	}

	public Result<OK> doExecute(){
		return Result.function().code(log -> {
			log.info("Generating Database Java Code...");

			DbCodeGenService.getInstances().forEach(cgs -> {
				getLog().info("FOUND CodeGen Service: " + cgs.getDescription());
			});

			JJMapper mapper = new JJMapper();

			File f = new File(configfile).getAbsoluteFile();
			if(f.exists() == false || f.isFile() == false || f.canRead() == false){
				DbCodeGenConfig initial = DbCodeGenConfigInitalEmpty.createInitialEmpty();
				log.error("No config file found at " + f.getAbsolutePath());
				String jsonStr = JJPrinter.print(true,mapper.write(initial));
				return Result.failure("Create a file like: " + jsonStr);

			}
			File baseDir = f.getParentFile();
			getLog().info("Loading & parsing config file ");
			log.info("Loading & parsing config file ");

			Result<DbCodeGenConfig> configRes = log.add(JJParser.parse(f, IO.utf8))
											 .map(json -> mapper.read(json,DbCodeGenConfig.class));
			if(configRes.isError()){
				DbCodeGenConfig initial = DbCodeGenConfigInitalEmpty.createInitialEmpty();
				log.error("Error parsing and reading config file at  " + f.getAbsolutePath());
				String jsonStr = JJPrinter.print(true,mapper.write(initial));
				log.error("Example Json file: " + jsonStr);
				return configRes.map(t -> null);
			}
			DbCodeGenConfig config = configRes.orElseThrow();


			for(Instance instance : config.getInstances()) {

				try(HikariDataSource ds = new HikariDataSource()) {
					ds.setJdbcUrl(instance.getConnector().getUrl());
					ds.setUsername(instance.getConnector().getUserName().orElse(null));
					ds.setPassword(instance.getConnector().getPassword().orElse(null));
					DbConnector             connector = DbConnector.fromDataSource(ds);
					Supplier<DbTransaction> transSup  = () -> DbTransaction.create(connector);

					getLog().info("Create code for instance " + instance);
					log.info("Create code for instance " + instance);
					DbCodeGenService service = DbCodeGenService
						.getInstances()
						.find(s -> s.getHandlingLevel(transSup, instance) == DbHandlingLevel.full)
						.orElseGet(() ->
									   DbCodeGenService
										   .getInstances()
										   .find(s -> s
											   .getHandlingLevel(transSup, instance) == DbHandlingLevel.onlyGeneric)
										   .orElse(null)
						);
					if(service == null) {
						return Result.failure("Could not find service for " + instance);
					}
					log.info("Creating code using service " + service.getDescription());
					getLog().info("Creating code using service " + service.getDescription());

					PList<JJavaFile> files     = service.generateCode(baseDir, transSup, instance).orElseThrow();
					File             outputDir = new File(baseDir, instance.getCodeGen().getOutputDir());
					if(outputDir.exists() == false) {
						outputDir.mkdirs();
					}
					for(JJavaFile file : files) {
						GeneratedJavaSource source  = file.toJavaSource();
						Path                outFile = source.writeSource(outputDir.toPath()).orElseThrow();
						getLog().info("Source generated: " + outFile);

					}
				}


			}



			getLog().info("Done importing");
			return OK.result;
		});


	}


}
