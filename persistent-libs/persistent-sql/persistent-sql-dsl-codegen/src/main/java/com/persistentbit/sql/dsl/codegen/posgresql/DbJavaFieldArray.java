package com.persistentbit.sql.dsl.codegen.posgresql;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JField;
import com.persistentbit.javacodegen.JImport;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NoBuilder;
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
public class DbJavaFieldArray implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	private  final	DbJavaField	elementField;
	
	
	@Generated
	public DbJavaFieldArray(DbMetaColumn column, String fieldName, DbJavaField elementField){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.elementField = Objects.requireNonNull(elementField, "elementField can not be null");
	}

	@Override
	public DbMetaColumn getDbMetaColumn() {
		return column;
	}


	@Override
	public String getJavaName() {
		return fieldName;
	}

	@Override
	public  JField	createJField(boolean allowPrimitives){
	    JField el = elementField.createJField(true);
	    JField f = new JField(fieldName, "PList<" + el.getDefinition() + ">");
	    for (JImport imp : el.getAllImports()) {
	        f = f.addImport(imp);
	    }
	    if (column.getType().getIsNullable()) {
	        f = f.asNullable();
	    }
	    f = f.addImport(PList.class);
	    return f;
	}



	@Override
	public JField createTableColumnField() {
		throw new ToDo(this.toString());
	}

	@Override
	public String createTableColumnFieldInitializer(String tableContext) {
		throw new ToDo(this.toString());
	}

	@Override
	public  PList<DbJavaFieldStruct>	getStructures(){
	    return elementField.getStructures();
	}
	@Override
	public  PList<DbJavaFieldEnum>	getUsedEnums(){
	    return elementField.getUsedEnums();
	}
	@Override
	public  PList<DbJavaFieldDomain>	getDomains(){
	    return elementField.getDomains();
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
	 * Create a copy of this DbJavaFieldArray object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldArray}
	 */
	@Generated
	public  DbJavaFieldArray	withColumn(DbMetaColumn column){
		return new DbJavaFieldArray(column, fieldName, elementField);
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
	 * Create a copy of this DbJavaFieldArray object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldArray}
	 */
	@Generated
	public  DbJavaFieldArray	withFieldName(String fieldName){
		return new DbJavaFieldArray(column, fieldName, elementField);
	}
	/**
	 * Get the value of field {@link #elementField}.<br>
	 * @return {@link #elementField}
	 */
	@Generated
	public  DbJavaField	getElementField(){
		return this.elementField;
	}
	/**
	 * Create a copy of this DbJavaFieldArray object with a new value for field {@link #elementField}.<br>
	 * @param elementField The new value for field {@link #elementField}
	 * @return A new instance of {@link DbJavaFieldArray}
	 */
	@Generated
	public  DbJavaFieldArray	withElementField(DbJavaField elementField){
		return new DbJavaFieldArray(column, fieldName, elementField);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldArray == false) return false;
		DbJavaFieldArray obj = (DbJavaFieldArray)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!elementField.equals(obj.elementField)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.elementField != null ? this.elementField.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldArray[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", elementField=" + elementField + 
			']';
	}
}
