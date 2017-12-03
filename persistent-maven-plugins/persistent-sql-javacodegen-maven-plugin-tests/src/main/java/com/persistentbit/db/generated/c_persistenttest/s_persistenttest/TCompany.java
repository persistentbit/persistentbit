package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;

public class TCompany extends DTableExprImpl<Company> {
	public  final	DExprLong	id;
	public  final	DExprString	adresStreet;
	public  final	DExprInt	adresHouseNumber;
	public  final	DExprString	adresBusNumber;
	public  final	DExprString	adresPostalcode;
	public  final	DExprString	adresCity;
	public  final	DExprString	adresCountry;
	public  final	DExprLong	ownerPersonId;
	
	
	public TCompany(DExprLong id, DExprString adresStreet, DExprInt adresHouseNumber, DExprString adresBusNumber, DExprString adresPostalcode, DExprString adresCity, DExprString adresCountry, DExprLong ownerPersonId){
		super(
			PList.val(id, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId),
			_scon -> _rr -> {
				Long	_id = DImpl._get(id)._read(_scon,_rr);
				String	_adresStreet = DImpl._get(adresStreet)._read(_scon,_rr);
				Integer	_adresHouseNumber = DImpl._get(adresHouseNumber)._read(_scon,_rr);
				String	_adresBusNumber = DImpl._get(adresBusNumber)._read(_scon,_rr);
				String	_adresPostalcode = DImpl._get(adresPostalcode)._read(_scon,_rr);
				String	_adresCity = DImpl._get(adresCity)._read(_scon,_rr);
				String	_adresCountry = DImpl._get(adresCountry)._read(_scon,_rr);
				Long	_ownerPersonId = DImpl._get(ownerPersonId)._read(_scon,_rr);
				if(_id== null && _adresStreet== null && _adresHouseNumber== null && _adresBusNumber== null && _adresPostalcode== null && _adresCity== null && _adresCountry== null && _ownerPersonId== null) { return null; }
				return new Company(_id, _adresStreet, _adresHouseNumber, _adresBusNumber, _adresPostalcode, _adresCity, _adresCountry, _ownerPersonId);
			}
		);
		this.id	=	id;
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
			(DExprLong)DImpl._get(id)._withAlias(alias), 
			(DExprString)DImpl._get(adresStreet)._withAlias(alias), 
			(DExprInt)DImpl._get(adresHouseNumber)._withAlias(alias), 
			(DExprString)DImpl._get(adresBusNumber)._withAlias(alias), 
			(DExprString)DImpl._get(adresPostalcode)._withAlias(alias), 
			(DExprString)DImpl._get(adresCity)._withAlias(alias), 
			(DExprString)DImpl._get(adresCountry)._withAlias(alias), 
			(DExprLong)DImpl._get(ownerPersonId)._withAlias(alias)
		);
		
	}
}
