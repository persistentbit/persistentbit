package com.persistentbit.sql.dsl.postgres.rt.customtypes.expressions;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;

import java.util.UUID;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public interface EUUID extends DExpr<UUID>{

	EBool eq(EUUID other);

	EBool notEq(EUUID other);
}
