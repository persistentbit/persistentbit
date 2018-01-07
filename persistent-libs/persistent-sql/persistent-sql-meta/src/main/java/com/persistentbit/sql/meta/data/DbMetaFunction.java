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
import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
@CaseClass
public class DbMetaFunction{

	private final DbMetaSchema     schema;
	private final MetaFunctionType functionType;
	@Nullable
	private final String           comment;
	private final String           name;
	private final String           specificName;


	@Generated
	public DbMetaFunction(DbMetaSchema schema, MetaFunctionType functionType, @Nullable String comment, String name,
						  String specificName) {
		this.schema = Objects.requireNonNull(schema, "schema can not be null");
		this.functionType = Objects.requireNonNull(functionType, "functionType can not be null");
		this.comment = comment;
		this.name = Objects.requireNonNull(name, "name can not be null");
		this.specificName = Objects.requireNonNull(specificName, "specificName can not be null");
	}

	@Generated
	public DbMetaFunction(DbMetaSchema schema, MetaFunctionType functionType, String name, String specificName) {
		this(schema, functionType, null, name, specificName);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4>{

		private DbMetaSchema     schema;
		private MetaFunctionType functionType;
		private String           comment;
		private String           name;
		private String           specificName;


		public Builder<SET, _T2, _T3, _T4> setSchema(DbMetaSchema schema) {
			this.schema = schema;
			return (Builder<SET, _T2, _T3, _T4>) this;
		}

		public Builder<_T1, SET, _T3, _T4> setFunctionType(MetaFunctionType functionType) {
			this.functionType = functionType;
			return (Builder<_T1, SET, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, _T4> setComment(@Nullable String comment) {
			this.comment = comment;
			return this;
		}

		public Builder<_T1, _T2, SET, _T4> setName(String name) {
			this.name = name;
			return (Builder<_T1, _T2, SET, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, SET> setSpecificName(String specificName) {
			this.specificName = specificName;
			return (Builder<_T1, _T2, _T3, SET>) this;
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
	 * Create a copy of this DbMetaFunction object with a new value for field {@link #schema}.<br>
	 *
	 * @param schema The new value for field {@link #schema}
	 *
	 * @return A new instance of {@link DbMetaFunction}
	 */
	@Generated
	public DbMetaFunction withSchema(DbMetaSchema schema) {
		return new DbMetaFunction(schema, functionType, comment, name, specificName);
	}

	/**
	 * Get the value of field {@link #functionType}.<br>
	 *
	 * @return {@link #functionType}
	 */
	@Generated
	public MetaFunctionType getFunctionType() {
		return this.functionType;
	}

	/**
	 * Create a copy of this DbMetaFunction object with a new value for field {@link #functionType}.<br>
	 *
	 * @param functionType The new value for field {@link #functionType}
	 *
	 * @return A new instance of {@link DbMetaFunction}
	 */
	@Generated
	public DbMetaFunction withFunctionType(MetaFunctionType functionType) {
		return new DbMetaFunction(schema, functionType, comment, name, specificName);
	}

	/**
	 * Get the value of field {@link #comment}.<br>
	 *
	 * @return {@link #comment}
	 */
	@Generated
	public Optional<String> getComment() {
		return Optional.ofNullable(this.comment);
	}

	/**
	 * Create a copy of this DbMetaFunction object with a new value for field {@link #comment}.<br>
	 *
	 * @param comment The new value for field {@link #comment}
	 *
	 * @return A new instance of {@link DbMetaFunction}
	 */
	@Generated
	public DbMetaFunction withComment(@Nullable String comment) {
		return new DbMetaFunction(schema, functionType, comment, name, specificName);
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
	 * Create a copy of this DbMetaFunction object with a new value for field {@link #name}.<br>
	 *
	 * @param name The new value for field {@link #name}
	 *
	 * @return A new instance of {@link DbMetaFunction}
	 */
	@Generated
	public DbMetaFunction withName(String name) {
		return new DbMetaFunction(schema, functionType, comment, name, specificName);
	}

	/**
	 * Get the value of field {@link #specificName}.<br>
	 *
	 * @return {@link #specificName}
	 */
	@Generated
	public String getSpecificName() {
		return this.specificName;
	}

	/**
	 * Create a copy of this DbMetaFunction object with a new value for field {@link #specificName}.<br>
	 *
	 * @param specificName The new value for field {@link #specificName}
	 *
	 * @return A new instance of {@link DbMetaFunction}
	 */
	@Generated
	public DbMetaFunction withSpecificName(String specificName) {
		return new DbMetaFunction(schema, functionType, comment, name, specificName);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof DbMetaFunction == false) return false;
		DbMetaFunction obj = (DbMetaFunction) o;
		if(!schema.equals(obj.schema)) return false;
		if(!functionType.equals(obj.functionType)) return false;
		if(comment != null ? !comment.equals(obj.comment) : obj.comment != null) return false;
		if(!name.equals(obj.name)) return false;
		if(!specificName.equals(obj.specificName)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.schema != null ? this.schema.hashCode() : 0);
		result = 31 * result + (this.functionType != null ? this.functionType.hashCode() : 0);
		result = 31 * result + (this.comment != null ? this.comment.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.specificName != null ? this.specificName.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "DbMetaFunction[" +
			"schema=" + schema +
			", functionType=" + functionType +
			", comment=" + (comment == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(comment), 32, "...") + '\"') +
			", name=" + (name == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(name), 32, "...") + '\"') +
			", specificName=" + (specificName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(specificName), 32, "...") + '\"') +
			']';
	}

	@Generated
	public DbMetaFunction updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setSchema(this.schema);
		b.setFunctionType(this.functionType);
		b.setComment(this.comment);
		b.setName(this.name);
		b.setSpecificName(this.specificName);
		b = updater.apply(b);
		return new DbMetaFunction(b.schema, b.functionType, b.comment, b.name, b.specificName);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static DbMetaFunction build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaFunction(b.schema, b.functionType, b.comment, b.name, b.specificName);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<DbMetaFunction> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new DbMetaFunction(b.schema, b.functionType, b.comment, b.name, b.specificName));
	}
}
