package com.pbtest.postgres;

import com.pbtest.postgres.expressions.*;
import com.pbtest.postgres.tables.*;
import com.pbtest.postgres.values.*;
import com.persistentbit.sql.dsl.postgres.rt.DbPostgres;

public class DbPg extends DbPostgres{


	public static final TGenders                genders                = new TGenders(_context);
	public static final TPeople                 people                 = new TPeople(_context);
	public static final TPeopleAddresses        peopleAddresses        = new TPeopleAddresses(_context);
	public static final TTranslationsCountry    translationsCountry    = new TTranslationsCountry(_context);
	public static final TSalutations            salutations            = new TSalutations(_context);
	public static final TTranslationsSalutation translationsSalutation = new TTranslationsSalutation(_context);
	public static final TAddresses              addresses              = new TAddresses(_context);
	public static final TProductGroups          productGroups          = new TProductGroups(_context);
	public static final TPeopleHistory          peopleHistory          = new TPeopleHistory(_context);
	public static final TPeopleAddressesHistory peopleAddressesHistory = new TPeopleAddressesHistory(_context);
	public static final TAddressRelations       addressRelations       = new TAddressRelations(_context);
	public static final TTranslationsGender     translationsGender     = new TTranslationsGender(_context);
	public static final TProducts               products               = new TProducts(_context);

	public static EGenders val(Genders value) {
		return _context.getTypeFactory(EGenders.class).buildVal(value);
	}

	public static EPeople val(People value) {
		return _context.getTypeFactory(EPeople.class).buildVal(value);
	}

	public static EPeopleAddresses val(PeopleAddresses value) {
		return _context.getTypeFactory(EPeopleAddresses.class).buildVal(value);
	}

	public static ETranslationsCountry val(TranslationsCountry value) {
		return _context.getTypeFactory(ETranslationsCountry.class).buildVal(value);
	}

	public static ESalutations val(Salutations value) {
		return _context.getTypeFactory(ESalutations.class).buildVal(value);
	}

	public static ETranslationsSalutation val(TranslationsSalutation value) {
		return _context.getTypeFactory(ETranslationsSalutation.class).buildVal(value);
	}

	public static EAddresses val(Addresses value) {
		return _context.getTypeFactory(EAddresses.class).buildVal(value);
	}

	public static EProductGroups val(ProductGroups value) {
		return _context.getTypeFactory(EProductGroups.class).buildVal(value);
	}

	public static EPeopleHistory val(PeopleHistory value) {
		return _context.getTypeFactory(EPeopleHistory.class).buildVal(value);
	}

	public static EPeopleAddressesHistory val(PeopleAddressesHistory value) {
		return _context.getTypeFactory(EPeopleAddressesHistory.class).buildVal(value);
	}

	public static EAddressRelations val(AddressRelations value) {
		return _context.getTypeFactory(EAddressRelations.class).buildVal(value);
	}

	public static ETranslationsGender val(TranslationsGender value) {
		return _context.getTypeFactory(ETranslationsGender.class).buildVal(value);
	}

	public static EProducts val(Products value) {
		return _context.getTypeFactory(EProducts.class).buildVal(value);
	}

	static {
	}
}
