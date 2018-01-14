package com.persistentbit.people;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@CaseClass
public class PersonQueryDetail{

	private final boolean addresses;
	@Nullable
	private final String  translationLanguage;


	@Generated
	public PersonQueryDetail(boolean addresses, @Nullable String translationLanguage) {
		this.addresses = Objects.requireNonNull(addresses, "addresses can not be null");
		this.translationLanguage = translationLanguage;
	}

	@Generated
	public PersonQueryDetail(boolean addresses) {
		this(addresses, null);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1>{

		private boolean addresses;
		private String  translationLanguage;


		public Builder<SET> setAddresses(boolean addresses) {
			this.addresses = addresses;
			return (Builder<SET>) this;
		}

		public Builder<_T1> setTranslationLanguage(@Nullable String translationLanguage) {
			this.translationLanguage = translationLanguage;
			return this;
		}
	}

	/**
	 * Get the value of field {@link #addresses}.<br>
	 *
	 * @return {@link #addresses}
	 */
	@Generated
	public boolean getAddresses() {
		return this.addresses;
	}

	/**
	 * Create a copy of this PersonQueryDetail object with a new value for field {@link #addresses}.<br>
	 *
	 * @param addresses The new value for field {@link #addresses}
	 *
	 * @return A new instance of {@link PersonQueryDetail}
	 */
	@Generated
	public PersonQueryDetail withAddresses(boolean addresses) {
		return new PersonQueryDetail(addresses, translationLanguage);
	}

	/**
	 * Get the value of field {@link #translationLanguage}.<br>
	 *
	 * @return {@link #translationLanguage}
	 */
	@Generated
	public Optional<String> getTranslationLanguage() {
		return Optional.ofNullable(this.translationLanguage);
	}

	/**
	 * Create a copy of this PersonQueryDetail object with a new value for field {@link #translationLanguage}.<br>
	 *
	 * @param translationLanguage The new value for field {@link #translationLanguage}
	 *
	 * @return A new instance of {@link PersonQueryDetail}
	 */
	@Generated
	public PersonQueryDetail withTranslationLanguage(@Nullable String translationLanguage) {
		return new PersonQueryDetail(addresses, translationLanguage);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PersonQueryDetail == false) return false;
		PersonQueryDetail obj = (PersonQueryDetail) o;
		if(addresses != obj.addresses) return false;
		if(translationLanguage != null ? !translationLanguage
			.equals(obj.translationLanguage) : obj.translationLanguage != null) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.addresses ? 1 : 0);
		result = 31 * result + (this.translationLanguage != null ? this.translationLanguage.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "PersonQueryDetail[" +
			"addresses=" + addresses +
			", translationLanguage=" + (translationLanguage == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(translationLanguage), 32, "...") + '\"') +
			']';
	}

	@Generated
	public PersonQueryDetail updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setAddresses(this.addresses);
		b.setTranslationLanguage(this.translationLanguage);
		b = updater.apply(b);
		return new PersonQueryDetail(b.addresses, b.translationLanguage);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static PersonQueryDetail build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PersonQueryDetail(b.addresses, b.translationLanguage);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PersonQueryDetail> buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PersonQueryDetail(b.addresses, b.translationLanguage));
	}
}
