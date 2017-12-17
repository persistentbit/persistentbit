package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.list;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.dtuples.DTuple2;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class ListTypeStrategy<E extends DExpr<J>,J> implements TypeStrategy<J>{
	private final ExprContext context;
	private final PList<DExpr>	exprList;
	private final PList<DExpr>	exprExpandedList;

	public ListTypeStrategy(ExprContext context,
							PList<DExpr> exprList
	) {
		this.context = context;
		this.exprList = exprList;
		this. exprExpandedList =exprList.map(expr -> context.getTypeFactory(expr).expand(expr)).<DExpr>flatten().plist();
	}

	@Override
	public SqlWithParams _toSqlSelection(String alias) {
		return null;
	}

	@Override
	public SqlWithParams _toSql() {
		return null;
	}

	@Override
	public J _read(RowReader rowReader) {
		return null;
	}

	@Override
	public DExpr<J> _withAlias(String alias) {
		return new DTuple2<>(
			DImpl._get(v1)._withAlias(alias + "v1"),
			DImpl._get(v2)._withAlias(alias + "v2")
		);
	}

	@Override
	public PList<DExpr> _expand(DExpr<J> expr) {
		return exprExpandedList;
	}

	@Override
	public PList<String> _getColumnNames() {
		return exprExpandedList.map(e ->
				(String)((ExprTypeImpl)(context.getTypeFactory(e)))
				.getStrategy()
				._getColumnNames()
				.<String>head()
		);
	}
}
