package com.pbtest.mysql;

import com.pbtest.mysql.expressions.EGenData;
import com.pbtest.mysql.tables.TGenData;
import com.pbtest.mysql.values.GenData;
import com.persistentbit.sql.dsl.genericdb.DbGeneric;

public class DbMy extends DbGeneric{


	public static final TGenData genData = new TGenData(_context);

	public static EGenData val(GenData value) {
		return _context.getTypeFactory(EGenData.class).buildVal(value);
	}

	static {
	}
}
