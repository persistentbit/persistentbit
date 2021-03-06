package com.persistentbit.io;

import com.persistentbit.collections.PByteList;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Utility class for reading streams, files,...
 *
 * @author petermuys
 * @since 15/04/17
 */
public final class IORead{

	/**
	 * Read a Reader stream into a String.<br>
	 * The given Reader is automatically closed.<br>
	 *
	 * @param fin the input Reader
	 * @return The String content from the Reader
	 */
	public static Result<String> readTextStream(Reader fin) {
		return Result.function().codePresentResult(60, l -> {
			if (fin == null) {
				return Result.failure("Reader is null");
			}

			try {
				StringBuilder stringBuffer = new StringBuilder();
				int           c;
				char[]        buffer       = new char[1024];
				do {
					c = fin.read(buffer);
					if (c != -1) {
						stringBuffer.append(buffer, 0, c);
					}
				}
				while (c != -1);
				return Result.success(stringBuffer.toString());
			} catch (IOException e) {
				return Result.failure(e);
			} finally {
				IOStreams.close(fin).orElseThrow();
			}
		});

	}

	/**
     * Read all bytes from an InputStream and closes the inputStream when done
     *
     * @param in The inputStream
     *
     * @return a Result of {@link PByteList}
     */
    public static Result<PByteList> readBytes(InputStream in) {
        return Result.function(in).code(l -> {
            if(in == null) {
                return Result.failure("in is null");
            }
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            return IOCopy.copyAndClose(in, bout)
						 .map(ok -> PByteList.from(bout.toByteArray()));
        });
    }

	/**
     * Read an InputStream into a String.<br>
     * The given stream is automatically closed.<br>
     *
     * @param fin the inputStream
     * @param charset The character encoding
     * @return The String content from the InputStream
     */
    public static Result<String> readTextStream(InputStream fin, Charset charset) {
        return Result.function().codePresentResult(60, l -> {
            if (fin == null) {
                return Result.failure("Inputstream is null");
            }
            if (charset == null) {
                return Result.failure("Charset is null");
            }
            return IOStreams.inputStreamToReader(fin,charset)
							.flatMapNoSuccess((r,e) ->
                        IOStreams.close(fin).flatMap(ok -> r)
                    )
							.flatMap(IORead::readTextStream);
        });

    }
	public static Result<String> readTextFromURL(URL url, Charset charset){
    	return Result.function(url,charset).codePresentResult(60, l -> {

			URLConnection connection = url.openConnection();
			return readTextStream(connection.getInputStream(),charset);

		});

	}
	public static Result<String> readTextFromURL(String url, Charset charset){
		return Result.function(url,charset).codePresentResult(60, l ->
			readTextFromURL(new URL(url),charset)
		);
	}


	/**
     * Reads a text file
     *
     * @param f The file to read
     * @param charset The character encoding
     * @return String with content of the text file
     */
    public static Result<String> readTextFile(File f, Charset charset) {
        return Result.function(f).codePresentResult(l ->
                IOStreams.fileToReader(f,charset).flatMap(IORead::readTextStream)
        );
    }

	public static Result<List<String>> readLines(String text) {
		return Result.function(UString.presentEscaped(text, 40)).code(l -> {
			if (text == null) {
                return Result.empty();
            }
            return readLinesFromReader(new StringReader(text));
        });

    }

	public static Result<List<String>> readLinesFromReader(Reader r) {
        return Result.function().codePresentResult(120, l -> {
            if (r == null) {
                return Result.failure("Reader is null");
            }
            try (BufferedReader bin = new BufferedReader(r)) {
                List<String> lines = new ArrayList<>();
                while (true) {
                    String line = bin.readLine();
                    if (line == null) {
                        break;
                    }
                    lines.add(line);
                }
                return Result.success(lines);
            } catch (IOException e) {
                return Result.failure(new RuntimeException("Error reading lines from Reader stream", e));
            }
        });

    }

	public static Result<List<String>> readLinesFromFile(File file, Charset charset) {
        return Result.function(file,charset).codePresentResult(l -> IOStreams.fileToReader(file,charset).flatMap(IORead::readLinesFromReader));
    }

	public static Result<Properties> readClassPathProperties(String classPathResource, Charset charset) {
		return Result.function(classPathResource, charset).code(l -> {
			if(classPathResource == null) {
				return Result.failure("classPathResource is null");
			}
			if(charset == null) {
				return Result.failure("charset is null");
			}
			return IOClassPath.getReader(classPathResource, charset)
							  .flatMap(IORead::readProperties);

		});

	}

	/**
	 * Try reading a properties file from a Reader, closing the Reader when finished.<br>
	 *
	 * @param reader The Reader for the properties. Automatically closed
	 *
	 * @return The Result {@link Properties}
	 */
	public static Result<Properties> readProperties(Reader reader) {
		return Result.function(reader).code(l -> {
			if(reader == null) {
				return Result.failure("reader is null");
			}
			Properties props = new Properties();

			return IOStreams.closeAfter(reader, () -> Result.noExceptions(() -> {
				props.load(reader);
				return props;
			}));
		});
	}
}
