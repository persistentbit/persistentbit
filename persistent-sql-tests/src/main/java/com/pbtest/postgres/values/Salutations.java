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

public class Salutations{

	private final String salutationCode;
	private final String description;
	
	
	@Generated
	public Salutations(String salutationCode, String description) {
		this.salutationCode = Objects.requireNonNull(salutationCode, "salutationCode can not be null");
		this.description = Objects.requireNonNull(description, "description can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private String salutationCode;
		private String description;


		public Builder<SET, _T2> setSalutationCode(String salutationCode) {
			this.salutationCode = salutationCode;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setDescription(String description) {
			this.description = description;
			return (Builder<_T1, SET>) this;
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
	 * Create a copy of this Salutations object with a new value for field {@link #salutationCode}.<br>
	 * @param salutationCode The new value for field {@link #salutationCode}
	 * @return A new instance of {@link Salutations}
	 */
	@Generated
	public Salutations withSalutationCode(String salutationCode) {
		return new Salutations(salutationCode, description);
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
	 * Create a copy of this Salutations object with a new value for field {@link #description}.<br>
	 * @param description The new value for field {@link #description}
	 * @return A new instance of {@link Salutations}
	 */
	@Generated
	public Salutations withDescription(String description) {
		return new Salutations(salutationCode, description);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Salutations == false) return false;
		Salutations obj = (Salutations) o;
		if(!salutationCode.equals(obj.salutationCode)) return false;
		if(!description.equals(obj.description)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.salutationCode != null ? this.salutationCode.hashCode() : 0);
		result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "Salutations[" +
			"salutationCode=" + (salutationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(salutationCode), 32, "...") + '\"') +
			", description=" + (description == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(description), 32, "...") + '\"') +
			']';
	}
	@Generated
	public Salutations updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setSalutationCode(this.salutationCode);
		b.setDescription(this.description);
		b = updater.apply(b);
		return new Salutations(b.salutationCode, b.description);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Salutations build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Salutations(b.salutationCode, b.description);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Salutations> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new Salutations(b.salutationCode, b.description));
	}
}
