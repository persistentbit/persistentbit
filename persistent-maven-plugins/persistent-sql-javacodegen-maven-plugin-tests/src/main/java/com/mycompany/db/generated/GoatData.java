package com.mycompany.db.generated;

import com.mycompany.db.generated.expressions.*;
import com.mycompany.db.generated.tables.*;
import com.mycompany.db.generated.values.*;
import com.persistentbit.sql.dsl.postgres.rt.DbPostgres;

public class GoatData extends DbPostgres{


	public static final TAllGenericNulls    allGenericNulls    = new TAllGenericNulls(_context);
	public static final TAPerson            aPerson            = new TAPerson(_context);
	public static final TAuthUser           authUser           = new TAuthUser(_context);
	public static final TAuthApp            authApp            = new TAuthApp(_context);
	public static final TAuthUserRememberMe authUserRememberMe = new TAuthUserRememberMe(_context);
	public static final TCompany            company            = new TCompany(_context);
	public static final TInvoiceLine        invoiceLine        = new TInvoiceLine(_context);
	public static final TInvoice            invoice            = new TInvoice(_context);

	public static EAllGenericNulls val(AllGenericNulls value) {
		return _context.getTypeFactory(EAllGenericNulls.class).buildVal(value);
	}

	public static EAPerson val(APerson value) {
		return _context.getTypeFactory(EAPerson.class).buildVal(value);
	}

	public static EAuthUser val(AuthUser value) {
		return _context.getTypeFactory(EAuthUser.class).buildVal(value);
	}

	public static EAuthApp val(AuthApp value) {
		return _context.getTypeFactory(EAuthApp.class).buildVal(value);
	}

	public static EAuthUserRememberMe val(AuthUserRememberMe value) {
		return _context.getTypeFactory(EAuthUserRememberMe.class).buildVal(value);
	}

	public static ECompany val(Company value) {
		return _context.getTypeFactory(ECompany.class).buildVal(value);
	}

	public static EInvoiceLine val(InvoiceLine value) {
		return _context.getTypeFactory(EInvoiceLine.class).buildVal(value);
	}

	public static EInvoice val(Invoice value) {
		return _context.getTypeFactory(EInvoice.class).buildVal(value);
	}

	static {
	}
}
