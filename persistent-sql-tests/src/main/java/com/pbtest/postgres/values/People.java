package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;

import java.util.Objects;
import java.util.function.Function;

public class People{

	private final long personId;
	
	
	@Generated
	public People(long personId) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1>{

		private long personId;


		public Builder<SET> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET>) this;
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
	 * Create a copy of this People object with a new value for field {@link #personId}.<br>
	 * @param personId The new value for field {@link #personId}
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withPersonId(long personId) {
		return new People(personId);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof People == false) return false;
		People obj = (People) o;
		if(personId != obj.personId) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId >>> 32));
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "People[" + 
			"personId=" + personId + 
			']';
	}
	@Generated
	public People updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b = updater.apply(b);
		return new People(b.personId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static People build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new People(b.personId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<People> buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new People(b.personId));
	}
}
