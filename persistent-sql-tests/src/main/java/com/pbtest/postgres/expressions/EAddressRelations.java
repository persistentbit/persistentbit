package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.AddressRelations;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EAddressRelations implements DExpr<AddressRelations>{

	public final EString addressRelationCode;
	public final EString description;


	public EAddressRelations(EString addressRelationCode, EString description) {
		this.addressRelationCode = addressRelationCode;
		this.description = description;
	}
}
