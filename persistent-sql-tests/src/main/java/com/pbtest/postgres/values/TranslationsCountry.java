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

public class TranslationsCountry{

	private final String countryCode;
	private final String languageCode;
	private final String name;
	
	
	@Generated
	public TranslationsCountry(String countryCode, String languageCode, String name) {
		this.countryCode = Objects.requireNonNull(countryCode, "countryCode can not be null");
		this.languageCode = Objects.requireNonNull(languageCode, "languageCode can not be null");
		this.name = Objects.requireNonNull(name, "name can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private String countryCode;
		private String languageCode;
		private String name;


		public Builder<SET, _T2, _T3> setCountryCode(String countryCode) {
			this.countryCode = countryCode;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setLanguageCode(String languageCode) {
			this.languageCode = languageCode;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setName(String name) {
			this.name = name;
			return (Builder<_T1, _T2, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #countryCode}.<br>
	 * @return {@link #countryCode}
	 */
	@Generated
	public String getCountryCode() {
		return this.countryCode;
	}
	/**
	 * Create a copy of this TranslationsCountry object with a new value for field {@link #countryCode}.<br>
	 * @param countryCode The new value for field {@link #countryCode}
	 * @return A new instance of {@link TranslationsCountry}
	 */
	@Generated
	public TranslationsCountry withCountryCode(String countryCode) {
		return new TranslationsCountry(countryCode, languageCode, name);
	}
	/**
	 * Get the value of field {@link #languageCode}.<br>
	 * @return {@link #languageCode}
	 */
	@Generated
	public String getLanguageCode() {
		return this.languageCode;
	}
	/**
	 * Create a copy of this TranslationsCountry object with a new value for field {@link #languageCode}.<br>
	 * @param languageCode The new value for field {@link #languageCode}
	 * @return A new instance of {@link TranslationsCountry}
	 */
	@Generated
	public TranslationsCountry withLanguageCode(String languageCode) {
		return new TranslationsCountry(countryCode, languageCode, name);
	}
	/**
	 * Get the value of field {@link #name}.<br>
	 * @return {@link #name}
	 */
	@Generated
	public String getName() {
		return this.name;
	}
	/**
	 * Create a copy of this TranslationsCountry object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link TranslationsCountry}
	 */
	@Generated
	public TranslationsCountry withName(String name) {
		return new TranslationsCountry(countryCode, languageCode, name);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof TranslationsCountry == false) return false;
		TranslationsCountry obj = (TranslationsCountry) o;
		if(!countryCode.equals(obj.countryCode)) return false;
		if(!languageCode.equals(obj.languageCode)) return false;
		if(!name.equals(obj.name)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.countryCode != null ? this.countryCode.hashCode() : 0);
		result = 31 * result + (this.languageCode != null ? this.languageCode.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "TranslationsCountry[" +
			"countryCode=" + (countryCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(countryCode), 32, "...") + '\"') +
			", languageCode=" + (languageCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(languageCode), 32, "...") + '\"') +
			", name=" + (name == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(name), 32, "...") + '\"') +
			']';
	}
	@Generated
	public TranslationsCountry updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setCountryCode(this.countryCode);
		b.setLanguageCode(this.languageCode);
		b.setName(this.name);
		b = updater.apply(b);
		return new TranslationsCountry(b.countryCode, b.languageCode, b.name);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static TranslationsCountry build(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new TranslationsCountry(b.countryCode, b.languageCode, b.name);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<TranslationsCountry> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new TranslationsCountry(b.countryCode, b.languageCode, b.name));
	}
}
