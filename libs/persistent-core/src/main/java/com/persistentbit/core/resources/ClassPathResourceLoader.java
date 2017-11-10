package com.persistentbit.core.resources;

import com.persistentbit.core.collections.PByteList;
import com.persistentbit.core.io.IO;
import com.persistentbit.core.io.IORead;
import com.persistentbit.core.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 6/02/17
 */
public class ClassPathResourceLoader implements ResourceLoader{


	private ClassPathResourceLoader() {

	}

	public static final ClassPathResourceLoader inst = new ClassPathResourceLoader();
	public static final ClassPathResourceLoader getInst() {
		return inst;
	}

	@Override
	public Result<PByteList> apply(String name) {
		return Result.function(name).code(l -> {
			if(name == null) {
				return Result.failure("name is null");
			}

			return IO.resolveResourceName("/", cleanName(name))
					 .flatMap(rn -> Result.noExceptions(() -> ClassPathResourceLoader.class.getResourceAsStream(rn)))
					 .flatMap(IORead::readBytes);
		});
	}

	static String cleanName(String name) {
		//return IO.resolveResourceName("/", IO.asURL(name).map(URL::getPath).orElse(name)).orElse(name);
		return name;
	}

	@Override
	public String toString() {
		return "ClassPathResource[]";
	}
}
