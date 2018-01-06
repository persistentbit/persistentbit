package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EComparableMixIn;
import com.persistentbit.sql.dsl.expressions.ESelection;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface TypeImplComparableMixin<E extends DExpr<J>, J> extends EComparableMixIn<E, J>{

	ExprTypeFactory<E, J> getTypeFactory();

	E getThis();

	@Override
	default EBool eq(E other) {
		return getContext().eq(getThis(), other);
	}

	@Override
	default EBool eq(J other) {
		return eq(getContext().buildVal(getThis(), other));
	}

	@Override
	default EBool notEq(E other) {
		return getContext().notEq(getThis(), other);
	}

	@Override
	default EBool notEq(J other) {
		return notEq(getContext().buildVal(getThis(), other));

	}

	@Override
	default EBool lt(E other) {
		return getContext().lt(getThis(), other);
	}

	@Override
	default EBool lt(J other) {
		return lt(getContext().buildVal(getThis(), other));
	}

	@Override
	default EBool gt(E other) {
		return getContext().gt(getThis(), other);
	}

	@Override
	default EBool gt(J other) {
		return gt(getContext().buildVal(getThis(), other));
	}

	@Override
	default EBool ltEq(E other) {
		return getContext().ltEq(getThis(), other);
	}

	@Override
	default EBool ltEq(J other) {
		return ltEq(getContext().buildVal(getThis(), other));
	}

	@Override
	default EBool gtEq(E other) {
		return getContext().gtEq(getThis(), other);
	}

	@Override
	default EBool gtEq(J other) {
		return gtEq(getContext().buildVal(getThis(), other));
	}

	@Override
	default EBool isNull() {
		return getTypeFactory().getExprContext().isNull(getThis());
	}

	@Override
	default EBool isNotNull() {
		return getTypeFactory().getExprContext().isNotNull(getThis());
	}

	@Override
	default EBool between(E lowerRangeInclusive, E upperRangeInclusive) {
		return getContext()
			.customSql(EBool.class, () ->
				getContext().toSql(getThis())
					.add(" BETWEEN (")
					.add(getContext().toSql(lowerRangeInclusive))
					.add(" AND ")
					.add(getContext().toSql(upperRangeInclusive))
					.add(") ")
			);
	}

	@Override
	default EBool notBetween(E lowerRangeInclusive, E upperRangeInclusive) {
		return getContext()
			.customSql(EBool.class, () ->
				getContext().toSql(getThis())
					.add(" NOT BETWEEN (")
					.add(getContext().toSql(lowerRangeInclusive))
					.add(" AND ")
					.add(getContext().toSql(upperRangeInclusive))
					.add(") ")
			);
	}

	@Override
	default EBool in(ESelection<J> subQuerySelection) {
		return getContext()
			.customSql(EBool.class, () ->
				getContext().toSql(getThis())
					.add(" IN ")
					.add(getContext().toSql(subQuerySelection))
			);
	}

	@Override
	default EBool in(Iterable<E> list) {
		return getContext()
			.customSql(EBool.class, () ->
				getContext().toSql(getThis())
					.add(" IN (")
					.add(PStream.from(list).map(e -> getContext().toSql(e)), ", ")
					.add(")")
			);
	}

	@Override
	default EBool notIn(ESelection<J> subQuerySelection) {
		return getContext()
			.customSql(EBool.class, () ->
				getContext().toSql(getThis())
					.add(" NOT IN ")
					.add(getContext().toSql(subQuerySelection))
			);
	}

	@Override
	default EBool notIn(Iterable<E> list) {
		return getContext()
			.customSql(EBool.class, () ->
				getContext().toSql(getThis())
					.add(" NOT IN (")
					.add(PStream.from(list).map(e -> getContext().toSql(e)), ", ")
					.add(")")
			);
	}

	private ExprContext getContext() {
		return getTypeFactory().getExprContext();
	}
}
