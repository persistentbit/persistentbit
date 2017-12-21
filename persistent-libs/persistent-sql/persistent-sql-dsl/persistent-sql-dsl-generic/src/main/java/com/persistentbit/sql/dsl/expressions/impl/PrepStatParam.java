package com.persistentbit.sql.dsl.expressions.impl;

import com.persistentbit.collections.PMap;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public interface PrepStatParam{
	int _setPrepStatement(PMap<String, Object> extParams,  PreparedStatement stat, int index) throws SQLException;


}
