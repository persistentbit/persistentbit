package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TProducts;
import com.pbtest.postgres.values.Products;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertProducts extends Insert<TProducts, Integer>{


	public InsertProducts(ExprContext context, TProducts into, PList<String> columnNames, PSet<String> withDefaults,
						  String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertProducts(ExprContext context, TProducts into) {
		this(context, into, columnNames, PSet.empty(), "product_id", PList.empty());
	}

	private static final PList<String> columnNames = PList.val("product_id", "product_name", "price", "group_id");

	public InsertProducts add(@Nullable Integer productId, @Nullable String productName, @Nullable Double price,
							  @Nullable Integer groupId) {
		Object[] row = new Object[]{
			productId
			, productName
			, price
			, groupId
		};
		return new InsertProducts(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertProducts add(Products value) {
		return add(
			value.getProductId(), value.getProductName(), value.getPrice(), value.getGroupId()
		);
	}
}
