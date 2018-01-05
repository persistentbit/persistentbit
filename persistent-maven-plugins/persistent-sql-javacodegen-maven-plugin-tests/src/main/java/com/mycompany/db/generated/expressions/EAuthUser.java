package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.AuthUser;
import com.persistentbit.sql.dsl.expressions.*;

public abstract class EAuthUser implements DExpr<AuthUser>{

	public final ELong     id;
	public final ELong     authAppId;
	public final EString   userName;
	public final EString   password;
	public final EInt      wrongPasswordCount;
	public final EDateTime created;
	public final EDateTime lastLogin;
	public final EDateTime verified;
	public final EString   resetPasswordCode;
	public final EDateTime resetPasswordValidUntil;
	public final EString   verifyCode;
	public final EDateTime verifyCodeValidUntil;


	public EAuthUser(ELong id, ELong authAppId, EString userName, EString password, EInt wrongPasswordCount,
					 EDateTime created, EDateTime lastLogin, EDateTime verified, EString resetPasswordCode,
					 EDateTime resetPasswordValidUntil, EString verifyCode, EDateTime verifyCodeValidUntil) {
		this.id = id;
		this.authAppId = authAppId;
		this.userName = userName;
		this.password = password;
		this.wrongPasswordCount = wrongPasswordCount;
		this.created = created;
		this.lastLogin = lastLogin;
		this.verified = verified;
		this.resetPasswordCode = resetPasswordCode;
		this.resetPasswordValidUntil = resetPasswordValidUntil;
		this.verifyCode = verifyCode;
		this.verifyCodeValidUntil = verifyCodeValidUntil;
	}
}
