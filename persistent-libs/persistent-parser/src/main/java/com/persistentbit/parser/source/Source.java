package com.persistentbit.parser.source;

import com.persistentbit.io.IO;
import com.persistentbit.io.IORead;
import com.persistentbit.parser.Parser;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * Represents the source and position in the source for a Parser.
 *
 * @author petermuys
 * @see Parser
 * @since 17/02/17
 */
public abstract class Source{

	public static final char EOF = 0;



	public final char current;
	public final StrPos position;


	protected Source(StrPos position, char current) {
		this.position = Objects.requireNonNull(position);
		this.current = current;
	}


	public static Source asSource(String source) {
		return asSource(StrPos.inst,source);
	}

	public static Source asSource(String name, String source) {
		return asSource(new StrPos(name), source);
	}
	public static Source asSource(StrPos pos, String source) {
		return new SourceFromString(source, 0, pos);
	}


	public static Result<Source> asSource(URL url, Charset charset){
		return IORead.readTextFromURL(url,charset)
					 .map(txt -> asSource(url.toString(),txt));
	}


	public static Result<Source> asSource(File f){
		return asSource(f, IO.utf8);
	}


	public static Result<Source> asSource(File f, Charset charSet){
		return IORead.readTextFile(f,charSet)
					 .map(txt -> asSource(f.getAbsolutePath(),txt));
	}

	public boolean isEOF() {
		return current == EOF;
	}

	public abstract Source next();

	public Source next(int count) {
		Source res = this;
		for(int t = 0; t < count; t++) {
			res = res.next();
		}
		return res;
	}

	public Source plus(Source right){
		if(isEOF()){
			return right;
		}
		return new SourcePlusSource(this,right);
	}

	public abstract String rest();

	@Override
	public String toString() {
		return "Source[" + position + ", current=" + UString
			.present(UString.escapeToJavaString(rest()), 20, "...") + "]";
	}
}
