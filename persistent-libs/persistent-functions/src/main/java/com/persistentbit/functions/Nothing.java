package com.persistentbit.functions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/12/16
 */
public final class Nothing{

	public static final Nothing inst = new Nothing();

	private Nothing() { }

	@Override
	public String toString() {
		return "Nothing";
	}

}
