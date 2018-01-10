package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EPeopleAddressesHistory;
import com.pbtest.postgres.impl.typefactories.PeopleAddressesHistoryTypeFactory;
import com.pbtest.postgres.inserts.InsertPeopleAddressesHistory;
import com.pbtest.postgres.values.PeopleAddressesHistory;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
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

public class TPeopleAddressesHistory extends AbstractTable<EPeopleAddressesHistory, PeopleAddressesHistory>{

	private final TableName _tableName = new TableName(null, "pbtest", "people_addresses_history");
	private final DbWorkP2<Long, LocalDate, PeopleAddressesHistory> _selectById;
	private final EPeopleAddressesHistory                           _all;
	public final  ELong                                             personId;
	public final  EString                                           addressRelationCode;
	public final  EDate                                             startDate;
	public final  EDateTime                                         endDate;
	public final  ELong                                             addressId;


	private TPeopleAddressesHistory(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EPeopleAddressesHistory.class, PeopleAddressesHistoryTypeFactory.class);
		this._all = context
			.getTypeFactory(EPeopleAddressesHistory.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.personId = _all.personId;
		this.addressRelationCode = _all.addressRelationCode;
		this.startDate = _all.startDate;
		this.endDate = _all.endDate;
		this.addressId = _all.addressId;
		this._selectById = query(p -> q -> {
			Param<ELong> parampersonId  = context.param(ELong.class, "personId");
			Param<EDate> paramstartDate = context.param(EDate.class, "startDate");
			return q
				.where(this.personId.eq(parampersonId.getExpr()).and(this.startDate.eq(paramstartDate.getExpr())))
				.selection(all())
				.one(parampersonId, paramstartDate);
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

	public DbWork<Integer> insert(@Nullable Long personId, @Nullable String addressRelationCode,
								  @Nullable LocalDate startDate, @Nullable LocalDateTime endDate,
								  @Nullable Long addressId) {
		return insert()
			.add(personId, addressRelationCode, startDate, endDate, addressId)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
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
			.where(this.personId.eq(e.personId).and(this.startDate.eq(e.startDate)));
	}

	public DbWork<PeopleAddressesHistory> selectById(long personId, LocalDate startDate) {
		return _selectById.with(personId, startDate);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long personId, LocalDate startDate) {
		return delete()
			.where(this.personId.eq(personId).and(this.startDate.eq(startDate)));
	}
}
