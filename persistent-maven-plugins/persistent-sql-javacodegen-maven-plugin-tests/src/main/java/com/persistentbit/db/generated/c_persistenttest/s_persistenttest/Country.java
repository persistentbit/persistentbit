package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public class Country{
	private final int id;
	private final String name;
	private final String code;

	public Country(int id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
}
