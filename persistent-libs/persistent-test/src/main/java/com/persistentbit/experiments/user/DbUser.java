package com.persistentbit.experiments.user;

import static com.persistentbit.experiments.MyDb.fromMyDb;
import static com.persistentbit.experiments.MyDb.hello;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/18
 */
public class DbUser{

	public static void main(String[] args) {
		System.out.println(fromMyDb());
		System.out.println(hello());
	}
}
