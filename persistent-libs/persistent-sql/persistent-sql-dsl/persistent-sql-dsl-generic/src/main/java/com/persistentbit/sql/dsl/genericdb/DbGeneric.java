package com.persistentbit.sql.dsl.genericdb;

import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.Case;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/18
 */
public class DbGeneric{

	protected static ExprContext _context = createExprContext();

	private static ExprContext createExprContext() {
		ExprContext c = new ExprContext();
		GenericTypeFactories.registerAll(c);
		GenericBinOps.setDefaultBinOpBuilders(c);
		GenericExprTypeJdbcConverters.init(c);
		return c;
	}

	public <
		E1 extends DExpr<J1>, J1,
		E2 extends DExpr<J2>, J2
		>
	ETuple2<E1, J1, E2, J2> tuple(E1 e1, E2 e2) {
		return _context.tuple(e1, e2);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		>
	ETuple3<E1, J1, E2, J2, E3, J3> tuple(E1 e1, E2 e2, E3 e3) {
		return _context.tuple(e1, e2, e3);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		>
	ETuple4<E1, J1, E2, J2, E3, J3, E4, J4> tuple(E1 e1, E2 e2, E3 e3, E4 e4) {
		return _context.tuple(e1, e2, e3, e4);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		, E5 extends DExpr<J5>, J5
		>
	ETuple5<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5> tuple(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5) {
		return _context.tuple(e1, e2, e3, e4, e5);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		, E5 extends DExpr<J5>, J5
		, E6 extends DExpr<J6>, J6
		>
	ETuple6<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6> tuple(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6) {
		return _context.tuple(e1, e2, e3, e4, e5, e6);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		, E5 extends DExpr<J5>, J5
		, E6 extends DExpr<J6>, J6
		, E7 extends DExpr<J7>, J7

		>
	ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7> tuple(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6,
																		  E7 e7
	) {
		return _context.tuple(e1, e2, e3, e4, e5, e6, e7);
	}

	public static EFloat val(Float value) {
		return _context.getTypeFactory(EFloat.class).buildVal(value);
	}

	public static EShort val(Short value) {
		return _context.getTypeFactory(EShort.class).buildVal(value);
	}

	public static EByteList val(PByteList value) {
		return _context.getTypeFactory(EByteList.class).buildVal(value);
	}

	public static EByte val(Byte value) {
		return _context.getTypeFactory(EByte.class).buildVal(value);
	}

	public static EBool val(Boolean value) {
		return _context.getTypeFactory(EBool.class).buildVal(value);
	}

	public static ETime val(LocalTime value) {
		return _context.getTypeFactory(ETime.class).buildVal(value);
	}

	public static EDate val(LocalDate value) {
		return _context.getTypeFactory(EDate.class).buildVal(value);
	}

	public static EDouble val(Double value) {
		return _context.getTypeFactory(EDouble.class).buildVal(value);
	}

	public static EString val(String value) {
		return _context.getTypeFactory(EString.class).buildVal(value);
	}

	public static EBigDecimal val(BigDecimal value) {
		return _context.getTypeFactory(EBigDecimal.class).buildVal(value);
	}

	public static EInt val(Integer value) {
		return _context.getTypeFactory(EInt.class).buildVal(value);
	}

	public static EDateTime val(LocalDateTime value) {
		return _context.getTypeFactory(EDateTime.class).buildVal(value);
	}

	public static ELong val(Long value) {
		return _context.getTypeFactory(ELong.class).buildVal(value);
	}


	public static Case caseWhen() {
		return new Case(_context);
	}

	//AGGREGATE FUNCTIONS

	public static <E1 extends DExpr<J1>, J1> E1 avg(E1 expr) {
		return _context.buildCall(expr, "AVG", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 avg_distinct(E1 expr) {
		return _context.buildCall(expr, "AVG", "DISTINCT ", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 corr(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "CORR", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 corr_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "CORR", "DISTINCT ", dependent, independent);
	}

	public static ELong countAll() {
		return _context.buildCall(ELong.class, "COUNT", "*");
	}

	public static ELong count(DExpr expr) {
		return _context.buildCall(ELong.class, "COUNT", expr);
	}

	public static ELong countDistinct(DExpr expr) {
		return _context.buildCall(ELong.class, "COUNT", "DISTINCT ", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 covar_pop(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "COVAR_POP", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 covar_pop_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "COVAR_POP", "DISTINCT ", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 covar_samp(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "COVAR_SAMP", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 covar_samp_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "COVAR_SAMP", "DISTINCT ", dependent, independent);
	}
	//TODO CUME_DIST( value_list) WITHIN GROUP (ORDER BY sort_list)
	//

	//TODO DENSE_RANK( value_list) WITHIN GROUP (ORDER BY
	//

	public static <E1 extends DExpr<J1>, J1> E1 min(E1 expr) {
		return _context.buildCall(expr, "MIN", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 max(E1 expr) {
		return _context.buildCall(expr, "MAX", expr);
	}

	//TODO	PERCENT_RANK( value_list) WITHIN GROUP (ORDER BY sort_list)
	//TODO	PERCENTILE_CONT ( percentile) WITHIN GROUP (ORDER BY sort_list)
	//TODO	PERCENTILE_DISC ( percentile) WITHIN GROUP (ORDER BY sort_list)
	//TODO	RANK( value_list) WITHIN GROUP (ORDER BY sort_list)
	public static <E1 extends DExpr<J1>, J1> E1 regr_avgx(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_AVGX", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_avgx_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_AVGX", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_avgy(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_AVGY", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_avgy_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_AVGY", "DISTINCT ", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_count(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_COUNT", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_count_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_COUNT", "DISTINCT ", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_intercept(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_INTERCEPT", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_intercept_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_INTERCEPT", "DISTINCT ", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_r2(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_R2", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_r2_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_R2", "DISTINCT ", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_slope(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_SLOPE", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_slope_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_SLOPE", "DISTINCT ", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_srx(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_SRX", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_srx_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_SRX", "DISTINCT ", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_sry(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_SRY", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 regr_sry_distinct(E1 dependent, E1 independent) {
		return _context.buildCall(dependent, "REGR_SRY", "DISTINCT ", dependent, independent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 stddev_pop(E1 expr) {
		return _context.buildCall(expr, "STDDEV_POP", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 stddev_pop_distinct(E1 expr) {
		return _context.buildCall(expr, "STDDEV_POP", "DISTINCT ", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 stddev_samp(E1 expr) {
		return _context.buildCall(expr, "STDDEV_SAMP", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 stddev_samp_distinct(E1 expr) {
		return _context.buildCall(expr, "STDDEV_SAMP", "DISTINCT ", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 sum(E1 expr) {
		return _context.buildCall(expr, "SUM", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 sum_distinct(E1 expr) {
		return _context.buildCall(expr, "SUM", "DISTINCT ", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 var_pop(E1 expr) {
		return _context.buildCall(expr, "VAR_POP", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 var_pop_distinct(E1 expr) {
		return _context.buildCall(expr, "VAR_POP", "DISTINCT ", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 var_samp(E1 expr) {
		return _context.buildCall(expr, "VAR_SAMP", expr);
	}

	public static <E1 extends DExpr<J1>, J1> E1 var_samp_distinct(E1 expr) {
		return _context.buildCall(expr, "VAR_SAMP", "DISTINCT ", expr);
	}

	// SCALAR FUNCTIONS

	public static final EDate     current_date      =
		_context.customSql(EDate.class, () -> SqlWithParams.sql("CURRENT_DATE"));
	public static final ETime     current_time      =
		_context.customSql(ETime.class, () -> SqlWithParams.sql("CURRENT_TIME"));
	public static final EDateTime current_timestamp =
		_context.customSql(EDateTime.class, () -> SqlWithParams.sql("CURRENT_TIMESTAMP"));
	public static final EString   current_user      =
		_context.customSql(EString.class, () -> SqlWithParams.sql("CURRENT_USER"));
	public static final EString   session_user      =
		_context.customSql(EString.class, () -> SqlWithParams.sql("SESSION_USER"));
	public static final EString   system_user       =
		_context.customSql(EString.class, () -> SqlWithParams.sql("SYSTEM_USER"));

	public static final <E1 extends DExpr<J>, J> E1 cast(DExpr expr, Class<E1> asType) {
		return _context.customSql(asType, () ->
			SqlWithParams.sql("CAST(").add(_context.toSql(expr)).add(" AS ").add(_context.getDefaultDbTypeName(asType))
				.add(")")
		);
	}

	public static final <E1 extends DExpr<J>, J> E1 cast(DExpr expr, Class<E1> asType, int length) {
		return _context.customSql(asType, () ->
			SqlWithParams.sql("CAST(").add(_context.toSql(expr)).add(" AS ").add(_context.getDefaultDbTypeName(asType))
				.add("(" + length + "))")
		);
	}

	public static <E1 extends DExpr<J1>, J1 extends Number> E1 abs(E1 x) {
		return _context.getTypeFactory(x).buildCall("abs", x);
	}

	public static EInt bit_length(EString string) {
		return intFun("BIT_LENGTH", string);
	}

	public static EInt char_length(EString string) {
		return intFun("CHAR_LENGTH", string);
	}


	public static EInt octet_length(EString string) {
		return intFun("OCTET_LENGTH", string);
	}

	public static EFloat ceil(EFloat x) {
		return fltFun("CEIL", x);
	}

	public static EDouble ceil(EDouble x) {
		return dblFun("CEIL", x);
	}

	public static EBigDecimal ceil(EBigDecimal x) {
		return decFun("CEIL", x);
	}


	public static EFloat exp(EFloat x) {
		return fltFun("EXP", x);
	}

	public static EDouble exp(EDouble x) {
		return dblFun("EXP", x);
	}

	public static EBigDecimal exp(EBigDecimal x) {
		return decFun("EXP", x);
	}


	public static <E1 extends DExpr<J1>, J1> E1 extract(String date_partName, Class<E1> resultTypeClass, DExpr expr) {
		return _context.buildCall(resultTypeClass, "EXTRACT", date_partName, " FROM ", expr);
	}

	public static EInt extract_day(EDate expr) {
		return extract("DAY", EInt.class, expr);
	}

	public static EInt extract_day(EDateTime expr) {
		return extract("DAY", EInt.class, expr);
	}

	public static EInt extract_month(EDate expr) {
		return extract("MONTH", EInt.class, expr);
	}

	public static EInt extract_month(EDateTime expr) {
		return extract("MONTH", EInt.class, expr);

	}

	public static EInt extract_year(EDate expr) {
		return extract("YEAR", EInt.class, expr);
	}

	public static EInt extract_year(EDateTime expr) {
		return extract("YEAR", EInt.class, expr);
	}

	public static EInt extract_second(EDateTime expr) {
		return extract("SECOND", EInt.class, expr);
	}

	public static EInt extract_second(ETime expr) {
		return extract("SECOND", EInt.class, expr);
	}

	public static EInt extract_hour(EDateTime expr) {
		return extract("HOUR", EInt.class, expr);
	}

	public static EInt extract_hour(ETime expr) {
		return extract("HOUR", EInt.class, expr);
	}

	public static EInt extract_minute(EDateTime expr) {
		return extract("MINUTE", EInt.class, expr);
	}

	public static EInt extract_minute(ETime expr) {
		return extract("MINUTE", EInt.class, expr);
	}

	public static EInt extract_timezone_hour(EDateTime expr) {
		return extract("TIMEZONE_HOUR", EInt.class, expr);
	}

	public static EInt extract_timezone_hour(ETime expr) {
		return extract("TIMEZONE_HOUR", EInt.class, expr);
	}

	public static EInt extract_timezone_minute(EDateTime expr) {
		return extract("TIMEZONE_MINUTE", EInt.class, expr);
	}

	public static EInt extract_timezone_minute(ETime expr) {
		return extract("TIMEZONE_MINUTE", EInt.class, expr);
	}

	public static EFloat floor(EFloat x) {
		return fltFun("FLOOR", x);
	}

	public static EDouble floor(EDouble x) {
		return dblFun("FLOOR", x);
	}

	public static EBigDecimal floor(EBigDecimal x) {
		return decFun("FLOOR", x);
	}

	public static EFloat ln(EFloat x) {
		return fltFun("LN", x);
	}

	public static EDouble ln(EDouble x) {
		return dblFun("LN", x);
	}

	public static EBigDecimal ln(EBigDecimal x) {
		return decFun("LN", x);
	}

	public static <E1 extends DExpr<J1>, J1> E1 mod(E1 divident, E1 divider) {
		return _context.buildCall(divident, "MOD", divident, divider);
	}

	public static EInt position(EString substring, EString inString) {
		return intFun("POSITION", substring, " IN ", inString);
	}

	public static <E1 extends DExpr<J1>, J1> E1 power(E1 base, E1 exponent) {
		return _context.buildCall(base, "POWER", base, exponent);
	}

	public static <E1 extends DExpr<J1>, J1> E1 sqrt(E1 expr) {
		return _context.buildCall(expr, "SQRT", expr);
	}

	public static <E1 extends DExpr<J1>, J1 extends Number> E1 width_bucket(E1 expr, E1 min, E1 max, E1 bucketCount) {
		return _context.buildCall(expr, "WIDTH_BUCKET", expr, min, max, bucketCount);
	}

	public static EString upper(EString string) {
		return strFun("upper", string);
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

	// HELPER FUNCTIONS

	static protected EInt intFun(String name, Object... args) {
		return _context.buildCall(EInt.class, name, args);
	}

	static protected EDouble dblFun(String name, Object... args) {
		return _context.buildCall(EDouble.class, name, args);
	}

	static protected EFloat fltFun(String name, Object... args) {
		return _context.buildCall(EFloat.class, name, args);
	}

	static protected EBigDecimal decFun(String name, Object... args) {
		return _context.buildCall(EBigDecimal.class, name, args);
	}

	static protected EString strFun(String name, Object... args) {
		return _context.buildCall(EString.class, name, args);
	}
}
