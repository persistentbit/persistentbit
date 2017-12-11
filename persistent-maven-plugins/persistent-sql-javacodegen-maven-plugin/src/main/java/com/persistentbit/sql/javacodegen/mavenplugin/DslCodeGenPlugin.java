package com.persistentbit.sql.javacodegen.mavenplugin;

import com.persistentbit.collections.PList;
import com.persistentbit.io.IO;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.json.mapping.JJMapper;
import com.persistentbit.json.nodes.JJParser;
import com.persistentbit.json.nodes.JJPrinter;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.logging.printing.LogPrintToString;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.DbJavaGen;
import com.persistentbit.sql.dsl.codegen.DbJavaGenOptions;
import com.persistentbit.sql.dsl.codegen.config.DbCodeGenConfig;
import com.persistentbit.sql.dsl.codegen.config.DbCodeGenConfigLoader;
import com.persistentbit.sql.dsl.codegen.config.DbCodeGentConfigInitalEmpty;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.nio.file.Path;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Generate Sql db java classes from database.
 **/
@Mojo(
	name = "generate-db-code",
	defaultPhase = LifecyclePhase.GENERATE_SOURCES,
	requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME
)
public class DslCodeGenPlugin extends AbstractDslCodeGenPlugin{

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

			JJMapper mapper = new JJMapper();

			File f = new File(configfile).getAbsoluteFile();
			if(f.exists() == false || f.isFile() == false || f.canRead() == false){
				DbCodeGenConfig initial = DbCodeGentConfigInitalEmpty.createInitialEmpty();
				log.error("No config file found at " + f.getAbsolutePath());
				String jsonStr = JJPrinter.print(true,mapper.write(initial));
				return Result.failure("Create a file like: " + jsonStr);

			}
			getLog().info("Loading & parsing config file ");
			log.info("Loading & parsing config file ");

			Result<DbCodeGenConfig> configRes = log.add(JJParser.parse(f, IO.utf8))
											 .map(json -> mapper.read(json,DbCodeGenConfig.class));
			if(configRes.isError()){
				DbCodeGenConfig initial = DbCodeGentConfigInitalEmpty.createInitialEmpty();
				log.error("Error parsing and reading config file at  " + f.getAbsolutePath());
				String jsonStr = JJPrinter.print(true,mapper.write(initial));
				log.error("Example Json file: " + jsonStr);
				return configRes.map(t -> null);
			}
			DbCodeGenConfig config = configRes.orElseThrow();

			for(String driver : config.getInstances().map(i -> i.getConnector().getDriverClass()).pset())
			{
				log.info("Registering driver " + driver);
				getLog().info("Registering driver " + driver);
				try
				{
					Class<?> dc             = Class.forName( driver );
					Driver   driverInstance = (Driver) dc.getDeclaredConstructor().newInstance();
					DriverManager.registerDriver(driverInstance);

				}
				catch ( Exception e )
				{
					return Result.failure(e);
				}

			};

			log.info("Loading db meta data");
			getLog().info("Loading db meta data");
			PList<DbJavaGenOptions> options = DbCodeGenConfigLoader.load(config).orElseThrow();
			for(DbJavaGenOptions option : options){
				getLog().info("Create code for instance ");
				log.info("Creating code for option " + option);
				DbJavaGen  javaGen = DbJavaGen.createGenerator(option).orElseThrow();
				PList<GeneratedJavaSource> sourceFiles = javaGen.generate().orElseThrow();
				if(option.getOutputDirectory().exists() == false){
					option.getOutputDirectory().mkdirs();
				}
				for(GeneratedJavaSource source : sourceFiles){
					Path outFile =source.writeSource(option.getOutputDirectory().toPath()).orElseThrow();
					getLog().info("Source generated: " + outFile);
				}
			}
			/*
			DbJavaGen                  javaGen     = DbJavaGen.createGenerator(options).orElseThrow();
			PList<GeneratedJavaSource> sourceFiles = javaGen.generate().orElseThrow();
			if(outputDirectory.exists() == false){
				outputDirectory.mkdirs();
			}
			for(GeneratedJavaSource source : sourceFiles){
				source.writeSource(outputDirectory.toPath()).orElseThrow();
			}

			*/


