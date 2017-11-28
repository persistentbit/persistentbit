package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.collections.PList;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Person;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.tuples.Tuple2;

public class TPerson extends DTable<Person> {
	public  final	DExprLong	id;
	public  final	DExprString	userName;
	public  final	DExprString	password;
	public  final	DExprString	street;
	public  final	DExprInt	houseNumber;
	public  final	DExprString	busNumber;
	public  final	DExprString	postalcode;
	public  final	DExprString	city;
	public  final	DExprString	country;
	
	
	public TPerson(DbTableContext context){
		super(context);
		this.id	=	context.createExprLong(this, "id");
		this.userName	=	context.createExprString(this, "user_name");
		this.password	=	context.createExprString(this, "password");
		this.street	=	context.createExprString(this, "street");
		this.houseNumber	=	context.createExprInt(this, "house_number");
		this.busNumber	=	context.createExprString(this, "bus_number");
		this.postalcode	=	context.createExprString(this, "postalcode");
		this.city	=	context.createExprString(this, "city");
		this.country	=	context.createExprString(this, "country");
		super._all = PList.val(Tuple2.of("id",id), Tuple2.of("userName",userName), Tuple2.of("password",password), Tuple2.of("street",street), Tuple2.of("houseNumber",houseNumber), Tuple2.of("busNumber",busNumber), Tuple2.of("postalcode",postalcode), Tuple2.of("city",city), Tuple2.of("country",country));
	}
	public  TPerson	alias(String aliasName){
		return new TPerson(_tableContext.withAlias(aliasName));
	}
}
