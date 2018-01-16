package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EAnimals;
import com.pbtest.postgres.impl.typefactories.AnimalsTypeFactory;
import com.pbtest.postgres.inserts.InsertAnimals;
import com.pbtest.postgres.values.Animals;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TAnimals extends AbstractTable<EAnimals, Animals>{

	private final TableName _tableName = new TableName(null, "pbtest", "animals");
	private final DbWorkP1<Integer, Animals> _selectById;
	private final EAnimals                   _all;
	public final  EInt                       id;
	public final  EString                    type;
	public final  EString                    name;


	private TAnimals(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EAnimals.class, AnimalsTypeFactory.class);
		this._all = context
			.getTypeFactory(EAnimals.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.type = _all.type;
		this.name = _all.name;
		this._selectById = query(p -> q -> {
			Param<EInt> paramid = context.param(EInt.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TAnimals(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EAnimals> getTypeClass() {
		return EAnimals.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TAnimals as(String aliasName) {
		return new TAnimals(context, aliasName);
	}

	@Override
	public EAnimals all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EAnimals, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertAnimals insert() {
		return new InsertAnimals(context, this);
	}

	public DbWork<Integer> insert(@Nullable Integer id, @Nullable String type, @Nullable String name) {
		return insert()
			.add(id, type, name)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<Animals> insert(Animals p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EAnimals val(Animals value) {
		return context.getTypeFactory(EAnimals.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Animals value) {
		EAnimals e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<Animals> selectById(int id) {
		return _selectById.with(id);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(int id) {
		return delete()
			.where(this.id.eq(id));
	}
}
