package com.persistentbit.people;

import com.persistentbit.collections.PList;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/01/18
 */
public interface PeopleService{

	Result<Person> createNewPerson(PersonBaseInfo baseInfo);

	Result<Person> updateBaseInfo(Person person);

	Result<PersonAddress> addPersonAddress(PersonAddress address);

	Result<OK> deletePersonAddress(Person person, PersonAddress address);

	Result<OK> deletePerson(Person person);

	Result<PList<Person>> selectList(PersonQuery query, PersonQueryDetail detail);

	Result<Person> selectOne(PersonQuery query, PersonQueryDetail detail);

	Result<Person> selectById(long id, PersonQueryDetail detail);
}
