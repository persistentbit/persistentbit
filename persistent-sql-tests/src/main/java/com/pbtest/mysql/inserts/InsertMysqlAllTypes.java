package com.pbtest.mysql.inserts;

import com.pbtest.mysql.tables.TMysqlAllTypes;
import com.pbtest.mysql.values.MysqlAllTypes;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertMysqlAllTypes extends Insert<TMysqlAllTypes, Void>{


	public InsertMysqlAllTypes(ExprContext context, TMysqlAllTypes into, PList<String> columnNames,
							   PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertMysqlAllTypes(ExprContext context, TMysqlAllTypes into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("a_bit", "a_tinyint", "a_tinyint_unsinged", "a_bool");

	public InsertMysqlAllTypes add(@Nullable Boolean aBit, @Nullable PByteList aTinyint,
								   @Nullable PByteList aTinyintUnsinged, @Nullable Boolean aBool) {
		Object[] row = new Object[]{
			aBit
			, aTinyint
			, aTinyintUnsinged
			, aBool
		};
		return new InsertMysqlAllTypes(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertMysqlAllTypes add(MysqlAllTypes value) {
		return add(
			value.getABit(), value.getATinyint(), value.getATinyintUnsinged(), value.getABool()
		);
	}
}
