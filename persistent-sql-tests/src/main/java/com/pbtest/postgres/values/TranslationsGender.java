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

public class TranslationsGender{

	private final Object genderCode;
	private final Object languageCode;
	private final String description;


	@Generated
	public TranslationsGender(Object genderCode, Object languageCode, String description) {
		this.genderCode = Objects.requireNonNull(genderCode, "genderCode can not be null");
		this.languageCode = Objects.requireNonNull(languageCode, "languageCode can not be null");
		this.description = Objects.requireNonNull(description, "description can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private Object genderCode;
		private Object languageCode;
		private String description;


		public Builder<SET, _T2, _T3> setGenderCode(Object genderCode) {
			this.genderCode = genderCode;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setLanguageCode(Object languageCode) {
			this.languageCode = languageCode;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setDescription(String description) {
			this.description = description;
			return (Builder<_T1, _T2, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #genderCode}.<br>
	 *
	 * @return {@link #genderCode}
	 */
	@Generated
	public Object getGenderCode() {
		return this.genderCode;
	}

	/**
	 * Create a copy of this TranslationsGender object with a new value for field {@link #genderCode}.<br>
	 *
	 * @param genderCode The new value for field {@link #genderCode}
	 *
	 * @return A new instance of {@link TranslationsGender}
	 */
	@Generated
	public TranslationsGender withGenderCode(Object genderCode) {
		return new TranslationsGender(genderCode, languageCode, description);
	}

	/**
	 * Get the value of field {@link #languageCode}.<br>
	 *
	 * @return {@link #languageCode}
	 */
	@Generated
	public Object getLanguageCode() {
		return this.languageCode;
	}

	/**
	 * Create a copy of this TranslationsGender object with a new value for field {@link #languageCode}.<br>
	 *
	 * @param languageCode The new value for field {@link #languageCode}
	 *
	 * @return A new instance of {@link TranslationsGender}
	 */
	@Generated
	public TranslationsGender withLanguageCode(Object languageCode) {
		return new TranslationsGender(genderCode, languageCode, description);
	}

	/**
	 * Get the value of field {@link #description}.<br>
	 *
	 * @return {@link #description}
	 */
	@Generated
	public String getDescription() {
		return this.description;
	}

	/**
	 * Create a copy of this TranslationsGender object with a new value for field {@link #description}.<br>
	 *
	 * @param description The new value for field {@link #description}
	 *
	 * @return A new instance of {@link TranslationsGender}
	 */
	@Generated
	public TranslationsGender withDescription(String description) {
		return new TranslationsGender(genderCode, languageCode, description);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof TranslationsGender == false) return false;
		TranslationsGender obj = (TranslationsGender) o;
		if(!genderCode.equals(obj.genderCode)) return false;
		if(!languageCode.equals(obj.languageCode)) return false;
		if(!description.equals(obj.description)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.genderCode != null ? this.genderCode.hashCode() : 0);
		result = 31 * result + (this.languageCode != null ? this.languageCode.hashCode() : 0);
		result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "TranslationsGender[" +
			"genderCode=" + genderCode +
			", languageCode=" + languageCode +
			", description=" + (description == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(description), 32, "...") + '\"') +
			']';
	}

	@Generated
	public TranslationsGender updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setGenderCode(this.genderCode);
		b.setLanguageCode(this.languageCode);
		b.setDescription(this.description);
		b = updater.apply(b);
		return new TranslationsGender(b.genderCode, b.languageCode, b.description);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static TranslationsGender build(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new TranslationsGender(b.genderCode, b.languageCode, b.description);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<TranslationsGender> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new TranslationsGender(b.genderCode, b.languageCode, b.description));
	}
}
