package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import java.lang.String;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Company;

public class TCompanyTable extends TCompany implements DExprTable<Company> {
	
	
	public TCompanyTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprString("company_name"),tableContext.createExprString("adres_street"),tableContext.createExprInt( "adres_house_number"),tableContext.createExprString("adres_bus_number"),tableContext.createExprString("adres_postalcode"),tableContext.createExprString("adres_city"),tableContext.createExprString("adres_country"),tableContext.createExprLong("owner_person_id"));
		this._tableContext = tableContext;
	}
	public  TCompanyTable	alias(String tableAlias){
		return new TCompanyTable(_tableContext.withTableAlias(tableAlias));
	}
	@Override
	public  Query	query(){
		return _tableContext.createQuery(this);
	}
	@Override
	public  TCompany	all(){
		return this;
	}
}
