package com.persistentbit.sql.dsl.maven.tests;

import com.persistentbit.sql.dsl.generic.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public class TCompany extends DTable{
	private final String _tableName;

	public final DExprLong 	 id;
	public final DExprString adresStreet;
	public final DExprInt 	 adresHouseNumber;
	public final DExprString adresBusNumber;
	public final DExprString adresPostalCode;
	public final DExprString adresCity;
	public final DExprString adresCountry;

	public TCompany(DbTableContext context) {
		super(context);
		this._tableName = context.getTableName();
		this.id = context.createExprLong(this,"id");
		this.adresStreet = context.createExprString(this,"adres_street");
		this.adresHouseNumber = context.createExprInt(this,"adres_house_number");
		this.adresBusNumber = context.createExprString(this,"adres_bus_number");
		this.adresPostalCode = context.createExprString(this,"adres_postalcode");
		this.adresCity = context.createExprString(this,"adres_city");
		this.adresCountry = context.createExprString(this,"adres_country");

	}

}
