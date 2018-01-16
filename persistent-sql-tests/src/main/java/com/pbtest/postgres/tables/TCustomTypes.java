package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.ECustomTypes;
import com.pbtest.postgres.impl.typefactories.CustomTypesTypeFactory;
import com.pbtest.postgres.inserts.InsertCustomTypes;
import com.pbtest.postgres.values.CustomTypes;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EObject;
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

public class TCustomTypes extends AbstractTable<ECustomTypes, CustomTypes>{

	private final TableName _tableName = new TableName(null, "pbtest", "custom_types");
	private final DbWorkP1<Object, CustomTypes> _selectById;
	private final ECustomTypes                  _all;
	public final  EObject                       tUuid;
	public final  EObject                       tInterval;


	private TCustomTypes(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(ECustomTypes.class, CustomTypesTypeFactory.class);
		this._all = context
			.getTypeFactory(ECustomTypes.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.tUuid = _all.tUuid;
		this.tInterval = _all.tInterval;
		this._selectById = query(p -> q -> {
			Param<EObject> paramtUuid = context.param(EObject.class, "tUuid");
			return q
				.where(this.tUuid.eq(paramtUuid.getExpr()))
				.selection(all())
				.one(paramtUuid);
		});
	}

	public TCustomTypes(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<ECustomTypes> getTypeClass() {
		return ECustomTypes.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TCustomTypes as(String aliasName) {
		return new TCustomTypes(context, aliasName);
	}
	@Override
	public ECustomTypes all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ECustomTypes, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertCustomTypes insert() {
		return new InsertCustomTypes(context, this);
	}

	public DbWork<Object> insert(@Nullable Object tUuid, @Nullable Object tInterval) {
		return insert()
			.add(tUuid, tInterval)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<CustomTypes> insert(CustomTypes p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public ECustomTypes val(CustomTypes value) {
		return context.getTypeFactory(ECustomTypes.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(CustomTypes value) {
		ECustomTypes e = val(value);
		return update()
			.set(all(), e)
			.where(this.tUuid.eq(e.tUuid));
	}

	public DbWork<CustomTypes> selectById(Object tUuid) {
		return _selectById.with(tUuid);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(Object tUuid) {
		return delete()
			.where(this.tUuid.eq(tUuid));
	}
}
