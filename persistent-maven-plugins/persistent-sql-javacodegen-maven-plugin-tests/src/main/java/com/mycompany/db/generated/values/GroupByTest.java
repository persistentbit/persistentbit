package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;

import java.util.Objects;
import java.util.function.Function;

public class GroupByTest{

	private final long    empId;
	private final int     hiringYear;
	private final int     hiringQuarter;
	private final boolean active;


	@Generated
	public GroupByTest(long empId, int hiringYear, int hiringQuarter, boolean active) {
		this.empId = Objects.requireNonNull(empId, "empId can not be null");
		this.hiringYear = Objects.requireNonNull(hiringYear, "hiringYear can not be null");
		this.hiringQuarter = Objects.requireNonNull(hiringQuarter, "hiringQuarter can not be null");
		this.active = Objects.requireNonNull(active, "active can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4>{

		private long    empId;
		private int     hiringYear;
		private int     hiringQuarter;
		private boolean active;


		public Builder<SET, _T2, _T3, _T4> setEmpId(long empId) {
			this.empId = empId;
			return (Builder<SET, _T2, _T3, _T4>) this;
		}

		public Builder<_T1, SET, _T3, _T4> setHiringYear(int hiringYear) {
			this.hiringYear = hiringYear;
			return (Builder<_T1, SET, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, SET, _T4> setHiringQuarter(int hiringQuarter) {
			this.hiringQuarter = hiringQuarter;
			return (Builder<_T1, _T2, SET, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, SET> setActive(boolean active) {
			this.active = active;
			return (Builder<_T1, _T2, _T3, SET>) this;
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
	 * Create a copy of this GroupByTest object with a new value for field {@link #empId}.<br>
	 *
	 * @param empId The new value for field {@link #empId}
	 *
	 * @return A new instance of {@link GroupByTest}
	 */
	@Generated
	public GroupByTest withEmpId(long empId) {
		return new GroupByTest(empId, hiringYear, hiringQuarter, active);
	}

	/**
	 * Get the value of field {@link #hiringYear}.<br>
	 *
	 * @return {@link #hiringYear}
	 */
	@Generated
	public int getHiringYear() {
		return this.hiringYear;
	}

	/**
	 * Create a copy of this GroupByTest object with a new value for field {@link #hiringYear}.<br>
	 *
	 * @param hiringYear The new value for field {@link #hiringYear}
	 *
	 * @return A new instance of {@link GroupByTest}
	 */
	@Generated
	public GroupByTest withHiringYear(int hiringYear) {
		return new GroupByTest(empId, hiringYear, hiringQuarter, active);
	}

	/**
	 * Get the value of field {@link #hiringQuarter}.<br>
	 *
	 * @return {@link #hiringQuarter}
	 */
	@Generated
	public int getHiringQuarter() {
		return this.hiringQuarter;
	}

	/**
	 * Create a copy of this GroupByTest object with a new value for field {@link #hiringQuarter}.<br>
	 *
	 * @param hiringQuarter The new value for field {@link #hiringQuarter}
	 *
	 * @return A new instance of {@link GroupByTest}
	 */
	@Generated
	public GroupByTest withHiringQuarter(int hiringQuarter) {
		return new GroupByTest(empId, hiringYear, hiringQuarter, active);
	}

	/**
	 * Get the value of field {@link #active}.<br>
	 *
	 * @return {@link #active}
	 */
	@Generated
	public boolean getActive() {
		return this.active;
	}

	/**
	 * Create a copy of this GroupByTest object with a new value for field {@link #active}.<br>
	 *
	 * @param active The new value for field {@link #active}
	 *
	 * @return A new instance of {@link GroupByTest}
	 */
	@Generated
	public GroupByTest withActive(boolean active) {
		return new GroupByTest(empId, hiringYear, hiringQuarter, active);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof GroupByTest == false) return false;
		GroupByTest obj = (GroupByTest) o;
		if(empId != obj.empId) return false;
		if(hiringYear != obj.hiringYear) return false;
		if(hiringQuarter != obj.hiringQuarter) return false;
		if(active != obj.active) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.empId ^ (this.empId >>> 32));
		result = 31 * result + this.hiringYear;
		result = 31 * result + this.hiringQuarter;
		result = 31 * result + (this.active ? 1 : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "GroupByTest[" +
			"empId=" + empId +
			", hiringYear=" + hiringYear +
			", hiringQuarter=" + hiringQuarter +
			", active=" + active +
			']';
	}

	@Generated
	public GroupByTest updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setEmpId(this.empId);
		b.setHiringYear(this.hiringYear);
		b.setHiringQuarter(this.hiringQuarter);
		b.setActive(this.active);
		b = updater.apply(b);
		return new GroupByTest(b.empId, b.hiringYear, b.hiringQuarter, b.active);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static GroupByTest build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new GroupByTest(b.empId, b.hiringYear, b.hiringQuarter, b.active);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<GroupByTest> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new GroupByTest(b.empId, b.hiringYear, b.hiringQuarter, b.active));
	}
}
