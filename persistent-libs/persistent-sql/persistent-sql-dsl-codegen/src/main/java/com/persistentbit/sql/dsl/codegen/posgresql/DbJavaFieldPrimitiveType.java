package com.persistentbit.sql.dsl.codegen.posgresql;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.JField;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NoBuilder;
import com.persistentbit.reflection.UReflect;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.meta.data.DbMetaColumn;
import com.persistentbit.string.UString;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/07/17
 */
@CaseClass
@NoBuilder
public class DbJavaFieldPrimitiveType implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	private  final	Class	primitiveType;
	
	
	@Generated
	public DbJavaFieldPrimitiveType(DbMetaColumn column, String fieldName, Class primitiveType){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.primitiveType = Objects.requireNonNull(primitiveType, "primitiveType can not be null");
	}

	@Override
	public DbMetaColumn getDbMetaColumn() {
		return column;
	}

	@Override
	public JField createTableColumnField() {
		return new JField(fieldName,getTableColumnClass().getSimpleName())
			   .addImport(getTableColumnClass());
	}
	@Override
	public String getJavaName() {
		return fieldName;
	}


	public Class getTableColumnClass() {
		switch(primitiveType.getSimpleName()){
			case "boolean": return DExprBoolean.class;
			case "byte": return DExprByte.class;
			case "int": return DExprInt.class;
			case "short": return DExprShort.class;
			case "long": return DExprLong.class;
			case "double": return DExprDouble.class;
			default: throw new ToDo("Unknown: " + primitiveType);
		}
	}


	@Override
	public String createTableColumnFieldInitializer() {
		switch(primitiveType.getSimpleName()){
			case "boolean": return "this." + fieldName + "\t=\tcontext.createExprBoolean(this, \"" + fieldName + "\");";
			case "byte": return "this." + fieldName + "\t=\tcontext.createExprByte(this, \"" + fieldName + "\");";
			case "int": return "this." + fieldName + "\t=\tcontext.createExprInt(this, \"" + fieldName + "\");";
			case "short": return "this." + fieldName + "\t=\tcontext.createExprShort(this, \"" + fieldName + "\");";
			case "long": return "this." + fieldName + "\t=\tcontext.createExprLong(this, \"" + fieldName + "\");";
			case "double": return "this." + fieldName + "\t=\tcontext.createExprDouble(this, \"" + fieldName + "\");";
			default: throw new ToDo("Unknown: " + primitiveType);
		}
	}

	@Override
	public  JField	createJField(){
	    Class cls = primitiveType;
	    if (column.getType().getIsNullable()) {
	        cls = UReflect.convertPrimitiveClassToObjectClass(cls).get();
	    }
	    JField res = new JField(fieldName, cls);
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
	 * Create a copy of this DbJavaFieldPrimitiveType object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldPrimitiveType}
	 */
	@Generated
	public  DbJavaFieldPrimitiveType	withColumn(DbMetaColumn column){
		return new DbJavaFieldPrimitiveType(column, fieldName, primitiveType);
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
	 * Create a copy of this DbJavaFieldPrimitiveType object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldPrimitiveType}
	 */
	@Generated
	public  DbJavaFieldPrimitiveType	withFieldName(String fieldName){
		return new DbJavaFieldPrimitiveType(column, fieldName, primitiveType);
	}
	/**
	 * Get the value of field {@link #primitiveType}.<br>
	 * @return {@link #primitiveType}
	 */
	@Generated
	public  Class	getPrimitiveType(){
		return this.primitiveType;
	}
	/**
	 * Create a copy of this DbJavaFieldPrimitiveType object with a new value for field {@link #primitiveType}.<br>
	 * @param primitiveType The new value for field {@link #primitiveType}
	 * @return A new instance of {@link DbJavaFieldPrimitiveType}
	 */
	@Generated
	public  DbJavaFieldPrimitiveType	withPrimitiveType(Class primitiveType){
		return new DbJavaFieldPrimitiveType(column, fieldName, primitiveType);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldPrimitiveType == false) return false;
		DbJavaFieldPrimitiveType obj = (DbJavaFieldPrimitiveType)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!primitiveType.equals(obj.primitiveType)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.primitiveType != null ? this.primitiveType.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldPrimitiveType[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", primitiveType=" + primitiveType + 
			']';
	}
}
