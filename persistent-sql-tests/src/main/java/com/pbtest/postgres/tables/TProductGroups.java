package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EProductGroups;
import com.pbtest.postgres.impl.typefactories.ProductGroupsTypeFactory;
import com.pbtest.postgres.inserts.InsertProductGroups;
import com.pbtest.postgres.values.ProductGroups;
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

public class TProductGroups extends AbstractTable<EProductGroups, ProductGroups>{

	private final TableName _tableName = new TableName(null, "pbtest", "product_groups");
	private final DbWorkP1<Integer, ProductGroups> _selectById;
	private final EProductGroups                   _all;
	public final  EInt                             groupId;
	public final  EString                          groupName;


	private TProductGroups(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EProductGroups.class, ProductGroupsTypeFactory.class);
		this._all = context
			.getTypeFactory(EProductGroups.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.groupId = _all.groupId;
		this.groupName = _all.groupName;
		this._selectById = query(p -> q -> {
			Param<EInt> paramgroupId = context.param(EInt.class, "groupId");
			return q
				.where(this.groupId.eq(paramgroupId.getExpr()))
				.selection(all())
				.one(paramgroupId);
		});
	}

	public TProductGroups(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EProductGroups> getTypeClass() {
		return EProductGroups.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TProductGroups as(String aliasName) {
		return new TProductGroups(context, aliasName);
	}
	@Override
	public EProductGroups all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EProductGroups, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertProductGroups insert() {
		return new InsertProductGroups(context, this);
	}

	public DbWork<Integer> insert(@Nullable Integer groupId, @Nullable String groupName) {
		return insert()
			.add(groupId, groupName)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<ProductGroups> insert(ProductGroups p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EProductGroups val(ProductGroups value) {
		return context.getTypeFactory(EProductGroups.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(ProductGroups value) {
		EProductGroups e = val(value);
		return update()
			.set(all(), e)
			.where(this.groupId.eq(e.groupId));
	}

	public DbWork<ProductGroups> selectById(int groupId) {
		return _selectById.with(groupId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(int groupId) {
		return delete()
			.where(this.groupId.eq(groupId));
	}
}
