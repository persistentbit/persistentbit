package com.persistentbit.sql.updater.parser;

import com.persistentbit.collections.PList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class ChangeSet{
	private final PList<Change>	changes;

	public ChangeSet(PList<Change> changes) {
		this.changes = changes;
	}

	public PList<ChangeResult> run(UpdateContext context){
		return changes.map(change -> change.run(context));
	}
}
