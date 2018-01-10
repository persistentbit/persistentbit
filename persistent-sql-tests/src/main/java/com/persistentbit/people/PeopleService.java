package com.persistentbit.people;

import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.transactions.DbTransaction;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static com.pbtest.postgres.DbPg.*;

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

	public Result<PersonInTime> createNewPerson(LocalDateTime pointInTime, PersonBaseInfo baseInfo) {
		return people.insert((Long) null)
			.run(transSup.get());
	}

	public Result<OK> updatePersonBaseInfo(PersonBaseInfo baseInfoInTime) {

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
				.setGender(s.getGenderCode())
				.setPersonId(s.getPersonId())
				.setPointInTime(time)
				.setSallutationCode(s.getSalutationCode())
			));
	}


}
