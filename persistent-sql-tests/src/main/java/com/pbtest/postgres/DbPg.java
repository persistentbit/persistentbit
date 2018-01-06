package com.pbtest.postgres;

import com.pbtest.postgres.expressions.EGenData;
import com.pbtest.postgres.tables.TGenData;
import com.pbtest.postgres.values.GenData;
import com.persistentbit.sql.dsl.postgres.rt.DbPostgres;

public class DbPg extends DbPostgres{


	public static final TGenData genData = new TGenData(_context);

	public static EGenData val(GenData value) {
		return _context.getTypeFactory(EGenData.class).buildVal(value);
	}

	static {
	}
}
