package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.AllGenericNulls;
import com.persistentbit.sql.dsl.expressions.*;

public abstract class EAllGenericNulls implements DExpr<AllGenericNulls>{

	public final EString     idPart1;
	public final ELong       idPart2;
	public final EShort      serSmall;
	public final EInt        ser;
	public final ELong       serBig;
	public final EInt        anInteger;
	public final ELong       aBigint;
	public final EBigDecimal aDecimal72;
	public final EBigDecimal aNumeric6;
	public final EBigDecimal aNumeric;
	public final EFloat      aReal;
	public final EDouble     aDouble;
	public final EShort      anInt2;
	public final EInt        anInt4;
	public final ELong       anInt8;
	public final EString     aVarchar;
	public final EString     aVarchar10;
	public final EString     aText;
	public final EString     aChar;
	public final EString     aChar10;
	public final EByteList   aBytea;
	public final EDateTime   aTimestamp3;
	public final EDateTime   aTimestamp;
	public final EDateTime   aTimestampWithZone;
	public final EDate       aDate;
	public final ETime       aTime;
	public final ETime       aTimeWithZone;
	public final EBool       aBoolean;


	public EAllGenericNulls(EString idPart1, ELong idPart2, EShort serSmall, EInt ser, ELong serBig, EInt anInteger,
							ELong aBigint, EBigDecimal aDecimal72, EBigDecimal aNumeric6, EBigDecimal aNumeric,
							EFloat aReal, EDouble aDouble, EShort anInt2, EInt anInt4, ELong anInt8, EString aVarchar,
							EString aVarchar10, EString aText, EString aChar, EString aChar10, EByteList aBytea,
							EDateTime aTimestamp3, EDateTime aTimestamp, EDateTime aTimestampWithZone, EDate aDate,
							ETime aTime, ETime aTimeWithZone, EBool aBoolean) {
		this.idPart1 = idPart1;
		this.idPart2 = idPart2;
		this.serSmall = serSmall;
		this.ser = ser;
		this.serBig = serBig;
		this.anInteger = anInteger;
		this.aBigint = aBigint;
		this.aDecimal72 = aDecimal72;
		this.aNumeric6 = aNumeric6;
		this.aNumeric = aNumeric;
		this.aReal = aReal;
		this.aDouble = aDouble;
		this.anInt2 = anInt2;
		this.anInt4 = anInt4;
		this.anInt8 = anInt8;
		this.aVarchar = aVarchar;
		this.aVarchar10 = aVarchar10;
		this.aText = aText;
		this.aChar = aChar;
		this.aChar10 = aChar10;
		this.aBytea = aBytea;
		this.aTimestamp3 = aTimestamp3;
		this.aTimestamp = aTimestamp;
		this.aTimestampWithZone = aTimestampWithZone;
		this.aDate = aDate;
		this.aTime = aTime;
		this.aTimeWithZone = aTimeWithZone;
		this.aBoolean = aBoolean;
	}
}
