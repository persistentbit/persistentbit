package com.persistentbit.io;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.collections.UPStreams;
import com.persistentbit.result.Empty;
import com.persistentbit.result.Result;
import com.persistentbit.result.Success;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for handling resources from the classpath
 *
 * @author petermuys
 * @since 15/04/17
 */
public final class IOClassPath{

	/**
     * Read a class path resource as a String
     * @param classPathResource The resource name
     * @param charset The char encoding
     * @return The String result
     */
    public static Result<String> read(String classPathResource, Charset charset) {
        return Result.function(classPathResource, charset).code(l-> {
            if(classPathResource == null){
                return Result.failure("classPathResource is null");
            }
            if(charset == null){
                return Result.failure("charset is null");
            }
            return getReader(classPathResource, charset)
                .flatMap(IORead::readTextStream);
        });
    }

	/**
	 * Wrapper for Class#getResourceAsStream which returns a {@link Result} instead of
	 * returning null when not found.
	 * @param classPathResource The resource to find
	 * @return  {@link Success} or {@link Empty}
	 * @see Class#getResourceAsStream(String)
	 * @see #getReader(String, Charset)
	 */
	public static Result<InputStream> getStream(String classPathResource) {
        return Result.function(classPathResource).code(l -> {
            if(classPathResource == null) {
                return Result.failure("classPathResource is null");
            }
            InputStream in = IOClassPath.class.getResourceAsStream(classPathResource);
            if(in == null){
                return Result.empty("Classpath resource not found:" + classPathResource);
            }
            return Result.success(in);
        });
    }

    public static boolean exists(String classPathResource){
		return find(classPathResource).map(l -> l.isEmpty() == false).orElse(false);
	}

	/**
	 * Create a {@link Reader} for a Classpath Resource Stream
	 * @param classPathResource The Resource name
	 * @param charset The charset to use
	 * @return A Result with the Reader
	 */
	public static Result<Reader> getReader(String classPathResource, Charset charset) {
        return Result.function(classPathResource,charset).code(l -> {
            if(classPathResource == null) {
                return Result.failure("classPathResource is null");
            }
            if(charset == null) {
                return Result.failure("charset is null");
            }
            return getStream(classPathResource)
                .flatMap(stream -> IOStreams.inputStreamToReader(stream, charset));
        });
    }

	public static Result<Path> getPath(String classPathResource){
        return Result.function(classPathResource).code(l-> {
            if(classPathResource == null){
                return Result.failure("classPathResource is null");
            }
            URL url = IO.class.getResource(classPathResource);
            if(url == null){
                return Result.failure("Resource not found: " + classPathResource);
            }
            return IO.asPath(url);
        });
    }

    private static Result<PList<String>> findInDir(Path rootPath, String matchPath){
		matchPath = matchPath.replace('/', File.separatorChar);
		if(matchPath.startsWith(File.separator)){
			matchPath = matchPath.substring(1);
		}
		return IOFiles.getAllFiles(rootPath,matchPath)
					  .flatMap(l -> UPStreams.fromSequence(
				l.map(p ->
					Result.success(p.toString().substring(rootPath.toString().length()).replace(File.separatorChar,'/'))
				))
			).map(PStream::plist);
	}

	/**
	 * Find all resources in the classpath matching a pattern.<br>
	 * example patterns:<br>
	 *     "/com/persistentbit/core/io/*.class" ==&gt; matches all classes in the io package<br>
	 *     "/com/persistentbit/core/ ** /*.class" ==&gt; matches all classes in sub packets of the core package<br>
	 * @param matchPath The pattern to match
	 * @return Result with list of absolute resource names
	 */
    public static Result<PList<String>> find(String matchPath){
		if(matchPath.startsWith("/") == false){
			matchPath = "/" + matchPath;
		}
		String        finalMatchPath = matchPath;
		PList<String> classPaths     = PList.val(System.getProperty("java.class.path").split("\\Q" + File.pathSeparator + "\\E"));
		PList<Result<PList<String>>> items = classPaths.map(classPath -> {
			Path cpItem = Paths.get(classPath);
			if(Files.isDirectory(cpItem)){
				return findInDir(cpItem,finalMatchPath);
			} else {

				//Must be a jar
				return IOJar.findInJar(cpItem, IOFiles.fileNameMatcher(finalMatchPath))
					.map(list -> list.map(s ->  s));
			}
		});
		Result<PList<PList<String>>> res = UPStreams.fromSequence(items).map(PStream::plist);
		if(res.isError()){
			return res.map(v -> null);
		}
		PList<PList<String>> itemItems = res.orElseThrow();
		PList<String>        all       = itemItems.<String>flatten().plist();
		return Result.success(all);
	}

	public static void main(String[] args) {
		find("/**/*Exception.class").orElseThrow().forEach(System.out::println);
		//findInDir(Paths.get("/Users/petermuys/feniks/persistentbit/persistent-core/target/classes"),
		//	"**/Result.*").doWithLogs(ModuleCore.consoleLogPrint::print).orElseThrow().forEach(System.out::println);
	}
}
