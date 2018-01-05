package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;
import com.persistentbit.sql.dsl.expressions.ETuple2;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public abstract class AbstractTupleTypeFactor<E extends AbstractTupleTypeFactor.ETupleImpl> implements ExprTypeFactory{

	private final ExprContext context;


	public AbstractTupleTypeFactor(ExprContext context) {
		this.context = context;
	}


	@Override
	public Class getTypeClass() {
		return ETuple2.class;
	}


	@Override
	public DExpr buildBinOp(DExpr left, BinOpOperator op, DExpr right
	) {
		throw new UnsupportedOperationException("BinOp " + op);
	}

	@Override
	public DExpr buildSingleOp(DExpr expr, SingleOpOperator op
	) {
		throw new UnsupportedOperationException("SingleOp " + op);
	}

	@Override
	public DExpr buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EArray buildArrayTableField(String fieldSelectionName, String fieldName, String columnName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EArray buildArrayVal(ImmutableArray values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DExpr buildVal(Object value) {
		throw new UnsupportedOperationException("buildVal on ETuple");
	}


	@Override
	public DExpr buildParam(Function paramGetter) {
		throw new ToDo();
	}

	@Override
	public DExpr buildCall(String callName, Object... params) {
		throw new ToDo();
	}


	/*public <
		E1 extends DExpr<J1>, J1,
		E2 extends DExpr<J2>, J2
		>
	ETuple2<E1, J1, E2, J2> of(E1 e1, E2 e2) {
		return (ETuple2) buildInstance(new DExpr[]{e1, e2});
	}*/

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	protected abstract E buildInstance(DExpr[] items);

	static protected class ETupleImpl<E1 extends DExpr<J1>, J1> implements ExprTypeImpl<E1, J1>{

		protected final DExpr[]                    items;
		protected final AbstractTupleTypeFactor    exprTypeFactory;
		protected final Function<Object[], Object> toJavaType;


		protected ETupleImpl(DExpr[] items, AbstractTupleTypeFactor exprTypeFactory,
							 Function<Object[], Object> toJavaType) {
			this.items = items;
			this.exprTypeFactory = exprTypeFactory;
			this.toJavaType = toJavaType;
		}

		@Override
		public ExprTypeFactory getTypeFactory() {
			return exprTypeFactory;
		}


		public ExprTypeJdbcConvert getJdbcConverter() {
			Function<Object[], Object> mapper = ol -> {
				if(ol == null) {
					return null;
				}
				boolean isNull = true;
				for(Object o : ol) {
					if(o != null) {
						isNull = false;
						break;
					}
				}
				if(isNull) {
					return null;
				}
				return toJavaType.apply(ol);
			};
			return ExprTypeJdbcConvert.createMultiColumn(
				PList.val(items).map(i -> exprTypeFactory.context.getJdbcConverter(i))
				, mapper
			);
		}

		@Override
		public E1 onlyTableColumn() {
			DExpr[] res = new DExpr[items.length];
			for(int t = 0; t < items.length; t++) {
				res[t] = exprTypeFactory.context.onlyTableColumn(items[t]);
			}
			return (E1) exprTypeFactory.buildInstance(res);
		}

		@Override
		public E1 buildSelection(String prefixAlias) {
			DExpr[] res = new DExpr[items.length];
			for(int t = 0; t < items.length; t++) {
				res[t] = (DExpr) exprTypeFactory.context
					.buildSelection(items[t], prefixAlias == null ? null : prefixAlias + "t" + (t + 1) + "_");
			}
			return (E1) exprTypeFactory.buildInstance(res);
		}

		@Override
		public PList<DExpr> expand() {
			PList<DExpr> res = PList.empty();
			for(int t = 0; t < items.length; t++) {
				res = res.plusAll(exprTypeFactory.context.expand(items[t]));
			}
			return res;
		}

		@Override
		public SqlWithParams toSql() {
			SqlWithParams res = SqlWithParams.empty;
			for(int t = 0; t < items.length; t++) {
				if(t != 0) {
					res = res.add(", ");
				}
				res = res.add(exprTypeFactory.context.toSql(items[t]));
			}
			return res;
		}

		@Override
		public E1 buildAlias(String alias) {
			throw new UnsupportedOperationException("buildAlias on a ETuple");
		}
	}
}
