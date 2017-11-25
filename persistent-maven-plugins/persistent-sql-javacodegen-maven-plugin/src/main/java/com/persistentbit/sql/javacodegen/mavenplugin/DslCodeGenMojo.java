package com.persistentbit.sql.javacodegen.mavenplugin;

import com.persistentbit.logging.printing.LogPrintToString;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;


@Mojo(
	name="generate-for-db",
	requiresDependencyResolution = ResolutionScope.NONE
)

public class DslCodeGenMojo extends AbstractMojo{

	@Parameter(property = "project",required = true, readonly = true)
	MavenProject project;

	@Parameter(required = true)
	String       dbDriverClass;
	@Parameter(required = true)
	String       dbUrl;
	@Parameter
	String       dbUserName;
	@Parameter
	String       dbPassword;
	@Parameter
	String       dbSchema;


	@Override
	public void execute()  throws MojoExecutionException {
		try{
			/*project.getCompileSourceRoots().forEach(srcRoot -> {

				File srcRootFile = new File(srcRoot);

				PList<CaseClassCodeGenerator.CaseClassGenResult> result =
					CaseClassCodeGenerator.makeCaseClassesRecursive(srcRootFile.toPath()).orElseThrow();

				result.filter(res -> res.getResult().isEmpty() == false).forEach(ccGenResult -> ccGenResult.getResult().ifFailure(fail -> {
					if(fail.getException() instanceof ParseProblemException){
						ParseProblemException parseProblem = (ParseProblemException)fail.getException();
						for(Problem problem : parseProblem.getProblems()){
							getLog().error("Error reading source " + ccGenResult.getFile() + ": " + problem.getVerboseMessage());
						}
					} else {
						getLog().error("Failed converting " + ccGenResult.getFile(), fail.getException());
					}
				}).ifPresent(success -> getLog().info("Regenerated " + ccGenResult.getFile())));
			});*/
		}catch(Exception le){
			LogPrintToString lp = new LogPrintToString();
			lp.print(le);
			getLog().error(lp.getLogString());
			throw new MojoExecutionException("Error while generating code:" + le.getMessage());
		}
		project.getCompileSourceRoots()
			.forEach(srcRoot -> getLog().info("Got src root " + srcRoot));


	}


}
