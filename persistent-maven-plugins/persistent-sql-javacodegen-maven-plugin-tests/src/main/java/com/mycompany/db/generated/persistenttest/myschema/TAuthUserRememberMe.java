package com.mycompany.db.generated.persistenttest.myschema;

import java.lang.Override;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.ELong;
import com.persistentbit.sql.dsl.generic.expressions.EDateTime;
import java.time.LocalDateTime;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTableExprImpl;
import com.persistentbit.sql.dsl.generic.expressions.EString;

import java.lang.String;

public class TAuthUserRememberMe extends DTableExprImpl<AuthUserRememberMe> {
	public  final ELong     id;
	public  final ELong     authUserId;
	public  final EString   code;
	public  final EDateTime validUntil;
	public  final EString   passwordCode;
	
	
	public TAuthUserRememberMe(ELong id, ELong authUserId, EString code, EDateTime validUntil, EString passwordCode){
		super(
			PList.val(id, authUserId, code, validUntil, passwordCode),
			_scon -> _rr -> {
				Long	_id = DImpl._get(id)._read(_scon,_rr);
				Long	_authUserId = DImpl._get(authUserId)._read(_scon,_rr);
				String	_code = DImpl._get(code)._read(_scon,_rr);
				LocalDateTime	_validUntil = DImpl._get(validUntil)._read(_scon,_rr);
				String	_passwordCode = DImpl._get(passwordCode)._read(_scon,_rr);
				if(_id== null && _authUserId== null && _code== null && _validUntil== null && _passwordCode== null) { return null; }
				return new AuthUserRememberMe(_id, _authUserId, _code, _validUntil, _passwordCode);
			}
		);
		this.id	=	id;
		this.authUserId	=	authUserId;
		this.code	=	code;
		this.validUntil	=	validUntil;
		this.passwordCode	=	passwordCode;
	}
	@Override
	protected  TAuthUserRememberMe	_doWithAlias(String alias){
		return new TAuthUserRememberMe(
			(ELong)DImpl._get(id)._withAlias(alias),
			(ELong)DImpl._get(authUserId)._withAlias(alias),
			(EString)DImpl._get(code)._withAlias(alias),
			(EDateTime)DImpl._get(validUntil)._withAlias(alias),
			(EString)DImpl._get(passwordCode)._withAlias(alias)
		);
	}
	public  static TAuthUserRememberMe	cast(DExpr<AuthUserRememberMe> expr){
		return (TAuthUserRememberMe)expr;
	}
}
