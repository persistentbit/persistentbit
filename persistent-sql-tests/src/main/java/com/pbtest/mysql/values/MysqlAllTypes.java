package com.pbtest.mysql.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PByteList;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;

import java.util.Objects;
import java.util.function.Function;

public class MysqlAllTypes{

	private final boolean   aBit;
	private final PByteList aTinyint;
	private final PByteList aTinyintUnsinged;
	private final boolean   aBool;


	@Generated
	public MysqlAllTypes(boolean aBit, PByteList aTinyint, PByteList aTinyintUnsinged, boolean aBool) {
		this.aBit = Objects.requireNonNull(aBit, "aBit can not be null");
		this.aTinyint = Objects.requireNonNull(aTinyint, "aTinyint can not be null");
		this.aTinyintUnsinged = Objects.requireNonNull(aTinyintUnsinged, "aTinyintUnsinged can not be null");
		this.aBool = Objects.requireNonNull(aBool, "aBool can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4>{

		private boolean   aBit;
		private PByteList aTinyint;
		private PByteList aTinyintUnsinged;
		private boolean   aBool;


		public Builder<SET, _T2, _T3, _T4> setABit(boolean aBit) {
			this.aBit = aBit;
			return (Builder<SET, _T2, _T3, _T4>) this;
		}

		public Builder<_T1, SET, _T3, _T4> setATinyint(PByteList aTinyint) {
			this.aTinyint = aTinyint;
			return (Builder<_T1, SET, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, SET, _T4> setATinyintUnsinged(PByteList aTinyintUnsinged) {
			this.aTinyintUnsinged = aTinyintUnsinged;
			return (Builder<_T1, _T2, SET, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, SET> setABool(boolean aBool) {
			this.aBool = aBool;
			return (Builder<_T1, _T2, _T3, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #aBit}.<br>
	 *
	 * @return {@link #aBit}
	 */
	@Generated
	public boolean getABit() {
		return this.aBit;
	}

	/**
	 * Create a copy of this MysqlAllTypes object with a new value for field {@link #aBit}.<br>
	 *
	 * @param aBit The new value for field {@link #aBit}
	 *
	 * @return A new instance of {@link MysqlAllTypes}
	 */
	@Generated
	public MysqlAllTypes withABit(boolean aBit) {
		return new MysqlAllTypes(aBit, aTinyint, aTinyintUnsinged, aBool);
	}

	/**
	 * Get the value of field {@link #aTinyint}.<br>
	 *
	 * @return {@link #aTinyint}
	 */
	@Generated
	public PByteList getATinyint() {
		return this.aTinyint;
	}

	/**
	 * Create a copy of this MysqlAllTypes object with a new value for field {@link #aTinyint}.<br>
	 *
	 * @param aTinyint The new value for field {@link #aTinyint}
	 *
	 * @return A new instance of {@link MysqlAllTypes}
	 */
	@Generated
	public MysqlAllTypes withATinyint(PByteList aTinyint) {
		return new MysqlAllTypes(aBit, aTinyint, aTinyintUnsinged, aBool);
	}

	/**
	 * Get the value of field {@link #aTinyintUnsinged}.<br>
	 *
	 * @return {@link #aTinyintUnsinged}
	 */
	@Generated
	public PByteList getATinyintUnsinged() {
		return this.aTinyintUnsinged;
	}

	/**
	 * Create a copy of this MysqlAllTypes object with a new value for field {@link #aTinyintUnsinged}.<br>
	 *
	 * @param aTinyintUnsinged The new value for field {@link #aTinyintUnsinged}
	 *
	 * @return A new instance of {@link MysqlAllTypes}
	 */
	@Generated
	public MysqlAllTypes withATinyintUnsinged(PByteList aTinyintUnsinged) {
		return new MysqlAllTypes(aBit, aTinyint, aTinyintUnsinged, aBool);
	}

	/**
	 * Get the value of field {@link #aBool}.<br>
	 *
	 * @return {@link #aBool}
	 */
	@Generated
	public boolean getABool() {
		return this.aBool;
	}

	/**
	 * Create a copy of this MysqlAllTypes object with a new value for field {@link #aBool}.<br>
	 *
	 * @param aBool The new value for field {@link #aBool}
	 *
	 * @return A new instance of {@link MysqlAllTypes}
	 */
	@Generated
	public MysqlAllTypes withABool(boolean aBool) {
		return new MysqlAllTypes(aBit, aTinyint, aTinyintUnsinged, aBool);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof MysqlAllTypes == false) return false;
		MysqlAllTypes obj = (MysqlAllTypes) o;
		if(aBit != obj.aBit) return false;
		if(!aTinyint.equals(obj.aTinyint)) return false;
		if(!aTinyintUnsinged.equals(obj.aTinyintUnsinged)) return false;
		if(aBool != obj.aBool) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.aBit ? 1 : 0);
		result = 31 * result + (this.aTinyint != null ? this.aTinyint.hashCode() : 0);
		result = 31 * result + (this.aTinyintUnsinged != null ? this.aTinyintUnsinged.hashCode() : 0);
		result = 31 * result + (this.aBool ? 1 : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "MysqlAllTypes[" +
			"aBit=" + aBit +
			", aTinyint=" + aTinyint +
			", aTinyintUnsinged=" + aTinyintUnsinged +
			", aBool=" + aBool +
			']';
	}

	@Generated
	public MysqlAllTypes updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setABit(this.aBit);
		b.setATinyint(this.aTinyint);
		b.setATinyintUnsinged(this.aTinyintUnsinged);
		b.setABool(this.aBool);
		b = updater.apply(b);
		return new MysqlAllTypes(b.aBit, b.aTinyint, b.aTinyintUnsinged, b.aBool);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static MysqlAllTypes build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new MysqlAllTypes(b.aBit, b.aTinyint, b.aTinyintUnsinged, b.aBool);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<MysqlAllTypes> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new MysqlAllTypes(b.aBit, b.aTinyint, b.aTinyintUnsinged, b.aBool));
	}
}