			getLog().info("Done importing");
			return OK.result;
		});



			/*getLog().info("Creating connection to " + url);

			Supplier<Result<Connection>> connector = DbConnector.fromUrl(driver, getUrl(),getUsername(),getPassword())
																.orElseThrow();
			try(Connection c = connector.get().orElseThrow()){
				getLog().info("Connection ok to " + url);
				;
			}
			Supplier<DbTransaction> transSup = ()-> DbTransaction.create(connector);
			JavaGenTableSelection sel = new JavaGenTableSelection(transSup).addCatalogs(cat -> true)
																		   .flatMap(s -> s.addSchemas(schema -> true)) //schema.getName().orElse("").equals("glasschema")))
																		   .flatMap(s -> s.addTablesAndViews(t -> true))
																		   .orElseThrow();

			DbJavaGenOptions options = DbJavaGenOptions.build(b -> b
				.setRootPackage("com.persistentbit.db.generated")
				.setSelection(sel)
				.setFullDbSupport(createGenericDbCode == false)
			);
			DbJavaGen                  javaGen     = DbJavaGen.createGenerator(connector,options).orElseThrow();
			PList<GeneratedJavaSource> sourceFiles = javaGen.generate().orElseThrow();
			if(outputDirectory.exists() == false){
				outputDirectory.mkdirs();
			}
			for(GeneratedJavaSource source : sourceFiles){
				source.writeSource(outputDirectory.toPath()).orElseThrow();
			}


		} catch(Exception e) {
			LogPrintToString lp = new LogPrintToString(ModuleLogging.createLogFormatter());
			lp.print(e);
			getLog().error(lp.getLogString());
			throw new MojoFailureException("Error while generating db code", e);
		}
	*/

	}

//
//	@Parameter(defaultValue = "target/generated-sources", required = true)
//	File outputDirectory;
//
//
//
//
//	//////////////////////////// User Info ///////////////////////////////////
//
//
//
//	/**
//	 * Database username. If not given, it will be looked up through <code>settings.xml</code>'s server with
//	 * <code>${settingsKey}</code> as key.
//	 *
//	 * @since 1.0
//	 */
//	@Parameter( property = "username" )
//	private String username;
//
//	/**
//	 * Database password. If not given, it will be looked up through <code>settings.xml</code>'s server with
//	 * <code>${settingsKey}</code> as key.
//	 *
//	 * @since 1.0
//	 */
//	@Parameter( property = "password" )
//	private String password;
//
//
//
//	/**
//	 * Additional key=value pairs separated by comma to be passed into JDBC driver.
//	 *
//	 * @since 1.0
//	 */
//	@Parameter( defaultValue = "", property = "driverProperties" )
//	private String driverProperties;
//
//	/**
//	 * @since 1.0
//	 */
//	@Parameter( defaultValue = "${settings}", readonly = true, required = true )
//	private Settings settings;
//
//	/**
//	 * Server's <code>id</code> in <code>settings.xml</code> to look up username and password. Defaults to
//	 * <code>${url}</code> if not given.
//	 *
//	 * @since 1.0
//	 */
//	@Parameter( property = "settingsKey" )
//	private String settingsKey;
//
//	////////////////////////////////// Database info /////////////////////////
//	/**
//	 * Database URL.
//	 *
//	 * @since 1.0-beta-1
//	 */
//	@Parameter( property = "url", required = true )
//	private String url;
//
//
//	@Parameter(property="driver", required = true)
//	private String driver;
//
//	@Parameter(property = "createGenericDbCode", defaultValue = "true",required = false)
//	private boolean createGenericDbCode;
//
//	/**
//	 * MNG-4384
//	 *
//	 * @since 1.5
//	 */
//	@Component( role = SecDispatcher.class, hint = "default" )
//	private SecDispatcher securityDispatcher;

