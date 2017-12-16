package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprInt;
import com.persistentbit.sql.dsl.generic.expressions.DExprLong;
import com.mycompany.db.generated.persistenttest.myschema.AuthUser;
import com.persistentbit.sql.dsl.generic.expressions.DExprDateTime;
import java.time.LocalDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.DExprString;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.code.annotations.Nullable;
import java.lang.String;

public class TAuthUser extends DTableExprImpl<AuthUser> {
	public  final	DExprLong	id;
	public  final	DExprLong	authAppId;
	public  final	DExprString	userName;
	public  final	DExprString	password;
	public  final	DExprInt	wrongPasswordCount;
	public  final	DExprDateTime	created;
	public  final	DExprDateTime	lastLogin;
	public  final	DExprDateTime	verified;
	public  final	DExprString	resetPasswordCode;
	public  final	DExprDateTime	resetPasswordValidUntil;
	public  final	DExprString	verifyCode;
	public  final	DExprDateTime	verifyCodeValidUntil;
	
	
	public TAuthUser(DExprLong id, DExprLong authAppId, DExprString userName, DExprString password, DExprInt wrongPasswordCount, DExprDateTime created, DExprDateTime lastLogin, DExprDateTime verified, DExprString resetPasswordCode, DExprDateTime resetPasswordValidUntil, DExprString verifyCode, DExprDateTime verifyCodeValidUntil){
		super(
			PList.val(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil),
			_scon -> _rr -> {
				Long	_id = DImpl._get(id)._read(_scon,_rr);
				Long	_authAppId = DImpl._get(authAppId)._read(_scon,_rr);
				String	_userName = DImpl._get(userName)._read(_scon,_rr);
				String	_password = DImpl._get(password)._read(_scon,_rr);
				Integer	_wrongPasswordCount = DImpl._get(wrongPasswordCount)._read(_scon,_rr);
				LocalDateTime	_created = DImpl._get(created)._read(_scon,_rr);
				LocalDateTime	_lastLogin = DImpl._get(lastLogin)._read(_scon,_rr);
				LocalDateTime	_verified = DImpl._get(verified)._read(_scon,_rr);
				String	_resetPasswordCode = DImpl._get(resetPasswordCode)._read(_scon,_rr);
				LocalDateTime	_resetPasswordValidUntil = DImpl._get(resetPasswordValidUntil)._read(_scon,_rr);
				String	_verifyCode = DImpl._get(verifyCode)._read(_scon,_rr);
				LocalDateTime	_verifyCodeValidUntil = DImpl._get(verifyCodeValidUntil)._read(_scon,_rr);
				if(_id== null && _authAppId== null && _userName== null && _password== null && _wrongPasswordCount== null && _created== null && _lastLogin== null && _verified== null && _resetPasswordCode== null && _resetPasswordValidUntil== null && _verifyCode== null && _verifyCodeValidUntil== null) { return null; }
				return new AuthUser(_id, _authAppId, _userName, _password, _wrongPasswordCount, _created, _lastLogin, _verified, _resetPasswordCode, _resetPasswordValidUntil, _verifyCode, _verifyCodeValidUntil);
			}
		);
		this.id	=	id;
		this.authAppId	=	authAppId;
		this.userName	=	userName;
		this.password	=	password;
		this.wrongPasswordCount	=	wrongPasswordCount;
		this.created	=	created;
		this.lastLogin	=	lastLogin;
		this.verified	=	verified;
		this.resetPasswordCode	=	resetPasswordCode;
		this.resetPasswordValidUntil	=	resetPasswordValidUntil;
		this.verifyCode	=	verifyCode;
		this.verifyCodeValidUntil	=	verifyCodeValidUntil;
	}
	@Override
	protected  TAuthUser	_doWithAlias(String alias){
		return new TAuthUser(
			(DExprLong)DImpl._get(id)._withAlias(alias), 
			(DExprLong)DImpl._get(authAppId)._withAlias(alias), 
			(DExprString)DImpl._get(userName)._withAlias(alias), 
			(DExprString)DImpl._get(password)._withAlias(alias), 
			(DExprInt)DImpl._get(wrongPasswordCount)._withAlias(alias), 
			(DExprDateTime)DImpl._get(created)._withAlias(alias), 
			(DExprDateTime)DImpl._get(lastLogin)._withAlias(alias), 
			(DExprDateTime)DImpl._get(verified)._withAlias(alias), 
			(DExprString)DImpl._get(resetPasswordCode)._withAlias(alias), 
			(DExprDateTime)DImpl._get(resetPasswordValidUntil)._withAlias(alias), 
			(DExprString)DImpl._get(verifyCode)._withAlias(alias), 
			(DExprDateTime)DImpl._get(verifyCodeValidUntil)._withAlias(alias)
		);
	}
	public  static TAuthUser	cast(DExpr<AuthUser> expr){
		return (TAuthUser)expr;
	}
}
