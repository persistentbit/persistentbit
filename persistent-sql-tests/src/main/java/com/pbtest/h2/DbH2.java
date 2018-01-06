package com.pbtest.h2;

import com.pbtest.h2.expressions.EGenData;
import com.pbtest.h2.tables.TGenData;
import com.pbtest.h2.values.GenData;
import com.persistentbit.sql.dsl.genericdb.DbGeneric;

public class DbH2 extends DbGeneric{


	public static final TGenData genData = new TGenData(_context);

	public static EGenData val(GenData value) {
		return _context.getTypeFactory(EGenData.class).buildVal(value);
	}

	static {
	}
}
