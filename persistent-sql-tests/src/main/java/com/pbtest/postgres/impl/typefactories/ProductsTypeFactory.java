package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EProducts;
import com.pbtest.postgres.values.Products;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDouble;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class ProductsTypeFactory extends AbstractStructureTypeFactory<EProducts, Products>{

	private class EProductsImpl extends EProducts implements StructTypeImplMixin<EProducts, Products>{


		public EProductsImpl(Iterator<DExpr> iter) {
			super(
				(EInt) iter.next()
				, (EString) iter.next()
				, (EDouble) iter.next()
				, (EInt) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EProducts, Products> getTypeFactory() {
			return ProductsTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EProducts[" + productId + productName + price + groupId + "]";
		}

		@Override
		public EProducts getThis() {
			return this;
		}
	}

	public ProductsTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EProducts, Products>> buildFields() {
		return PList.val(
			createField(EInt.class, "product_id", "productId", v -> v.getProductId(), v -> v.productId)
			, createField(EString.class, "product_name", "productName", v -> v.getProductName(), v -> v.productName)
			, createField(EDouble.class, "price", "price", v -> v.getPrice(), v -> v.price)
			, createField(EInt.class, "group_id", "groupId", v -> v.getGroupId(), v -> v.groupId)
		);
	}

	@Override
	protected Products buildValue(Object[] fieldValues) {
		return new Products(
			(Integer) fieldValues[0]
			, (String) fieldValues[1]
			, (Double) fieldValues[2]
			, (Integer) fieldValues[3]
		);
	}

	@Override
	protected EProductsImpl createExpression(PStream<DExpr> fieldValues) {
		return new EProductsImpl(fieldValues.iterator());
	}

	@Override
	public Class<EProducts> getTypeClass() {
		return EProducts.class;
	}
}
