package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.EBool;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;

import java.lang.String;

public class TAuthApp extends DTableExprImpl<AuthApp> {
	public  final ELong   id;
	public  final EString name;
	public  final EString password;
	public  final EBool   isRoot;
	public  final EBool   isActive;
	public  final EInt    maxWrongPasswordCount;
	
	
	public TAuthApp(ELong id, EString name, EString password, EBool isRoot, EBool isActive, EInt maxWrongPasswordCount){
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
			(ELong)DImpl._get(id)._withAlias(alias),
			(EString)DImpl._get(name)._withAlias(alias),
			(EString)DImpl._get(password)._withAlias(alias),
			(EBool)DImpl._get(isRoot)._withAlias(alias),
			(EBool)DImpl._get(isActive)._withAlias(alias),
			(EInt)DImpl._get(maxWrongPasswordCount)._withAlias(alias)
		);
	}
	public  static TAuthApp	cast(DExpr<AuthApp> expr){
		return (TAuthApp)expr;
	}
}
