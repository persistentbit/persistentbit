package com.persistentbit.resources;

import com.persistentbit.collections.PByteList;
import com.persistentbit.io.IO;
import com.persistentbit.io.IORead;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 6/02/17
 */
public class URLResourceLoader implements ResourceLoader{


	private URLResourceLoader() {
	}

	public static final URLResourceLoader inst = new URLResourceLoader();

	@Override
	public Result<PByteList> apply(String name) {
		return Result.function(name).code(l -> {
			if(name == null) {
				return Result.failure("name is null");
			}
			return IO.asURL(name)
					 .flatMap(url -> Result.noExceptions(url::openStream))
					 .flatMap(IORead::readBytes);
		});
	}

	@Override
	public String toString() {
		return "URLResourceLoader[]";
	}
}
