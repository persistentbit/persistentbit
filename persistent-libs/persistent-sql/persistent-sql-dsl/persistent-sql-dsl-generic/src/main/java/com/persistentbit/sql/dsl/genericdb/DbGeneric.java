package com.persistentbit.sql.dsl.genericdb;

import com.persistentbit.collections.PByteList;
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
}
