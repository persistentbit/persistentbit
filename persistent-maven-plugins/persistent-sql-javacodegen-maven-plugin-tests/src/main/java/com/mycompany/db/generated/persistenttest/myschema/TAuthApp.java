package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.mycompany.db.generated.persistenttest.myschema.AuthApp;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import java.lang.String;

public class TAuthApp extends DTableExprImpl<AuthApp> {
	public  final	DExprLong	id;
	public  final	DExprString	name;
	public  final	DExprString	password;
	public  final	DExprBoolean	isRoot;
	public  final	DExprBoolean	isActive;
	public  final	DExprInt	maxWrongPasswordCount;
	
	
	public TAuthApp(DExprLong id, DExprString name, DExprString password, DExprBoolean isRoot, DExprBoolean isActive, DExprInt maxWrongPasswordCount){
		super(
			PList.val(id, name, password, isRoot, isActive, maxWrongPasswordCount),
			_scon -> _rr -> {
				Long	_id = DImpl._get(id)._read(_scon,_rr);
				String	_name = DImpl._get(name)._read(_scon,_rr);
				String	_password = DImpl._get(password)._read(_scon,_rr);
				Boolean	_isRoot = DImpl._get(isRoot)._read(_scon,_rr);
				Boolean	_isActive = DImpl._get(isActive)._read(_scon,_rr);
				Integer	_maxWrongPasswordCount = DImpl._get(maxWrongPasswordCount)._read(_scon,_rr);
				if(_id== null && _name== null && _password== null && _isRoot== null && _isActive== null && _maxWrongPasswordCount== null) { return null; }
				return new AuthApp(_id, _name, _password, _isRoot, _isActive, _maxWrongPasswordCount);
			}
		);
		this.id	=	id;
		this.name	=	name;
		this.password	=	password;
		this.isRoot	=	isRoot;
		this.isActive	=	isActive;
		this.maxWrongPasswordCount	=	maxWrongPasswordCount;
	}
	@Override
	protected  TAuthApp	_doWithAlias(String alias){
		return new TAuthApp(
			(DExprLong)DImpl._get(id)._withAlias(alias), 
			(DExprString)DImpl._get(name)._withAlias(alias), 
			(DExprString)DImpl._get(password)._withAlias(alias), 
			(DExprBoolean)DImpl._get(isRoot)._withAlias(alias), 
			(DExprBoolean)DImpl._get(isActive)._withAlias(alias), 
			(DExprInt)DImpl._get(maxWrongPasswordCount)._withAlias(alias)
		);
	}
	public  static TAuthApp	cast(DExpr<AuthApp> expr){
		return (TAuthApp)expr;
	}
}
