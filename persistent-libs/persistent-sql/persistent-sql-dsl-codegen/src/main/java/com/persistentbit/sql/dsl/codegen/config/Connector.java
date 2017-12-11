package com.persistentbit.sql.dsl.codegen.config;

import java.lang.SuppressWarnings;
import java.util.Optional;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import java.util.function.Function;
import com.persistentbit.string.UString;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.result.Result;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.javacodegen.annotations.NoWith;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
@CaseClass
public class Connector {
	private  final	String	driverClass;
	private  final	String	url;
	@Nullable
	private  final	String	userName;
	@Nullable
	private  final	String	password;
	
	
	@Generated
	public Connector(String driverClass, String url, @Nullable String userName, @Nullable String password){
			this.driverClass = Objects.requireNonNull(driverClass, "driverClass can not be null");
			this.url = Objects.requireNonNull(url, "url can not be null");
			this.userName = userName;
			this.password = password;
	}
	@Generated
	public Connector(String driverClass, String url){
			this(driverClass, url, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2> {
		private	String	driverClass;
		private	String	url;
		private	String	userName;
		private	String	password;
		
		
		public  Builder<SET, _T2>	setDriverClass(String driverClass){
			this.driverClass	=	driverClass;
			return (Builder<SET, _T2>)this;
		}
		public  Builder<_T1, SET>	setUrl(String url){
			this.url	=	url;
			return (Builder<_T1, SET>)this;
		}
		public  Builder<_T1, _T2>	setUserName(@Nullable String userName){
			this.userName	=	userName;
			return this;
		}
		public  Builder<_T1, _T2>	setPassword(@Nullable String password){
			this.password	=	password;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #driverClass}.<br>
	 * @return {@link #driverClass}
	 */
	@Generated
	public  String	getDriverClass(){
		return this.driverClass;
	}
	/**
	 * Create a copy of this Connector object with a new value for field {@link #driverClass}.<br>
	 * @param driverClass The new value for field {@link #driverClass}
	 * @return A new instance of {@link Connector}
	 */
	@Generated
	public  Connector	withDriverClass(String driverClass){
		return new Connector(driverClass, url, userName, password);
	}
	/**
	 * Get the value of field {@link #url}.<br>
	 * @return {@link #url}
	 */
	@Generated
	public  String	getUrl(){
		return this.url;
	}
	/**
	 * Create a copy of this Connector object with a new value for field {@link #url}.<br>
	 * @param url The new value for field {@link #url}
	 * @return A new instance of {@link Connector}
	 */
	@Generated
	public  Connector	withUrl(String url){
		return new Connector(driverClass, url, userName, password);
	}
	/**
	 * Get the value of field {@link #userName}.<br>
	 * @return {@link #userName}
	 */
	@Generated
	public  Optional<String>	getUserName(){
		return Optional.ofNullable(this.userName);
	}
	/**
	 * Create a copy of this Connector object with a new value for field {@link #userName}.<br>
	 * @param userName The new value for field {@link #userName}
	 * @return A new instance of {@link Connector}
	 */
	@Generated
	public  Connector	withUserName(@Nullable String userName){
		return new Connector(driverClass, url, userName, password);
	}
	/**
	 * Get the value of field {@link #password}.<br>
	 * @return {@link #password}
	 */
	@Generated
	public  Optional<String>	getPassword(){
		return Optional.ofNullable(this.password);
	}
	/**
	 * Create a copy of this Connector object with a new value for field {@link #password}.<br>
	 * @param password The new value for field {@link #password}
	 * @return A new instance of {@link Connector}
	 */
	@Generated
	public  Connector	withPassword(@Nullable String password){
		return new Connector(driverClass, url, userName, password);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof Connector == false) return false;
		Connector obj = (Connector)o;
		if(!driverClass.equals(obj.driverClass)) return false;
		if(!url.equals(obj.url)) return false;
		if(userName != null ? !userName.equals(obj.userName) : obj.userName!= null) return false;
		if(password != null ? !password.equals(obj.password) : obj.password!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.driverClass != null ? this.driverClass.hashCode() : 0);
		result = 31 * result + (this.url != null ? this.url.hashCode() : 0);
		result = 31 * result + (this.userName != null ? this.userName.hashCode() : 0);
		result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "Connector[" + 
			"driverClass=" + (driverClass == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(driverClass),32,"...") + '\"') +
			", url=" + (url == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(url),32,"...") + '\"') +
			", userName=" + (userName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(userName),32,"...") + '\"') +
			", password=" + (password == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(password),32,"...") + '\"') +
			']';
	}
	@Generated
	public  Connector	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setDriverClass(this.driverClass);
		b.setUrl(this.url);
		b.setUserName(this.userName);
		b.setPassword(this.password);
		b = updater.apply(b);
		return new Connector(b.driverClass, b.url, b.userName, b.password);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Connector	build(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Connector(b.driverClass, b.url, b.userName, b.password);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<Connector>	buildExc(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new Connector(b.driverClass, b.url, b.userName, b.password));
	}
}