/*
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public String getDriverProperties() {
		return driverProperties;
	}

	public Settings getSettings() {
		return settings;
	}

	public String getSettingsKey() {
		return settingsKey;
	}

	public SecDispatcher getSecurityDispatcher() {
		return securityDispatcher;
	}

	public DslCodeGenPlugin setUsername(String username) {
		this.username = username;
		return this;
	}

	public DslCodeGenPlugin setPassword(String password) {
		this.password = password;
		return this;
	}

	private void loadUserInfoFromSettings()
		throws MojoExecutionException
	{
		if ( this.settingsKey == null )
		{
			this.settingsKey = getUrl();
		}

		if ( ( getUsername() == null || getPassword() == null ) && ( settings != null ) )
		{
			Server server = this.settings.getServer( this.settingsKey );

			if ( server != null )
			{
				if ( getUsername() == null )
				{
					setUsername( server.getUsername() );
				}

				if ( getPassword() == null && server.getPassword() != null )
				{
					try
					{
						setPassword( securityDispatcher.decrypt( server.getPassword() ) );
					}
					catch ( SecDispatcherException e )
					{
						throw new MojoExecutionException( e.getMessage() );
					}
				}
			}
		}

		if ( getUsername() == null )
		{
			// allow empty username
			setUsername( "" );
		}

		if ( getPassword() == null )
		{
			// allow empty password
			setPassword( "" );
		}
	}


	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			getLog().info("Generating Database Java Code...");
			loadUserInfoFromSettings();


			try
			{
				Class<?> dc             = Class.forName( driver );
				Driver   driverInstance = (Driver) dc.getDeclaredConstructor().newInstance();
				DriverManager.registerDriver(driverInstance);
				getLog().info("Registered driver: " + dc.getName());
			}
			catch ( ClassNotFoundException e )
			{
				throw new MojoExecutionException( "Driver class not found: " + driver, e );
			}
			catch ( Exception e )
			{
				throw new MojoExecutionException( "Failure loading driver: " + driver, e );
			}
			getLog().info("Creating connection to " + url);

			Supplier<Result<Connection>> connector = DbConnector.fromUrl(driver, getUrl(),getUsername(),getPassword())
															   .orElseThrow();
			try(Connection c = connector.get().orElseThrow()){
				getLog().info("Connection ok to " + url);
				;
			}
			Supplier<DbTransaction> transSup = ()-> DbTransaction.create(connector);
			JavaGenTableSelection sel = new JavaGenTableSelection(transSup).addCatalogs(cat -> true)
					  .flatMap(s -> s.addSchemas(schema -> true)) //schema.getName().orElse("").equals("glasschema")))
					  .flatMap(s -> s.addTablesAndViews(t -> true))
					  .orElseThrow();

			DbJavaGenOptions options = DbJavaGenOptions.build(b -> b
				.setRootPackage("com.persistentbit.db.generated")
				.setSelection(sel)
				.setFullDbSupport(createGenericDbCode == false)
			);
			DbJavaGen                  javaGen     = DbJavaGen.createGenerator(connector,options).orElseThrow();
			PList<GeneratedJavaSource> sourceFiles = javaGen.generate().orElseThrow();
			if(outputDirectory.exists() == false){
				outputDirectory.mkdirs();
			}
			for(GeneratedJavaSource source : sourceFiles){
				source.writeSource(outputDirectory.toPath()).orElseThrow();
			}


		} catch(Exception e) {
			LogPrintToString lp = new LogPrintToString(ModuleLogging.createLogFormatter());
			lp.print(e);
			getLog().error(lp.getLogString());
			throw new MojoFailureException("Error while generating db code", e);
		}


	}
*/

}
