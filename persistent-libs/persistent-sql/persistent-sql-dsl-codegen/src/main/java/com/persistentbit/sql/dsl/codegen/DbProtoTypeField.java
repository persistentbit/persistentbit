package com.persistentbit.sql.dsl.codegen;


import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.NoBuilder;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 22/06/17
 */
@CaseClass
@NoBuilder
public class DbProtoTypeField{
	private final Predicate<String> fieldSelector;
	private final Function<String, String> nameConverter;

	public DbProtoTypeField(Predicate<String> fieldSelector,
							Function<String, String> nameConverter
	) {
		this.fieldSelector = fieldSelector;
		this.nameConverter = nameConverter;

	}

	static public DbProtoTypeField	nameStartingWith(String prefix){
		return new DbProtoTypeField(
			name -> name.startsWith(prefix),
			name -> name.substring(prefix.length())
		);
	}

	public static DbProtoTypeField nameEndingWith(String suffix){
		return new DbProtoTypeField(
			name -> name.endsWith(suffix),
			name -> name.substring(0,name.length()-suffix.length())
		);
	}


}
