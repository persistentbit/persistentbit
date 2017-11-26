package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import java.math.BigDecimal;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBigDecimalValue extends DBigDecimalAbstract{
	private final BigDecimal value;

	public DBigDecimalValue(BigDecimal value) {
		this.value = value;
	}
}
