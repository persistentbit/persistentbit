package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class People{

	private final long   personId;
	private final Object salutationCode;
	private final String nameFirst;
	@Nullable
	private final String nameMiddle;
	private final String nameLast;
	private final Object genderCode;


	@Generated
	public People(long personId, Object salutationCode, String nameFirst, @Nullable String nameMiddle, String nameLast,
				  Object genderCode) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.salutationCode = Objects.requireNonNull(salutationCode, "salutationCode can not be null");
		this.nameFirst = Objects.requireNonNull(nameFirst, "nameFirst can not be null");
		this.nameMiddle = nameMiddle;
		this.nameLast = Objects.requireNonNull(nameLast, "nameLast can not be null");
		this.genderCode = Objects.requireNonNull(genderCode, "genderCode can not be null");
	}

	@Generated
	public People(long personId, Object salutationCode, String nameFirst, String nameLast, Object genderCode) {
		this(personId, salutationCode, nameFirst, null, nameLast, genderCode);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5>{

		private long   personId;
		private Object salutationCode;
		private String nameFirst;
		private String nameMiddle;
		private String nameLast;
		private Object genderCode;


		public Builder<SET, _T2, _T3, _T4, _T5> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5> setSalutationCode(Object salutationCode) {
			this.salutationCode = salutationCode;
			return (Builder<_T1, SET, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5> setNameFirst(String nameFirst) {
			this.nameFirst = nameFirst;
			return (Builder<_T1, _T2, SET, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setNameMiddle(@Nullable String nameMiddle) {
			this.nameMiddle = nameMiddle;
			return this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5> setNameLast(String nameLast) {
			this.nameLast = nameLast;
			return (Builder<_T1, _T2, _T3, SET, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET> setGenderCode(Object genderCode) {
			this.genderCode = genderCode;
			return (Builder<_T1, _T2, _T3, _T4, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #personId}.<br>
	 *
	 * @return {@link #personId}
	 */
	@Generated
	public long getPersonId() {
		return this.personId;
	}

	/**
	 * Create a copy of this People object with a new value for field {@link #personId}.<br>
	 *
	 * @param personId The new value for field {@link #personId}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withPersonId(long personId) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode);
	}

	/**
	 * Get the value of field {@link #salutationCode}.<br>
	 *
	 * @return {@link #salutationCode}
	 */
	@Generated
	public Object getSalutationCode() {
		return this.salutationCode;
	}

	/**
	 * Create a copy of this People object with a new value for field {@link #salutationCode}.<br>
	 *
	 * @param salutationCode The new value for field {@link #salutationCode}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withSalutationCode(Object salutationCode) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode);
	}

	/**
	 * Get the value of field {@link #nameFirst}.<br>
	 *
	 * @return {@link #nameFirst}
	 */
	@Generated
	public String getNameFirst() {
		return this.nameFirst;
	}

	/**
	 * Create a copy of this People object with a new value for field {@link #nameFirst}.<br>
	 *
	 * @param nameFirst The new value for field {@link #nameFirst}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withNameFirst(String nameFirst) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode);
	}

	/**
	 * Get the value of field {@link #nameMiddle}.<br>
	 *
	 * @return {@link #nameMiddle}
	 */
	@Generated
	public Optional<String> getNameMiddle() {
		return Optional.ofNullable(this.nameMiddle);
	}

	/**
	 * Create a copy of this People object with a new value for field {@link #nameMiddle}.<br>
	 *
	 * @param nameMiddle The new value for field {@link #nameMiddle}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withNameMiddle(@Nullable String nameMiddle) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode);
	}

	/**
	 * Get the value of field {@link #nameLast}.<br>
	 *
	 * @return {@link #nameLast}
	 */
	@Generated
	public String getNameLast() {
		return this.nameLast;
	}

	/**
	 * Create a copy of this People object with a new value for field {@link #nameLast}.<br>
	 *
	 * @param nameLast The new value for field {@link #nameLast}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withNameLast(String nameLast) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode);
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
	 * Create a copy of this People object with a new value for field {@link #genderCode}.<br>
	 *
	 * @param genderCode The new value for field {@link #genderCode}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withGenderCode(Object genderCode) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof People == false) return false;
		People obj = (People) o;
		if(personId != obj.personId) return false;
		if(!salutationCode.equals(obj.salutationCode)) return false;
		if(!nameFirst.equals(obj.nameFirst)) return false;
		if(nameMiddle != null ? !nameMiddle.equals(obj.nameMiddle) : obj.nameMiddle != null) return false;
		if(!nameLast.equals(obj.nameLast)) return false;
		if(!genderCode.equals(obj.genderCode)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId >>> 32));
		result = 31 * result + (this.salutationCode != null ? this.salutationCode.hashCode() : 0);
		result = 31 * result + (this.nameFirst != null ? this.nameFirst.hashCode() : 0);
		result = 31 * result + (this.nameMiddle != null ? this.nameMiddle.hashCode() : 0);
		result = 31 * result + (this.nameLast != null ? this.nameLast.hashCode() : 0);
		result = 31 * result + (this.genderCode != null ? this.genderCode.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "People[" +
			"personId=" + personId +
			", salutationCode=" + salutationCode +
			", nameFirst=" + (nameFirst == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(nameFirst), 32, "...") + '\"') +
			", nameMiddle=" + (nameMiddle == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(nameMiddle), 32, "...") + '\"') +
			", nameLast=" + (nameLast == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(nameLast), 32, "...") + '\"') +
			", genderCode=" + genderCode +
			']';
	}

	@Generated
	public People updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setSalutationCode(this.salutationCode);
		b.setNameFirst(this.nameFirst);
		b.setNameMiddle(this.nameMiddle);
		b.setNameLast(this.nameLast);
		b.setGenderCode(this.genderCode);
		b = updater.apply(b);
		return new People(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static People build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new People(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<People> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new People(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode));
	}
}
