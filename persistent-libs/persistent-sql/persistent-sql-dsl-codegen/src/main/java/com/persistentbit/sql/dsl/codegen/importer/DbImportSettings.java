package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.transactions.DbTransaction;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
@CaseClass
public class DbImportSettings {

	private final Instance                instance;
	private final JavaGenTableSelection   tableSelection;
	private final Supplier<DbTransaction> transactionSupplier;
	
	
	@Generated
	public DbImportSettings(Instance instance, JavaGenTableSelection tableSelection,
							Supplier<DbTransaction> transactionSupplier) {
		this.instance = Objects.requireNonNull(instance, "instance can not be null");
			this.tableSelection = Objects.requireNonNull(tableSelection, "tableSelection can not be null");
			this.transactionSupplier = Objects.requireNonNull(transactionSupplier, "transactionSupplier can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private    Instance                   instance;
		private	JavaGenTableSelection	tableSelection;
		private	Supplier<DbTransaction>	transactionSupplier;


		public Builder<SET, _T2, _T3> setInstance(Instance instance) {
			this.instance = instance;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setTableSelection(JavaGenTableSelection tableSelection) {
			this.tableSelection	=	tableSelection;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setTransactionSupplier(Supplier<DbTransaction> transactionSupplier) {
			this.transactionSupplier	=	transactionSupplier;
			return (Builder<_T1, _T2, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #instance}.<br>
	 * @return {@link #instance}
	 */
	@Generated
	public Instance getInstance() {
		return this.instance;
	}
	/**
	 * Create a copy of this DbImportSettings object with a new value for field {@link #instance}.<br>
	 * @param instance The new value for field {@link #instance}
	 * @return A new instance of {@link DbImportSettings}
	 */
	@Generated
	public DbImportSettings withInstance(Instance instance) {
		return new DbImportSettings(instance, tableSelection, transactionSupplier);
	}
	/**
	 * Get the value of field {@link #tableSelection}.<br>
	 * @return {@link #tableSelection}
	 */
	@Generated
	public  JavaGenTableSelection	getTableSelection(){
		return this.tableSelection;
	}
	/**
	 * Create a copy of this DbImportSettings object with a new value for field {@link #tableSelection}.<br>
	 * @param tableSelection The new value for field {@link #tableSelection}
	 * @return A new instance of {@link DbImportSettings}
	 */
	@Generated
	public DbImportSettings withTableSelection(JavaGenTableSelection tableSelection) {
		return new DbImportSettings(instance, tableSelection, transactionSupplier);
	}
	/**
	 * Get the value of field {@link #transactionSupplier}.<br>
	 * @return {@link #transactionSupplier}
	 */
	@Generated
	public  Supplier<DbTransaction>	getTransactionSupplier(){
		return this.transactionSupplier;
	}
	/**
	 * Create a copy of this DbImportSettings object with a new value for field {@link #transactionSupplier}.<br>
	 * @param transactionSupplier The new value for field {@link #transactionSupplier}
	 * @return A new instance of {@link DbImportSettings}
	 */
	@Generated
	public DbImportSettings withTransactionSupplier(Supplier<DbTransaction> transactionSupplier) {
		return new DbImportSettings(instance, tableSelection, transactionSupplier);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbImportSettings == false) return false;
		DbImportSettings obj = (DbImportSettings) o;
		if(!instance.equals(obj.instance)) return false;
		if(!tableSelection.equals(obj.tableSelection)) return false;
		if(!transactionSupplier.equals(obj.transactionSupplier)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.instance != null ? this.instance.hashCode() : 0);
		result = 31 * result + (this.tableSelection != null ? this.tableSelection.hashCode() : 0);
		result = 31 * result + (this.transactionSupplier != null ? this.transactionSupplier.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "DbImportSettings[" +
			"instance=" + instance + 
			", tableSelection=" + tableSelection + 
			", transactionSupplier=" + transactionSupplier + 
			']';
	}
	@Generated
	public DbImportSettings updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setInstance(this.instance);
		b.setTableSelection(this.tableSelection);
		b.setTransactionSupplier(this.transactionSupplier);
		b = updater.apply(b);
		return new DbImportSettings(b.instance, b.tableSelection, b.transactionSupplier);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static DbImportSettings build(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbImportSettings(b.instance, b.tableSelection, b.transactionSupplier);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<DbImportSettings> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new DbImportSettings(b.instance, b.tableSelection, b.transactionSupplier));
	}
}
