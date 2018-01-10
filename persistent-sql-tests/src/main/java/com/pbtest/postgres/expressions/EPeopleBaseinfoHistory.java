package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.PeopleBaseinfoHistory;
import com.persistentbit.sql.dsl.expressions.*;

public abstract class EPeopleBaseinfoHistory implements DExpr<PeopleBaseinfoHistory>{

	public final ELong     personId;
	public final EDateTime startTime;
	public final EDateTime endTime;
	public final EString   salutationCode;
	public final EString   nameFirst;
	public final EString   nameMiddle;
	public final EString   nameLast;
	public final EString   genderCode;
	public final EDate     birthDay;


	public EPeopleBaseinfoHistory(ELong personId, EDateTime startTime, EDateTime endTime, EString salutationCode,
								  EString nameFirst, EString nameMiddle, EString nameLast, EString genderCode,
								  EDate birthDay) {
		this.personId = personId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.salutationCode = salutationCode;
		this.nameFirst = nameFirst;
		this.nameMiddle = nameMiddle;
		this.nameLast = nameLast;
		this.genderCode = genderCode;
		this.birthDay = birthDay;
	}
}
