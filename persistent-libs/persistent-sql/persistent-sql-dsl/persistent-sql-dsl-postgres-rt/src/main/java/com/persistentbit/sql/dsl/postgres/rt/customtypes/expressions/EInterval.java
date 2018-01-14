package com.persistentbit.sql.dsl.postgres.rt.customtypes.expressions;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EComparableMixIn;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.Interval;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public interface EInterval extends DExpr<Interval>, EComparableMixIn<EInterval, Interval>{
}
