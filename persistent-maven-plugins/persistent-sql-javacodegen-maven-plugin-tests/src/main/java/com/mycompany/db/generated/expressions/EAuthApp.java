package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.AuthApp;
import com.persistentbit.sql.dsl.expressions.*;

public abstract class EAuthApp implements DExpr<AuthApp>{

	public final ELong   id;
	public final EString name;
	public final EString password;
	public final EBool   isRoot;
	public final EBool   isActive;
	public final EInt    maxWrongPasswordCount;


	public EAuthApp(ELong id, EString name, EString password, EBool isRoot, EBool isActive,
					EInt maxWrongPasswordCount) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.isRoot = isRoot;
		this.isActive = isActive;
		this.maxWrongPasswordCount = maxWrongPasswordCount;
	}
}
