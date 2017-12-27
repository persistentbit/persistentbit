package com.generated.expressions;

import com.generated.values.Persons;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EPersons implements DExpr<Persons>{

	public final ELong     personId;
	public final EString   firstName;
	public final EString   middleName;
	public final EString   lastName;
	public final EAddress  home;
	public final EDateTime created;


	public EPersons(ELong personId, EString firstName, EString middleName, EString lastName, EAddress home,
					EDateTime created
	) {
		this.personId = personId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.home = home;
		this.created = created;
	}
}
