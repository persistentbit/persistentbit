package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.collections.PList;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Person;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import java.lang.String;

public class TPerson extends DTableExprImpl<Person> {
	public  final	DExprLong	id;
	public  final	DExprString	userName;
	public  final	DExprString	password;
	public  final	DExprString	street;
	public  final	DExprInt	houseNumber;
	public  final	DExprString	busNumber;
	public  final	DExprString	postalcode;
	public  final	DExprString	city;
	public  final	DExprString	country;
	
	
	public TPerson(DExprLong id, DExprString userName, DExprString password, DExprString street, DExprInt houseNumber, DExprString busNumber, DExprString postalcode, DExprString city, DExprString country){
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
			(DExprLong)DImpl._get(id)._withAlias(alias), 
			(DExprString)DImpl._get(userName)._withAlias(alias), 
			(DExprString)DImpl._get(password)._withAlias(alias), 
			(DExprString)DImpl._get(street)._withAlias(alias), 
			(DExprInt)DImpl._get(houseNumber)._withAlias(alias), 
			(DExprString)DImpl._get(busNumber)._withAlias(alias), 
			(DExprString)DImpl._get(postalcode)._withAlias(alias), 
			(DExprString)DImpl._get(city)._withAlias(alias), 
			(DExprString)DImpl._get(country)._withAlias(alias)
		);
	}
	public  static TPerson	cast(DExpr<Person> expr){
		return (TPerson)expr;
	}
}
