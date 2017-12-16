package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExprByteList;
import java.time.ZonedDateTime;
import com.persistentbit.sql.dsl.generic.expressions.DExprZonedDateTime;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.collections.PBitList;
import com.persistentbit.collections.PByteList;
import com.mycompany.db.generated.persistenttest.myschema.AllGeneric;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.sql.dsl.generic.expressions.DExprDateTime;
import com.persistentbit.sql.dsl.generic.expressions.DExprBitList;
import java.time.LocalDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import java.math.BigDecimal;
import com.persistentbit.sql.dsl.generic.expressions.DExprBigDecimal;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.generic.expressions.DExprDouble;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprDate;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.dsl.generic.expressions.DExprTime;
import java.lang.String;
import java.time.LocalDate;
import java.time.LocalTime;
import com.persistentbit.sql.dsl.generic.expressions.DExprShort;
import com.persistentbit.sql.dsl.generic.expressions.DExprFloat;

public class TAllGeneric extends DTableExprImpl<AllGeneric> {
	public  final	DExprString	idPart1;
	public  final	DExprLong	idPart2;
	public  final	DExprShort	serSmall;
	public  final	DExprInt	ser;
	public  final	DExprLong	serBig;
	public  final	DExprInt	anInteger;
	public  final	DExprLong	aBigint;
	public  final	DExprBigDecimal	aDecimal72;
	public  final	DExprBigDecimal	aNumeric6;
	public  final	DExprBigDecimal	aNumeric;
	public  final	DExprFloat	aReal;
	public  final	DExprDouble	aDouble;
	public  final	DExprShort	anInt2;
	public  final	DExprInt	anInt4;
	public  final	DExprLong	anInt8;
	public  final	DExprString	aVarchar;
	public  final	DExprString	aVarchar10;
	public  final	DExprString	aText;
	public  final	DExprString	aChar;
	public  final	DExprString	aChar10;
	public  final	DExprByteList	aBytea;
	public  final	DExprDateTime	aTimestamp3;
	public  final	DExprDateTime	aTimestamp;
	public  final	DExprZonedDateTime	aTimestampWithZone;
	public  final	DExprDate	aDate;
	public  final	DExprTime	aTime;
	public  final	DExprTime	aTimeWithZone;
	public  final	DExprBoolean	aBoolean;
	public  final	DExprBoolean	aBit;
	public  final	DExprBitList	aBit40;
	public  final	DExprBitList	aBitVarying;
	
	
	public TAllGeneric(DExprString idPart1, DExprLong idPart2, DExprShort serSmall, DExprInt ser, DExprLong serBig, DExprInt anInteger, DExprLong aBigint, DExprBigDecimal aDecimal72, DExprBigDecimal aNumeric6, DExprBigDecimal aNumeric, DExprFloat aReal, DExprDouble aDouble, DExprShort anInt2, DExprInt anInt4, DExprLong anInt8, DExprString aVarchar, DExprString aVarchar10, DExprString aText, DExprString aChar, DExprString aChar10, DExprByteList aBytea, DExprDateTime aTimestamp3, DExprDateTime aTimestamp, DExprZonedDateTime aTimestampWithZone, DExprDate aDate, DExprTime aTime, DExprTime aTimeWithZone, DExprBoolean aBoolean, DExprBoolean aBit, DExprBitList aBit40, DExprBitList aBitVarying){
		super(
			PList.val(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean, aBit, aBit40, aBitVarying),
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
				Boolean	_aBit = DImpl._get(aBit)._read(_scon,_rr);
				PBitList	_aBit40 = DImpl._get(aBit40)._read(_scon,_rr);
				PBitList	_aBitVarying = DImpl._get(aBitVarying)._read(_scon,_rr);
				if(_idPart1== null && _idPart2== null && _serSmall== null && _ser== null && _serBig== null && _anInteger== null && _aBigint== null && _aDecimal72== null && _aNumeric6== null && _aNumeric== null && _aReal== null && _aDouble== null && _anInt2== null && _anInt4== null && _anInt8== null && _aVarchar== null && _aVarchar10== null && _aText== null && _aChar== null && _aChar10== null && _aBytea== null && _aTimestamp3== null && _aTimestamp== null && _aTimestampWithZone== null && _aDate== null && _aTime== null && _aTimeWithZone== null && _aBoolean== null && _aBit== null && _aBit40== null && _aBitVarying== null) { return null; }
				return new AllGeneric(_idPart1, _idPart2, _serSmall, _ser, _serBig, _anInteger, _aBigint, _aDecimal72, _aNumeric6, _aNumeric, _aReal, _aDouble, _anInt2, _anInt4, _anInt8, _aVarchar, _aVarchar10, _aText, _aChar, _aChar10, _aBytea, _aTimestamp3, _aTimestamp, _aTimestampWithZone, _aDate, _aTime, _aTimeWithZone, _aBoolean, _aBit, _aBit40, _aBitVarying);
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
		this.aBit	=	aBit;
		this.aBit40	=	aBit40;
		this.aBitVarying	=	aBitVarying;
	}
	@Override
	protected  TAllGeneric	_doWithAlias(String alias){
		return new TAllGeneric(
			(DExprString)DImpl._get(idPart1)._withAlias(alias), 
			(DExprLong)DImpl._get(idPart2)._withAlias(alias), 
			(DExprShort)DImpl._get(serSmall)._withAlias(alias), 
			(DExprInt)DImpl._get(ser)._withAlias(alias), 
			(DExprLong)DImpl._get(serBig)._withAlias(alias), 
			(DExprInt)DImpl._get(anInteger)._withAlias(alias), 
			(DExprLong)DImpl._get(aBigint)._withAlias(alias), 
			(DExprBigDecimal)DImpl._get(aDecimal72)._withAlias(alias), 
			(DExprBigDecimal)DImpl._get(aNumeric6)._withAlias(alias), 
			(DExprBigDecimal)DImpl._get(aNumeric)._withAlias(alias), 
			(DExprFloat)DImpl._get(aReal)._withAlias(alias), 
			(DExprDouble)DImpl._get(aDouble)._withAlias(alias), 
			(DExprShort)DImpl._get(anInt2)._withAlias(alias), 
			(DExprInt)DImpl._get(anInt4)._withAlias(alias), 
			(DExprLong)DImpl._get(anInt8)._withAlias(alias), 
			(DExprString)DImpl._get(aVarchar)._withAlias(alias), 
			(DExprString)DImpl._get(aVarchar10)._withAlias(alias), 
			(DExprString)DImpl._get(aText)._withAlias(alias), 
			(DExprString)DImpl._get(aChar)._withAlias(alias), 
			(DExprString)DImpl._get(aChar10)._withAlias(alias), 
			(DExprByteList)DImpl._get(aBytea)._withAlias(alias), 
			(DExprDateTime)DImpl._get(aTimestamp3)._withAlias(alias), 
			(DExprDateTime)DImpl._get(aTimestamp)._withAlias(alias), 
			(DExprZonedDateTime)DImpl._get(aTimestampWithZone)._withAlias(alias), 
			(DExprDate)DImpl._get(aDate)._withAlias(alias), 
			(DExprTime)DImpl._get(aTime)._withAlias(alias), 
			(DExprTime)DImpl._get(aTimeWithZone)._withAlias(alias), 
			(DExprBoolean)DImpl._get(aBoolean)._withAlias(alias), 
			(DExprBoolean)DImpl._get(aBit)._withAlias(alias), 
			(DExprBitList)DImpl._get(aBit40)._withAlias(alias), 
			(DExprBitList)DImpl._get(aBitVarying)._withAlias(alias)
		);
	}
	public  static TAllGeneric	cast(DExpr<AllGeneric> expr){
		return (TAllGeneric)expr;
	}
}
