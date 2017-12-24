package com.persistentbit.sql.dsl.statements.insert;

import com.persistentbit.code.annotations.Nullable;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */

public class InsertResult<KEY>{

	private final int updateCount;
	@Nullable
	private final KEY autoGenKey;

	public InsertResult(int updateCount, @Nullable KEY autoGenKey) {
		this.updateCount = updateCount;
		this.autoGenKey = autoGenKey;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public KEY getAutoGenKey() {
		return autoGenKey;
	}

	@Override
	public String toString() {
		return "InsertResult{" +
			"updateCount=" + updateCount +
			", autoGenKey=" + autoGenKey +
			'}';
	}
}
