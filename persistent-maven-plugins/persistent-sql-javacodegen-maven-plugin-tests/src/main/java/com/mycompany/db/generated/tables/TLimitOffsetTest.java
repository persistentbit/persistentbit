package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.ELimitOffsetTest;
import com.mycompany.db.generated.impl.typefactories.LimitOffsetTestTypeFactory;
import com.mycompany.db.generated.inserts.InsertLimitOffsetTest;
import com.mycompany.db.generated.values.LimitOffsetTest;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TLimitOffsetTest extends AbstractTable<ELimitOffsetTest, LimitOffsetTest>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "limit_offset_test");
	private final ELimitOffsetTest _all;
	public final  EInt             id;
	public final  ELong            value;


	private TLimitOffsetTest(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(ELimitOffsetTest.class, LimitOffsetTestTypeFactory.class);
		this._all = context
			.getTypeFactory(ELimitOffsetTest.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.value = _all.value;
	}

	public TLimitOffsetTest(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<ELimitOffsetTest> getTypeClass() {
		return ELimitOffsetTest.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TLimitOffsetTest as(String aliasName) {
		return new TLimitOffsetTest(context, aliasName);
	}
	@Override
	public ELimitOffsetTest all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ELimitOffsetTest, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertLimitOffsetTest insert() {
		return new InsertLimitOffsetTest(context, this);
	}

	public DbWork<Integer> insert(@Nullable Integer id, @Nullable Long value) {
		return insert()
			.add(id, value)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<LimitOffsetTest> insert(LimitOffsetTest p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public ELimitOffsetTest val(LimitOffsetTest value) {
		return context.getTypeFactory(ELimitOffsetTest.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public Delete delete() {
		return new Delete(context, this); 
	}
}
