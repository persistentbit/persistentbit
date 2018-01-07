package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EProducts;
import com.pbtest.postgres.impl.typefactories.ProductsTypeFactory;
import com.pbtest.postgres.inserts.InsertProducts;
import com.pbtest.postgres.values.Products;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EDouble;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
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

public class TProducts extends AbstractTable<EProducts, Products>{

	private final TableName _tableName = new TableName("postgres", "pbtest", "products");
	private final DbWorkP1<Integer, Products> _selectById;
	private final EProducts                   _all;
	public final  EInt                        productId;
	public final  EString                     productName;
	public final  EDouble                     price;
	public final  EInt                        groupId;


	private TProducts(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EProducts.class, ProductsTypeFactory.class);
		this._all = context
			.getTypeFactory(EProducts.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.productId = _all.productId;
		this.productName = _all.productName;
		this.price = _all.price;
		this.groupId = _all.groupId;
		this._selectById = query(p -> q -> {
			Param<EInt> paramproductId = context.param(EInt.class, "productId");
			return q
				.where(this.productId.eq(paramproductId.getExpr()))
				.selection(all())
				.one(paramproductId);
		});
	}

	public TProducts(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EProducts> getTypeClass() {
		return EProducts.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TProducts as(String aliasName) {
		return new TProducts(context, aliasName);
	}

	@Override
	public EProducts all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EProducts, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertProducts insert() {
		return new InsertProducts(context, this);
	}

	public DbWork<Integer> insert(@Nullable Integer productId, @Nullable String productName, @Nullable Double price,
								  @Nullable Integer groupId) {
		return insert()
			.add(productId, productName, price, groupId)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<Products> insert(Products p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EProducts val(Products value) {
		return context.getTypeFactory(EProducts.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Products value) {
		EProducts e = val(value);
		return update()
			.set(all(), e)
			.where(this.productId.eq(e.productId));
	}

	public DbWork<Products> selectById(int productId) {
		return _selectById.with(productId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(int productId) {
		return delete()
			.where(this.productId.eq(productId));
	}
}
