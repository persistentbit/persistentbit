package com.persistentbit.sql.javacodegen.mavenplugin;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.dsl.codegen.DbJavaGen;
import com.persistentbit.sql.dsl.codegen.DbJavaGenOptions;
import com.persistentbit.sql.dsl.codegen.posgresql.JavaGenTableSelection;
import com.persistentbit.sql.transactions.DbTransaction;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;
import org.sonatype.plexus.components.sec.dispatcher.SecDispatcher;
import org.sonatype.plexus.components.sec.dispatcher.SecDispatcherException;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.function.Supplier;

/**
 * Generate Sql db java classes from database.
 **/
@Mojo(
	name = "generate-db-code",
	defaultPhase = LifecyclePhase.GENERATE_SOURCES,
	requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME
)
public class DslCodeGenPlugin extends AbstractDslCodeGenPlugin{


	@Parameter(defaultValue = "target/generated-sources", required = true)
	File outputDirectory;




	//////////////////////////// User Info ///////////////////////////////////



	/**
	 * Database username. If not given, it will be looked up through <code>settings.xml</code>'s server with
	 * <code>${settingsKey}</code> as key.
	 *
	 * @since 1.0
	 */
	@Parameter( property = "username" )
	private String username;

	/**
	 * Database password. If not given, it will be looked up through <code>settings.xml</code>'s server with
	 * <code>${settingsKey}</code> as key.
	 *
	 * @since 1.0
	 */
	@Parameter( property = "password" )
	private String password;



	/**
	 * Additional key=value pairs separated by comma to be passed into JDBC driver.
	 *
	 * @since 1.0
	 */
	@Parameter( defaultValue = "", property = "driverProperties" )
	private String driverProperties;

	/**
	 * @since 1.0
	 */
	@Parameter( defaultValue = "${settings}", readonly = true, required = true )
	private Settings settings;

	/**
	 * Server's <code>id</code> in <code>settings.xml</code> to look up username and password. Defaults to
	 * <code>${url}</code> if not given.
	 *
	 * @since 1.0
	 */
	@Parameter( property = "settingsKey" )
	private String settingsKey;

	////////////////////////////////// Database info /////////////////////////
	/**
	 * Database URL.
	 *
	 * @since 1.0-beta-1
	 */
	@Parameter( property = "url", required = true )
	private String url;


	@Parameter(property="driver", required = true)
	private String driver;

	/**
	 * MNG-4384
	 *
	 * @since 1.5
	 */
	@Component( role = SecDispatcher.class, hint = "default" )
	private SecDispatcher securityDispatcher;


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
			);
			DbJavaGen                  javaGen     = DbJavaGen.createGenerator(connector,options).orElseThrow();
			PList<GeneratedJavaSource> sourceFiles = javaGen.generate().orElseThrow();
			if(outputDirectory.exists() == false){
				outputDirectory.mkdirs();
			}
			for(GeneratedJavaSource source : sourceFiles){
				source.writeSource(outputDirectory.toPath()).orElseThrow();
			}
/*
			DependencySupplier dependencySupplier = createDependencySupplier();
			SubstemaCompiler   compiler           = new SubstemaCompiler(dependencySupplier);
			//PList<RSubstema> substemas = PList.from(packages).map(p -> compiler.compile(p));

			//substemas.forEach(ss -> getLog().info(ss.toString()));

			if(!outputDirectory.exists()) {
				if(outputDirectory.mkdirs() == false) {
					throw new MojoExecutionException("Can't create output folder " + outputDirectory.getAbsolutePath());
				}
			}
			project.addCompileSourceRoot(outputDirectory.getAbsolutePath());
			JavaGenOptions genOptions = new JavaGenOptions(true, true);


			PStream.from(packages).forEach(packageName -> {
				//SubstemaJavaGen.generateAndWriteToFiles(compiler,genOptions,ss,outputDirectory);
				PList<Result<GeneratedJava>> genCodeList = DbJavaGen.generate(genOptions, packageName, compiler);
				genCodeList.forEach(resultGen -> {

					Result<File> resultFile = resultGen.flatMap(rg -> rg.writeToFile(outputDirectory));
					resultFile.ifFailure(failure -> getLog().error(failure.getException()));
					resultFile.ifPresent(success -> getLog().info("Generated " + success.getValue().getAbsolutePath()));

				});

			});
*/

		} catch(Exception e) {
			getLog().error("General error", e);
			throw new MojoFailureException("Error while generating db code", e);
		}


	}


}
