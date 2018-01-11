package com.persistentbit.people;

import com.persistentbit.sql.transactions.DbTransaction;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/01/18
 */
public class PeopleServiceImpl{

	private final Supplier<DbTransaction> transSup;

	public PeopleServiceImpl(Supplier<DbTransaction> transSup) {
		this.transSup = transSup;
	}


/*
	public Result<PersonInTime> createNewPerson(LocalDateTime pointInTime, PersonBaseInfo baseInfo) {
		//return people.insert((Long) null)
		//	.run(transSup.get());
		throw new ToDo();
	}

	public Result<OK> updatePersonBaseInfo(PersonBaseInfo baseInfoInTime) {
		throw new ToDo();
	}

	public Result<PersonBaseInfo> getPersonBaseInfo(long personId, LocalDateTime time) {
		return peopleBaseinfoHistory.query()
			.where(
				peopleBaseinfoHistory.personId.eq(personId)
					.and(val(time).between(peopleBaseinfoHistory.startTime, peopleBaseinfoHistory.endTime)))
			.selection(peopleBaseinfoHistory.all())
			.one()
			.run(transSup.get())
			.map(s -> PersonBaseInfo.build(b -> b
				.setFirstName(s.getNameFirst())
				.setMiddleName(s.getNameMiddle().orElse(null))
				.setLastName(s.getNameLast())
				.setBirthDay(s.getBirthDay().orElse(null))
				//.setGender(s.getGenderCode())
				.setGender(PersonGender.unknown)
				.setPersonId(s.getPersonId())
				//.setPointInTime(time)
				.setSallutationCode(s.getSalutationCode())
			));
	}
*/

}
