package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.Company;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class ECompany implements DExpr<Company>{

	public final ELong   id;
	public final EString companyName;
	public final EString adresStreet;
	public final EInt    adresHouseNumber;
	public final EString adresBusNumber;
	public final EString adresPostalcode;
	public final EString adresCity;
	public final EString adresCountry;
	public final ELong   ownerPersonId;


	public ECompany(ELong id, EString companyName, EString adresStreet, EInt adresHouseNumber, EString adresBusNumber,
					EString adresPostalcode, EString adresCity, EString adresCountry, ELong ownerPersonId) {
		this.id = id;
		this.companyName = companyName;
		this.adresStreet = adresStreet;
		this.adresHouseNumber = adresHouseNumber;
		this.adresBusNumber = adresBusNumber;
		this.adresPostalcode = adresPostalcode;
		this.adresCity = adresCity;
		this.adresCountry = adresCountry;
		this.ownerPersonId = ownerPersonId;
	}
}
