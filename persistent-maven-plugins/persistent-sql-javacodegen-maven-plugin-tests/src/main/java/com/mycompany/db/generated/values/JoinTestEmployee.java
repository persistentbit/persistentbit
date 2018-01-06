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

public class JoinTestEmployee{

	private final long   empId;
	private final String name;


	@Generated
	public JoinTestEmployee(long empId, String name) {
		this.empId = Objects.requireNonNull(empId, "empId can not be null");
		this.name = Objects.requireNonNull(name, "name can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private long   empId;
		private String name;


		public Builder<SET, _T2> setEmpId(long empId) {
			this.empId = empId;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setName(String name) {
			this.name = name;
			return (Builder<_T1, SET>) this;
		}
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
	 * Create a copy of this JoinTestEmployee object with a new value for field {@link #empId}.<br>
	 *
	 * @param empId The new value for field {@link #empId}
	 *
	 * @return A new instance of {@link JoinTestEmployee}
	 */
	@Generated
	public JoinTestEmployee withEmpId(long empId) {
		return new JoinTestEmployee(empId, name);
	}

	/**
	 * Get the value of field {@link #name}.<br>
	 *
	 * @return {@link #name}
	 */
	@Generated
	public String getName() {
		return this.name;
	}

	/**
	 * Create a copy of this JoinTestEmployee object with a new value for field {@link #name}.<br>
	 *
	 * @param name The new value for field {@link #name}
	 *
	 * @return A new instance of {@link JoinTestEmployee}
	 */
	@Generated
	public JoinTestEmployee withName(String name) {
		return new JoinTestEmployee(empId, name);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof JoinTestEmployee == false) return false;
		JoinTestEmployee obj = (JoinTestEmployee) o;
		if(empId != obj.empId) return false;
		if(!name.equals(obj.name)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.empId ^ (this.empId >>> 32));
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "JoinTestEmployee[" +
			"empId=" + empId +
			", name=" + (name == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(name), 32, "...") + '\"') +
			']';
	}

	@Generated
	public JoinTestEmployee updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setEmpId(this.empId);
		b.setName(this.name);
		b = updater.apply(b);
		return new JoinTestEmployee(b.empId, b.name);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static JoinTestEmployee build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new JoinTestEmployee(b.empId, b.name);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<JoinTestEmployee> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new JoinTestEmployee(b.empId, b.name));
	}
}
