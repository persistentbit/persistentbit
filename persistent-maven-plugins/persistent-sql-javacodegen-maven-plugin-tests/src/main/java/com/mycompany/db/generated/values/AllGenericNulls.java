package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PByteList;
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
import java.util.Optional;
import java.util.function.Function;

public class AllGenericNulls{

	private final String        idPart1;
	private final long          idPart2;
	private final short         serSmall;
	private final int           ser;
	private final long          serBig;
	@Nullable
	private final Integer       anInteger;
	@Nullable
	private final Long          aBigint;
	@Nullable
	private final BigDecimal    aDecimal72;
	@Nullable
	private final BigDecimal    aNumeric6;
	@Nullable
	private final BigDecimal    aNumeric;
	@Nullable
	private final Float         aReal;
	@Nullable
	private final Double        aDouble;
	@Nullable
	private final Short         anInt2;
	@Nullable
	private final Integer       anInt4;
	@Nullable
	private final Long          anInt8;
	@Nullable
	private final String        aVarchar;
	@Nullable
	private final String        aVarchar10;
	@Nullable
	private final String        aText;
	@Nullable
	private final String        aChar;
	@Nullable
	private final String        aChar10;
	@Nullable
	private final PByteList     aBytea;
	@Nullable
	private final LocalDateTime aTimestamp3;
	@Nullable
	private final LocalDateTime aTimestamp;
	@Nullable
	private final LocalDateTime aTimestampWithZone;
	@Nullable
	private final LocalDate     aDate;
	@Nullable
	private final LocalTime     aTime;
	@Nullable
	private final LocalTime     aTimeWithZone;
	@Nullable
	private final Boolean       aBoolean;
	
	
	@Generated
	public AllGenericNulls(String idPart1, long idPart2, short serSmall, int ser, long serBig,
						   @Nullable Integer anInteger, @Nullable Long aBigint, @Nullable BigDecimal aDecimal72,
						   @Nullable BigDecimal aNumeric6, @Nullable BigDecimal aNumeric, @Nullable Float aReal,
						   @Nullable Double aDouble, @Nullable Short anInt2, @Nullable Integer anInt4,
						   @Nullable Long anInt8, @Nullable String aVarchar, @Nullable String aVarchar10,
						   @Nullable String aText, @Nullable String aChar, @Nullable String aChar10,
						   @Nullable PByteList aBytea, @Nullable LocalDateTime aTimestamp3,
						   @Nullable LocalDateTime aTimestamp, @Nullable LocalDateTime aTimestampWithZone,
						   @Nullable LocalDate aDate, @Nullable LocalTime aTime, @Nullable LocalTime aTimeWithZone,
						   @Nullable Boolean aBoolean) {
		this.idPart1 = Objects.requireNonNull(idPart1, "idPart1 can not be null");
		this.idPart2 = Objects.requireNonNull(idPart2, "idPart2 can not be null");
		this.serSmall = Objects.requireNonNull(serSmall, "serSmall can not be null");
		this.ser = Objects.requireNonNull(ser, "ser can not be null");
		this.serBig = Objects.requireNonNull(serBig, "serBig can not be null");
		this.anInteger = anInteger;
		this.aBigint = aBigint;
		this.aDecimal72 = aDecimal72;
		this.aNumeric6 = aNumeric6;
		this.aNumeric = aNumeric;
		this.aReal = aReal;
		this.aDouble = aDouble;
		this.anInt2 = anInt2;
		this.anInt4 = anInt4;
		this.anInt8 = anInt8;
		this.aVarchar = aVarchar;
		this.aVarchar10 = aVarchar10;
		this.aText = aText;
		this.aChar = aChar;
		this.aChar10 = aChar10;
		this.aBytea = aBytea;
		this.aTimestamp3 = aTimestamp3;
		this.aTimestamp = aTimestamp;
		this.aTimestampWithZone = aTimestampWithZone;
		this.aDate = aDate;
		this.aTime = aTime;
		this.aTimeWithZone = aTimeWithZone;
		this.aBoolean = aBoolean;
	}
	@Generated
	public AllGenericNulls(String idPart1, long idPart2, short serSmall, int ser, long serBig) {
		this(idPart1, idPart2, serSmall, ser, serBig, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5>{

		private String        idPart1;
		private long          idPart2;
		private short         serSmall;
		private int           ser;
		private long          serBig;
		private Integer       anInteger;
		private Long          aBigint;
		private BigDecimal    aDecimal72;
		private BigDecimal    aNumeric6;
		private BigDecimal    aNumeric;
		private Float         aReal;
		private Double        aDouble;
		private Short         anInt2;
		private Integer       anInt4;
		private Long          anInt8;
		private String        aVarchar;
		private String        aVarchar10;
		private String        aText;
		private String        aChar;
		private String        aChar10;
		private PByteList     aBytea;
		private LocalDateTime aTimestamp3;
		private LocalDateTime aTimestamp;
		private LocalDateTime aTimestampWithZone;
		private LocalDate     aDate;
		private LocalTime     aTime;
		private LocalTime     aTimeWithZone;
		private Boolean       aBoolean;


		public Builder<SET, _T2, _T3, _T4, _T5> setIdPart1(String idPart1) {
			this.idPart1 = idPart1;
			return (Builder<SET, _T2, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5> setIdPart2(long idPart2) {
			this.idPart2 = idPart2;
			return (Builder<_T1, SET, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5> setSerSmall(short serSmall) {
			this.serSmall = serSmall;
			return (Builder<_T1, _T2, SET, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5> setSer(int ser) {
			this.ser = ser;
			return (Builder<_T1, _T2, _T3, SET, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET> setSerBig(long serBig) {
			this.serBig = serBig;
			return (Builder<_T1, _T2, _T3, _T4, SET>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAnInteger(@Nullable Integer anInteger) {
			this.anInteger = anInteger;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setABigint(@Nullable Long aBigint) {
			this.aBigint = aBigint;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setADecimal72(@Nullable BigDecimal aDecimal72) {
			this.aDecimal72 = aDecimal72;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setANumeric6(@Nullable BigDecimal aNumeric6) {
			this.aNumeric6 = aNumeric6;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setANumeric(@Nullable BigDecimal aNumeric) {
			this.aNumeric = aNumeric;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAReal(@Nullable Float aReal) {
			this.aReal = aReal;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setADouble(@Nullable Double aDouble) {
			this.aDouble = aDouble;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAnInt2(@Nullable Short anInt2) {
			this.anInt2 = anInt2;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAnInt4(@Nullable Integer anInt4) {
			this.anInt4 = anInt4;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAnInt8(@Nullable Long anInt8) {
			this.anInt8 = anInt8;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAVarchar(@Nullable String aVarchar) {
			this.aVarchar = aVarchar;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAVarchar10(@Nullable String aVarchar10) {
			this.aVarchar10 = aVarchar10;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAText(@Nullable String aText) {
			this.aText = aText;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAChar(@Nullable String aChar) {
			this.aChar = aChar;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setAChar10(@Nullable String aChar10) {
			this.aChar10 = aChar10;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setABytea(@Nullable PByteList aBytea) {
			this.aBytea = aBytea;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setATimestamp3(@Nullable LocalDateTime aTimestamp3) {
			this.aTimestamp3 = aTimestamp3;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setATimestamp(@Nullable LocalDateTime aTimestamp) {
			this.aTimestamp = aTimestamp;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setATimestampWithZone(@Nullable LocalDateTime aTimestampWithZone) {
			this.aTimestampWithZone = aTimestampWithZone;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setADate(@Nullable LocalDate aDate) {
			this.aDate = aDate;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setATime(@Nullable LocalTime aTime) {
			this.aTime = aTime;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setATimeWithZone(@Nullable LocalTime aTimeWithZone) {
			this.aTimeWithZone = aTimeWithZone;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setABoolean(@Nullable Boolean aBoolean) {
			this.aBoolean = aBoolean;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #idPart1}.<br>
	 * @return {@link #idPart1}
	 */
	@Generated
	public String getIdPart1() {
		return this.idPart1;
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #idPart1}.<br>
	 * @param idPart1 The new value for field {@link #idPart1}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withIdPart1(String idPart1) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #idPart2}.<br>
	 * @return {@link #idPart2}
	 */
	@Generated
	public long getIdPart2() {
		return this.idPart2;
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #idPart2}.<br>
	 * @param idPart2 The new value for field {@link #idPart2}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withIdPart2(long idPart2) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #serSmall}.<br>
	 * @return {@link #serSmall}
	 */
	@Generated
	public short getSerSmall() {
		return this.serSmall;
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #serSmall}.<br>
	 * @param serSmall The new value for field {@link #serSmall}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withSerSmall(short serSmall) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #ser}.<br>
	 * @return {@link #ser}
	 */
	@Generated
	public int getSer() {
		return this.ser;
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #ser}.<br>
	 * @param ser The new value for field {@link #ser}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withSer(int ser) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #serBig}.<br>
	 * @return {@link #serBig}
	 */
	@Generated
	public long getSerBig() {
		return this.serBig;
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #serBig}.<br>
	 * @param serBig The new value for field {@link #serBig}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withSerBig(long serBig) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #anInteger}.<br>
	 * @return {@link #anInteger}
	 */
	@Generated
	public Optional<Integer> getAnInteger() {
		return Optional.ofNullable(this.anInteger);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #anInteger}.<br>
	 * @param anInteger The new value for field {@link #anInteger}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAnInteger(@Nullable Integer anInteger) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aBigint}.<br>
	 * @return {@link #aBigint}
	 */
	@Generated
	public Optional<Long> getABigint() {
		return Optional.ofNullable(this.aBigint);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aBigint}.<br>
	 * @param aBigint The new value for field {@link #aBigint}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withABigint(@Nullable Long aBigint) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aDecimal72}.<br>
	 * @return {@link #aDecimal72}
	 */
	@Generated
	public Optional<BigDecimal> getADecimal72() {
		return Optional.ofNullable(this.aDecimal72);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aDecimal72}.<br>
	 * @param aDecimal72 The new value for field {@link #aDecimal72}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withADecimal72(@Nullable BigDecimal aDecimal72) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aNumeric6}.<br>
	 * @return {@link #aNumeric6}
	 */
	@Generated
	public Optional<BigDecimal> getANumeric6() {
		return Optional.ofNullable(this.aNumeric6);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aNumeric6}.<br>
	 * @param aNumeric6 The new value for field {@link #aNumeric6}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withANumeric6(@Nullable BigDecimal aNumeric6) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aNumeric}.<br>
	 * @return {@link #aNumeric}
	 */
	@Generated
	public Optional<BigDecimal> getANumeric() {
		return Optional.ofNullable(this.aNumeric);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aNumeric}.<br>
	 * @param aNumeric The new value for field {@link #aNumeric}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withANumeric(@Nullable BigDecimal aNumeric) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aReal}.<br>
	 * @return {@link #aReal}
	 */
	@Generated
	public Optional<Float> getAReal() {
		return Optional.ofNullable(this.aReal);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aReal}.<br>
	 * @param aReal The new value for field {@link #aReal}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAReal(@Nullable Float aReal) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aDouble}.<br>
	 * @return {@link #aDouble}
	 */
	@Generated
	public Optional<Double> getADouble() {
		return Optional.ofNullable(this.aDouble);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aDouble}.<br>
	 * @param aDouble The new value for field {@link #aDouble}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withADouble(@Nullable Double aDouble) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #anInt2}.<br>
	 * @return {@link #anInt2}
	 */
	@Generated
	public Optional<Short> getAnInt2() {
		return Optional.ofNullable(this.anInt2);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #anInt2}.<br>
	 * @param anInt2 The new value for field {@link #anInt2}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAnInt2(@Nullable Short anInt2) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #anInt4}.<br>
	 * @return {@link #anInt4}
	 */
	@Generated
	public Optional<Integer> getAnInt4() {
		return Optional.ofNullable(this.anInt4);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #anInt4}.<br>
	 * @param anInt4 The new value for field {@link #anInt4}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAnInt4(@Nullable Integer anInt4) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #anInt8}.<br>
	 * @return {@link #anInt8}
	 */
	@Generated
	public Optional<Long> getAnInt8() {
		return Optional.ofNullable(this.anInt8);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #anInt8}.<br>
	 * @param anInt8 The new value for field {@link #anInt8}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAnInt8(@Nullable Long anInt8) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aVarchar}.<br>
	 * @return {@link #aVarchar}
	 */
	@Generated
	public Optional<String> getAVarchar() {
		return Optional.ofNullable(this.aVarchar);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aVarchar}.<br>
	 * @param aVarchar The new value for field {@link #aVarchar}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAVarchar(@Nullable String aVarchar) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aVarchar10}.<br>
	 * @return {@link #aVarchar10}
	 */
	@Generated
	public Optional<String> getAVarchar10() {
		return Optional.ofNullable(this.aVarchar10);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aVarchar10}.<br>
	 * @param aVarchar10 The new value for field {@link #aVarchar10}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAVarchar10(@Nullable String aVarchar10) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aText}.<br>
	 * @return {@link #aText}
	 */
	@Generated
	public Optional<String> getAText() {
		return Optional.ofNullable(this.aText);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aText}.<br>
	 * @param aText The new value for field {@link #aText}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAText(@Nullable String aText) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aChar}.<br>
	 * @return {@link #aChar}
	 */
	@Generated
	public Optional<String> getAChar() {
		return Optional.ofNullable(this.aChar);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aChar}.<br>
	 * @param aChar The new value for field {@link #aChar}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAChar(@Nullable String aChar) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aChar10}.<br>
	 * @return {@link #aChar10}
	 */
	@Generated
	public Optional<String> getAChar10() {
		return Optional.ofNullable(this.aChar10);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aChar10}.<br>
	 * @param aChar10 The new value for field {@link #aChar10}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withAChar10(@Nullable String aChar10) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aBytea}.<br>
	 * @return {@link #aBytea}
	 */
	@Generated
	public Optional<PByteList> getABytea() {
		return Optional.ofNullable(this.aBytea);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aBytea}.<br>
	 * @param aBytea The new value for field {@link #aBytea}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withABytea(@Nullable PByteList aBytea) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aTimestamp3}.<br>
	 * @return {@link #aTimestamp3}
	 */
	@Generated
	public Optional<LocalDateTime> getATimestamp3() {
		return Optional.ofNullable(this.aTimestamp3);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aTimestamp3}.<br>
	 * @param aTimestamp3 The new value for field {@link #aTimestamp3}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withATimestamp3(@Nullable LocalDateTime aTimestamp3) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aTimestamp}.<br>
	 * @return {@link #aTimestamp}
	 */
	@Generated
	public Optional<LocalDateTime> getATimestamp() {
		return Optional.ofNullable(this.aTimestamp);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aTimestamp}.<br>
	 * @param aTimestamp The new value for field {@link #aTimestamp}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withATimestamp(@Nullable LocalDateTime aTimestamp) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aTimestampWithZone}.<br>
	 * @return {@link #aTimestampWithZone}
	 */
	@Generated
	public Optional<LocalDateTime> getATimestampWithZone() {
		return Optional.ofNullable(this.aTimestampWithZone);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aTimestampWithZone}.<br>
	 * @param aTimestampWithZone The new value for field {@link #aTimestampWithZone}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withATimestampWithZone(@Nullable LocalDateTime aTimestampWithZone) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aDate}.<br>
	 * @return {@link #aDate}
	 */
	@Generated
	public Optional<LocalDate> getADate() {
		return Optional.ofNullable(this.aDate);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aDate}.<br>
	 * @param aDate The new value for field {@link #aDate}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withADate(@Nullable LocalDate aDate) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aTime}.<br>
	 * @return {@link #aTime}
	 */
	@Generated
	public Optional<LocalTime> getATime() {
		return Optional.ofNullable(this.aTime);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aTime}.<br>
	 * @param aTime The new value for field {@link #aTime}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withATime(@Nullable LocalTime aTime) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aTimeWithZone}.<br>
	 * @return {@link #aTimeWithZone}
	 */
	@Generated
	public Optional<LocalTime> getATimeWithZone() {
		return Optional.ofNullable(this.aTimeWithZone);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aTimeWithZone}.<br>
	 * @param aTimeWithZone The new value for field {@link #aTimeWithZone}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withATimeWithZone(@Nullable LocalTime aTimeWithZone) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	/**
	 * Get the value of field {@link #aBoolean}.<br>
	 * @return {@link #aBoolean}
	 */
	@Generated
	public Optional<Boolean> getABoolean() {
		return Optional.ofNullable(this.aBoolean);
	}
	/**
	 * Create a copy of this AllGenericNulls object with a new value for field {@link #aBoolean}.<br>
	 * @param aBoolean The new value for field {@link #aBoolean}
	 * @return A new instance of {@link AllGenericNulls}
	 */
	@Generated
	public AllGenericNulls withABoolean(@Nullable Boolean aBoolean) {
		return new AllGenericNulls(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof AllGenericNulls == false) return false;
		AllGenericNulls obj = (AllGenericNulls) o;
		if(!idPart1.equals(obj.idPart1)) return false;
		if(idPart2 != obj.idPart2) return false;
		if(serSmall != obj.serSmall) return false;
		if(ser != obj.ser) return false;
		if(serBig != obj.serBig) return false;
		if(anInteger != null ? !anInteger.equals(obj.anInteger) : obj.anInteger != null) return false;
		if(aBigint != null ? !aBigint.equals(obj.aBigint) : obj.aBigint != null) return false;
		if(aDecimal72 != null ? !aDecimal72.equals(obj.aDecimal72) : obj.aDecimal72 != null) return false;
		if(aNumeric6 != null ? !aNumeric6.equals(obj.aNumeric6) : obj.aNumeric6 != null) return false;
		if(aNumeric != null ? !aNumeric.equals(obj.aNumeric) : obj.aNumeric != null) return false;
		if(aReal != null ? !aReal.equals(obj.aReal) : obj.aReal != null) return false;
		if(aDouble != null ? !aDouble.equals(obj.aDouble) : obj.aDouble != null) return false;
		if(anInt2 != null ? !anInt2.equals(obj.anInt2) : obj.anInt2 != null) return false;
		if(anInt4 != null ? !anInt4.equals(obj.anInt4) : obj.anInt4 != null) return false;
		if(anInt8 != null ? !anInt8.equals(obj.anInt8) : obj.anInt8 != null) return false;
		if(aVarchar != null ? !aVarchar.equals(obj.aVarchar) : obj.aVarchar != null) return false;
		if(aVarchar10 != null ? !aVarchar10.equals(obj.aVarchar10) : obj.aVarchar10 != null) return false;
		if(aText != null ? !aText.equals(obj.aText) : obj.aText != null) return false;
		if(aChar != null ? !aChar.equals(obj.aChar) : obj.aChar != null) return false;
		if(aChar10 != null ? !aChar10.equals(obj.aChar10) : obj.aChar10 != null) return false;
		if(aBytea != null ? !aBytea.equals(obj.aBytea) : obj.aBytea != null) return false;
		if(aTimestamp3 != null ? !aTimestamp3.equals(obj.aTimestamp3) : obj.aTimestamp3 != null) return false;
		if(aTimestamp != null ? !aTimestamp.equals(obj.aTimestamp) : obj.aTimestamp != null) return false;
		if(aTimestampWithZone != null ? !aTimestampWithZone
			.equals(obj.aTimestampWithZone) : obj.aTimestampWithZone != null) return false;
		if(aDate != null ? !aDate.equals(obj.aDate) : obj.aDate != null) return false;
		if(aTime != null ? !aTime.equals(obj.aTime) : obj.aTime != null) return false;
		if(aTimeWithZone != null ? !aTimeWithZone.equals(obj.aTimeWithZone) : obj.aTimeWithZone != null) return false;
		if(aBoolean != null ? !aBoolean.equals(obj.aBoolean) : obj.aBoolean != null) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.idPart1 != null ? this.idPart1.hashCode() : 0);
		result = 31 * result + (int) (this.idPart2 ^ (this.idPart2 >>> 32));
		result = 31 * result + (int) this.serSmall;
		result = 31 * result + this.ser;
		result = 31 * result + (int) (this.serBig ^ (this.serBig >>> 32));
		result = 31 * result + (this.anInteger != null ? this.anInteger.hashCode() : 0);
		result = 31 * result + (this.aBigint != null ? this.aBigint.hashCode() : 0);
		result = 31 * result + (this.aDecimal72 != null ? this.aDecimal72.hashCode() : 0);
		result = 31 * result + (this.aNumeric6 != null ? this.aNumeric6.hashCode() : 0);
		result = 31 * result + (this.aNumeric != null ? this.aNumeric.hashCode() : 0);
		result = 31 * result + (this.aReal != null ? this.aReal.hashCode() : 0);
		result = 31 * result + (this.aDouble != null ? this.aDouble.hashCode() : 0);
		result = 31 * result + (this.anInt2 != null ? this.anInt2.hashCode() : 0);
		result = 31 * result + (this.anInt4 != null ? this.anInt4.hashCode() : 0);
		result = 31 * result + (this.anInt8 != null ? this.anInt8.hashCode() : 0);
		result = 31 * result + (this.aVarchar != null ? this.aVarchar.hashCode() : 0);
		result = 31 * result + (this.aVarchar10 != null ? this.aVarchar10.hashCode() : 0);
		result = 31 * result + (this.aText != null ? this.aText.hashCode() : 0);
		result = 31 * result + (this.aChar != null ? this.aChar.hashCode() : 0);
		result = 31 * result + (this.aChar10 != null ? this.aChar10.hashCode() : 0);
		result = 31 * result + (this.aBytea != null ? this.aBytea.hashCode() : 0);
		result = 31 * result + (this.aTimestamp3 != null ? this.aTimestamp3.hashCode() : 0);
		result = 31 * result + (this.aTimestamp != null ? this.aTimestamp.hashCode() : 0);
		result = 31 * result + (this.aTimestampWithZone != null ? this.aTimestampWithZone.hashCode() : 0);
		result = 31 * result + (this.aDate != null ? this.aDate.hashCode() : 0);
		result = 31 * result + (this.aTime != null ? this.aTime.hashCode() : 0);
		result = 31 * result + (this.aTimeWithZone != null ? this.aTimeWithZone.hashCode() : 0);
		result = 31 * result + (this.aBoolean != null ? this.aBoolean.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "AllGenericNulls[" +
			"idPart1=" + (idPart1 == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(idPart1), 32, "...") + '\"') +
			", idPart2=" + idPart2 +
			", serSmall=" + serSmall +
			", ser=" + ser +
			", serBig=" + serBig +
			", anInteger=" + anInteger +
			", aBigint=" + aBigint +
			", aDecimal72=" + aDecimal72 +
			", aNumeric6=" + aNumeric6 +
			", aNumeric=" + aNumeric +
			", aReal=" + aReal +
			", aDouble=" + aDouble +
			", anInt2=" + anInt2 +
			", anInt4=" + anInt4 +
			", anInt8=" + anInt8 +
			", aVarchar=" + (aVarchar == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(aVarchar), 32, "...") + '\"') +
			", aVarchar10=" + (aVarchar10 == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(aVarchar10), 32, "...") + '\"') +
			", aText=" + (aText == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(aText), 32, "...") + '\"') +
			", aChar=" + (aChar == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(aChar), 32, "...") + '\"') +
			", aChar10=" + (aChar10 == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(aChar10), 32, "...") + '\"') +
			", aBytea=" + aBytea +
			", aTimestamp3=" + aTimestamp3 +
			", aTimestamp=" + aTimestamp +
			", aTimestampWithZone=" + aTimestampWithZone +
			", aDate=" + aDate + 
			", aTime=" + aTime + 
			", aTimeWithZone=" + aTimeWithZone + 
			", aBoolean=" + aBoolean + 
			']';
	}
	@Generated
	public AllGenericNulls updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setIdPart1(this.idPart1);
		b.setIdPart2(this.idPart2);
		b.setSerSmall(this.serSmall);
		b.setSer(this.ser);
		b.setSerBig(this.serBig);
		b.setAnInteger(this.anInteger);
		b.setABigint(this.aBigint);
		b.setADecimal72(this.aDecimal72);
		b.setANumeric6(this.aNumeric6);
		b.setANumeric(this.aNumeric);
		b.setAReal(this.aReal);
		b.setADouble(this.aDouble);
		b.setAnInt2(this.anInt2);
		b.setAnInt4(this.anInt4);
		b.setAnInt8(this.anInt8);
		b.setAVarchar(this.aVarchar);
		b.setAVarchar10(this.aVarchar10);
		b.setAText(this.aText);
		b.setAChar(this.aChar);
		b.setAChar10(this.aChar10);
		b.setABytea(this.aBytea);
		b.setATimestamp3(this.aTimestamp3);
		b.setATimestamp(this.aTimestamp);
		b.setATimestampWithZone(this.aTimestampWithZone);
		b.setADate(this.aDate);
		b.setATime(this.aTime);
		b.setATimeWithZone(this.aTimeWithZone);
		b.setABoolean(this.aBoolean);
		b = updater.apply(b);
		return new AllGenericNulls(b.idPart1, b.idPart2, b.serSmall, b.ser, b.serBig, b.anInteger, b.aBigint, b.aDecimal72, b.aNumeric6, b.aNumeric, b.aReal, b.aDouble, b.anInt2, b.anInt4, b.anInt8, b.aVarchar, b.aVarchar10, b.aText, b.aChar, b.aChar10, b.aBytea, b.aTimestamp3, b.aTimestamp, b.aTimestampWithZone, b.aDate, b.aTime, b.aTimeWithZone, b.aBoolean);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static AllGenericNulls build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new AllGenericNulls(b.idPart1, b.idPart2, b.serSmall, b.ser, b.serBig, b.anInteger, b.aBigint, b.aDecimal72, b.aNumeric6, b.aNumeric, b.aReal, b.aDouble, b.anInt2, b.anInt4, b.anInt8, b.aVarchar, b.aVarchar10, b.aText, b.aChar, b.aChar10, b.aBytea, b.aTimestamp3, b.aTimestamp, b.aTimestampWithZone, b.aDate, b.aTime, b.aTimeWithZone, b.aBoolean);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<AllGenericNulls> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new AllGenericNulls(b.idPart1, b.idPart2, b.serSmall, b.ser, b.serBig, b.anInteger, b.aBigint, b.aDecimal72, b.aNumeric6, b.aNumeric, b.aReal, b.aDouble, b.anInt2, b.anInt4, b.anInt8, b.aVarchar, b.aVarchar10, b.aText, b.aChar, b.aChar10, b.aBytea, b.aTimestamp3, b.aTimestamp, b.aTimestampWithZone, b.aDate, b.aTime, b.aTimeWithZone, b.aBoolean));
	}
}
