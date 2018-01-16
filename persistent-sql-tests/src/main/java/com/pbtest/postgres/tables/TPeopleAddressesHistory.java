package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EPeopleAddressesHistory;
import com.pbtest.postgres.impl.typefactories.PeopleAddressesHistoryTypeFactory;
import com.pbtest.postgres.inserts.InsertPeopleAddressesHistory;
import com.pbtest.postgres.values.PeopleAddressesHistory;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP3;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.time.LocalDateTime;
import java.util.function.Function;

public class TPeopleAddressesHistory extends AbstractTable<EPeopleAddressesHistory, PeopleAddressesHistory>{

	private final TableName _tableName = new TableName(null, "pbtest", "people_addresses_history");
	private final DbWorkP3<Long, String, LocalDateTime, PeopleAddressesHistory> _selectById;
	private final EPeopleAddressesHistory                                       _all;
	public final  ELong                                                         personId;
	public final  EString                                                       addressRelationCode;
	public final  ELong                                                         addressId;
	public final  EDateTime                                                     startTime;
	public final  EDateTime                                                     endTime;


	private TPeopleAddressesHistory(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EPeopleAddressesHistory.class, PeopleAddressesHistoryTypeFactory.class);
		this._all = context
			.getTypeFactory(EPeopleAddressesHistory.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.personId = _all.personId;
		this.addressRelationCode = _all.addressRelationCode;
		this.addressId = _all.addressId;
		this.startTime = _all.startTime;
		this.endTime = _all.endTime;
		this._selectById = query(p -> q -> {
			Param<ELong>     parampersonId            = context.param(ELong.class, "personId");
			Param<EString>   paramaddressRelationCode = context.param(EString.class, "addressRelationCode");
			Param<EDateTime> paramstartTime           = context.param(EDateTime.class, "startTime");
			return q
				.where(this.personId.eq(parampersonId.getExpr())
						   .and(this.addressRelationCode.eq(paramaddressRelationCode.getExpr()))
						   .and(this.startTime.eq(paramstartTime.getExpr())))
				.selection(all())
				.one(parampersonId, paramaddressRelationCode, paramstartTime);
		});
	}

	public TPeopleAddressesHistory(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EPeopleAddressesHistory> getTypeClass() {
		return EPeopleAddressesHistory.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TPeopleAddressesHistory as(String aliasName) {
		return new TPeopleAddressesHistory(context, aliasName);
	}
	@Override
	public EPeopleAddressesHistory all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EPeopleAddressesHistory, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertPeopleAddressesHistory insert() {
		return new InsertPeopleAddressesHistory(context, this);
	}

	public DbWork<Object> insert(@Nullable Long personId, @Nullable String addressRelationCode,
								 @Nullable Long addressId, @Nullable LocalDateTime startTime,
								 @Nullable LocalDateTime endTime) {
		return insert()
			.add(personId, addressRelationCode, addressId, startTime, endTime)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<PeopleAddressesHistory> insert(PeopleAddressesHistory p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EPeopleAddressesHistory val(PeopleAddressesHistory value) {
		return context.getTypeFactory(EPeopleAddressesHistory.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(PeopleAddressesHistory value) {
		EPeopleAddressesHistory e = val(value);
		return update()
			.set(all(), e)
			.where(this.personId.eq(e.personId).and(this.addressRelationCode.eq(e.addressRelationCode))
					   .and(this.startTime.eq(e.startTime)));
	}

	public DbWork<PeopleAddressesHistory> selectById(long personId, String addressRelationCode,
													 LocalDateTime startTime) {
		return _selectById.with(personId, addressRelationCode, startTime);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long personId, String addressRelationCode, LocalDateTime startTime) {
		return delete()
			.where(this.personId.eq(personId).and(this.addressRelationCode.eq(addressRelationCode))
					   .and(this.startTime.eq(startTime)));
	}
}
