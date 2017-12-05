package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.SuppressWarnings;
import java.util.Optional;
import com.persistentbit.sql.dsl.annotations.DbColumnName;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import java.util.function.Function;
import com.persistentbit.string.UString;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.result.Result;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.SET;
import java.lang.String;
import com.persistentbit.javacodegen.annotations.NoWith;

public class InvoiceLine {
	@Nullable
	@DbColumnName("id")
	private  final	Long	id;
	@DbColumnName("invoice_id")
	private  final	long	invoiceId;
	@Nullable
	@DbColumnName("product")
	private  final	String	product;
	
	
	@Generated
	public InvoiceLine(@Nullable Long id, long invoiceId, @Nullable String product){
			this.id = id;
			this.invoiceId = Objects.requireNonNull(invoiceId, "invoiceId can not be null");
			this.product = product;
	}
	@Generated
	public InvoiceLine(long invoiceId){
			this(null, invoiceId, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1> {
		private	Long	id;
		private	long	invoiceId;
		private	String	product;
		
		
		public  Builder<_T1>	setId(@Nullable Long id){
			this.id	=	id;
			return this;
		}
		public  Builder<SET>	setInvoiceId(long invoiceId){
			this.invoiceId	=	invoiceId;
			return (Builder<SET>)this;
		}
		public  Builder<_T1>	setProduct(@Nullable String product){
			this.product	=	product;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #id}.<br>
	 * @return {@link #id}
	 */
	@Generated
	public  Optional<Long>	getId(){
		return Optional.ofNullable(this.id);
	}
	/**
	 * Create a copy of this InvoiceLine object with a new value for field {@link #id}.<br>
	 * @param id The new value for field {@link #id}
	 * @return A new instance of {@link InvoiceLine}
	 */
	@Generated
	public  InvoiceLine	withId(@Nullable Long id){
		return new InvoiceLine(id, invoiceId, product);
	}
	/**
	 * Get the value of field {@link #invoiceId}.<br>
	 * @return {@link #invoiceId}
	 */
	@Generated
	public  long	getInvoiceId(){
		return this.invoiceId;
	}
	/**
	 * Create a copy of this InvoiceLine object with a new value for field {@link #invoiceId}.<br>
	 * @param invoiceId The new value for field {@link #invoiceId}
	 * @return A new instance of {@link InvoiceLine}
	 */
	@Generated
	public  InvoiceLine	withInvoiceId(long invoiceId){
		return new InvoiceLine(id, invoiceId, product);
	}
	/**
	 * Get the value of field {@link #product}.<br>
	 * @return {@link #product}
	 */
	@Generated
	public  Optional<String>	getProduct(){
		return Optional.ofNullable(this.product);
	}
	/**
	 * Create a copy of this InvoiceLine object with a new value for field {@link #product}.<br>
	 * @param product The new value for field {@link #product}
	 * @return A new instance of {@link InvoiceLine}
	 */
	@Generated
	public  InvoiceLine	withProduct(@Nullable String product){
		return new InvoiceLine(id, invoiceId, product);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof InvoiceLine == false) return false;
		InvoiceLine obj = (InvoiceLine)o;
		if(id != null ? !id.equals(obj.id) : obj.id!= null) return false;
		if(invoiceId!= obj.invoiceId) return false;
		if(product != null ? !product.equals(obj.product) : obj.product!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.id != null ? this.id.hashCode() : 0);
		result = 31 * result + (int) (this.invoiceId ^ (this.invoiceId>>> 32));
		result = 31 * result + (this.product != null ? this.product.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "InvoiceLine[" + 
			"id=" + id + 
			", invoiceId=" + invoiceId + 
			", product=" + (product == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(product),32,"...") + '\"') +
			']';
	}
	@Generated
	public  InvoiceLine	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setId(this.id);
		b.setInvoiceId(this.invoiceId);
		b.setProduct(this.product);
		b = updater.apply(b);
		return new InvoiceLine(b.id, b.invoiceId, b.product);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static InvoiceLine	build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new InvoiceLine(b.id, b.invoiceId, b.product);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<InvoiceLine>	buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new InvoiceLine(b.id, b.invoiceId, b.product));
	}
}
