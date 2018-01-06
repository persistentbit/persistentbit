package com.persistentbit.sql.dsl.expressions.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.utils.Lazy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 6/01/18
 */
public class Case{

	private final ExprContext context;

	public Case(ExprContext context) {
		this.context = context;
	}

	public <E extends DExpr<J>, J> TypedCase<E, J> when(EBool condition, E then) {
		return new TypedCase<>(PList.val(Tuple2.of(condition, then)), null);
	}

	public class TypedCase<E extends DExpr<J>, J>{

		private final PList<Tuple2<EBool, E>> items;
		@Nullable
		private final E                       elseItem;

		public TypedCase(PList<Tuple2<EBool, E>> items, E elseItem) {
			this.items = items;
			this.elseItem = elseItem;
		}

		public TypedCase when(EBool condition, E then) {
			return new TypedCase(items.plus(Tuple2.of(condition, then)), elseItem);
		}

		public TypedCase whenElse(E elseItem) {
			if(this.elseItem != null && elseItem != null) {
				throw new RuntimeException("ElseItem is already defined");
			}
			return new TypedCase(items, elseItem);
		}

		public E end() {
			Lazy<SqlWithParams> sqlSupl = Lazy.code(() -> {
				SqlWithParams sql = SqlWithParams.sql("CASE");
				for(Tuple2<EBool, E> item : items) {
					sql = sql.add(" WHEN ")
						.add(context.toSql(item._1))
						.add(" THEN ")
						.add(context.toSql(item._2));
				}
				if(elseItem != null) {
					sql = sql.add(" ELSE ").add(context.toSql(elseItem));
				}
				sql = sql.add(" END ");
				return sql;
			});
			return context.customSql(items.head()._2, sqlSupl);
		}
	}
}
