package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class EValImpl<E extends DExpr<J>,I extends DExprInternal<J>, J> implements InvocationHandler{
	private final J value;
	private final I impl;
	private final Class<E> typeClass;

	public EValImpl(J value, I impl, Class<E> typeClass) {
		this.value = value;
		this.impl = impl;
		this.typeClass = typeClass;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(impl,args);
	}

	static public <E extends DExpr<J>,I extends DExprInternal<J>, J>
	E createVal(ClassLoader cl, J value, I impl, Class<E> typeClass){
		return (E)Proxy.newProxyInstance(
			cl,
			new Class[]{DExprInternal.class, typeClass},
			new EValImpl<>(value,impl,typeClass)
		);
	}
}
