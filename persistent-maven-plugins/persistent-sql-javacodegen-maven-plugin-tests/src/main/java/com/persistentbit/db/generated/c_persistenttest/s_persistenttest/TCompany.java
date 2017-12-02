package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.code.annotations.Nullable;
import java.lang.String;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Company;

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
			long	id = DImpl._get(this.id).read(_scon,_rr);
			String	adresStreet = DImpl._get(this.adresStreet).read(_scon,_rr);
			int	adresHouseNumber = DImpl._get(this.adresHouseNumber).read(_scon,_rr);
			String	adresBusNumber = DImpl._get(this.adresBusNumber).read(_scon,_rr);
			String	adresPostalcode = DImpl._get(this.adresPostalcode).read(_scon,_rr);
			String	adresCity = DImpl._get(this.adresCity).read(_scon,_rr);
			String	adresCountry = DImpl._get(this.adresCountry).read(_scon,_rr);
			Long	ownerPersonId = DImpl._get(this.ownerPersonId).read(_scon,_rr);
			return new Company(id, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId);
		};
	}
	public  TCompany	alias(String aliasName){
		return new TCompany(_tableContext.withAlias(aliasName));
	}
}
