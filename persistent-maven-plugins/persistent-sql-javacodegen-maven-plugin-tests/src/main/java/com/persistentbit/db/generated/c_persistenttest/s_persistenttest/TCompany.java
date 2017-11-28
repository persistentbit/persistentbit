package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.tuples.Tuple2;
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
		this.adresStreet	=	context.createExprString(this, "adresStreet");
		this.adresHouseNumber	=	context.createExprInt(this, "adresHouseNumber");
		this.adresBusNumber	=	context.createExprString(this, "adresBusNumber");
		this.adresPostalcode	=	context.createExprString(this, "adresPostalcode");
		this.adresCity	=	context.createExprString(this, "adresCity");
		this.adresCountry	=	context.createExprString(this, "adresCountry");
		this.ownerPersonId	=	context.createExprLong(this, "ownerPersonId");
		super._all = PList.val(Tuple2.of("id",id), Tuple2.of("adresStreet",adresStreet), Tuple2.of("adresHouseNumber",adresHouseNumber), Tuple2.of("adresBusNumber",adresBusNumber), Tuple2.of("adresPostalcode",adresPostalcode), Tuple2.of("adresCity",adresCity), Tuple2.of("adresCountry",adresCountry), Tuple2.of("ownerPersonId",ownerPersonId));
	}
}
