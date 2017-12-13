package com.persistentbit.string.withprops;



import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class Text{
	private final List<TextPart> parts;

	private Text(List<TextPart> parts) {
		this.parts = parts;
	}
	public Text() {
		this(Collections.emptyList());
	}
	public Text add(TextPart tp){
		List newList = new ArrayList(parts);
		newList.add(tp);
		return new Text(newList);
	}
	public Text add(String str){
		return add(new StringTextPart(str));
	}

	public String toString(Function<String, Optional<String>> propertyGetter){
		return parts
			.stream()
			.map(p -> p.toString(propertyGetter))
			.collect(Collectors.joining());
	}

	@Override
	public String toString() {
		return "Text[" + parts.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
	}

	static private final String regExIdentifier = "[a-zA-Z_][a-zA-Z0-9_]*";
	static private final Pattern propPattern = Pattern.compile(
		"(\\$" + regExIdentifier + ")|(\\$\\{" + regExIdentifier + "\\})|(\\\\\\$)");
	static public Text strToText(String str){
		Matcher m       = propPattern.matcher(str);
		Text    res     = new Text();

		int     prevEnd = 0;
		while(m.find()){
			int start = m.start();
			int end = m.end();

			if(start-prevEnd != 0){
				res = res.add(str.substring(prevEnd,start));
			}

			String parsedValue = str.substring(start,end);
			if(parsedValue.equals("\\$")){
				res = res.add("$");
			}else {
				String value = parsedValue.startsWith("${")
					? parsedValue.substring(2,parsedValue.length()-1)
					: parsedValue.substring(1,parsedValue.length());


				res = res.add(new PropertyTextPart(value,parsedValue));

			}
			prevEnd = end;
		}
		if(prevEnd != str.length()){
			res = res.add(str.substring(prevEnd));
		}
		return res;
	}

	public static void main(String[] args) {
		Map<String,String> props = Map.of(
			"name","peter",
			"country","Belgium",
			"sister","Katrien"
		);
		Function<String,Optional<String>> propGetter = name -> Optional.ofNullable(props.getOrDefault(name,null));

		String test = "Hello $name.abc\\$testje $ How is ${sister}? Still living in $country?";
		Text txt = Text.strToText(test);
		System.out.println(txt);
		System.out.println(txt.toString(propGetter));
	}
}
