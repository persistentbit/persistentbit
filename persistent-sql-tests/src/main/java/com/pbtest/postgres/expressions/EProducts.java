package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.Products;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDouble;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EProducts implements DExpr<Products>{

	public final EInt    productId;
	public final EString productName;
	public final EDouble price;
	public final EInt    groupId;


	public EProducts(EInt productId, EString productName, EDouble price, EInt groupId) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.groupId = groupId;
	}
}
