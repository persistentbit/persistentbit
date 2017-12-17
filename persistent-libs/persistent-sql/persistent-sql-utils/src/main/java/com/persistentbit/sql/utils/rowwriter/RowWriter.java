package com.persistentbit.sql.utils.rowwriter;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface RowWriter{
	<T> void writeNext(Class<T> cls,T value);
}
