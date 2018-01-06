package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.function.Function;

public class GenData{

	private final int           genDataId;
	private final int           aInt;
	private final short         aShort;
	private final long          aLong;
	private final BigDecimal    aNum;
	private final double        aDouble;
	private final float         aReal;
	private final boolean       aBool;
	private final LocalDate     aDate;
	private final LocalTime     aTime;
	private final LocalDateTime aTimestamp;
	private final String        aString;
	
	
	@Generated
	public GenData(int genDataId, int aInt, short aShort, long aLong, BigDecimal aNum, double aDouble, float aReal,
				   boolean aBool, LocalDate aDate, LocalTime aTime, LocalDateTime aTimestamp, String aString) {
		this.genDataId = Objects.requireNonNull(genDataId, "genDataId can not be null");
		this.aInt = Objects.requireNonNull(aInt, "aInt can not be null");
		this.aShort = Objects.requireNonNull(aShort, "aShort can not be null");
		this.aLong = Objects.requireNonNull(aLong, "aLong can not be null");
		this.aNum = Objects.requireNonNull(aNum, "aNum can not be null");
		this.aDouble = Objects.requireNonNull(aDouble, "aDouble can not be null");
		this.aReal = Objects.requireNonNull(aReal, "aReal can not be null");
		this.aBool = Objects.requireNonNull(aBool, "aBool can not be null");
		this.aDate = Objects.requireNonNull(aDate, "aDate can not be null");
		this.aTime = Objects.requireNonNull(aTime, "aTime can not be null");
		this.aTimestamp = Objects.requireNonNull(aTimestamp, "aTimestamp can not be null");
		this.aString = Objects.requireNonNull(aString, "aString can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>{

		private int           genDataId;
		private int           aInt;
		private short         aShort;
		private long          aLong;
		private BigDecimal    aNum;
		private double        aDouble;
		private float         aReal;
		private boolean       aBool;
		private LocalDate     aDate;
		private LocalTime     aTime;
		private LocalDateTime aTimestamp;
		private String        aString;


		public Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setGenDataId(int genDataId) {
			this.genDataId = genDataId;
			return (Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setAInt(int aInt) {
			this.aInt = aInt;
			return (Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setAShort(short aShort) {
			this.aShort = aShort;
			return (Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setALong(long aLong) {
			this.aLong = aLong;
			return (Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setANum(BigDecimal aNum) {
			this.aNum = aNum;
			return (Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7, _T8, _T9, _T10, _T11, _T12> setADouble(double aDouble) {
			this.aDouble = aDouble;
			return (Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET, _T8, _T9, _T10, _T11, _T12> setAReal(float aReal) {
			this.aReal = aReal;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, SET, _T9, _T10, _T11, _T12> setABool(boolean aBool) {
			this.aBool = aBool;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, SET, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, SET, _T10, _T11, _T12> setADate(LocalDate aDate) {
			this.aDate = aDate;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, SET, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, SET, _T11, _T12> setATime(LocalTime aTime) {
			this.aTime = aTime;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, SET, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, SET, _T12> setATimestamp(
			LocalDateTime aTimestamp) {
			this.aTimestamp = aTimestamp;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, SET, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, SET> setAString(String aString) {
			this.aString = aString;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #genDataId}.<br>
	 * @return {@link #genDataId}
	 */
	@Generated
	public int getGenDataId() {
		return this.genDataId;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #genDataId}.<br>
	 * @param genDataId The new value for field {@link #genDataId}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withGenDataId(int genDataId) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aInt}.<br>
	 * @return {@link #aInt}
	 */
	@Generated
	public int getAInt() {
		return this.aInt;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aInt}.<br>
	 * @param aInt The new value for field {@link #aInt}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withAInt(int aInt) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aShort}.<br>
	 * @return {@link #aShort}
	 */
	@Generated
	public short getAShort() {
		return this.aShort;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aShort}.<br>
	 * @param aShort The new value for field {@link #aShort}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withAShort(short aShort) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aLong}.<br>
	 * @return {@link #aLong}
	 */
	@Generated
	public long getALong() {
		return this.aLong;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aLong}.<br>
	 * @param aLong The new value for field {@link #aLong}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withALong(long aLong) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aNum}.<br>
	 * @return {@link #aNum}
	 */
	@Generated
	public BigDecimal getANum() {
		return this.aNum;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aNum}.<br>
	 * @param aNum The new value for field {@link #aNum}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withANum(BigDecimal aNum) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aDouble}.<br>
	 * @return {@link #aDouble}
	 */
	@Generated
	public double getADouble() {
		return this.aDouble;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aDouble}.<br>
	 * @param aDouble The new value for field {@link #aDouble}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withADouble(double aDouble) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aReal}.<br>
	 * @return {@link #aReal}
	 */
	@Generated
	public float getAReal() {
		return this.aReal;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aReal}.<br>
	 * @param aReal The new value for field {@link #aReal}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withAReal(float aReal) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aBool}.<br>
	 * @return {@link #aBool}
	 */
	@Generated
	public boolean getABool() {
		return this.aBool;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aBool}.<br>
	 * @param aBool The new value for field {@link #aBool}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withABool(boolean aBool) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aDate}.<br>
	 * @return {@link #aDate}
	 */
	@Generated
	public LocalDate getADate() {
		return this.aDate;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aDate}.<br>
	 * @param aDate The new value for field {@link #aDate}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withADate(LocalDate aDate) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aTime}.<br>
	 * @return {@link #aTime}
	 */
	@Generated
	public LocalTime getATime() {
		return this.aTime;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aTime}.<br>
	 * @param aTime The new value for field {@link #aTime}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withATime(LocalTime aTime) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aTimestamp}.<br>
	 * @return {@link #aTimestamp}
	 */
	@Generated
	public LocalDateTime getATimestamp() {
		return this.aTimestamp;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aTimestamp}.<br>
	 * @param aTimestamp The new value for field {@link #aTimestamp}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withATimestamp(LocalDateTime aTimestamp) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	/**
	 * Get the value of field {@link #aString}.<br>
	 * @return {@link #aString}
	 */
	@Generated
	public String getAString() {
		return this.aString;
	}
	/**
	 * Create a copy of this GenData object with a new value for field {@link #aString}.<br>
	 * @param aString The new value for field {@link #aString}
	 * @return A new instance of {@link GenData}
	 */
	@Generated
	public GenData withAString(String aString) {
		return new GenData(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof GenData == false) return false;
		GenData obj = (GenData) o;
		if(genDataId != obj.genDataId) return false;
		if(aInt != obj.aInt) return false;
		if(aShort != obj.aShort) return false;
		if(aLong != obj.aLong) return false;
		if(!aNum.equals(obj.aNum)) return false;
		if(Double.compare(aDouble, obj.aDouble) != 0) return false;
		if(aDouble != obj.aDouble) return false;
		if(Float.compare(aReal, obj.aReal) != 0) return false;
		if(aReal != obj.aReal) return false;
		if(aBool != obj.aBool) return false;
		if(!aDate.equals(obj.aDate)) return false;
		if(!aTime.equals(obj.aTime)) return false;
		if(!aTimestamp.equals(obj.aTimestamp)) return false;
		if(!aString.equals(obj.aString)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int  result;
		long temp;
		result = this.genDataId;
		result = 31 * result + this.aInt;
		result = 31 * result + (int) this.aShort;
		result = 31 * result + (int) (this.aLong ^ (this.aLong >>> 32));
		result = 31 * result + (this.aNum != null ? this.aNum.hashCode() : 0);
		temp = Double.doubleToLongBits(this.aDouble);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (this.aReal != +0.0f ? Float.floatToIntBits(this.aReal) : 0);
		result = 31 * result + (this.aBool ? 1 : 0);
		result = 31 * result + (this.aDate != null ? this.aDate.hashCode() : 0);
		result = 31 * result + (this.aTime != null ? this.aTime.hashCode() : 0);
		result = 31 * result + (this.aTimestamp != null ? this.aTimestamp.hashCode() : 0);
		result = 31 * result + (this.aString != null ? this.aString.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "GenData[" +
			"genDataId=" + genDataId +
			", aInt=" + aInt +
			", aShort=" + aShort +
			", aLong=" + aLong +
			", aNum=" + aNum +
			", aDouble=" + aDouble +
			", aReal=" + aReal +
			", aBool=" + aBool +
			", aDate=" + aDate +
			", aTime=" + aTime +
			", aTimestamp=" + aTimestamp +
			", aString=" + (aString == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(aString), 32, "...") + '\"') +
			']';
	}
	@Generated
	public GenData updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setGenDataId(this.genDataId);
		b.setAInt(this.aInt);
		b.setAShort(this.aShort);
		b.setALong(this.aLong);
		b.setANum(this.aNum);
		b.setADouble(this.aDouble);
		b.setAReal(this.aReal);
		b.setABool(this.aBool);
		b.setADate(this.aDate);
		b.setATime(this.aTime);
		b.setATimestamp(this.aTimestamp);
		b.setAString(this.aString);
		b = updater.apply(b);
		return new GenData(b.genDataId, b.aInt, b.aShort, b.aLong, b.aNum, b.aDouble, b.aReal, b.aBool, b.aDate, b.aTime, b.aTimestamp, b.aString);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static GenData build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new GenData(b.genDataId, b.aInt, b.aShort, b.aLong, b.aNum, b.aDouble, b.aReal, b.aBool, b.aDate, b.aTime, b.aTimestamp, b.aString);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<GenData> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new GenData(b.genDataId, b.aInt, b.aShort, b.aLong, b.aNum, b.aDouble, b.aReal, b.aBool, b.aDate, b.aTime, b.aTimestamp, b.aString));
	}
}
