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

public class TPerson extends DTableExprImpl<Person> {
	public  final ELong   id;
	public  final EString userName;
	public  final EString password;
	public  final EString street;
	public  final EInt    houseNumber;
	public  final EString busNumber;
	public  final EString postalcode;
	public  final EString city;
	public  final EString country;
	
	
	public TPerson(ELong id, EString userName, EString password, EString street, EInt houseNumber, EString busNumber, EString postalcode, EString city, EString country){
		super(
			PList.val(id, userName, password, street, houseNumber, busNumber, postalcode, city, country),
			_scon -> _rr -> {
				Long	_id = DImpl._get(id)._read(_scon,_rr);
				String	_userName = DImpl._get(userName)._read(_scon,_rr);
				String	_password = DImpl._get(password)._read(_scon,_rr);
				String	_street = DImpl._get(street)._read(_scon,_rr);
				Integer	_houseNumber = DImpl._get(houseNumber)._read(_scon,_rr);
				String	_busNumber = DImpl._get(busNumber)._read(_scon,_rr);
				String	_postalcode = DImpl._get(postalcode)._read(_scon,_rr);
				String	_city = DImpl._get(city)._read(_scon,_rr);
				String	_country = DImpl._get(country)._read(_scon,_rr);
				if(_id== null && _userName== null && _password== null && _street== null && _houseNumber== null && _busNumber== null && _postalcode== null && _city== null && _country== null) { return null; }
				return new Person(_id, _userName, _password, _street, _houseNumber, _busNumber, _postalcode, _city, _country);
			}
		);
		this.id	=	id;
		this.userName	=	userName;
		this.password	=	password;
		this.street	=	street;
		this.houseNumber	=	houseNumber;
		this.busNumber	=	busNumber;
		this.postalcode	=	postalcode;
		this.city	=	city;
		this.country	=	country;
	}
	@Override
	protected  TPerson	_doWithAlias(String alias){
		return new TPerson(
			(ELong)DImpl._get(id)._withAlias(alias),
			(EString)DImpl._get(userName)._withAlias(alias),
			(EString)DImpl._get(password)._withAlias(alias),
			(EString)DImpl._get(street)._withAlias(alias),
			(EInt)DImpl._get(houseNumber)._withAlias(alias),
			(EString)DImpl._get(busNumber)._withAlias(alias),
			(EString)DImpl._get(postalcode)._withAlias(alias),
			(EString)DImpl._get(city)._withAlias(alias),
			(EString)DImpl._get(country)._withAlias(alias)
		);
	}
	public  static TPerson	cast(DExpr<Person> expr){
		return (TPerson)expr;
	}
}
