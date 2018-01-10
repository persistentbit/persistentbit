package com.persistentbit.people;

import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/01/18
 */
public class PeopleService{

	private final Supplier<DbTransaction> transSup;

	public PeopleService(Supplier<DbTransaction> transSup) {
		this.transSup = transSup;
	}

	public PersonInTime createNewPerson() {
		throw new ToDo();
	}
}
