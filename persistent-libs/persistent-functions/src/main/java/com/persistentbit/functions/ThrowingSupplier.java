package com.persistentbit.functions;

/**
 * A Supplier that can throw an {@link Exception}
 *
 * @author petermuys
 * @since 15/04/17
 */
@FunctionalInterface
public interface ThrowingSupplier<T>{
	T get() throws Exception;
}
