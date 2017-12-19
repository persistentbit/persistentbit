package com.persistentbit.sql.dsl.generic_old.inserts;

import com.persistentbit.javacodegen.annotations.NoBuilder;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.NoWith;

/**
 * TODOC
 *
 * @author petermuys
 * @since 8/12/17
 */
@CaseClass
@NoBuilder
@NoWith
public class InsertResult {
	private  final	int	updateCount;
	private  final	PList<PList<Object>>	autoGenKeys;
	
	
	@Generated
	public InsertResult(int updateCount, PList<PList<Object>> autoGenKeys){
			this.updateCount = Objects.requireNonNull(updateCount, "updateCount can not be null");
			this.autoGenKeys = Objects.requireNonNull(autoGenKeys, "autoGenKeys can not be null");
	}
	public  static InsertResult	empty(){
	    return new InsertResult(0, PList.empty());
	}
	public  InsertResult	add(InsertResult other){
	    return new InsertResult(updateCount + other.updateCount, autoGenKeys.plusAll(other.autoGenKeys));
	}
	/**
	 * Get the value of field {@link #updateCount}.<br>
	 * @return {@link #updateCount}
	 */
	@Generated
	public  int	getUpdateCount(){
		return this.updateCount;
	}
	/**
	 * Get the value of field {@link #autoGenKeys}.<br>
	 * @return {@link #autoGenKeys}
	 */
	@Generated
	public  PList<PList<Object>>	getAutoGenKeys(){
		return this.autoGenKeys;
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof InsertResult == false) return false;
		InsertResult obj = (InsertResult)o;
		if(updateCount!= obj.updateCount) return false;
		if(!autoGenKeys.equals(obj.autoGenKeys)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = this.updateCount;
		result = 31 * result + (this.autoGenKeys != null ? this.autoGenKeys.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "InsertResult[" + 
			"updateCount=" + updateCount + 
			", autoGenKeys=" + autoGenKeys + 
			']';
	}
}
