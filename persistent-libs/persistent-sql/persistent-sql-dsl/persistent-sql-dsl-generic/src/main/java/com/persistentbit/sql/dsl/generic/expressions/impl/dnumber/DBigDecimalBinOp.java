package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

import java.math.BigDecimal;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBigDecimalBinOp extends DBigDecimalAbstract{
	private final DExpr left;
	private final NumberBinOperator operator;
	private final DExpr right;

	public DBigDecimalBinOp(DExpr left, NumberBinOperator operator,
							DExpr right
	) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}

	@Override
	public BigDecimal _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(BigDecimal.class);
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return operator.asSql(context,left,right);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return _toSql(context).add(alias == null ? "" : " AS " + alias);
	}
}
