package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.PeopleAddressesHistory;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EPeopleAddressesHistory implements DExpr<PeopleAddressesHistory>{

	public final ELong     personId;
	public final EString   addressRelationCode;
	public final ELong     addressId;
	public final EDateTime startTime;
	public final EDateTime endTime;


	public EPeopleAddressesHistory(ELong personId, EString addressRelationCode, ELong addressId, EDateTime startTime,
								   EDateTime endTime) {
		this.personId = personId;
		this.addressRelationCode = addressRelationCode;
		this.addressId = addressId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
