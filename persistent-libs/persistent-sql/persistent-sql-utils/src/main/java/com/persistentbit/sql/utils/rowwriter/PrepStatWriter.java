package com.persistentbit.sql.utils.rowwriter;

import java.sql.PreparedStatement;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class PrepStatWriter implements RowWriter{
	protected final PreparedStatement stat;
	protected int index = 1;

	public PrepStatWriter(PreparedStatement stat) {
		this.stat = stat;
	}

	@Override
	public <T> void writeNext(Class<T> cls, T value) {
		throw new RuntimeException("TODO");
	}
}
