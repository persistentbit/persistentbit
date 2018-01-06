package com.pbtest.mysql.expressions;

import com.pbtest.mysql.values.GenData;
import com.persistentbit.sql.dsl.expressions.*;

public abstract class EGenData implements DExpr<GenData>{

	public final EInt        genDataId;
	public final EInt        aInt;
	public final EShort      aShort;
	public final ELong       aLong;
	public final EBigDecimal aNum;
	public final EDouble     aDouble;
	public final EDouble     aReal;
	public final EBool       aBool;
	public final EDate       aDate;
	public final ETime       aTime;
	public final EDateTime   aTimestamp;
	public final EString     aString;


	public EGenData(EInt genDataId, EInt aInt, EShort aShort, ELong aLong, EBigDecimal aNum, EDouble aDouble,
					EDouble aReal, EBool aBool, EDate aDate, ETime aTime, EDateTime aTimestamp, EString aString) {
		this.genDataId = genDataId;
		this.aInt = aInt;
		this.aShort = aShort;
		this.aLong = aLong;
		this.aNum = aNum;
		this.aDouble = aDouble;
		this.aReal = aReal;
		this.aBool = aBool;
		this.aDate = aDate;
		this.aTime = aTime;
		this.aTimestamp = aTimestamp;
		this.aString = aString;
	}
}
