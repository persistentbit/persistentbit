package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class PeopleAddressesHistory{

	private final long          personId;
	private final String        addressRelationCode;
	private final long          addressId;
	private final LocalDateTime startTime;
	@Nullable
	private final LocalDateTime endTime;
	
	
	@Generated
	public PeopleAddressesHistory(long personId, String addressRelationCode, long addressId, LocalDateTime startTime,
								  @Nullable LocalDateTime endTime) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.addressRelationCode = Objects.requireNonNull(addressRelationCode, "addressRelationCode can not be null");
		this.addressId = Objects.requireNonNull(addressId, "addressId can not be null");
		this.startTime = Objects.requireNonNull(startTime, "startTime can not be null");
		this.endTime = endTime;
	}
	@Generated
	public PeopleAddressesHistory(long personId, String addressRelationCode, long addressId, LocalDateTime startTime) {
		this(personId, addressRelationCode, addressId, startTime, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4>{

		private long          personId;
		private String        addressRelationCode;
		private long          addressId;
		private LocalDateTime startTime;
		private LocalDateTime endTime;


		public Builder<SET, _T2, _T3, _T4> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3, _T4>) this;
		}

		public Builder<_T1, SET, _T3, _T4> setAddressRelationCode(String addressRelationCode) {
			this.addressRelationCode = addressRelationCode;
			return (Builder<_T1, SET, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, SET, _T4> setAddressId(long addressId) {
			this.addressId = addressId;
			return (Builder<_T1, _T2, SET, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, SET> setStartTime(LocalDateTime startTime) {
			this.startTime = startTime;
			return (Builder<_T1, _T2, _T3, SET>) this;
		}

		public Builder<_T1, _T2, _T3, _T4> setEndTime(@Nullable LocalDateTime endTime) {
			this.endTime = endTime;
			return this;
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
		return new PeopleAddressesHistory(personId, addressRelationCode, addressId, startTime, endTime);
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
		return new PeopleAddressesHistory(personId, addressRelationCode, addressId, startTime, endTime);
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
		return new PeopleAddressesHistory(personId, addressRelationCode, addressId, startTime, endTime);
	}
	/**
	 * Get the value of field {@link #startTime}.<br>
	 * @return {@link #startTime}
	 */
	@Generated
	public LocalDateTime getStartTime() {
		return this.startTime;
	}
	/**
	 * Create a copy of this PeopleAddressesHistory object with a new value for field {@link #startTime}.<br>
	 * @param startTime The new value for field {@link #startTime}
	 * @return A new instance of {@link PeopleAddressesHistory}
	 */
	@Generated
	public PeopleAddressesHistory withStartTime(LocalDateTime startTime) {
		return new PeopleAddressesHistory(personId, addressRelationCode, addressId, startTime, endTime);
	}
	/**
	 * Get the value of field {@link #endTime}.<br>
	 * @return {@link #endTime}
	 */
	@Generated
	public Optional<LocalDateTime> getEndTime() {
		return Optional.ofNullable(this.endTime);
	}
	/**
	 * Create a copy of this PeopleAddressesHistory object with a new value for field {@link #endTime}.<br>
	 * @param endTime The new value for field {@link #endTime}
	 * @return A new instance of {@link PeopleAddressesHistory}
	 */
	@Generated
	public PeopleAddressesHistory withEndTime(@Nullable LocalDateTime endTime) {
		return new PeopleAddressesHistory(personId, addressRelationCode, addressId, startTime, endTime);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof PeopleAddressesHistory == false) return false;
		PeopleAddressesHistory obj = (PeopleAddressesHistory)o;
		if(personId!= obj.personId) return false;
		if(!addressRelationCode.equals(obj.addressRelationCode)) return false;
		if(addressId != obj.addressId) return false;
		if(!startTime.equals(obj.startTime)) return false;
		if(endTime != null ? !endTime.equals(obj.endTime) : obj.endTime != null) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId>>> 32));
		result = 31 * result + (this.addressRelationCode != null ? this.addressRelationCode.hashCode() : 0);
		result = 31 * result + (int) (this.addressId ^ (this.addressId >>> 32));
		result = 31 * result + (this.startTime != null ? this.startTime.hashCode() : 0);
		result = 31 * result + (this.endTime != null ? this.endTime.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "PeopleAddressesHistory[" +
			"personId=" + personId +
			", addressRelationCode=" + (addressRelationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(addressRelationCode), 32, "...") + '\"') +
			", addressId=" + addressId +
			", startTime=" + startTime + 
			", endTime=" + endTime + 
			']';
	}
	@Generated
	public  PeopleAddressesHistory	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setAddressRelationCode(this.addressRelationCode);
		b.setAddressId(this.addressId);
		b.setStartTime(this.startTime);
		b.setEndTime(this.endTime);
		b = updater.apply(b);
		return new PeopleAddressesHistory(b.personId, b.addressRelationCode, b.addressId, b.startTime, b.endTime);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static PeopleAddressesHistory build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PeopleAddressesHistory(b.personId, b.addressRelationCode, b.addressId, b.startTime, b.endTime);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PeopleAddressesHistory> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new PeopleAddressesHistory(b.personId, b.addressRelationCode, b.addressId, b.startTime, b.endTime));
	}
}
