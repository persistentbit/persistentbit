package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.PeopleHistory;
import com.persistentbit.sql.dsl.expressions.*;

public abstract class EPeopleHistory implements DExpr<PeopleHistory>{

	public final ELong     personId;
	public final EString   salutationCode;
	public final EString   nameFirst;
	public final EString   nameMiddle;
	public final EString   nameLast;
	public final EString   genderCode;
	public final EDate     birthDay;
	public final EDateTime startTime;
	public final EDateTime endTime;


	public EPeopleHistory(ELong personId, EString salutationCode, EString nameFirst, EString nameMiddle,
						  EString nameLast, EString genderCode, EDate birthDay, EDateTime startTime,
						  EDateTime endTime) {
		this.personId = personId;
		this.salutationCode = salutationCode;
		this.nameFirst = nameFirst;
		this.nameMiddle = nameMiddle;
		this.nameLast = nameLast;
		this.genderCode = genderCode;
		this.birthDay = birthDay;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
