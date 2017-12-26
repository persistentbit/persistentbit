package com.generated.catalog.schema;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EAddress implements DExpr<Address>{

	public final EString street;
	public final EString postalCode;
	public final EString city;


	public EAddress(EString street, EString postalCode, EString city) {
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
}
