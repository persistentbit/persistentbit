package com.persistentbit.printable;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/11/17
 */
public class IndentFilterWriter extends FilterWriter{
	private boolean prevNl;
	private final String indentString;
	private final String newLineString;
	public IndentFilterWriter(Writer out,String indentString, boolean indentFirstLine, String newLineString){
		super(out);
		prevNl = indentFirstLine;
		this.indentString = indentString;
		this.newLineString = newLineString;
	}


	public String apply(String s) {
		if(s.isEmpty()){
			return s;
		}
		if(prevNl){
			s = indentString + s;
		}
		prevNl = s.endsWith(newLineString);
		if(prevNl){
			s = s.substring(0,s.length()- newLineString.length());
			return s.replace(newLineString, newLineString + indentString) + newLineString;
		}
		return s.replace(newLineString,newLineString +  indentString);

	}


	@Override
	public void write(int c) throws IOException {
		out.append(apply(Character.toString((char)c)));
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		out.append(apply(new String(cbuf,off,len)));
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		out.append(apply(str.substring(off,off + len)));
	}
}
