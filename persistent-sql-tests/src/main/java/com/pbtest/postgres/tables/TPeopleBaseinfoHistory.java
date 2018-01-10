package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EPeopleBaseinfoHistory;
import com.pbtest.postgres.impl.typefactories.PeopleBaseinfoHistoryTypeFactory;
import com.pbtest.postgres.inserts.InsertPeopleBaseinfoHistory;
import com.pbtest.postgres.values.PeopleBaseinfoHistory;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class TPeopleBaseinfoHistory extends AbstractTable<EPeopleBaseinfoHistory, PeopleBaseinfoHistory>{

	private final TableName _tableName = new TableName(null, "pbtest", "people_baseinfo_history");
	private final DbWorkP2<Long, LocalDateTime, PeopleBaseinfoHistory> _selectById;
	private final EPeopleBaseinfoHistory                               _all;
	public final  ELong                                                personId;
	public final  EDateTime                                            startTime;
	public final  EDateTime                                            endTime;
	public final  EString                                              salutationCode;
	public final  EString                                              nameFirst;
	public final  EString                                              nameMiddle;
	public final  EString                                              nameLast;
	public final  EString                                              genderCode;
	public final  EDate                                                birthDay;


	private TPeopleBaseinfoHistory(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EPeopleBaseinfoHistory.class, PeopleBaseinfoHistoryTypeFactory.class);
		this._all = context
			.getTypeFactory(EPeopleBaseinfoHistory.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.personId = _all.personId;
		this.startTime = _all.startTime;
		this.endTime = _all.endTime;
		this.salutationCode = _all.salutationCode;
		this.nameFirst = _all.nameFirst;
		this.nameMiddle = _all.nameMiddle;
		this.nameLast = _all.nameLast;
		this.genderCode = _all.genderCode;
		this.birthDay = _all.birthDay;
		this._selectById = query(p -> q -> {
			Param<ELong>     parampersonId  = context.param(ELong.class, "personId");
			Param<EDateTime> paramstartTime = context.param(EDateTime.class, "startTime");
			return q
				.where(this.personId.eq(parampersonId.getExpr()).and(this.startTime.eq(paramstartTime.getExpr())))
				.selection(all())
				.one(parampersonId, paramstartTime);
		});
	}

	public TPeopleBaseinfoHistory(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EPeopleBaseinfoHistory> getTypeClass() {
		return EPeopleBaseinfoHistory.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TPeopleBaseinfoHistory as(String aliasName) {
		return new TPeopleBaseinfoHistory(context, aliasName);
	}
	@Override
	public EPeopleBaseinfoHistory all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EPeopleBaseinfoHistory, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertPeopleBaseinfoHistory insert() {
		return new InsertPeopleBaseinfoHistory(context, this);
	}

	public DbWork<Long> insert(@Nullable Long personId, @Nullable LocalDateTime startTime,
							   @Nullable LocalDateTime endTime, @Nullable String salutationCode,
							   @Nullable String nameFirst, @Nullable String nameMiddle, @Nullable String nameLast,
							   @Nullable String genderCode, @Nullable LocalDate birthDay) {
		return insert()
			.add(personId, startTime, endTime, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<PeopleBaseinfoHistory> insert(PeopleBaseinfoHistory p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EPeopleBaseinfoHistory val(PeopleBaseinfoHistory value) {
		return context.getTypeFactory(EPeopleBaseinfoHistory.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(PeopleBaseinfoHistory value) {
		EPeopleBaseinfoHistory e = val(value);
		return update()
			.set(all(), e)
			.where(this.personId.eq(e.personId).and(this.startTime.eq(e.startTime)));
	}

	public DbWork<PeopleBaseinfoHistory> selectById(long personId, LocalDateTime startTime) {
		return _selectById.with(personId, startTime);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long personId, LocalDateTime startTime) {
		return delete()
			.where(this.personId.eq(personId).and(this.startTime.eq(startTime)));
	}
}
