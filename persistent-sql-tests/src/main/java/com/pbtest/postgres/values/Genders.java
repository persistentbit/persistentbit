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

public class Genders{

	private final String genderCode;
	private final String description;
	
	
	@Generated
	public Genders(String genderCode, String description) {
		this.genderCode = Objects.requireNonNull(genderCode, "genderCode can not be null");
		this.description = Objects.requireNonNull(description, "description can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private String genderCode;
		private String description;


		public Builder<SET, _T2> setGenderCode(String genderCode) {
			this.genderCode = genderCode;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setDescription(String description) {
			this.description = description;
			return (Builder<_T1, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #genderCode}.<br>
	 * @return {@link #genderCode}
	 */
	@Generated
	public String getGenderCode() {
		return this.genderCode;
	}
	/**
	 * Create a copy of this Genders object with a new value for field {@link #genderCode}.<br>
	 * @param genderCode The new value for field {@link #genderCode}
	 * @return A new instance of {@link Genders}
	 */
	@Generated
	public Genders withGenderCode(String genderCode) {
		return new Genders(genderCode, description);
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
	 * Create a copy of this Genders object with a new value for field {@link #description}.<br>
	 * @param description The new value for field {@link #description}
	 * @return A new instance of {@link Genders}
	 */
	@Generated
	public Genders withDescription(String description) {
		return new Genders(genderCode, description);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Genders == false) return false;
		Genders obj = (Genders) o;
		if(!genderCode.equals(obj.genderCode)) return false;
		if(!description.equals(obj.description)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.genderCode != null ? this.genderCode.hashCode() : 0);
		result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "Genders[" +
			"genderCode=" + (genderCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(genderCode), 32, "...") + '\"') +
			", description=" + (description == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(description), 32, "...") + '\"') +
			']';
	}
	@Generated
	public Genders updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setGenderCode(this.genderCode);
		b.setDescription(this.description);
		b = updater.apply(b);
		return new Genders(b.genderCode, b.description);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Genders build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Genders(b.genderCode, b.description);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Genders> buildExc(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new Genders(b.genderCode, b.description));
	}
}
