package com.generated;

import com.generated.expressions.EAddress;
import com.generated.expressions.EPersons;
import com.generated.impl.typefactories.AddressTypeFactory;
import com.generated.tables.TPersons;
import com.generated.values.Address;
import com.generated.values.Persons;
import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers.*;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.EBoolTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.EByteListTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.EStringTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTimeTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.ETimeTypeFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Db{


	private static final ExprContext _context = new ExprContext();
	public static final  TPersons    persons  = new TPersons(_context);

	public static EFloat val(Float value) {
		return _context.getTypeFactory(EFloat.class).buildVal(value);
	}

	public static EShort val(Short value) {
		return _context.getTypeFactory(EShort.class).buildVal(value);
	}

	public static EByteList val(PByteList value) {
		return _context.getTypeFactory(EByteList.class).buildVal(value);
	}

	public static EAddress val(Address value) {
		return _context.getTypeFactory(EAddress.class).buildVal(value);
	}

	public static EByte val(Byte value) {
		return _context.getTypeFactory(EByte.class).buildVal(value);
	}

	public static EBool val(Boolean value) {
		return _context.getTypeFactory(EBool.class).buildVal(value);
	}

	public static ETime val(LocalTime value) {
		return _context.getTypeFactory(ETime.class).buildVal(value);
	}

	public static EDate val(LocalDate value) {
		return _context.getTypeFactory(EDate.class).buildVal(value);
	}

	public static EPersons val(Persons value) {
		return _context.getTypeFactory(EPersons.class).buildVal(value);
	}

	public static EDouble val(Double value) {
		return _context.getTypeFactory(EDouble.class).buildVal(value);
	}

	public static EString val(String value) {
		return _context.getTypeFactory(EString.class).buildVal(value);
	}

	public static EBigDecimal val(BigDecimal value) {
		return _context.getTypeFactory(EBigDecimal.class).buildVal(value);
	}

	public static EInt val(Integer value) {
		return _context.getTypeFactory(EInt.class).buildVal(value);
	}

	public static EDateTime val(LocalDateTime value) {
		return _context.getTypeFactory(EDateTime.class).buildVal(value);
	}

	public static ELong val(Long value) {
		return _context.getTypeFactory(ELong.class).buildVal(value);
	}

	static {
		_context.registerType(EFloat.class, EFloatTypeFactory.class);
		_context.registerType(EShort.class, EShortTypeFactory.class);
		_context.registerType(EByteList.class, EByteListTypeFactory.class);
		_context.registerType(EAddress.class, AddressTypeFactory.class);
		_context.registerType(EByte.class, EByteTypeFactory.class);
		_context.registerType(EBool.class, EBoolTypeFactory.class);
		_context.registerType(ETime.class, ETimeTypeFactory.class);
		_context.registerType(EDate.class, EDateTypeFactory.class);
		_context.registerType(EDouble.class, EDoubleTypeFactory.class);
		_context.registerType(EString.class, EStringTypeFactory.class);
		_context.registerType(EBigDecimal.class, EBigDecimalTypeFactory.class);
		_context.registerType(EInt.class, EIntTypeFactory.class);
		_context.registerType(EDateTime.class, EDateTimeTypeFactory.class);
		_context.registerType(ELong.class, ELongTypeFactory.class);
	}
}
