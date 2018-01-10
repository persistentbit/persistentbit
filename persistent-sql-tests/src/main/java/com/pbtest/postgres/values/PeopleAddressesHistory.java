package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class PeopleAddressesHistory{

	private final long          personId;
	private final String        addressRelationCode;
	private final LocalDate     startDate;
	@Nullable
	private final LocalDateTime endDate;
	private final long          addressId;
	
	
	@Generated
	public PeopleAddressesHistory(long personId, String addressRelationCode, LocalDate startDate,
								  @Nullable LocalDateTime endDate, long addressId) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.addressRelationCode = Objects.requireNonNull(addressRelationCode, "addressRelationCode can not be null");
		this.startDate = Objects.requireNonNull(startDate, "startDate can not be null");
		this.endDate = endDate;
		this.addressId = Objects.requireNonNull(addressId, "addressId can not be null");
	}
	@Generated
	public PeopleAddressesHistory(long personId, String addressRelationCode, LocalDate startDate, long addressId) {
		this(personId, addressRelationCode, startDate, null, addressId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4>{

		private long          personId;
		private String        addressRelationCode;
		private LocalDate     startDate;
		private LocalDateTime endDate;
		private long          addressId;


		public Builder<SET, _T2, _T3, _T4> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3, _T4>) this;
		}

		public Builder<_T1, SET, _T3, _T4> setAddressRelationCode(String addressRelationCode) {
			this.addressRelationCode = addressRelationCode;
			return (Builder<_T1, SET, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, SET, _T4> setStartDate(LocalDate startDate) {
			this.startDate = startDate;
			return (Builder<_T1, _T2, SET, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, _T4> setEndDate(@Nullable LocalDateTime endDate) {
			this.endDate = endDate;
			return this;
		}

		public Builder<_T1, _T2, _T3, SET> setAddressId(long addressId) {
			this.addressId = addressId;
			return (Builder<_T1, _T2, _T3, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #personId}.<br>
	 * @return {@link #personId}
	 */
	@Generated
	public long getPersonId() {
		return this.personId;
	}
	/**
	 * Create a copy of this PeopleAddressesHistory object with a new value for field {@link #personId}.<br>
	 * @param personId The new value for field {@link #personId}
	 * @return A new instance of {@link PeopleAddressesHistory}
	 */
	@Generated
	public PeopleAddressesHistory withPersonId(long personId) {
		return new PeopleAddressesHistory(personId, addressRelationCode, startDate, endDate, addressId);
	}
	/**
	 * Get the value of field {@link #addressRelationCode}.<br>
	 * @return {@link #addressRelationCode}
	 */
	@Generated
	public String getAddressRelationCode() {
		return this.addressRelationCode;
	}
	/**
	 * Create a copy of this PeopleAddressesHistory object with a new value for field {@link #addressRelationCode}.<br>
	 * @param addressRelationCode The new value for field {@link #addressRelationCode}
	 * @return A new instance of {@link PeopleAddressesHistory}
	 */
	@Generated
	public PeopleAddressesHistory withAddressRelationCode(String addressRelationCode) {
		return new PeopleAddressesHistory(personId, addressRelationCode, startDate, endDate, addressId);
	}
	/**
	 * Get the value of field {@link #startDate}.<br>
	 * @return {@link #startDate}
	 */
	@Generated
	public LocalDate getStartDate() {
		return this.startDate;
	}
	/**
	 * Create a copy of this PeopleAddressesHistory object with a new value for field {@link #startDate}.<br>
	 * @param startDate The new value for field {@link #startDate}
	 * @return A new instance of {@link PeopleAddressesHistory}
	 */
	@Generated
	public PeopleAddressesHistory withStartDate(LocalDate startDate) {
		return new PeopleAddressesHistory(personId, addressRelationCode, startDate, endDate, addressId);
	}
	/**
	 * Get the value of field {@link #endDate}.<br>
	 * @return {@link #endDate}
	 */
	@Generated
	public Optional<LocalDateTime> getEndDate() {
		return Optional.ofNullable(this.endDate);
	}
	/**
	 * Create a copy of this PeopleAddressesHistory object with a new value for field {@link #endDate}.<br>
	 * @param endDate The new value for field {@link #endDate}
	 * @return A new instance of {@link PeopleAddressesHistory}
	 */
	@Generated
	public PeopleAddressesHistory withEndDate(@Nullable LocalDateTime endDate) {
		return new PeopleAddressesHistory(personId, addressRelationCode, startDate, endDate, addressId);
	}
	/**
	 * Get the value of field {@link #addressId}.<br>
	 * @return {@link #addressId}
	 */
	@Generated
	public long getAddressId() {
		return this.addressId;
	}
	/**
	 * Create a copy of this PeopleAddressesHistory object with a new value for field {@link #addressId}.<br>
	 * @param addressId The new value for field {@link #addressId}
	 * @return A new instance of {@link PeopleAddressesHistory}
	 */
	@Generated
	public PeopleAddressesHistory withAddressId(long addressId) {
		return new PeopleAddressesHistory(personId, addressRelationCode, startDate, endDate, addressId);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PeopleAddressesHistory == false) return false;
		PeopleAddressesHistory obj = (PeopleAddressesHistory) o;
		if(personId != obj.personId) return false;
		if(!addressRelationCode.equals(obj.addressRelationCode)) return false;
		if(!startDate.equals(obj.startDate)) return false;
		if(endDate != null ? !endDate.equals(obj.endDate) : obj.endDate != null) return false;
		if(addressId != obj.addressId) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId >>> 32));
		result = 31 * result + (this.addressRelationCode != null ? this.addressRelationCode.hashCode() : 0);
		result = 31 * result + (this.startDate != null ? this.startDate.hashCode() : 0);
		result = 31 * result + (this.endDate != null ? this.endDate.hashCode() : 0);
		result = 31 * result + (int) (this.addressId ^ (this.addressId >>> 32));
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "PeopleAddressesHistory[" +
			"personId=" + personId +
			", addressRelationCode=" + (addressRelationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(addressRelationCode), 32, "...") + '\"') +
			", startDate=" + startDate + 
			", endDate=" + endDate + 
			", addressId=" + addressId + 
			']';
	}
	@Generated
	public PeopleAddressesHistory updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setAddressRelationCode(this.addressRelationCode);
		b.setStartDate(this.startDate);
		b.setEndDate(this.endDate);
		b.setAddressId(this.addressId);
		b = updater.apply(b);
		return new PeopleAddressesHistory(b.personId, b.addressRelationCode, b.startDate, b.endDate, b.addressId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static PeopleAddressesHistory build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PeopleAddressesHistory(b.personId, b.addressRelationCode, b.startDate, b.endDate, b.addressId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PeopleAddressesHistory> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PeopleAddressesHistory(b.personId, b.addressRelationCode, b.startDate, b.endDate, b.addressId));
	}
}
