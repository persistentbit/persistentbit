package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.EByteList;
import java.time.ZonedDateTime;
import com.persistentbit.sql.dsl.generic.expressions.EZonedDateTime;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.EInt;
import com.persistentbit.sql.dsl.generic.expressions.ELong;
import com.persistentbit.sql.dsl.generic.expressions.EDateTime;
import java.time.LocalDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.EBool;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import java.math.BigDecimal;
import com.persistentbit.sql.dsl.generic.expressions.EBigDecimal;
import com.persistentbit.sql.dsl.generic.expressions.EString;
import com.persistentbit.sql.dsl.generic.expressions.EDouble;
import com.persistentbit.sql.dsl.generic.expressions.EDate;
import com.persistentbit.sql.dsl.generic.expressions.ETime;
import java.lang.String;
import java.time.LocalDate;
import java.time.LocalTime;
import com.persistentbit.sql.dsl.generic.expressions.EShort;
import com.persistentbit.sql.dsl.generic.expressions.EFloat;

public class TAllGeneric extends DTableExprImpl<AllGeneric> {
	public  final EString            idPart1;
	public  final ELong              idPart2;
	public  final EShort             serSmall;
	public  final EInt               ser;
	public  final ELong              serBig;
	public  final EInt               anInteger;
	public  final ELong              aBigint;
	public  final EBigDecimal        aDecimal72;
	public  final EBigDecimal        aNumeric6;
	public  final EBigDecimal        aNumeric;
	public  final EFloat             aReal;
	public  final EDouble        aDouble;
	public  final EShort         anInt2;
	public  final EInt           anInt4;
	public  final ELong          anInt8;
	public  final EString        aVarchar;
	public  final EString        aVarchar10;
	public  final EString        aText;
	public  final EString        aChar;
	public  final EString        aChar10;
	public  final EByteList      aBytea;
	public  final EDateTime      aTimestamp3;
	public  final EDateTime      aTimestamp;
	public  final EZonedDateTime aTimestampWithZone;
	public  final EDate          aDate;
	public  final ETime          aTime;
	public  final ETime          aTimeWithZone;
	public  final EBool          aBoolean;
	
	
	public TAllGeneric(EString idPart1, ELong idPart2, EShort serSmall, EInt ser, ELong serBig, EInt anInteger, ELong aBigint, EBigDecimal aDecimal72, EBigDecimal aNumeric6, EBigDecimal aNumeric, EFloat aReal, EDouble aDouble, EShort anInt2, EInt anInt4, ELong anInt8, EString aVarchar, EString aVarchar10, EString aText, EString aChar, EString aChar10, EByteList aBytea, EDateTime aTimestamp3, EDateTime aTimestamp, EZonedDateTime aTimestampWithZone, EDate aDate, ETime aTime, ETime aTimeWithZone, EBool aBoolean){
		super(
			PList.val(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean),
			_scon -> _rr -> {
				String	_idPart1 = DImpl._get(idPart1)._read(_scon,_rr);
				Long	_idPart2 = DImpl._get(idPart2)._read(_scon,_rr);
				Short	_serSmall = DImpl._get(serSmall)._read(_scon,_rr);
				Integer	_ser = DImpl._get(ser)._read(_scon,_rr);
				Long	_serBig = DImpl._get(serBig)._read(_scon,_rr);
				Integer	_anInteger = DImpl._get(anInteger)._read(_scon,_rr);
				Long	_aBigint = DImpl._get(aBigint)._read(_scon,_rr);
				BigDecimal	_aDecimal72 = DImpl._get(aDecimal72)._read(_scon,_rr);
				BigDecimal	_aNumeric6 = DImpl._get(aNumeric6)._read(_scon,_rr);
				BigDecimal	_aNumeric = DImpl._get(aNumeric)._read(_scon,_rr);
				Float	_aReal = DImpl._get(aReal)._read(_scon,_rr);
				Double	_aDouble = DImpl._get(aDouble)._read(_scon,_rr);
				Short	_anInt2 = DImpl._get(anInt2)._read(_scon,_rr);
				Integer	_anInt4 = DImpl._get(anInt4)._read(_scon,_rr);
				Long	_anInt8 = DImpl._get(anInt8)._read(_scon,_rr);
				String	_aVarchar = DImpl._get(aVarchar)._read(_scon,_rr);
				String	_aVarchar10 = DImpl._get(aVarchar10)._read(_scon,_rr);
				String	_aText = DImpl._get(aText)._read(_scon,_rr);
				String	_aChar = DImpl._get(aChar)._read(_scon,_rr);
				String	_aChar10 = DImpl._get(aChar10)._read(_scon,_rr);
				PByteList	_aBytea = DImpl._get(aBytea)._read(_scon,_rr);
				LocalDateTime	_aTimestamp3 = DImpl._get(aTimestamp3)._read(_scon,_rr);
				LocalDateTime	_aTimestamp = DImpl._get(aTimestamp)._read(_scon,_rr);
				ZonedDateTime	_aTimestampWithZone = DImpl._get(aTimestampWithZone)._read(_scon,_rr);
				LocalDate	_aDate = DImpl._get(aDate)._read(_scon,_rr);
				LocalTime	_aTime = DImpl._get(aTime)._read(_scon,_rr);
				LocalTime	_aTimeWithZone = DImpl._get(aTimeWithZone)._read(_scon,_rr);
				Boolean	_aBoolean = DImpl._get(aBoolean)._read(_scon,_rr);
				if(_idPart1== null && _idPart2== null && _serSmall== null && _ser== null && _serBig== null && _anInteger== null && _aBigint== null && _aDecimal72== null && _aNumeric6== null && _aNumeric== null && _aReal== null && _aDouble== null && _anInt2== null && _anInt4== null && _anInt8== null && _aVarchar== null && _aVarchar10== null && _aText== null && _aChar== null && _aChar10== null && _aBytea== null && _aTimestamp3== null && _aTimestamp== null && _aTimestampWithZone== null && _aDate== null && _aTime== null && _aTimeWithZone== null && _aBoolean== null) { return null; }
				return new AllGeneric(_idPart1, _idPart2, _serSmall, _ser, _serBig, _anInteger, _aBigint, _aDecimal72, _aNumeric6, _aNumeric, _aReal, _aDouble, _anInt2, _anInt4, _anInt8, _aVarchar, _aVarchar10, _aText, _aChar, _aChar10, _aBytea, _aTimestamp3, _aTimestamp, _aTimestampWithZone, _aDate, _aTime, _aTimeWithZone, _aBoolean);
			}
		);
		this.idPart1	=	idPart1;
		this.idPart2	=	idPart2;
		this.serSmall	=	serSmall;
		this.ser	=	ser;
		this.serBig	=	serBig;
		this.anInteger	=	anInteger;
		this.aBigint	=	aBigint;
		this.aDecimal72	=	aDecimal72;
		this.aNumeric6	=	aNumeric6;
		this.aNumeric	=	aNumeric;
		this.aReal	=	aReal;
		this.aDouble	=	aDouble;
		this.anInt2	=	anInt2;
		this.anInt4	=	anInt4;
		this.anInt8	=	anInt8;
		this.aVarchar	=	aVarchar;
		this.aVarchar10	=	aVarchar10;
		this.aText	=	aText;
		this.aChar	=	aChar;
		this.aChar10	=	aChar10;
		this.aBytea	=	aBytea;
		this.aTimestamp3	=	aTimestamp3;
		this.aTimestamp	=	aTimestamp;
		this.aTimestampWithZone	=	aTimestampWithZone;
		this.aDate	=	aDate;
		this.aTime	=	aTime;
		this.aTimeWithZone	=	aTimeWithZone;
		this.aBoolean	=	aBoolean;
	}
	@Override
	protected  TAllGeneric	_doWithAlias(String alias){
		return new TAllGeneric(
			(EString)DImpl._get(idPart1)._withAlias(alias),
			(ELong)DImpl._get(idPart2)._withAlias(alias),
			(EShort)DImpl._get(serSmall)._withAlias(alias),
			(EInt)DImpl._get(ser)._withAlias(alias),
			(ELong)DImpl._get(serBig)._withAlias(alias),
			(EInt)DImpl._get(anInteger)._withAlias(alias),
			(ELong)DImpl._get(aBigint)._withAlias(alias),
			(EBigDecimal)DImpl._get(aDecimal72)._withAlias(alias),
			(EBigDecimal)DImpl._get(aNumeric6)._withAlias(alias),
			(EBigDecimal)DImpl._get(aNumeric)._withAlias(alias),
			(EFloat)DImpl._get(aReal)._withAlias(alias),
			(EDouble)DImpl._get(aDouble)._withAlias(alias),
			(EShort)DImpl._get(anInt2)._withAlias(alias),
			(EInt)DImpl._get(anInt4)._withAlias(alias),
			(ELong)DImpl._get(anInt8)._withAlias(alias),
			(EString)DImpl._get(aVarchar)._withAlias(alias),
			(EString)DImpl._get(aVarchar10)._withAlias(alias),
			(EString)DImpl._get(aText)._withAlias(alias),
			(EString)DImpl._get(aChar)._withAlias(alias),
			(EString)DImpl._get(aChar10)._withAlias(alias),
			(EByteList)DImpl._get(aBytea)._withAlias(alias),
			(EDateTime)DImpl._get(aTimestamp3)._withAlias(alias),
			(EDateTime)DImpl._get(aTimestamp)._withAlias(alias),
			(EZonedDateTime)DImpl._get(aTimestampWithZone)._withAlias(alias),
			(EDate)DImpl._get(aDate)._withAlias(alias),
			(ETime)DImpl._get(aTime)._withAlias(alias),
			(ETime)DImpl._get(aTimeWithZone)._withAlias(alias),
			(EBool)DImpl._get(aBoolean)._withAlias(alias)
		);
	}
	public  static TAllGeneric	cast(DExpr<AllGeneric> expr){
		return (TAllGeneric)expr;
	}
}
