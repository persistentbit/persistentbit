package com.persistentbit.sql.updater.parser;

import com.persistentbit.collections.PList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class Text{
	private final PList<TextPart>	parts;

	public Text(PList<TextPart> parts) {
		this.parts = parts;
	}
	public Text() {
		this(PList.empty());
	}
	public Text add(TextPart tp){
		return new Text(parts.plus(tp));
	}
	public Text add(String str){
		return add(new StringTextPart(str));
	}

	public String toString(UpdateContext context){
		return parts.map(p -> p.toString(context)).toString("");
	}
}
