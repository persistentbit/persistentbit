package com.persistentbit.parser;

import com.persistentbit.parser.source.StrPos;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/02/17
 */
public class ParseExceptionEOF extends ParseException{

	public ParseExceptionEOF(String message, StrPos pos) {
		super(message, pos);
	}

	public ParseExceptionEOF(String message, Throwable cause, StrPos pos) {
		super(message, cause, pos);
	}
}
