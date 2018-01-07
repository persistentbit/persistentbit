package com.persistentbit.sql.meta.data;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
@CaseClass
public class DbMetaFunctionColumn{

	private final DbMetaSchema           schema;
	private final String                 functionName;
	private final String                 specificFunctionName;
	private final String                 columnName;
	private final DbMetaDataType         dataType;
	private final String                 comments;
	private final MetaFunctionColumnType columnType;
	private final int                    ordinalPostion;


	@Generated
	public DbMetaFunctionColumn(DbMetaSchema schema, String functionName, String specificFunctionName,
								String columnName, DbMetaDataType dataType, String comments,
								MetaFunctionColumnType columnType, int ordinalPostion) {
		this.schema = Objects.requireNonNull(schema, "schema can not be null");
		this.functionName = Objects.requireNonNull(functionName, "functionName can not be null");
		this.specificFunctionName =
			Objects.requireNonNull(specificFunctionName, "specificFunctionName can not be null");
		this.columnName = Objects.requireNonNull(columnName, "columnName can not be null");
		this.dataType = Objects.requireNonNull(dataType, "dataType can not be null");
		this.comments = Objects.requireNonNull(comments, "comments can not be null");
		this.columnType = Objects.requireNonNull(columnType, "columnType can not be null");
		this.ordinalPostion = Objects.requireNonNull(ordinalPostion, "ordinalPostion can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8>{

		private DbMetaSchema           schema;
		private String                 functionName;
		private String                 specificFunctionName;
		private String                 columnName;
		private DbMetaDataType         dataType;
		private String                 comments;
		private MetaFunctionColumnType columnType;
		private int                    ordinalPostion;


		public Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7, _T8> setSchema(DbMetaSchema schema) {
			this.schema = schema;
			return (Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7, _T8> setFunctionName(String functionName) {
			this.functionName = functionName;
			return (Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7, _T8> setSpecificFunctionName(String specificFunctionName) {
			this.specificFunctionName = specificFunctionName;
			return (Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7, _T8> setColumnName(String columnName) {
			this.columnName = columnName;
			return (Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7, _T8> setDataType(DbMetaDataType dataType) {
			this.dataType = dataType;
			return (Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7, _T8> setComments(String comments) {
			this.comments = comments;
			return (Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET, _T8> setColumnType(MetaFunctionColumnType columnType) {
			this.columnType = columnType;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, SET> setOrdinalPostion(int ordinalPostion) {
			this.ordinalPostion = ordinalPostion;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #schema}.<br>
	 *
	 * @return {@link #schema}
	 */
	@Generated
	public DbMetaSchema getSchema() {
		return this.schema;
	}

	/**
	 * Create a copy of this DbMetaFunctionColumn object with a new value for field {@link #schema}.<br>
	 *
	 * @param schema The new value for field {@link #schema}
	 *
	 * @return A new instance of {@link DbMetaFunctionColumn}
	 */
	@Generated
	public DbMetaFunctionColumn withSchema(DbMetaSchema schema) {
		return new DbMetaFunctionColumn(schema, functionName, specificFunctionName, columnName, dataType, comments, columnType, ordinalPostion);
	}

	/**
	 * Get the value of field {@link #functionName}.<br>
	 *
	 * @return {@link #functionName}
	 */
	@Generated
	public String getFunctionName() {
		return this.functionName;
	}

	/**
	 * Create a copy of this DbMetaFunctionColumn object with a new value for field {@link #functionName}.<br>
	 *
	 * @param functionName The new value for field {@link #functionName}
	 *
	 * @return A new instance of {@link DbMetaFunctionColumn}
	 */
	@Generated
	public DbMetaFunctionColumn withFunctionName(String functionName) {
		return new DbMetaFunctionColumn(schema, functionName, specificFunctionName, columnName, dataType, comments, columnType, ordinalPostion);
	}

	/**
	 * Get the value of field {@link #specificFunctionName}.<br>
	 *
	 * @return {@link #specificFunctionName}
	 */
	@Generated
	public String getSpecificFunctionName() {
		return this.specificFunctionName;
	}

	/**
	 * Create a copy of this DbMetaFunctionColumn object with a new value for field {@link #specificFunctionName}.<br>
	 *
	 * @param specificFunctionName The new value for field {@link #specificFunctionName}
	 *
	 * @return A new instance of {@link DbMetaFunctionColumn}
	 */
	@Generated
	public DbMetaFunctionColumn withSpecificFunctionName(String specificFunctionName) {
		return new DbMetaFunctionColumn(schema, functionName, specificFunctionName, columnName, dataType, comments, columnType, ordinalPostion);
	}

	/**
	 * Get the value of field {@link #columnName}.<br>
	 *
	 * @return {@link #columnName}
	 */
	@Generated
	public String getColumnName() {
		return this.columnName;
	}

	/**
	 * Create a copy of this DbMetaFunctionColumn object with a new value for field {@link #columnName}.<br>
	 *
	 * @param columnName The new value for field {@link #columnName}
	 *
	 * @return A new instance of {@link DbMetaFunctionColumn}
	 */
	@Generated
	public DbMetaFunctionColumn withColumnName(String columnName) {
		return new DbMetaFunctionColumn(schema, functionName, specificFunctionName, columnName, dataType, comments, columnType, ordinalPostion);
	}

	/**
	 * Get the value of field {@link #dataType}.<br>
	 *
	 * @return {@link #dataType}
	 */
	@Generated
	public DbMetaDataType getDataType() {
		return this.dataType;
	}

	/**
	 * Create a copy of this DbMetaFunctionColumn object with a new value for field {@link #dataType}.<br>
	 *
	 * @param dataType The new value for field {@link #dataType}
	 *
	 * @return A new instance of {@link DbMetaFunctionColumn}
	 */
	@Generated
	public DbMetaFunctionColumn withDataType(DbMetaDataType dataType) {
		return new DbMetaFunctionColumn(schema, functionName, specificFunctionName, columnName, dataType, comments, columnType, ordinalPostion);
	}

	/**
	 * Get the value of field {@link #comments}.<br>
	 *
	 * @return {@link #comments}
	 */
	@Generated
	public String getComments() {
		return this.comments;
	}

	/**
	 * Create a copy of this DbMetaFunctionColumn object with a new value for field {@link #comments}.<br>
	 *
	 * @param comments The new value for field {@link #comments}
	 *
	 * @return A new instance of {@link DbMetaFunctionColumn}
	 */
	@Generated
	public DbMetaFunctionColumn withComments(String comments) {
		return new DbMetaFunctionColumn(schema, functionName, specificFunctionName, columnName, dataType, comments, columnType, ordinalPostion);
	}

	/**
	 * Get the value of field {@link #columnType}.<br>
	 *
	 * @return {@link #columnType}
	 */
	@Generated
	public MetaFunctionColumnType getColumnType() {
		return this.columnType;
	}

	/**
	 * Create a copy of this DbMetaFunctionColumn object with a new value for field {@link #columnType}.<br>
	 *
	 * @param columnType The new value for field {@link #columnType}
	 *
	 * @return A new instance of {@link DbMetaFunctionColumn}
	 */
	@Generated
	public DbMetaFunctionColumn withColumnType(MetaFunctionColumnType columnType) {
		return new DbMetaFunctionColumn(schema, functionName, specificFunctionName, columnName, dataType, comments, columnType, ordinalPostion);
	}

	/**
	 * Get the value of field {@link #ordinalPostion}.<br>
	 *
	 * @return {@link #ordinalPostion}
	 */
	@Generated
	public int getOrdinalPostion() {
		return this.ordinalPostion;
	}

	/**
	 * Create a copy of this DbMetaFunctionColumn object with a new value for field {@link #ordinalPostion}.<br>
	 *
	 * @param ordinalPostion The new value for field {@link #ordinalPostion}
	 *
	 * @return A new instance of {@link DbMetaFunctionColumn}
	 */
	@Generated
	public DbMetaFunctionColumn withOrdinalPostion(int ordinalPostion) {
		return new DbMetaFunctionColumn(schema, functionName, specificFunctionName, columnName, dataType, comments, columnType, ordinalPostion);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof DbMetaFunctionColumn == false) return false;
		DbMetaFunctionColumn obj = (DbMetaFunctionColumn) o;
		if(!schema.equals(obj.schema)) return false;
		if(!functionName.equals(obj.functionName)) return false;
		if(!specificFunctionName.equals(obj.specificFunctionName)) return false;
		if(!columnName.equals(obj.columnName)) return false;
		if(!dataType.equals(obj.dataType)) return false;
		if(!comments.equals(obj.comments)) return false;
		if(!columnType.equals(obj.columnType)) return false;
		if(ordinalPostion != obj.ordinalPostion) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.schema != null ? this.schema.hashCode() : 0);
		result = 31 * result + (this.functionName != null ? this.functionName.hashCode() : 0);
		result = 31 * result + (this.specificFunctionName != null ? this.specificFunctionName.hashCode() : 0);
		result = 31 * result + (this.columnName != null ? this.columnName.hashCode() : 0);
		result = 31 * result + (this.dataType != null ? this.dataType.hashCode() : 0);
		result = 31 * result + (this.comments != null ? this.comments.hashCode() : 0);
		result = 31 * result + (this.columnType != null ? this.columnType.hashCode() : 0);
		result = 31 * result + this.ordinalPostion;
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "DbMetaFunctionColumn[" +
			"schema=" + schema +
			", functionName=" + (functionName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(functionName), 32, "...") + '\"') +
			", specificFunctionName=" + (specificFunctionName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(specificFunctionName), 32, "...") + '\"') +
			", columnName=" + (columnName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(columnName), 32, "...") + '\"') +
			", dataType=" + dataType +
			", comments=" + (comments == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(comments), 32, "...") + '\"') +
			", columnType=" + columnType +
			", ordinalPostion=" + ordinalPostion +
			']';
	}

	@Generated
	public DbMetaFunctionColumn updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setSchema(this.schema);
		b.setFunctionName(this.functionName);
		b.setSpecificFunctionName(this.specificFunctionName);
		b.setColumnName(this.columnName);
		b.setDataType(this.dataType);
		b.setComments(this.comments);
		b.setColumnType(this.columnType);
		b.setOrdinalPostion(this.ordinalPostion);
		b = updater.apply(b);
		return new DbMetaFunctionColumn(b.schema, b.functionName, b.specificFunctionName, b.columnName, b.dataType, b.comments, b.columnType, b.ordinalPostion);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static DbMetaFunctionColumn build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaFunctionColumn(b.schema, b.functionName, b.specificFunctionName, b.columnName, b.dataType, b.comments, b.columnType, b.ordinalPostion);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<DbMetaFunctionColumn> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new DbMetaFunctionColumn(b.schema, b.functionName, b.specificFunctionName, b.columnName, b.dataType, b.comments, b.columnType, b.ordinalPostion));
	}
}
