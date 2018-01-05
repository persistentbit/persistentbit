package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.APerson;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EAPerson implements DExpr<APerson>{

	public final ELong   id;
	public final EString userName;
	public final EString password;
	public final EString street;
	public final EInt    houseNumber;
	public final EString busNumber;
	public final EString postalcode;
	public final EString city;
	public final EString country;


	public EAPerson(ELong id, EString userName, EString password, EString street, EInt houseNumber, EString busNumber,
					EString postalcode, EString city, EString country) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.street = street;
		this.houseNumber = houseNumber;
		this.busNumber = busNumber;
		this.postalcode = postalcode;
		this.city = city;
		this.country = country;
	}
}
