package com.persistentbit.sql.dsl.generic.expressions.impl;

import java.sql.PreparedStatement;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public interface PrepStatParam{
	void _setPrepStatement(PreparedStatement stat, int index);


}
