package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.EInt;
import com.persistentbit.sql.dsl.generic.expressions.ELong;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.EString;

import java.lang.String;

public class TCompany extends DTableExprImpl<Company> {
	public  final ELong   id;
	public  final EString companyName;
	public  final EString adresStreet;
	public  final EInt    adresHouseNumber;
	public  final EString adresBusNumber;
	public  final EString adresPostalcode;
	public  final EString adresCity;
	public  final EString adresCountry;
	public  final ELong   ownerPersonId;
	
	
	public TCompany(ELong id, EString companyName, EString adresStreet, EInt adresHouseNumber, EString adresBusNumber, EString adresPostalcode, EString adresCity, EString adresCountry, ELong ownerPersonId){
		super(
			PList.val(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId),
			_scon -> _rr -> {
				Long	_id = DImpl._get(id)._read(_scon,_rr);
				String	_companyName = DImpl._get(companyName)._read(_scon,_rr);
				String	_adresStreet = DImpl._get(adresStreet)._read(_scon,_rr);
				Integer	_adresHouseNumber = DImpl._get(adresHouseNumber)._read(_scon,_rr);
				String	_adresBusNumber = DImpl._get(adresBusNumber)._read(_scon,_rr);
				String	_adresPostalcode = DImpl._get(adresPostalcode)._read(_scon,_rr);
				String	_adresCity = DImpl._get(adresCity)._read(_scon,_rr);
				String	_adresCountry = DImpl._get(adresCountry)._read(_scon,_rr);
				Long	_ownerPersonId = DImpl._get(ownerPersonId)._read(_scon,_rr);
				if(_id== null && _companyName== null && _adresStreet== null && _adresHouseNumber== null && _adresBusNumber== null && _adresPostalcode== null && _adresCity== null && _adresCountry== null && _ownerPersonId== null) { return null; }
				return new Company(_id, _companyName, _adresStreet, _adresHouseNumber, _adresBusNumber, _adresPostalcode, _adresCity, _adresCountry, _ownerPersonId);
			}
		);
		this.id	=	id;
		this.companyName	=	companyName;
		this.adresStreet	=	adresStreet;
		this.adresHouseNumber	=	adresHouseNumber;
		this.adresBusNumber	=	adresBusNumber;
		this.adresPostalcode	=	adresPostalcode;
		this.adresCity	=	adresCity;
		this.adresCountry	=	adresCountry;
		this.ownerPersonId	=	ownerPersonId;
	}
	@Override
	protected  TCompany	_doWithAlias(String alias){
		return new TCompany(
			(ELong)DImpl._get(id)._withAlias(alias),
			(EString)DImpl._get(companyName)._withAlias(alias),
			(EString)DImpl._get(adresStreet)._withAlias(alias),
			(EInt)DImpl._get(adresHouseNumber)._withAlias(alias),
			(EString)DImpl._get(adresBusNumber)._withAlias(alias),
			(EString)DImpl._get(adresPostalcode)._withAlias(alias),
			(EString)DImpl._get(adresCity)._withAlias(alias),
			(EString)DImpl._get(adresCountry)._withAlias(alias),
			(ELong)DImpl._get(ownerPersonId)._withAlias(alias)
		);
	}
	public  static TCompany	cast(DExpr<Company> expr){
		return (TCompany)expr;
	}
}
