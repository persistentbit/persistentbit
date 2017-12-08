package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.SuppressWarnings;
import java.util.Optional;
import com.persistentbit.sql.dsl.annotations.DbColumnName;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import java.util.function.Function;
import com.persistentbit.string.UString;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.result.Result;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.SET;
import java.lang.String;
import com.persistentbit.javacodegen.annotations.NoWith;

public class Company {
	@DbColumnName("id")
	private  final	long	id;
	@DbColumnName("company_name")
	private  final	String	companyName;
	@DbColumnName("adres_street")
	private  final	String	adresStreet;
	@DbColumnName("adres_house_number")
	private  final	int	adresHouseNumber;
	@Nullable
	@DbColumnName("adres_bus_number")
	private  final	String	adresBusNumber;
	@DbColumnName("adres_postalcode")
	private  final	String	adresPostalcode;
	@DbColumnName("adres_city")
	private  final	String	adresCity;
	@DbColumnName("adres_country")
	private  final	String	adresCountry;
	@Nullable
	@DbColumnName("owner_person_id")
	private  final	Long	ownerPersonId;
	
	
	@Generated
	public Company(long id, String companyName, String adresStreet, int adresHouseNumber, @Nullable String adresBusNumber, String adresPostalcode, String adresCity, String adresCountry, @Nullable Long ownerPersonId){
			this.id = Objects.requireNonNull(id, "id can not be null");
			this.companyName = Objects.requireNonNull(companyName, "companyName can not be null");
			this.adresStreet = Objects.requireNonNull(adresStreet, "adresStreet can not be null");
			this.adresHouseNumber = Objects.requireNonNull(adresHouseNumber, "adresHouseNumber can not be null");
			this.adresBusNumber = adresBusNumber;
			this.adresPostalcode = Objects.requireNonNull(adresPostalcode, "adresPostalcode can not be null");
			this.adresCity = Objects.requireNonNull(adresCity, "adresCity can not be null");
			this.adresCountry = Objects.requireNonNull(adresCountry, "adresCountry can not be null");
			this.ownerPersonId = ownerPersonId;
	}
	@Generated
	public Company(long id, String companyName, String adresStreet, int adresHouseNumber, String adresPostalcode, String adresCity, String adresCountry){
			this(id, companyName, adresStreet, adresHouseNumber, null, adresPostalcode, adresCity, adresCountry, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7> {
		private	long	id;
		private	String	companyName;
		private	String	adresStreet;
		private	int	adresHouseNumber;
		private	String	adresBusNumber;
		private	String	adresPostalcode;
		private	String	adresCity;
		private	String	adresCountry;
		private	Long	ownerPersonId;
		
		
		public  Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7>	setId(long id){
			this.id	=	id;
			return (Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7>)this;
		}
		public  Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7>	setCompanyName(String companyName){
			this.companyName	=	companyName;
			return (Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7>)this;
		}
		public  Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7>	setAdresStreet(String adresStreet){
			this.adresStreet	=	adresStreet;
			return (Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7>)this;
		}
		public  Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7>	setAdresHouseNumber(int adresHouseNumber){
			this.adresHouseNumber	=	adresHouseNumber;
			return (Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7>	setAdresBusNumber(@Nullable String adresBusNumber){
			this.adresBusNumber	=	adresBusNumber;
			return this;
		}
		public  Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7>	setAdresPostalcode(String adresPostalcode){
			this.adresPostalcode	=	adresPostalcode;
			return (Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7>	setAdresCity(String adresCity){
			this.adresCity	=	adresCity;
			return (Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET>	setAdresCountry(String adresCountry){
			this.adresCountry	=	adresCountry;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7>	setOwnerPersonId(@Nullable Long ownerPersonId){
			this.ownerPersonId	=	ownerPersonId;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #id}.<br>
	 * @return {@link #id}
	 */
	@Generated
	public  long	getId(){
		return this.id;
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #id}.<br>
	 * @param id The new value for field {@link #id}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withId(long id){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	/**
	 * Get the value of field {@link #companyName}.<br>
	 * @return {@link #companyName}
	 */
	@Generated
	public  String	getCompanyName(){
		return this.companyName;
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #companyName}.<br>
	 * @param companyName The new value for field {@link #companyName}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withCompanyName(String companyName){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	/**
	 * Get the value of field {@link #adresStreet}.<br>
	 * @return {@link #adresStreet}
	 */
	@Generated
	public  String	getAdresStreet(){
		return this.adresStreet;
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #adresStreet}.<br>
	 * @param adresStreet The new value for field {@link #adresStreet}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withAdresStreet(String adresStreet){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	/**
	 * Get the value of field {@link #adresHouseNumber}.<br>
	 * @return {@link #adresHouseNumber}
	 */
	@Generated
	public  int	getAdresHouseNumber(){
		return this.adresHouseNumber;
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #adresHouseNumber}.<br>
	 * @param adresHouseNumber The new value for field {@link #adresHouseNumber}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withAdresHouseNumber(int adresHouseNumber){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	/**
	 * Get the value of field {@link #adresBusNumber}.<br>
	 * @return {@link #adresBusNumber}
	 */
	@Generated
	public  Optional<String>	getAdresBusNumber(){
		return Optional.ofNullable(this.adresBusNumber);
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #adresBusNumber}.<br>
	 * @param adresBusNumber The new value for field {@link #adresBusNumber}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withAdresBusNumber(@Nullable String adresBusNumber){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	/**
	 * Get the value of field {@link #adresPostalcode}.<br>
	 * @return {@link #adresPostalcode}
	 */
	@Generated
	public  String	getAdresPostalcode(){
		return this.adresPostalcode;
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #adresPostalcode}.<br>
	 * @param adresPostalcode The new value for field {@link #adresPostalcode}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withAdresPostalcode(String adresPostalcode){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	/**
	 * Get the value of field {@link #adresCity}.<br>
	 * @return {@link #adresCity}
	 */
	@Generated
	public  String	getAdresCity(){
		return this.adresCity;
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #adresCity}.<br>
	 * @param adresCity The new value for field {@link #adresCity}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withAdresCity(String adresCity){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	/**
	 * Get the value of field {@link #adresCountry}.<br>
	 * @return {@link #adresCountry}
	 */
	@Generated
	public  String	getAdresCountry(){
		return this.adresCountry;
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #adresCountry}.<br>
	 * @param adresCountry The new value for field {@link #adresCountry}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withAdresCountry(String adresCountry){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	/**
	 * Get the value of field {@link #ownerPersonId}.<br>
	 * @return {@link #ownerPersonId}
	 */
	@Generated
	public  Optional<Long>	getOwnerPersonId(){
		return Optional.ofNullable(this.ownerPersonId);
	}
	/**
	 * Create a copy of this Company object with a new value for field {@link #ownerPersonId}.<br>
	 * @param ownerPersonId The new value for field {@link #ownerPersonId}
	 * @return A new instance of {@link Company}
	 */
	@Generated
	public  Company	withOwnerPersonId(@Nullable Long ownerPersonId){
		return new Company(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof Company == false) return false;
		Company obj = (Company)o;
		if(id!= obj.id) return false;
		if(!companyName.equals(obj.companyName)) return false;
		if(!adresStreet.equals(obj.adresStreet)) return false;
		if(adresHouseNumber!= obj.adresHouseNumber) return false;
		if(adresBusNumber != null ? !adresBusNumber.equals(obj.adresBusNumber) : obj.adresBusNumber!= null) return false;
		if(!adresPostalcode.equals(obj.adresPostalcode)) return false;
		if(!adresCity.equals(obj.adresCity)) return false;
		if(!adresCountry.equals(obj.adresCountry)) return false;
		if(ownerPersonId != null ? !ownerPersonId.equals(obj.ownerPersonId) : obj.ownerPersonId!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (int) (this.id ^ (this.id>>> 32));
		result = 31 * result + (this.companyName != null ? this.companyName.hashCode() : 0);
		result = 31 * result + (this.adresStreet != null ? this.adresStreet.hashCode() : 0);
		result = 31 * result + this.adresHouseNumber;
		result = 31 * result + (this.adresBusNumber != null ? this.adresBusNumber.hashCode() : 0);
		result = 31 * result + (this.adresPostalcode != null ? this.adresPostalcode.hashCode() : 0);
		result = 31 * result + (this.adresCity != null ? this.adresCity.hashCode() : 0);
		result = 31 * result + (this.adresCountry != null ? this.adresCountry.hashCode() : 0);
		result = 31 * result + (this.ownerPersonId != null ? this.ownerPersonId.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "Company[" + 
			"id=" + id + 
			", companyName=" + (companyName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(companyName),32,"...") + '\"') +
			", adresStreet=" + (adresStreet == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(adresStreet),32,"...") + '\"') +
			", adresHouseNumber=" + adresHouseNumber + 
			", adresBusNumber=" + (adresBusNumber == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(adresBusNumber),32,"...") + '\"') +
			", adresPostalcode=" + (adresPostalcode == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(adresPostalcode),32,"...") + '\"') +
			", adresCity=" + (adresCity == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(adresCity),32,"...") + '\"') +
			", adresCountry=" + (adresCountry == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(adresCountry),32,"...") + '\"') +
			", ownerPersonId=" + ownerPersonId + 
			']';
	}
	@Generated
	public  Company	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setId(this.id);
		b.setCompanyName(this.companyName);
		b.setAdresStreet(this.adresStreet);
		b.setAdresHouseNumber(this.adresHouseNumber);
		b.setAdresBusNumber(this.adresBusNumber);
		b.setAdresPostalcode(this.adresPostalcode);
		b.setAdresCity(this.adresCity);
		b.setAdresCountry(this.adresCountry);
		b.setOwnerPersonId(this.ownerPersonId);
		b = updater.apply(b);
		return new Company(b.id, b.companyName, b.adresStreet, b.adresHouseNumber, b.adresBusNumber, b.adresPostalcode, b.adresCity, b.adresCountry, b.ownerPersonId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Company	build(ThrowingFunction<Builder<NOT,NOT,NOT,NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET,SET,SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Company(b.id, b.companyName, b.adresStreet, b.adresHouseNumber, b.adresBusNumber, b.adresPostalcode, b.adresCity, b.adresCountry, b.ownerPersonId);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<Company>	buildExc(ThrowingFunction<Builder<NOT,NOT,NOT,NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET,SET,SET,SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new Company(b.id, b.companyName, b.adresStreet, b.adresHouseNumber, b.adresBusNumber, b.adresPostalcode, b.adresCity, b.adresCountry, b.ownerPersonId));
	}
}
