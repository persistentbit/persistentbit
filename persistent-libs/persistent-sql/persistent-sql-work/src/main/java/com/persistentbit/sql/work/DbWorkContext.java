package com.persistentbit.sql.work;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.*;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.string.UString;

import java.sql.Connection;
import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/06/17
 */
@CaseClass
@NoBuilder
public class DbWorkContext {
	private  final	DbTransaction	transaction;

	@Nullable
	@NoGet
	private  final	String	schema;
	
	
	DbWorkContext(DbTransaction transaction, @Nullable String schema){
	    this.transaction = Objects.requireNonNull(transaction);
	    this.dbType = Objects.requireNonNull(dbType);
	    this.schema = schema;
	}
	@Generated
	public DbWorkContext(DbTransaction transaction, Result<WorkDbType> dbType){
			this(transaction, dbType, null);
	}
	public  static DbWorkContext	create(DbConnector connector, WorkDbType type){
	    return new DbWorkContext(DbTransaction.create(connector), Result.result(type), null);
	}
	public  static DbWorkContext	create(DbConnector connector){
	    return create(connector, null);
	}
	public  DbWorkContext	withSchema(String schema){
	    return new DbWorkContext(transaction, dbType, schema);
	}
	public  DbWorkContext	newTransaction(){
	    return new DbWorkContext(transaction.createNew(), dbType, schema);
	}
	public  Result<Connection>	get(){
	    return transaction.get();
	}
	public  Result<WorkDbType>	getDbType(){
	    return dbType;
	}
	public  Result<String>	getSchema(){
	    return Result.result(schema);
	}
	public  String	getFullTableName(String tableName){
	    return schema == null ? tableName : schema + "." + tableName;
	}
	/**
	 * Get the value of field {@link #transaction}.<br>
	 * @return {@link #transaction}
	 */
	@Generated
	public  DbTransaction	getTransaction(){
		return this.transaction;
	}
	/**
	 * Create a copy of this DbWorkContext object with a new value for field {@link #transaction}.<br>
	 * @param transaction The new value for field {@link #transaction}
	 * @return A new instance of {@link DbWorkContext}
	 */
	@Generated
	public  DbWorkContext	withTransaction(DbTransaction transaction){
		return new DbWorkContext(transaction, dbType, schema);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbWorkContext == false) return false;
		DbWorkContext obj = (DbWorkContext)o;
		if(!transaction.equals(obj.transaction)) return false;
		if(!dbType.equals(obj.dbType)) return false;
		if(schema != null ? !schema.equals(obj.schema) : obj.schema!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.transaction != null ? this.transaction.hashCode() : 0);
		result = 31 * result + (this.dbType != null ? this.dbType.hashCode() : 0);
		result = 31 * result + (this.schema != null ? this.schema.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbWorkContext[" + 
			"transaction=" + transaction + 
			", dbType=" + dbType + 
			", schema=" + (schema == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(schema),32,"...") + '\"') +
			']';
	}
}
