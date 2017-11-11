package com.persistentbit.javacodegen.mavenplugin.maven;


import com.github.javaparser.ParseProblemException;
import com.github.javaparser.Problem;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.logging.printing.LogPrintToString;
import com.persistentbit.javacodegen.mavenplugin.CaseClassCodeGenerator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;

/*
 * Generate packages from a ROD file
 *
 * @goal generate-packages
 * @phase generate-packages
 *
 * @description Generate packages from a ROD file
 */
@Mojo(
	name="generate-caseclasses",
	//defaultPhase = LifecyclePhase.GENERATE_SOURCES,
	requiresDependencyResolution = ResolutionScope.NONE
)

public class CaseClassMojo extends AbstractMojo{

	@Parameter(property = "project",required = true, readonly = true)
	MavenProject project;

	@Override
	public void execute()  throws MojoExecutionException {
		try{
			project.getCompileSourceRoots().forEach(srcRoot -> {

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
			});
		}catch(Exception le){
			LogPrintToString lp = new LogPrintToString();
			lp.print(le);
			getLog().error(lp.getLogString());
			throw new MojoExecutionException("Error while generating code:" + le.getMessage());
		}
		project.getCompileSourceRoots()
			.forEach(srcRoot -> getLog().info("Got src root " + srcRoot));
		/*
		try{
			DependencySupplier dependencySupplier = createDependencySupplier();

			//Compile the source
			SubstemaCompiler compiler = new SubstemaCompiler(dependencySupplier);
			//PList<RSubstema> substemas = PList.from(packages)
			//    .map(p -> compiler.compile(p).orElseThrow());

			//substemas.forEach(ss -> getLog().info(ss.toString()));

			//Create output directory

			project.addCompileSourceRoot(outputDirectory.getAbsolutePath());

			// GENERATE JAVA
			JavaGenOptions genOptions  =   new JavaGenOptions(true,true);
			PList.from(packages).forEach(packageName -> {

				getLog().info("Compiling package " + packageName);

				Result<RSubstema> resSubstema = compiler.compile(packageName);

				resSubstema.ifFailure(failure -> {
					getLog().error(failure.getException());
					throw new SubstemaException("Error while compiling " + packageName, failure.getException());
				});

				resSubstema.ifPresent(substema -> {
					SubstemaJavaGen.generateAndWriteToFiles(compiler, genOptions, substema.getValue(), outputDirectory)
						.forEach(rf -> {

							rf.ifFailure(f -> {
								getLog().error(f.getException());
								throw new SubstemaException("Error creating file " + f);
							});

							rf.ifPresent(success -> getLog()
								.info("Generated source in " + success.getValue().getAbsolutePath()));
						});
				});
			});
		}catch (Exception e){
			getLog().error("General error",e);
			throw new MojoExecutionException("Error while generating code:" + e.getMessage(),e);
		}
		*/

	}


}
