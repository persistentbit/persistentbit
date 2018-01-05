package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.function.Function;

public class Invoice{

	private final long   id;
	private final String invoiceNummer;
	private final long   fromCompanyId;
	private final long   toCompanyId;


	@Generated
	public Invoice(long id, String invoiceNummer, long fromCompanyId, long toCompanyId) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.invoiceNummer = Objects.requireNonNull(invoiceNummer, "invoiceNummer can not be null");
		this.fromCompanyId = Objects.requireNonNull(fromCompanyId, "fromCompanyId can not be null");
		this.toCompanyId = Objects.requireNonNull(toCompanyId, "toCompanyId can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4>{

		private long   id;
		private String invoiceNummer;
		private long   fromCompanyId;
		private long   toCompanyId;


		public Builder<SET, _T2, _T3, _T4> setId(long id) {
			this.id = id;
			return (Builder<SET, _T2, _T3, _T4>) this;
		}

		public Builder<_T1, SET, _T3, _T4> setInvoiceNummer(String invoiceNummer) {
			this.invoiceNummer = invoiceNummer;
			return (Builder<_T1, SET, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, SET, _T4> setFromCompanyId(long fromCompanyId) {
			this.fromCompanyId = fromCompanyId;
			return (Builder<_T1, _T2, SET, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, SET> setToCompanyId(long toCompanyId) {
			this.toCompanyId = toCompanyId;
			return (Builder<_T1, _T2, _T3, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #id}.<br>
	 *
	 * @return {@link #id}
	 */
	@Generated
	public long getId() {
		return this.id;
	}

	/**
	 * Create a copy of this Invoice object with a new value for field {@link #id}.<br>
	 *
	 * @param id The new value for field {@link #id}
	 *
	 * @return A new instance of {@link Invoice}
	 */
	@Generated
	public Invoice withId(long id) {
		return new Invoice(id, invoiceNummer, fromCompanyId, toCompanyId);
	}

	/**
	 * Get the value of field {@link #invoiceNummer}.<br>
	 *
	 * @return {@link #invoiceNummer}
	 */
	@Generated
	public String getInvoiceNummer() {
		return this.invoiceNummer;
	}

	/**
	 * Create a copy of this Invoice object with a new value for field {@link #invoiceNummer}.<br>
	 *
	 * @param invoiceNummer The new value for field {@link #invoiceNummer}
	 *
	 * @return A new instance of {@link Invoice}
	 */
	@Generated
	public Invoice withInvoiceNummer(String invoiceNummer) {
		return new Invoice(id, invoiceNummer, fromCompanyId, toCompanyId);
	}

	/**
	 * Get the value of field {@link #fromCompanyId}.<br>
	 *
	 * @return {@link #fromCompanyId}
	 */
	@Generated
	public long getFromCompanyId() {
		return this.fromCompanyId;
	}

	/**
	 * Create a copy of this Invoice object with a new value for field {@link #fromCompanyId}.<br>
	 *
	 * @param fromCompanyId The new value for field {@link #fromCompanyId}
	 *
	 * @return A new instance of {@link Invoice}
	 */
	@Generated
	public Invoice withFromCompanyId(long fromCompanyId) {
		return new Invoice(id, invoiceNummer, fromCompanyId, toCompanyId);
	}

	/**
	 * Get the value of field {@link #toCompanyId}.<br>
	 *
	 * @return {@link #toCompanyId}
	 */
	@Generated
	public long getToCompanyId() {
		return this.toCompanyId;
	}

	/**
	 * Create a copy of this Invoice object with a new value for field {@link #toCompanyId}.<br>
	 *
	 * @param toCompanyId The new value for field {@link #toCompanyId}
	 *
	 * @return A new instance of {@link Invoice}
	 */
	@Generated
	public Invoice withToCompanyId(long toCompanyId) {
		return new Invoice(id, invoiceNummer, fromCompanyId, toCompanyId);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Invoice == false) return false;
		Invoice obj = (Invoice) o;
		if(id != obj.id) return false;
		if(!invoiceNummer.equals(obj.invoiceNummer)) return false;
		if(fromCompanyId != obj.fromCompanyId) return false;
		if(toCompanyId != obj.toCompanyId) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.id ^ (this.id >>> 32));
		result = 31 * result + (this.invoiceNummer != null ? this.invoiceNummer.hashCode() : 0);
		result = 31 * result + (int) (this.fromCompanyId ^ (this.fromCompanyId >>> 32));
		result = 31 * result + (int) (this.toCompanyId ^ (this.toCompanyId >>> 32));
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Invoice[" +
			"id=" + id +
			", invoiceNummer=" + (invoiceNummer == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(invoiceNummer), 32, "...") + '\"') +
			", fromCompanyId=" + fromCompanyId +
			", toCompanyId=" + toCompanyId +
			']';
	}

	@Generated
	public Invoice updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setInvoiceNummer(this.invoiceNummer);
		b.setFromCompanyId(this.fromCompanyId);
		b.setToCompanyId(this.toCompanyId);
		b = updater.apply(b);
		return new Invoice(b.id, b.invoiceNummer, b.fromCompanyId, b.toCompanyId);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Invoice build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Invoice(b.id, b.invoiceNummer, b.fromCompanyId, b.toCompanyId);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Invoice> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new Invoice(b.id, b.invoiceNummer, b.fromCompanyId, b.toCompanyId));
	}
}
