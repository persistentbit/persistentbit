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

public class TranslationsSalutation{

	private final String salutationCode;
	private final String languageCode;
	private final String description;
	
	
	@Generated
	public TranslationsSalutation(String salutationCode, String languageCode, String description) {
		this.salutationCode = Objects.requireNonNull(salutationCode, "salutationCode can not be null");
		this.languageCode = Objects.requireNonNull(languageCode, "languageCode can not be null");
		this.description = Objects.requireNonNull(description, "description can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private String salutationCode;
		private String languageCode;
		private String description;


		public Builder<SET, _T2, _T3> setSalutationCode(String salutationCode) {
			this.salutationCode = salutationCode;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setLanguageCode(String languageCode) {
			this.languageCode = languageCode;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setDescription(String description) {
			this.description = description;
			return (Builder<_T1, _T2, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #salutationCode}.<br>
	 * @return {@link #salutationCode}
	 */
	@Generated
	public String getSalutationCode() {
		return this.salutationCode;
	}
	/**
	 * Create a copy of this TranslationsSalutation object with a new value for field {@link #salutationCode}.<br>
	 * @param salutationCode The new value for field {@link #salutationCode}
	 * @return A new instance of {@link TranslationsSalutation}
	 */
	@Generated
	public TranslationsSalutation withSalutationCode(String salutationCode) {
		return new TranslationsSalutation(salutationCode, languageCode, description);
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
	 * Create a copy of this TranslationsSalutation object with a new value for field {@link #languageCode}.<br>
	 * @param languageCode The new value for field {@link #languageCode}
	 * @return A new instance of {@link TranslationsSalutation}
	 */
	@Generated
	public TranslationsSalutation withLanguageCode(String languageCode) {
		return new TranslationsSalutation(salutationCode, languageCode, description);
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
	 * Create a copy of this TranslationsSalutation object with a new value for field {@link #description}.<br>
	 * @param description The new value for field {@link #description}
	 * @return A new instance of {@link TranslationsSalutation}
	 */
	@Generated
	public TranslationsSalutation withDescription(String description) {
		return new TranslationsSalutation(salutationCode, languageCode, description);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof TranslationsSalutation == false) return false;
		TranslationsSalutation obj = (TranslationsSalutation) o;
		if(!salutationCode.equals(obj.salutationCode)) return false;
		if(!languageCode.equals(obj.languageCode)) return false;
		if(!description.equals(obj.description)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.salutationCode != null ? this.salutationCode.hashCode() : 0);
		result = 31 * result + (this.languageCode != null ? this.languageCode.hashCode() : 0);
		result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "TranslationsSalutation[" +
			"salutationCode=" + (salutationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(salutationCode), 32, "...") + '\"') +
			", languageCode=" + (languageCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(languageCode), 32, "...") + '\"') +
			", description=" + (description == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(description), 32, "...") + '\"') +
			']';
	}
	@Generated
	public TranslationsSalutation updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setSalutationCode(this.salutationCode);
		b.setLanguageCode(this.languageCode);
		b.setDescription(this.description);
		b = updater.apply(b);
		return new TranslationsSalutation(b.salutationCode, b.languageCode, b.description);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static TranslationsSalutation build(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new TranslationsSalutation(b.salutationCode, b.languageCode, b.description);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<TranslationsSalutation> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new TranslationsSalutation(b.salutationCode, b.languageCode, b.description));
	}
}
