package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.function.Function;

public class JoinTestCompanyEmployee{

	private final long   cmpId;
	private final long   empId;
	private final String function;


	@Generated
	public JoinTestCompanyEmployee(long cmpId, long empId, String function) {
		this.cmpId = Objects.requireNonNull(cmpId, "cmpId can not be null");
		this.empId = Objects.requireNonNull(empId, "empId can not be null");
		this.function = Objects.requireNonNull(function, "function can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private long   cmpId;
		private long   empId;
		private String function;


		public Builder<SET, _T2, _T3> setCmpId(long cmpId) {
			this.cmpId = cmpId;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setEmpId(long empId) {
			this.empId = empId;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setFunction(String function) {
			this.function = function;
			return (Builder<_T1, _T2, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #cmpId}.<br>
	 *
	 * @return {@link #cmpId}
	 */
	@Generated
	public long getCmpId() {
		return this.cmpId;
	}

	/**
	 * Create a copy of this JoinTestCompanyEmployee object with a new value for field {@link #cmpId}.<br>
	 *
	 * @param cmpId The new value for field {@link #cmpId}
	 *
	 * @return A new instance of {@link JoinTestCompanyEmployee}
	 */
	@Generated
	public JoinTestCompanyEmployee withCmpId(long cmpId) {
		return new JoinTestCompanyEmployee(cmpId, empId, function);
	}

	/**
	 * Get the value of field {@link #empId}.<br>
	 *
	 * @return {@link #empId}
	 */
	@Generated
	public long getEmpId() {
		return this.empId;
	}

	/**
	 * Create a copy of this JoinTestCompanyEmployee object with a new value for field {@link #empId}.<br>
	 *
	 * @param empId The new value for field {@link #empId}
	 *
	 * @return A new instance of {@link JoinTestCompanyEmployee}
	 */
	@Generated
	public JoinTestCompanyEmployee withEmpId(long empId) {
		return new JoinTestCompanyEmployee(cmpId, empId, function);
	}

	/**
	 * Get the value of field {@link #function}.<br>
	 *
	 * @return {@link #function}
	 */
	@Generated
	public String getFunction() {
		return this.function;
	}

	/**
	 * Create a copy of this JoinTestCompanyEmployee object with a new value for field {@link #function}.<br>
	 *
	 * @param function The new value for field {@link #function}
	 *
	 * @return A new instance of {@link JoinTestCompanyEmployee}
	 */
	@Generated
	public JoinTestCompanyEmployee withFunction(String function) {
		return new JoinTestCompanyEmployee(cmpId, empId, function);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof JoinTestCompanyEmployee == false) return false;
		JoinTestCompanyEmployee obj = (JoinTestCompanyEmployee) o;
		if(cmpId != obj.cmpId) return false;
		if(empId != obj.empId) return false;
		if(!function.equals(obj.function)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.cmpId ^ (this.cmpId >>> 32));
		result = 31 * result + (int) (this.empId ^ (this.empId >>> 32));
		result = 31 * result + (this.function != null ? this.function.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "JoinTestCompanyEmployee[" +
			"cmpId=" + cmpId +
			", empId=" + empId +
			", function=" + (function == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(function), 32, "...") + '\"') +
			']';
	}

	@Generated
	public JoinTestCompanyEmployee updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setCmpId(this.cmpId);
		b.setEmpId(this.empId);
		b.setFunction(this.function);
		b = updater.apply(b);
		return new JoinTestCompanyEmployee(b.cmpId, b.empId, b.function);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static JoinTestCompanyEmployee build(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new JoinTestCompanyEmployee(b.cmpId, b.empId, b.function);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<JoinTestCompanyEmployee> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new JoinTestCompanyEmployee(b.cmpId, b.empId, b.function));
	}
}
