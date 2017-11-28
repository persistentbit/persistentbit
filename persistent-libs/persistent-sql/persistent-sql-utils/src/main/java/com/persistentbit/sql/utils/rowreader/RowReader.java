package com.persistentbit.sql.utils.rowreader;

import com.persistentbit.result.Result;

/**
 * Source for reading a row of data.
 * @since 3/10/16
 * @author Peter Muys
 */
@FunctionalInterface
public interface RowReader{

	<T> T readNext(Class<T> cls);
}
