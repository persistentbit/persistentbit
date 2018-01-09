package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.PeopleAddressesHistory;
import com.persistentbit.sql.dsl.expressions.*;

public abstract class EPeopleAddressesHistory implements DExpr<PeopleAddressesHistory>{

	public final ELong     personId;
	public final EObject   addressRelationCode;
	public final EDate     startDate;
	public final EDateTime endDate;
	public final ELong     addressId;


	public EPeopleAddressesHistory(ELong personId, EObject addressRelationCode, EDate startDate, EDateTime endDate,
								   ELong addressId) {
		this.personId = personId;
		this.addressRelationCode = addressRelationCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.addressId = addressId;
	}
}
