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

public class AddressRelations{

	private final String addressRelationCode;
	private final String description;
	
	
	@Generated
	public AddressRelations(String addressRelationCode, String description) {
		this.addressRelationCode = Objects.requireNonNull(addressRelationCode, "addressRelationCode can not be null");
		this.description = Objects.requireNonNull(description, "description can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private String addressRelationCode;
		private String description;


		public Builder<SET, _T2> setAddressRelationCode(String addressRelationCode) {
			this.addressRelationCode = addressRelationCode;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setDescription(String description) {
			this.description = description;
			return (Builder<_T1, SET>) this;
		}
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
	 * Create a copy of this AddressRelations object with a new value for field {@link #addressRelationCode}.<br>
	 * @param addressRelationCode The new value for field {@link #addressRelationCode}
	 * @return A new instance of {@link AddressRelations}
	 */
	@Generated
	public AddressRelations withAddressRelationCode(String addressRelationCode) {
		return new AddressRelations(addressRelationCode, description);
	}
	/**
	 * Get the value of field {@link #description}.<br>
	 * @return {@link #description}
	 */
	@Generated
	public String getDescription() {
		return this.description;
	}
	/**
	 * Create a copy of this AddressRelations object with a new value for field {@link #description}.<br>
	 * @param description The new value for field {@link #description}
	 * @return A new instance of {@link AddressRelations}
	 */
	@Generated
	public AddressRelations withDescription(String description) {
		return new AddressRelations(addressRelationCode, description);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof AddressRelations == false) return false;
		AddressRelations obj = (AddressRelations) o;
		if(!addressRelationCode.equals(obj.addressRelationCode)) return false;
		if(!description.equals(obj.description)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.addressRelationCode != null ? this.addressRelationCode.hashCode() : 0);
		result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "AddressRelations[" +
			"addressRelationCode=" + (addressRelationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(addressRelationCode), 32, "...") + '\"') +
			", description=" + (description == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(description), 32, "...") + '\"') +
			']';
	}
	@Generated
	public AddressRelations updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setAddressRelationCode(this.addressRelationCode);
		b.setDescription(this.description);
		b = updater.apply(b);
		return new AddressRelations(b.addressRelationCode, b.description);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static AddressRelations build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new AddressRelations(b.addressRelationCode, b.description);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<AddressRelations> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new AddressRelations(b.addressRelationCode, b.description));
	}
}
