package com.persistentbit.sql.dsl.postgres.rt;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.genericdb.DbGeneric;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public class DbPostgres extends DbGeneric{

	public static EInt bit_length(EString string) {
		return intFun("bit_length", string);
	}

	public static EInt char_length(EString string) {
		return intFun("char_length", string);
	}

	public static EInt octet_length(EString string) {
		return intFun("octet_length", string);
	}

	public static EString lower(EString v) {
		return strFun("lower", v);
	}

	public static EString overlay(EString v1, EInt from) {
		return strFun("overlay", v1, " placing ", from);
	}

	public static EString overlay(EString v1, EInt from, EInt vFor) {
		return strFun("overlay", v1, " placing ", from, " for ", vFor);
	}

	public static EString overlay(EString v1, int from) {
		return strFun("overlay", v1, " placing ", _context.val(from));
	}

	public static EString overlay(EString v1, int from, int vFor) {
		return strFun("overlay", v1, " placing ", _context.val(from), " for ", _context.val(vFor));
	}

	public static EInt position(EString substring, EString inString) {
		return intFun("position", substring, " in ", inString);
	}

	public static EString substring(EString string, EInt from) {
		return strFun("substring", string, " from ", from);
	}

	public static EString subString(EString string, EInt from, EInt vFor) {
		return strFun(
			"substring", string, " from ", from, " for ", vFor
		);
	}

	public static EString subString(EString string, EString fromPattern) {
		return strFun(
			"substring", string, " from ", fromPattern
		);
	}

	public static EString subString(EString string, EString fromPattern, EString forEscape) {
		return strFun(
			"substring", string, " from ", fromPattern, " for ", forEscape
		);
	}

	public static EString trimLeading(EString chars, EString fromString) {
		return strFun(
			"trim", "leading ", chars, " from ", fromString
		);
	}

	public static EString trimLeading(EString fromString) {
		return strFun(
			"trim", "leading from ", fromString
		);
	}

	public static EString trimTrailing(EString chars, EString fromString) {
		return strFun(
			"trim", "trailing ", chars, " from ", fromString
		);
	}

	public static EString trimTrailing(EString fromString) {
		return strFun(
			"trim", "trailing from ", fromString
		);
	}

	public static EString trimBoth(EString chars, EString fromString) {
		return strFun(
			"trim", "both ", chars, " from ", fromString
		);
	}

	public static EString trimBoth(EString fromString) {
		return strFun(
			"trim", "both from ", fromString
		);
	}

	public static EString upper(EString string) {
		return strFun("upper", string);
	}

	public static EInt ascii(EString string) {
		return intFun("ascii", string);
	}

	public static EString btrim(EString text) {
		return strFun("btrim", text);
	}

	public static EString btrim(EString text, EString chars) {
		return strFun("btrim", text, chars);
	}

	public static EString chr(EInt v) {
		return strFun("chr", v);
	}

	public static EString concat(DExpr... expr) {
		return strFun("concat", expr);
	}

	public static EString concat_ws(DExpr... expr) {
		return strFun("concat", expr);
	}

	public static EString convert(EString textToConvert, EString src_encoding_name, EString dest_encoding_name) {
		return strFun("convert", textToConvert, src_encoding_name, dest_encoding_name);
	}

	public static EString convert_from(EString textToConvert, EString src_encoding_name) {
		return strFun("convert_from", textToConvert, src_encoding_name);
	}

	public static EString convert_to(EString textToConvert, EString dst_encoding_name) {
		return strFun("convert_to", textToConvert, dst_encoding_name);
	}

	public static EString decode(EString textToDecode, EString format) {
		return strFun("decode", textToDecode, format);
	}

	public static EString encode(EByteList bytea, EString format) {
		return strFun("encode", bytea, format);
	}

	public static EString format(EString... formatAndArgs) {
		return strFun("format", formatAndArgs);
	}

	public static EString initcap(EString string) {
		return strFun("initcap", string);
	}

	public static EString left(EString string, EInt count) {
		return strFun("left", string, count);
	}

	public static EInt length(EString string) {
		return intFun("length", string);
	}

	public static EInt length(EString string, EString enc_name) {
		return intFun("length", string, enc_name);
	}

	public static EString lpad(EString string, EInt length) {
		return strFun("lpad", string, length);
	}

	public static EString lpad(EString string, EInt length, EString fill) {
		return strFun("lpad", string, length, fill);
	}

	public static EString ltrim(EString string) {
		return strFun("ltrim", string);
	}

	public static EString ltrim(EString string, EString chars) {
		return strFun("ltrim", string, chars);
	}

	public static EString md5(EString string) {
		return strFun("md5", string);
	}

	public static EString pg_client_encoding() {
		return strFun("pg_client_encoding");
	}

	public static EString quote_ident(EString text) {
		return strFun("quote_ident", text);
	}

	public static EString quote_literal(DExpr expr) {
		return strFun("quote_literal", expr);
	}

	public static EString quote_nullable(@Nullable DExpr expr) {
		return strFun("quote_nullable", expr);
	}

	//TODO
	/*
	regexp_matches(string text, pattern text [, flags text])

	 */
	public static EString regexp_replace(EString string, EString pattern, EString replacement) {
		return strFun("regexp_replace", string, pattern, replacement);
	}

	public static EString regexp_replace(EString string, EString pattern, EString replacement, EString flags) {
		return strFun("regexp_replace", string, pattern, replacement, flags);
	}

	//TODO
	/*
	regexp_split_to_array(string text, pattern text [, flags text ])
	regexp_split_to_table(string text, pattern text [, flags text])
	 */
	public static EString repeat(EString string, EInt number) {
		return strFun("repeat", string, number);
	}

	public static EString replace(EString string, EString from, EString to) {
		return strFun("replace", string, from, to);
	}

	public static EString reverse(EString string) {
		return strFun("reverse", string);
	}

	public static EString right(EString string, EInt n) {
		return strFun("right", string, n);
	}

	public static EString rpad(EString string, EInt length) {
		return strFun("rpad", string, length);
	}

	public static EString rpad(EString string, EInt length, EString fill) {
		return strFun("rpad", string, length, fill);
	}

	public static EString rtrim(EString string) {
		return strFun("rtrim", string);
	}

	public static EString rtrim(EString string, EString chars) {
		return strFun("rtrim", string, chars);
	}

	public static EString split_part(EString string, EString delimiter, EInt field) {
		return strFun("split_part", string, delimiter, field);
	}

	public static EInt strpos(EString string, EString substring) {
		return intFun("strpos", string, substring);
	}

	public static EString substr(EString string, EInt from) {
		return strFun("substr", string, from);
	}

	public static EString substr(EString string, EInt from, EInt count) {
		return strFun("substr", string, from, count);
	}

	public static EString to_ascii(EString string) {
		return strFun("to_ascii", string);
	}

	public static EString to_ascii(EString string, EString encoding) {
		return strFun("to_ascii", string, encoding);
	}

	public static EString to_hex(EInt number) {
		return strFun("to_hex", number);
	}

	public static EString to_hex(ELong number) {
		return strFun("to_hex", number);
	}

	public static EString translate(EString string, EString from, EString to) {
		return strFun("translate", string, from, to);
	}

	//		MATHEMATICAL FUNCTIONS
	public static <E1 extends DExpr<J1>, J1 extends Number> E1 abs(E1 x) {
		return _context.getTypeFactory(x).buildCall("abs", x);
	}

	public static EDouble cbrt(EDouble x) {
		return dblFun("cbrt", x);
	}

	public static EFloat ceil(EFloat x) {
		return fltFun("ceil", x);
	}

	public static EDouble ceil(EDouble x) {
		return dblFun("ceil", x);
	}

	public static EBigDecimal ceil(EBigDecimal x) {
		return decFun("ceil", x);
	}

	public static EDouble degrees(EDouble x) {
		return dblFun("degrees", x);
	}

	public static <E1 extends DExpr<J1>, J1 extends Number, E2 extends DExpr<J2>, J2 extends Number> E1 div(E1 y, E2 x
	) {
		return _context.getTypeFactory(y).buildCall("div", y, x);
	}
	//		HELPER FUNCTIONS

	static private EInt intFun(String name, Object... args) {
		return _context.getTypeFactory(EInt.class).buildCall(name, args);
	}

	static private EDouble dblFun(String name, Object... args) {
		return _context.getTypeFactory(EDouble.class).buildCall(name, args);
	}

	static private EFloat fltFun(String name, Object... args) {
		return _context.getTypeFactory(EFloat.class).buildCall(name, args);
	}

	static private EBigDecimal decFun(String name, Object... args) {
		return _context.getTypeFactory(EBigDecimal.class).buildCall(name, args);
	}

	static private EString strFun(String name, Object... args) {
		return _context.getTypeFactory(EString.class).buildCall(name, args);
	}
}
