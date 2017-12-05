package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.collections.PList;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.Person;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import java.lang.String;

public class TPersonTable extends TPerson implements DExprTable<Person> {
	
	
	public TPersonTable(DbTableContext tableContext){
		super(tableContext.createExprLong("id"),tableContext.createExprString("user_name"),tableContext.createExprString("password"),tableContext.createExprString("street"),tableContext.createExprInt( "house_number"),tableContext.createExprString("bus_number"),tableContext.createExprString("postalcode"),tableContext.createExprString("city"),tableContext.createExprString("country"));
		this._tableContext = tableContext;
		this._insertFieldNames = PList.val("id", "user_name", "password", "street", "house_number", "bus_number", "postalcode", "city", "country");
	}
	public  TPersonTable	alias(String tableAlias){
		return new TPersonTable(_tableContext.withTableAlias(tableAlias));
	}
	@Override
	public  Query	query(){
		return _tableContext.createQuery(this);
	}
	@Override
	public  TPerson	all(){
		return this;
	}
	public  TPerson	val(Person v){
		DbContext db = _tableContext.getDbContext();
		return new TPerson(
			db.val(v.getId().orElse(null)), db.val(v.getUserName()), db.val(v.getPassword()), db.val(v.getStreet()), db.val(v.getHouseNumber()), db.val(v.getBusNumber().orElse(null)), db.val(v.getPostalcode()), db.val(v.getCity()), db.val(v.getCountry())
		);
	}
	public  DbWork<Person>	insert(Person record){
		DbWork<Integer> count = new Insert<>(this._tableContext.getDbContext(), this, val(record));
		return count.flatMap(c -> c==0 ? Result.empty() : Result.success(record));
	}
}
