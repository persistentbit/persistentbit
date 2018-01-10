package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.Addresses;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EAddresses implements DExpr<Addresses>{

	public final ELong   addressId;
	public final EString streetLine1;
	public final EString streetLine2;
	public final EString postalCode;
	public final EString cityName;
	public final EString countryCode;
	public final EString district;


	public EAddresses(ELong addressId, EString streetLine1, EString streetLine2, EString postalCode, EString cityName,
					  EString countryCode, EString district) {
		this.addressId = addressId;
		this.streetLine1 = streetLine1;
		this.streetLine2 = streetLine2;
		this.postalCode = postalCode;
		this.cityName = cityName;
		this.countryCode = countryCode;
		this.district = district;
	}
}
