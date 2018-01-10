package com.persistentbit.sql.updater;

import com.persistentbit.collections.PList;
import com.persistentbit.io.IO;
import com.persistentbit.io.IORead;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Load Sql Statements in text form.
 *
 * @author petermuys
 * @since 10/01/18
 */
public class SqlFile{

	/**
	 * Load Sql Statements from InputStream with charset UTF8
	 *
	 * @param in The InputStream
	 *
	 * @return List of Sql Statements
	 *
	 * @see #loadStatements(InputStream, Charset)
	 */
	public static Result<PList<String>> loadStatements(InputStream in) {
		return loadStatements(in, IO.utf8);
	}

	public static Result<PList<String>> loadStatements(File file, Charset charset) {
		return IORead.readTextFile(file, charset)
			.flatMap(SqlFile::loadStatements);
	}

	/**
	 * Load Sql Statements from an InputStream in the given charset.
	 *
	 * @param in      The InputStream
	 * @param charset The Charset to use
	 *
	 * @return A List of Sql Statements
	 *
	 * @see #loadStatements(InputStream)
	 * @see #loadStatements(String)
	 */
	public static Result<PList<String>> loadStatements(InputStream in, Charset charset) {
		return Result.function().code(log -> {
			if(in == null) {
				return Result.failure("Can't load Sql statements from a null InputStream");
			}
			return IORead.readTextStream(in, charset)
				.flatMap(SqlFile::loadStatements);

		});

	}

	public static Result<PList<String>> loadStatements(String text) {
		return Result.function(UString.present(text, 40)).code(log -> {
			PList<String> res              = PList.empty();
			String        delimiter        = ";";
			List<String>  currentStatement = new ArrayList<>();
			for(String line : UString.splitInLines(text)) {
				if(line.toLowerCase().startsWith("delimiter")) {
					delimiter = line.substring("delimiter".length()).trim();
					continue;
				}
				String trimLine = line.trim();
				if(trimLine.endsWith(delimiter)) {
					currentStatement.add(trimLine.substring(0, trimLine.length() - delimiter.length()));
					res = res.plus(UString.joinLines(currentStatement));
					currentStatement.clear();
				}
				else {
					currentStatement.add(line);
				}
			}
			if(currentStatement.isEmpty() == false) {
				return Result.failure("Last statement is not ended by '" + delimiter + "'");
			}
			log.info("Loaded " + res.size() + " sql statements");
			return Result.success(res);
		});
	}
}
