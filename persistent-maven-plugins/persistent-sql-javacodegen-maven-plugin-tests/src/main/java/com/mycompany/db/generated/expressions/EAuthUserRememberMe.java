package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.AuthUserRememberMe;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EAuthUserRememberMe implements DExpr<AuthUserRememberMe>{

	public final ELong     id;
	public final ELong     authUserId;
	public final EString   code;
	public final EDateTime validUntil;
	public final EString   passwordCode;


	public EAuthUserRememberMe(ELong id, ELong authUserId, EString code, EDateTime validUntil, EString passwordCode) {
		this.id = id;
		this.authUserId = authUserId;
		this.code = code;
		this.validUntil = validUntil;
		this.passwordCode = passwordCode;
	}
}
