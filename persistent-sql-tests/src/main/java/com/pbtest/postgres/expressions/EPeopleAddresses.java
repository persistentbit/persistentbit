package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.PeopleAddresses;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EPeopleAddresses implements DExpr<PeopleAddresses>{

	public final ELong   personId;
	public final EString addressRelationCode;
	public final ELong   addressId;


	public EPeopleAddresses(ELong personId, EString addressRelationCode, ELong addressId) {
		this.personId = personId;
		this.addressRelationCode = addressRelationCode;
		this.addressId = addressId;
	}
}
