package com.pbtest.postgres;

import com.pbtest.postgres.expressions.EGenData;
import com.pbtest.postgres.expressions.EProductGroups;
import com.pbtest.postgres.expressions.EProducts;
import com.pbtest.postgres.tables.TGenData;
import com.pbtest.postgres.tables.TProductGroups;
import com.pbtest.postgres.tables.TProducts;
import com.pbtest.postgres.values.GenData;
import com.pbtest.postgres.values.ProductGroups;
import com.pbtest.postgres.values.Products;
import com.persistentbit.sql.dsl.postgres.rt.DbPostgres;

public class DbPg extends DbPostgres{


	public static final TGenData       genData       = new TGenData(_context);
	public static final TProductGroups productGroups = new TProductGroups(_context);
	public static final TProducts      products      = new TProducts(_context);

	public static EGenData val(GenData value) {
		return _context.getTypeFactory(EGenData.class).buildVal(value);
	}

	public static EProductGroups val(ProductGroups value) {
		return _context.getTypeFactory(EProductGroups.class).buildVal(value);
	}

	public static EProducts val(Products value) {
		return _context.getTypeFactory(EProducts.class).buildVal(value);
	}

	static {
	}
}
