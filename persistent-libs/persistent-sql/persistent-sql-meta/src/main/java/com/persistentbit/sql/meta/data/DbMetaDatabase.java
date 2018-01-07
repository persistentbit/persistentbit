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
 * @since 10/12/17
 */
@CaseClass
public class DbMetaDatabase {

	private final String  productName;
	private final int     dbMajorVersion;
	private final int     dbMinorVersion;
	private final String  driveName;
	private final int     driverMajorVersion;
	private final int     driverMinorVersion;
	private final int     jdbcMajorVersion;
	private final int     jdbcMinorVersion;
	@Nullable
	private final String  url;
	private final boolean supportsGetGeneratedKeys;
	private final String  catalogSeparator;
	private final String  schemaTerm;
	private final String  catalogTerm;
	
	
	@Generated
	public DbMetaDatabase(String productName, int dbMajorVersion, int dbMinorVersion, String driveName,
						  int driverMajorVersion, int driverMinorVersion, int jdbcMajorVersion, int jdbcMinorVersion,
						  @Nullable String url, boolean supportsGetGeneratedKeys, String catalogSeparator,
						  String schemaTerm, String catalogTerm) {
			this.productName = Objects.requireNonNull(productName, "productName can not be null");
			this.dbMajorVersion = Objects.requireNonNull(dbMajorVersion, "dbMajorVersion can not be null");
			this.dbMinorVersion = Objects.requireNonNull(dbMinorVersion, "dbMinorVersion can not be null");
			this.driveName = Objects.requireNonNull(driveName, "driveName can not be null");
			this.driverMajorVersion = Objects.requireNonNull(driverMajorVersion, "driverMajorVersion can not be null");
			this.driverMinorVersion = Objects.requireNonNull(driverMinorVersion, "driverMinorVersion can not be null");
			this.jdbcMajorVersion = Objects.requireNonNull(jdbcMajorVersion, "jdbcMajorVersion can not be null");
			this.jdbcMinorVersion = Objects.requireNonNull(jdbcMinorVersion, "jdbcMinorVersion can not be null");
			this.url = url;
			this.supportsGetGeneratedKeys = Objects.requireNonNull(supportsGetGeneratedKeys, "supportsGetGeneratedKeys can not be null");
			this.catalogSeparator = Objects.requireNonNull(catalogSeparator, "catalogSeparator can not be null");
		this.schemaTerm = Objects.requireNonNull(schemaTerm, "schemaTerm can not be null");
		this.catalogTerm = Objects.requireNonNull(catalogTerm, "catalogTerm can not be null");
	}
	@Generated
	public DbMetaDatabase(String productName, int dbMajorVersion, int dbMinorVersion, String driveName,
						  int driverMajorVersion, int driverMinorVersion, int jdbcMajorVersion, int jdbcMinorVersion,
						  boolean supportsGetGeneratedKeys, String catalogSeparator, String schemaTerm,
						  String catalogTerm) {
		this(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, null, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>{

		private String  productName;
		private int     dbMajorVersion;
		private int     dbMinorVersion;
		private String  driveName;
		private int     driverMajorVersion;
		private int     driverMinorVersion;
		private int     jdbcMajorVersion;
		private int     jdbcMinorVersion;
		private String  url;
		private boolean supportsGetGeneratedKeys;
		private String  catalogSeparator;
		private String  schemaTerm;
		private String  catalogTerm;


		public Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setProductName(
			String productName) {
			this.productName	=	productName;
			return (Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setDbMajorVersion(
			int dbMajorVersion) {
			this.dbMajorVersion	=	dbMajorVersion;
			return (Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setDbMinorVersion(
			int dbMinorVersion) {
			this.dbMinorVersion	=	dbMinorVersion;
			return (Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setDriveName(String driveName) {
			this.driveName	=	driveName;
			return (Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setDriverMajorVersion(
			int driverMajorVersion) {
			this.driverMajorVersion	=	driverMajorVersion;
			return (Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7, _T8, _T9, _T10, _T11, _T12> setDriverMinorVersion(
			int driverMinorVersion) {
			this.driverMinorVersion	=	driverMinorVersion;
			return (Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET, _T8, _T9, _T10, _T11, _T12> setJdbcMajorVersion(
			int jdbcMajorVersion) {
			this.jdbcMajorVersion	=	jdbcMajorVersion;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET, _T8, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, SET, _T9, _T10, _T11, _T12> setJdbcMinorVersion(
			int jdbcMinorVersion) {
			this.jdbcMinorVersion	=	jdbcMinorVersion;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, SET, _T9, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, _T12> setUrl(@Nullable String url) {
			this.url	=	url;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, SET, _T10, _T11, _T12> setSupportsGetGeneratedKeys(
			boolean supportsGetGeneratedKeys) {
			this.supportsGetGeneratedKeys	=	supportsGetGeneratedKeys;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, SET, _T10, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, SET, _T11, _T12> setCatalogSeparator(
			String catalogSeparator) {
			this.catalogSeparator	=	catalogSeparator;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, SET, _T11, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, SET, _T12> setSchemaTerm(String schemaTerm) {
			this.schemaTerm = schemaTerm;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, SET, _T12>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, SET> setCatalogTerm(
			String catalogTerm) {
			this.catalogTerm = catalogTerm;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _T10, _T11, SET>) this;
		}
	}

	public boolean usesSchemas() {
		return schemaTerm.isEmpty() == false;
	}
	/**
	 * Get the value of field {@link #productName}.<br>
	 * @return {@link #productName}
	 */
	@Generated
	public  String	getProductName(){
		return this.productName;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #productName}.<br>
	 * @param productName The new value for field {@link #productName}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withProductName(String productName){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #dbMajorVersion}.<br>
	 * @return {@link #dbMajorVersion}
	 */
	@Generated
	public  int	getDbMajorVersion(){
		return this.dbMajorVersion;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #dbMajorVersion}.<br>
	 * @param dbMajorVersion The new value for field {@link #dbMajorVersion}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withDbMajorVersion(int dbMajorVersion){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #dbMinorVersion}.<br>
	 * @return {@link #dbMinorVersion}
	 */
	@Generated
	public  int	getDbMinorVersion(){
		return this.dbMinorVersion;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #dbMinorVersion}.<br>
	 * @param dbMinorVersion The new value for field {@link #dbMinorVersion}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withDbMinorVersion(int dbMinorVersion){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #driveName}.<br>
	 * @return {@link #driveName}
	 */
	@Generated
	public  String	getDriveName(){
		return this.driveName;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #driveName}.<br>
	 * @param driveName The new value for field {@link #driveName}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withDriveName(String driveName){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #driverMajorVersion}.<br>
	 * @return {@link #driverMajorVersion}
	 */
	@Generated
	public  int	getDriverMajorVersion(){
		return this.driverMajorVersion;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #driverMajorVersion}.<br>
	 * @param driverMajorVersion The new value for field {@link #driverMajorVersion}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withDriverMajorVersion(int driverMajorVersion){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #driverMinorVersion}.<br>
	 * @return {@link #driverMinorVersion}
	 */
	@Generated
	public  int	getDriverMinorVersion(){
		return this.driverMinorVersion;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #driverMinorVersion}.<br>
	 * @param driverMinorVersion The new value for field {@link #driverMinorVersion}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withDriverMinorVersion(int driverMinorVersion){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #jdbcMajorVersion}.<br>
	 * @return {@link #jdbcMajorVersion}
	 */
	@Generated
	public  int	getJdbcMajorVersion(){
		return this.jdbcMajorVersion;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #jdbcMajorVersion}.<br>
	 * @param jdbcMajorVersion The new value for field {@link #jdbcMajorVersion}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withJdbcMajorVersion(int jdbcMajorVersion){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #jdbcMinorVersion}.<br>
	 * @return {@link #jdbcMinorVersion}
	 */
	@Generated
	public  int	getJdbcMinorVersion(){
		return this.jdbcMinorVersion;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #jdbcMinorVersion}.<br>
	 * @param jdbcMinorVersion The new value for field {@link #jdbcMinorVersion}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withJdbcMinorVersion(int jdbcMinorVersion){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #url}.<br>
	 * @return {@link #url}
	 */
	@Generated
	public  Optional<String>	getUrl(){
		return Optional.ofNullable(this.url);
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #url}.<br>
	 * @param url The new value for field {@link #url}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withUrl(@Nullable String url){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #supportsGetGeneratedKeys}.<br>
	 * @return {@link #supportsGetGeneratedKeys}
	 */
	@Generated
	public  boolean	getSupportsGetGeneratedKeys(){
		return this.supportsGetGeneratedKeys;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #supportsGetGeneratedKeys}.<br>
	 * @param supportsGetGeneratedKeys The new value for field {@link #supportsGetGeneratedKeys}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withSupportsGetGeneratedKeys(boolean supportsGetGeneratedKeys){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #catalogSeparator}.<br>
	 * @return {@link #catalogSeparator}
	 */
	@Generated
	public  String	getCatalogSeparator(){
		return this.catalogSeparator;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #catalogSeparator}.<br>
	 * @param catalogSeparator The new value for field {@link #catalogSeparator}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public  DbMetaDatabase	withCatalogSeparator(String catalogSeparator){
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #schemaTerm}.<br>
	 * @return {@link #schemaTerm}
	 */
	@Generated
	public String getSchemaTerm() {
		return this.schemaTerm;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #schemaTerm}.<br>
	 * @param schemaTerm The new value for field {@link #schemaTerm}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public DbMetaDatabase withSchemaTerm(String schemaTerm) {
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	/**
	 * Get the value of field {@link #catalogTerm}.<br>
	 * @return {@link #catalogTerm}
	 */
	@Generated
	public String getCatalogTerm() {
		return this.catalogTerm;
	}
	/**
	 * Create a copy of this DbMetaDatabase object with a new value for field {@link #catalogTerm}.<br>
	 * @param catalogTerm The new value for field {@link #catalogTerm}
	 * @return A new instance of {@link DbMetaDatabase}
	 */
	@Generated
	public DbMetaDatabase withCatalogTerm(String catalogTerm) {
		return new DbMetaDatabase(productName, dbMajorVersion, dbMinorVersion, driveName, driverMajorVersion, driverMinorVersion, jdbcMajorVersion, jdbcMinorVersion, url, supportsGetGeneratedKeys, catalogSeparator, schemaTerm, catalogTerm);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbMetaDatabase == false) return false;
		DbMetaDatabase obj = (DbMetaDatabase)o;
		if(!productName.equals(obj.productName)) return false;
		if(dbMajorVersion!= obj.dbMajorVersion) return false;
		if(dbMinorVersion!= obj.dbMinorVersion) return false;
		if(!driveName.equals(obj.driveName)) return false;
		if(driverMajorVersion!= obj.driverMajorVersion) return false;
		if(driverMinorVersion!= obj.driverMinorVersion) return false;
		if(jdbcMajorVersion!= obj.jdbcMajorVersion) return false;
		if(jdbcMinorVersion!= obj.jdbcMinorVersion) return false;
		if(url != null ? !url.equals(obj.url) : obj.url!= null) return false;
		if(supportsGetGeneratedKeys!= obj.supportsGetGeneratedKeys) return false;
		if(!catalogSeparator.equals(obj.catalogSeparator)) return false;
		if(!schemaTerm.equals(obj.schemaTerm)) return false;
		if(!catalogTerm.equals(obj.catalogTerm)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.productName != null ? this.productName.hashCode() : 0);
		result = 31 * result + this.dbMajorVersion;
		result = 31 * result + this.dbMinorVersion;
		result = 31 * result + (this.driveName != null ? this.driveName.hashCode() : 0);
		result = 31 * result + this.driverMajorVersion;
		result = 31 * result + this.driverMinorVersion;
		result = 31 * result + this.jdbcMajorVersion;
		result = 31 * result + this.jdbcMinorVersion;
		result = 31 * result + (this.url != null ? this.url.hashCode() : 0);
		result = 31 * result + (this.supportsGetGeneratedKeys ? 1 : 0);
		result = 31 * result + (this.catalogSeparator != null ? this.catalogSeparator.hashCode() : 0);
		result = 31 * result + (this.schemaTerm != null ? this.schemaTerm.hashCode() : 0);
		result = 31 * result + (this.catalogTerm != null ? this.catalogTerm.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbMetaDatabase[" + 
			"productName=" + (productName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(productName),32,"...") + '\"') +
			", dbMajorVersion=" + dbMajorVersion + 
			", dbMinorVersion=" + dbMinorVersion + 
			", driveName=" + (driveName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(driveName),32,"...") + '\"') +
			", driverMajorVersion=" + driverMajorVersion + 
			", driverMinorVersion=" + driverMinorVersion + 
			", jdbcMajorVersion=" + jdbcMajorVersion + 
			", jdbcMinorVersion=" + jdbcMinorVersion + 
			", url=" + (url == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(url),32,"...") + '\"') +
			", supportsGetGeneratedKeys=" + supportsGetGeneratedKeys +
			", catalogSeparator=" + (catalogSeparator == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(catalogSeparator), 32, "...") + '\"') +
			", schemaTerm=" + (schemaTerm == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(schemaTerm), 32, "...") + '\"') +
			", catalogTerm=" + (catalogTerm == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(catalogTerm),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbMetaDatabase	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setProductName(this.productName);
		b.setDbMajorVersion(this.dbMajorVersion);
		b.setDbMinorVersion(this.dbMinorVersion);
		b.setDriveName(this.driveName);
		b.setDriverMajorVersion(this.driverMajorVersion);
		b.setDriverMinorVersion(this.driverMinorVersion);
		b.setJdbcMajorVersion(this.jdbcMajorVersion);
		b.setJdbcMinorVersion(this.jdbcMinorVersion);
		b.setUrl(this.url);
		b.setSupportsGetGeneratedKeys(this.supportsGetGeneratedKeys);
		b.setCatalogSeparator(this.catalogSeparator);
		b.setSchemaTerm(this.schemaTerm);
		b.setCatalogTerm(this.catalogTerm);
		b = updater.apply(b);
		return new DbMetaDatabase(b.productName, b.dbMajorVersion, b.dbMinorVersion, b.driveName, b.driverMajorVersion, b.driverMinorVersion, b.jdbcMajorVersion, b.jdbcMinorVersion, b.url, b.supportsGetGeneratedKeys, b.catalogSeparator, b.schemaTerm, b.catalogTerm);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static DbMetaDatabase build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaDatabase(b.productName, b.dbMajorVersion, b.dbMinorVersion, b.driveName, b.driverMajorVersion, b.driverMinorVersion, b.jdbcMajorVersion, b.jdbcMinorVersion, b.url, b.supportsGetGeneratedKeys, b.catalogSeparator, b.schemaTerm, b.catalogTerm);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<DbMetaDatabase> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new DbMetaDatabase(b.productName, b.dbMajorVersion, b.dbMinorVersion, b.driveName, b.driverMajorVersion, b.driverMinorVersion, b.jdbcMajorVersion, b.jdbcMinorVersion, b.url, b.supportsGetGeneratedKeys, b.catalogSeparator, b.schemaTerm, b.catalogTerm));
	}
}
