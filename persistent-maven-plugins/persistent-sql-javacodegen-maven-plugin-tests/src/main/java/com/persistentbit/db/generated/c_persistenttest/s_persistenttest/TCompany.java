package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.tuples.Tuple2;

public class TCompany extends DTable<Company> {
	public  final	DExprLong	id;
	public  final	DExprString	adresStreet;
	public  final	DExprInt	adresHouseNumber;
	public  final	DExprString	adresBusNumber;
	public  final	DExprString	adresPostalcode;
	public  final	DExprString	adresCity;
	public  final	DExprString	adresCountry;
	public  final	DExprLong	ownerPersonId;
	
	
	public TCompany(DbTableContext context){
		super(context);
		this.id	=	context.createExprLong(this, "id");
		this.adresStreet	=	context.createExprString(this, "adres_street");
		this.adresHouseNumber	=	context.createExprInt(this, "adres_house_number");
		this.adresBusNumber	=	context.createExprString(this, "adres_bus_number");
		this.adresPostalcode	=	context.createExprString(this, "adres_postalcode");
		this.adresCity	=	context.createExprString(this, "adres_city");
		this.adresCountry	=	context.createExprString(this, "adres_country");
		this.ownerPersonId	=	context.createExprLong(this, "owner_person_id");
		super._all = PList.val(Tuple2.of("id",id), Tuple2.of("adresStreet",adresStreet), Tuple2.of("adresHouseNumber",adresHouseNumber), Tuple2.of("adresBusNumber",adresBusNumber), Tuple2.of("adresPostalcode",adresPostalcode), Tuple2.of("adresCity",adresCity), Tuple2.of("adresCountry",adresCountry), Tuple2.of("ownerPersonId",ownerPersonId));
		
		_recordReader = _scon -> _rr -> {
			Long	id = DImpl._get(this.id)._read(_scon,_rr);
			String	adresStreet = DImpl._get(this.adresStreet)._read(_scon,_rr);
			Integer	adresHouseNumber = DImpl._get(this.adresHouseNumber)._read(_scon,_rr);
			String	adresBusNumber = DImpl._get(this.adresBusNumber)._read(_scon,_rr);
			String	adresPostalcode = DImpl._get(this.adresPostalcode)._read(_scon,_rr);
			String	adresCity = DImpl._get(this.adresCity)._read(_scon,_rr);
			String	adresCountry = DImpl._get(this.adresCountry)._read(_scon,_rr);
			Long	ownerPersonId = DImpl._get(this.ownerPersonId)._read(_scon,_rr);
			if(id== null && adresStreet== null && adresHouseNumber== null && adresBusNumber== null && adresPostalcode== null && adresCity== null && adresCountry== null && ownerPersonId== null) { return null; }
			return new Company(id, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
		};
	}
	public  TCompany _withAlias(String selectionAliasName){
		return new TCompany(_tableContext.withAlias(selectionAliasName));
	}
	public  TCompany	withTableAlias(String tableAlias){
		return new TCompany(_tableContext.withTableAlias(tableAlias));
	}
}
