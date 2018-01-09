package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.function.Function;

public class Products{

	private final int    productId;
	private final String productName;
	private final double price;
	private final int    groupId;
	
	
	@Generated
	public Products(int productId, String productName, double price, int groupId) {
		this.productId = Objects.requireNonNull(productId, "productId can not be null");
		this.productName = Objects.requireNonNull(productName, "productName can not be null");
		this.price = Objects.requireNonNull(price, "price can not be null");
		this.groupId = Objects.requireNonNull(groupId, "groupId can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4>{

		private int    productId;
		private String productName;
		private double price;
		private int    groupId;


		public Builder<SET, _T2, _T3, _T4> setProductId(int productId) {
			this.productId = productId;
			return (Builder<SET, _T2, _T3, _T4>) this;
		}

		public Builder<_T1, SET, _T3, _T4> setProductName(String productName) {
			this.productName = productName;
			return (Builder<_T1, SET, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, SET, _T4> setPrice(double price) {
			this.price = price;
			return (Builder<_T1, _T2, SET, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, SET> setGroupId(int groupId) {
			this.groupId = groupId;
			return (Builder<_T1, _T2, _T3, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #productId}.<br>
	 * @return {@link #productId}
	 */
	@Generated
	public int getProductId() {
		return this.productId;
	}
	/**
	 * Create a copy of this Products object with a new value for field {@link #productId}.<br>
	 * @param productId The new value for field {@link #productId}
	 * @return A new instance of {@link Products}
	 */
	@Generated
	public Products withProductId(int productId) {
		return new Products(productId, productName, price, groupId);
	}
	/**
	 * Get the value of field {@link #productName}.<br>
	 * @return {@link #productName}
	 */
	@Generated
	public String getProductName() {
		return this.productName;
	}
	/**
	 * Create a copy of this Products object with a new value for field {@link #productName}.<br>
	 * @param productName The new value for field {@link #productName}
	 * @return A new instance of {@link Products}
	 */
	@Generated
	public Products withProductName(String productName) {
		return new Products(productId, productName, price, groupId);
	}
	/**
	 * Get the value of field {@link #price}.<br>
	 * @return {@link #price}
	 */
	@Generated
	public double getPrice() {
		return this.price;
	}
	/**
	 * Create a copy of this Products object with a new value for field {@link #price}.<br>
	 * @param price The new value for field {@link #price}
	 * @return A new instance of {@link Products}
	 */
	@Generated
	public Products withPrice(double price) {
		return new Products(productId, productName, price, groupId);
	}
	/**
	 * Get the value of field {@link #groupId}.<br>
	 * @return {@link #groupId}
	 */
	@Generated
	public int getGroupId() {
		return this.groupId;
	}
	/**
	 * Create a copy of this Products object with a new value for field {@link #groupId}.<br>
	 * @param groupId The new value for field {@link #groupId}
	 * @return A new instance of {@link Products}
	 */
	@Generated
	public Products withGroupId(int groupId) {
		return new Products(productId, productName, price, groupId);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Products == false) return false;
		Products obj = (Products) o;
		if(productId != obj.productId) return false;
		if(!productName.equals(obj.productName)) return false;
		if(Double.compare(price, obj.price) != 0) return false;
		if(price != obj.price) return false;
		if(groupId != obj.groupId) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int  result;
		long temp;
		result = this.productId;
		result = 31 * result + (this.productName != null ? this.productName.hashCode() : 0);
		temp = Double.doubleToLongBits(this.price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + this.groupId;
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "Products[" +
			"productId=" + productId +
			", productName=" + (productName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(productName), 32, "...") + '\"') +
			", price=" + price +
			", groupId=" + groupId + 
			']';
	}
	@Generated
	public Products updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setProductId(this.productId);
		b.setProductName(this.productName);
		b.setPrice(this.price);
		b.setGroupId(this.groupId);
		b = updater.apply(b);
		return new Products(b.productId, b.productName, b.price, b.groupId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Products build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Products(b.productId, b.productName, b.price, b.groupId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Products> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new Products(b.productId, b.productName, b.price, b.groupId));
	}
}
