package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.EInt;
import com.persistentbit.sql.dsl.generic.expressions.ELong;
import com.persistentbit.sql.dsl.generic.expressions.EDateTime;
import java.time.LocalDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.EString;

import java.lang.String;

public class TAuthUser extends DTableExprImpl<AuthUser> {
	public  final ELong     id;
	public  final ELong     authAppId;
	public  final EString   userName;
	public  final EString   password;
	public  final EInt      wrongPasswordCount;
	public  final EDateTime created;
	public  final EDateTime lastLogin;
	public  final EDateTime verified;
	public  final EString   resetPasswordCode;
	public  final EDateTime resetPasswordValidUntil;
	public  final EString   verifyCode;
	public  final EDateTime verifyCodeValidUntil;
	
	
	public TAuthUser(ELong id, ELong authAppId, EString userName, EString password, EInt wrongPasswordCount, EDateTime created, EDateTime lastLogin, EDateTime verified, EString resetPasswordCode, EDateTime resetPasswordValidUntil, EString verifyCode, EDateTime verifyCodeValidUntil){
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
			(ELong)DImpl._get(id)._withAlias(alias),
			(ELong)DImpl._get(authAppId)._withAlias(alias),
			(EString)DImpl._get(userName)._withAlias(alias),
			(EString)DImpl._get(password)._withAlias(alias),
			(EInt)DImpl._get(wrongPasswordCount)._withAlias(alias),
			(EDateTime)DImpl._get(created)._withAlias(alias),
			(EDateTime)DImpl._get(lastLogin)._withAlias(alias),
			(EDateTime)DImpl._get(verified)._withAlias(alias),
			(EString)DImpl._get(resetPasswordCode)._withAlias(alias),
			(EDateTime)DImpl._get(resetPasswordValidUntil)._withAlias(alias),
			(EString)DImpl._get(verifyCode)._withAlias(alias),
			(EDateTime)DImpl._get(verifyCodeValidUntil)._withAlias(alias)
		);
	}
	public  static TAuthUser	cast(DExpr<AuthUser> expr){
		return (TAuthUser)expr;
	}
}
