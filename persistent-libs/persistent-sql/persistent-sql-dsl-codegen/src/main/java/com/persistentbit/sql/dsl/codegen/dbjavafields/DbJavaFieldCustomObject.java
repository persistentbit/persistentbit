package com.persistentbit.sql.dsl.codegen.dbjavafields;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.JField;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NoBuilder;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.meta.data.DbMetaColumn;
import com.persistentbit.string.UString;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.Objects;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/07/17
 */
@CaseClass
@NoBuilder
public class DbJavaFieldCustomObject implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	private  final	Class	javaClass;
	@Nullable
	private  final	Class	javaSubType;
	
	
	@Generated
	public DbJavaFieldCustomObject(DbMetaColumn column, String fieldName, Class javaClass, @Nullable Class javaSubType){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.javaClass = Objects.requireNonNull(javaClass, "javaClass can not be null");
			this.javaSubType = javaSubType;
	}
	@Generated
	public DbJavaFieldCustomObject(DbMetaColumn column, String fieldName, Class javaClass){
			this(column, fieldName, javaClass, null);
	}
	@Override
	public  DbMetaColumn	getDbMetaColumn(){
	    return column;
	}
	@Override
	public  String	getJavaName(){
	    return fieldName;
	}
	@Override
	public  JField	createTableColumnField(){
	    JField f;
	    switch(javaClass.getSimpleName()) {
	        case "String":
	            f = new JField(fieldName, DExprString.class).addImport(DExprString.class);
	            break;
	        case "Boolean":
	            f = new JField(fieldName, DExprBoolean.class).addImport(DExprBoolean.class);
	            break;
	        case "Byte":
	            f = new JField(fieldName, DExprByte.class).addImport(DExprByte.class);
	            break;
	        case "Short":
	            f = new JField(fieldName, DExprShort.class).addImport(DExprShort.class);
	            break;
	        case "Integer":
	            f = new JField(fieldName, DExprInt.class).addImport(DExprInt.class);
	            break;
	        case "Long":
	            f = new JField(fieldName, DExprLong.class).addImport(DExprLong.class);
	            break;
	        case "Double":
	            f = new JField(fieldName, DExprDouble.class).addImport(DExprDouble.class);
	            break;
	        case "BigDecimal":
	            f = new JField(fieldName, DExprBigDecimal.class).addImport(DExprBigDecimal.class);
	            break;
	        case "LocalDateTime":
	            f = new JField(fieldName, DExprDateTime.class).addImport(DExprDateTime.class);
	            break;
	        default:
	            throw new ToDo("Unknown: " + javaClass + " for " + column);
	    }
	    return f;
	}
	@Override
	public  String	createTableColumnFieldInitializer(String tableContext){
	    String pre = tableContext + ".createExpr";
	    String post = "(\"" + column.getName() + "\")";
	    switch(javaClass.getSimpleName()) {
	        case "String":
	            return pre + "String" + post;
	        case "Boolean":
	            return pre + "Boolean" + post;
	        case "Byte":
	            return pre + "Byte" + post;
	        case "Short":
	            return pre + "Short" + post;
	        case "Integer":
	            return pre + "Integer" + post;
	        case "Long":
	            return pre + "Long" + post;
	        case "Double":
	            return pre + "Double" + post;
	        case "BigDecimal":
	            return pre + "BigDecimal" + post;
	        case "LocalDateTime":
	            return pre + "DateTime" + post;
	        default:
	            throw new ToDo("Unknown: " + javaClass);
	    }
	}
	@Override
	public  JField	createJField(boolean allowPrimitives){
	    JField res = new JField(fieldName, javaClass.getSimpleName());
	    res = res.addImport(javaClass);
	    if (column.getType().getIsNullable()) {
	        res = res.asNullable();
	    }
	    return res;
	}
	/**
	 * Get the value of field {@link #column}.<br>
	 * @return {@link #column}
	 */
	@Generated
	public  DbMetaColumn	getColumn(){
		return this.column;
	}
	/**
	 * Create a copy of this DbJavaFieldCustomObject object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldCustomObject}
	 */
	@Generated
	public  DbJavaFieldCustomObject	withColumn(DbMetaColumn column){
		return new DbJavaFieldCustomObject(column, fieldName, javaClass, javaSubType);
	}
	/**
	 * Get the value of field {@link #fieldName}.<br>
	 * @return {@link #fieldName}
	 */
	@Generated
	public  String	getFieldName(){
		return this.fieldName;
	}
	/**
	 * Create a copy of this DbJavaFieldCustomObject object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldCustomObject}
	 */
	@Generated
	public  DbJavaFieldCustomObject	withFieldName(String fieldName){
		return new DbJavaFieldCustomObject(column, fieldName, javaClass, javaSubType);
	}
	/**
	 * Get the value of field {@link #javaClass}.<br>
	 * @return {@link #javaClass}
	 */
	@Generated
	public  Class	getJavaClass(){
		return this.javaClass;
	}
	/**
	 * Create a copy of this DbJavaFieldCustomObject object with a new value for field {@link #javaClass}.<br>
	 * @param javaClass The new value for field {@link #javaClass}
	 * @return A new instance of {@link DbJavaFieldCustomObject}
	 */
	@Generated
	public  DbJavaFieldCustomObject	withJavaClass(Class javaClass){
		return new DbJavaFieldCustomObject(column, fieldName, javaClass, javaSubType);
	}
	/**
	 * Get the value of field {@link #javaSubType}.<br>
	 * @return {@link #javaSubType}
	 */
	@Generated
	public  Optional<Class>	getJavaSubType(){
		return Optional.ofNullable(this.javaSubType);
	}
	/**
	 * Create a copy of this DbJavaFieldCustomObject object with a new value for field {@link #javaSubType}.<br>
	 * @param javaSubType The new value for field {@link #javaSubType}
	 * @return A new instance of {@link DbJavaFieldCustomObject}
	 */
	@Generated
	public  DbJavaFieldCustomObject	withJavaSubType(@Nullable Class javaSubType){
		return new DbJavaFieldCustomObject(column, fieldName, javaClass, javaSubType);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldCustomObject == false) return false;
		DbJavaFieldCustomObject obj = (DbJavaFieldCustomObject)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!javaClass.equals(obj.javaClass)) return false;
		if(javaSubType != null ? !javaSubType.equals(obj.javaSubType) : obj.javaSubType!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.javaClass != null ? this.javaClass.hashCode() : 0);
		result = 31 * result + (this.javaSubType != null ? this.javaSubType.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldCustomObject[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", javaClass=" + javaClass + 
			", javaSubType=" + javaSubType + 
			']';
	}
}
