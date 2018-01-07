package com.persistentbit.sql.dsl.postgres.rt;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.genericdb.DbGeneric;
import com.persistentbit.sql.dsl.orderby.OrderBy;
import com.persistentbit.sql.dsl.postgres.rt.windowover.WindowOver;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public class DbPostgres extends DbGeneric{




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


	public static EDouble cbrt(EDouble x) {
		return dblFun("cbrt", x);
	}


	public static EDouble degrees(EDouble x) {
		return dblFun("degrees", x);
	}

	public static <E1 extends DExpr<J1>, J1 extends Number, E2 extends DExpr<J2>, J2 extends Number> E1 div(E1 y, E2 x
	) {
		return _context.buildCall(y, "div", y, x);
	}

	//		WINDOW FUNCTIONS
	public static ELong row_number() {
		return _context.buildCall(ELong.class, "row_number");
	}

	public static ELong row_number(OrderBy orderBy) {
		return _context.buildCall(ELong.class, "row_number", _context.toSql(orderBy));
	}

	public static ELong rank() {
		return _context.buildCall(ELong.class, "rank");
	}

	public static ELong rank(OrderBy orderBy) {
		return _context.buildCall(ELong.class, "rank", _context.toSql(orderBy));
	}

	public static ELong dense_rank() {
		return _context.buildCall(ELong.class, "dense_rank");
	}

	public static ELong dense_rank(OrderBy orderBy) {
		return _context.buildCall(ELong.class, "dense_rank", _context.toSql(orderBy));
	}

	public static EDouble cume_dist() {
		return _context.buildCall(EDouble.class, "cume_dist");
	}

	public static EDouble cume_dist(OrderBy orderBy) {
		return _context.buildCall(EDouble.class, "cume_dist", _context.toSql(orderBy));
	}

	public static EInt ntile(EInt numBuckets) {
		return _context.buildCall(EInt.class, "ntile", numBuckets);
	}

	public static EInt ntile(EInt numBuckets, OrderBy orderBy) {
		return _context.buildCall(EInt.class, "ntile", numBuckets, ", ", _context.toSql(orderBy));
	}

	public static <E1 extends DExpr<J1>, J1> E1 lag(E1 value) {
		return _context.buildCall(value, "lag", value);
	}

	public static <E1 extends DExpr<J1>, J1> E1 lag(E1 value, EInt offset, @Nullable E1 defaultNull) {
		if(defaultNull == null) {
			return _context.buildCall(value, "lag", value, offset);
		}
		else {
			return _context.buildCall(value, "lag", value, offset, defaultNull);
		}
	}

	public static <E1 extends DExpr<J1>, J1> E1 lag(E1 value, OrderBy orderBy) {
		return _context.buildCall(value, "lag", value, ", ", _context.toSql(orderBy));
	}

	public static <E1 extends DExpr<J1>, J1> E1 lag(E1 value, EInt offset, @Nullable E1 defaultNull, OrderBy orderBy) {
		if(defaultNull == null) {
			return _context.buildCall(value, "lag", value, offset, _context.toSql(orderBy));
		}
		else {
			return _context.buildCall(value, "lag", value, offset, defaultNull, _context.toSql(orderBy));
		}
	}

	public static <E1 extends DExpr<J1>, J1> E1 lead(E1 value) {
		return _context.buildCall(value, "lead", value);
	}

	public static <E1 extends DExpr<J1>, J1> E1 lead(E1 value, EInt offset, @Nullable E1 defaultNull) {
		if(defaultNull == null) {
			return _context.buildCall(value, "lead", value, offset);
		}
		else {
			return _context.buildCall(value, "lead", value, offset, defaultNull);
		}
	}

	public static <E1 extends DExpr<J1>, J1> E1 lead(E1 value, OrderBy orderBy) {
		return _context.buildCall(value, "lead", value, _context.toSql(orderBy));
	}

	public static <E1 extends DExpr<J1>, J1> E1 lead(E1 value, EInt offset, @Nullable E1 defaultNull, OrderBy orderBy) {
		if(defaultNull == null) {
			return _context.buildCall(value, "lead", value, offset, _context.toSql(orderBy));
		}
		else {
			return _context.buildCall(value, "lead", value, offset, defaultNull, _context.toSql(orderBy));
		}
	}

	static public <E1 extends DExpr<J1>, J1> E1 first_value(E1 value) {
		return _context.buildCall(value, "first_value", value);
	}

	static public <E1 extends DExpr<J1>, J1> E1 first_value(E1 value, OrderBy orderBy) {
		return _context.buildCall(value, "first_value", value, _context.toSql(orderBy));
	}

	static public <E1 extends DExpr<J1>, J1> E1 last_value(E1 value) {
		return _context.buildCall(value, "last_value", value);
	}

	static public <E1 extends DExpr<J1>, J1> E1 last_value(E1 value, OrderBy orderBy) {
		return _context.buildCall(value, "last_value", value, _context.toSql(orderBy));
	}

	static public <E1 extends DExpr<J1>, J1> E1 nth_value(E1 value, EInt nth) {
		return _context.buildCall(value, "nth_value", value, nth);
	}

	static public <E1 extends DExpr<J1>, J1> E1 nth_value(E1 value, EInt nth, OrderBy orderBy) {
		return _context.buildCall(value, "nth_value", value, nth, _context.toSql(orderBy));
	}


	public static <E1 extends DExpr<J1>, J1> E1 filter(E1 aggregateFunction, EBool whereExpr) {
		return _context.customSql(aggregateFunction, () ->
			_context.toSql(aggregateFunction)
				.add(" FILTER ( WHERE")
				.add(_context.toSql(whereExpr))
				.add(")")
		);
	}

	public static <E1 extends DExpr<J1>, J1> E1 withinGroup(E1 aggregateFunction, OrderBy orderBy) {
		return _context.customSql(aggregateFunction, () ->
			_context.toSql(aggregateFunction)
				.add(" WITHIN GROUP (")
				.add(_context.toSql(orderBy))
				.add(")")
		);
	}

	public static <E1 extends DExpr<J1>, J1> E1 over(E1 aggregateFunction, Function<WindowOver, WindowOver> over) {
		return over(aggregateFunction, over.apply(WindowOver.empty()));
	}

	public static <E1 extends DExpr<J1>, J1> E1 over(E1 aggregateFunction, WindowOver window) {
		return _context.customSql(aggregateFunction, () ->
			_context.toSql(aggregateFunction)
				.add(" OVER(")
				.add(_context.toSql(window))
				.add(")")
		);
	}
}
