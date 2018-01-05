package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EPgArrayTest;
import com.mycompany.db.generated.impl.typefactories.PgArrayTestTypeFactory;
import com.mycompany.db.generated.inserts.InsertPgArrayTest;
import com.mycompany.db.generated.values.PgArrayTest;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TPgArrayTest extends AbstractTable<EPgArrayTest, PgArrayTest>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "pg_array_test");
	private final DbWorkP1<Long, PgArrayTest> _selectById;
	private final EPgArrayTest                _all;
	public final  ELong                       id;
	public final  EArray<EString, String>     strings;
	public final  EArray<EInt, Integer>       ints;


	private TPgArrayTest(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EPgArrayTest.class, PgArrayTestTypeFactory.class);
		this._all = context
			.getTypeFactory(EPgArrayTest.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.strings = _all.strings;
		this.ints = _all.ints;
		this._selectById = query(p -> q -> {
			Param<ELong> paramid = context.param(ELong.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TPgArrayTest(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EPgArrayTest> getTypeClass() {
		return EPgArrayTest.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TPgArrayTest as(String aliasName) {
		return new TPgArrayTest(context, aliasName);
	}

	@Override
	public EPgArrayTest all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EPgArrayTest, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertPgArrayTest insert() {
		return new InsertPgArrayTest(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long id, @Nullable ImmutableArray<String> strings,
								  @Nullable ImmutableArray<Integer> ints) {
		return insert()
			.add(id, strings, ints)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<PgArrayTest> insert(PgArrayTest p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EPgArrayTest val(PgArrayTest value) {
		return context.getTypeFactory(EPgArrayTest.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(PgArrayTest value) {
		EPgArrayTest e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<PgArrayTest> selectById(long id) {
		return _selectById.with(id);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long id) {
		return delete()
			.where(this.id.eq(id));
	}
}
