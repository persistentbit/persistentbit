package com.pbtest.mysql.tables;

import com.pbtest.mysql.expressions.EMysqlAllTypes;
import com.pbtest.mysql.impl.typefactories.MysqlAllTypesTypeFactory;
import com.pbtest.mysql.inserts.InsertMysqlAllTypes;
import com.pbtest.mysql.values.MysqlAllTypes;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EByte;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TMysqlAllTypes extends AbstractTable<EMysqlAllTypes, MysqlAllTypes>{

	private final TableName _tableName = new TableName("pbtest", null, "mysql_all_types");
	private final EMysqlAllTypes _all;
	public final  EBool          aBit;
	public final  EByte          aTinyint;
	public final  EByte          aTinyintUnsinged;
	public final  EBool          aBool;


	private TMysqlAllTypes(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EMysqlAllTypes.class, MysqlAllTypesTypeFactory.class);
		this._all = context
			.getTypeFactory(EMysqlAllTypes.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.aBit = _all.aBit;
		this.aTinyint = _all.aTinyint;
		this.aTinyintUnsinged = _all.aTinyintUnsinged;
		this.aBool = _all.aBool;
	}

	public TMysqlAllTypes(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EMysqlAllTypes> getTypeClass() {
		return EMysqlAllTypes.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TMysqlAllTypes as(String aliasName) {
		return new TMysqlAllTypes(context, aliasName);
	}
	@Override
	public EMysqlAllTypes all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EMysqlAllTypes, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertMysqlAllTypes insert() {
		return new InsertMysqlAllTypes(context, this);
	}

	public DbWork<Object> insert(@Nullable Boolean aBit, @Nullable Byte aTinyint, @Nullable Byte aTinyintUnsinged,
								 @Nullable Boolean aBool) {
		return insert()
			.add(aBit, aTinyint, aTinyintUnsinged, aBool)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<MysqlAllTypes> insert(MysqlAllTypes p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EMysqlAllTypes val(MysqlAllTypes value) {
		return context.getTypeFactory(EMysqlAllTypes.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public Delete delete() {
		return new Delete(context, this); 
	}
}
