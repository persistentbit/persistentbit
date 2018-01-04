package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/12/17
 */
public class FunctionCallTypeStrategy<J> extends AbstractTypeStrategy<J>{

	private final ExprContext context;
	private final String      callName;
	private final Object[]    arguments;

	public FunctionCallTypeStrategy(ExprContext context,
									String callName,
									Object... arguments
	) {
		this.context = context;
		this.callName = callName;
		this.arguments = arguments;
	}

	@Override
	public SqlWithParams _toSql() {
		SqlWithParams sql        = SqlWithParams.sql(callName + "(");
		boolean       first      = true;
		boolean       prevString = false;
		for(Object obj : arguments) {
			DExpr expr = obj instanceof DExpr ? (DExpr) obj : null;
			if(expr == null) {
				//use it as a string
				sql = sql.add(obj.toString());
				prevString = true;
			}
			else {
				if(first == false && prevString == false) {
					sql = sql.add(", ");
				}
				first = false;
				prevString = false;
				sql = sql.add(context.toSql(expr));
			}

		}
		return sql.add(")");
	}

	@Override
	public String _createAliasName(String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public TypeStrategy<J> onlyColumnName() {
		return this;
	}
}
