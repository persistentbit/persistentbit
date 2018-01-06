package com.persistentbit.sql.dsl.codegen.config;

import com.persistentbit.string.UString;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
public enum DbNameToJavaNameType{
	asIs, toLowerCase, toUpperCase, snakeToMixedCase;


	public Function<String,String> transformer() {
		switch(this){
			case asIs:
				return s -> s;
			case toLowerCase:
				return s -> s.toLowerCase();
			case toUpperCase:
				return s -> s.toUpperCase();
			case snakeToMixedCase:
				return s -> UString.firstUpperCase(UString.snake_toCamelCase(s.toLowerCase()));
			default:
				throw new ToDo(this.name());
		}
	}

	public Function<String,String> fieldTransformer() {
		switch(this){
			case asIs:
				return s -> s;
			case toLowerCase:
				return s -> s.toLowerCase();
			case toUpperCase:
				return s -> s.toUpperCase();
			case snakeToMixedCase:
				return s -> UString.firstLowerCase(UString.snake_toCamelCase(s.toLowerCase()));
			default:
				throw new ToDo(this.name());
		}
	}
}
