package com.persistentbit.sql.dsl.generic.expressions.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public interface PrepStatParam{
	void _setPrepStatement(PreparedStatement stat, int index) throws SQLException;


}
