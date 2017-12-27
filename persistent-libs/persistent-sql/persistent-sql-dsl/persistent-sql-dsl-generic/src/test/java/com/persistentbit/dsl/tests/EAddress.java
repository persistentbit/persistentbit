package com.persistentbit.dsl.tests;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EString;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public abstract class EAddress implements DExpr<Address>{

	public final EString street;
	public final EString postalCode;
	public final EString city;

	public EAddress(EString street, EString postalCode, EString city) {
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}

	public EBool eq(EAddress other) {
		return street.eq(other.street)
			.and(postalCode.eq(other.postalCode))
			.and(city.eq(other.city));
	}

	public EBool notEq(EAddress other) {
		return eq(other).not();
	}
}
