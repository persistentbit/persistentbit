package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class EString implements DExprString{
	private final ExprContext context;

	public EString(ExprContext context) {
		this.context = context;
	}

	@Override
	public DExprString concat(DExpr<String> other) {
		throw new ToDo();
	}

	@Override
	public DExprString concat(String other) {
		return concat(context.val(other,DExprString.class));
	}

	@Override
	public DExprBoolean like(DExpr<String> likeOther
	) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean like(String likeOther) {
		return like(context.val(likeOther,DExprString.class));
	}

	@Override
	public DExprBoolean eq(DExpr<String> other
	) {
		return context.eq(this, other);
	}

	@Override
	public DExprBoolean eq(String other) {
		return eq(context.val(other,DExprString.class));
	}

	@Override
	public DExprBoolean notEq(DExpr<String> other
	) {
		return context.notEq(this,other);
	}

	@Override
	public DExprBoolean notEq(String other) {
		return notEq(context.val(other,DExprString.class));
	}

	@Override
	public DExprBoolean lt(DExpr<String> other) {
		return context.lt(this,other);
	}

	@Override
	public DExprBoolean lt(String other) {
		return null;
	}

	@Override
	public DExprBoolean gt(DExpr<String> other
	) {
		return context.gt(this,other);
	}

	@Override
	public DExprBoolean gt(String other) {
		return null;
	}

	@Override
	public DExprBoolean ltEq(DExpr<String> other
	) {
		return context.ltEq(this,other);
	}

	@Override
	public DExprBoolean ltEq(String other) {
		return null;
	}

	@Override
	public DExprBoolean gtEq(DExpr<String> other
	) {
		return context.gtEq(this,other);
	}

	@Override
	public DExprBoolean gtEq(String other) {
		return null;
	}

	@Override
	public DExprBoolean isNull() {
		return null;
	}

	@Override
	public DExprBoolean isNotNull() {
		return null;
	}

	@Override
	public DExprBoolean in(PList<DExpr<String>> values
	) {
		return null;
	}

	@Override
	public DExprBoolean in(DExpr<String>... values
	) {
		return null;
	}
}
