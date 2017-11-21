package com.persistentbit.sql.updater.impl;


import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.updater.DbBuilder;
import com.persistentbit.sql.work.DbWork;

/**
 * @author Peter Muys
 * @since 18/06/16
 */
public class DbBuilderGroup implements DbBuilder{

	private final PList<DbBuilder> builders;

	public DbBuilderGroup(DbBuilder... builders) {
		this.builders = PStream.from(builders).plist();
	}


	@Override
	public DbWork<OK> buildOrUpdate() {
		return DbWork.sequence(builders.map(DbBuilder::buildOrUpdate));
	}

	@Override
	public DbWork<OK> dropAll() {
		return DbWork.sequence(builders.map(DbBuilder::dropAll));
	}

	@Override
	public DbWork<Boolean> hasUpdatesThatAreDone() {
		return DbWork.function().code(trans -> con -> log ->{
			boolean ok = true;
			for(DbBuilder b : builders) {
				Result<Boolean> itemOk = b.hasUpdatesThatAreDone().run(trans);
				if(itemOk.isError()) {
					return itemOk;
				}
				ok = ok && itemOk.orElseThrow();
				log.add(itemOk);
			}
			return Result.success(ok);
		});
	}
}
