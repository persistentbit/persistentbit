package com.persistentbit.sql.dsl.generic.expressions.impl.datetime;

import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DDateTimeValue extends DDateTimeAbstract{
	private final LocalDateTime value;

	public DDateTimeValue(LocalDateTime value) {
		this.value = value;
	}

}
