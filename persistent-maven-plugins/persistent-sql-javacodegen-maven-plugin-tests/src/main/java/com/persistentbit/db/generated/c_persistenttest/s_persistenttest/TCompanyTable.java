package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import java.lang.String;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Company;

public class TCompanyTable extends TCompany implements DExprTable<Company> {
	
	
	public TCompanyTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprString("company_name"),tableContext.createExprString("adres_street"),tableContext.createExprInt( "adres_house_number"),tableContext.createExprString("adres_bus_number"),tableContext.createExprString("adres_postalcode"),tableContext.createExprString("adres_city"),tableContext.createExprString("adres_country"),tableContext.createExprLong("owner_person_id"));
		this._tableContext = tableContext;
		this._insertFieldNames = PList.val("id", "company_name", "adres_street", "adres_house_number", "adres_bus_number", "adres_postalcode", "adres_city", "adres_country", "owner_person_id");
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
	public  TCompany	val(Company v){
		DbContext db = _tableContext.getDbContext();
		return new TCompany(
			db.val(v.getId().orElse(null)), db.val(v.getCompanyName()), db.val(v.getAdresStreet()), db.val(v.getAdresHouseNumber()), db.val(v.getAdresBusNumber().orElse(null)), db.val(v.getAdresPostalcode()), db.val(v.getAdresCity()), db.val(v.getAdresCountry()), db.val(v.getOwnerPersonId().orElse(null))
		);
	}
	public  DbWork<Company>	insert(Company record){
		DbWork<Integer> count = new Insert<>(this._tableContext.getDbContext(), this, val(record));
		return count.flatMap(c -> c==0 ? Result.empty() : Result.success(record));
	}
}
