package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EPeopleAddresses;
import com.pbtest.postgres.impl.typefactories.PeopleAddressesTypeFactory;
import com.pbtest.postgres.inserts.InsertPeopleAddresses;
import com.pbtest.postgres.values.PeopleAddresses;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.postgres.rt.statements.PgQuery;
import com.persistentbit.sql.dsl.postgres.rt.statements.impl.PgQueryImpl;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TPeopleAddresses extends AbstractTable<EPeopleAddresses, PeopleAddresses>{

	private final TableName _tableName = new TableName(null, "pbtest", "people_addresses");
	private final DbWorkP2<Long, String, PeopleAddresses> _selectById;
	private final EPeopleAddresses                        _all;
	public final  ELong                                   personId;
	public final  EString                                 addressRelationCode;
	public final  ELong                                   addressId;


	private TPeopleAddresses(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EPeopleAddresses.class, PeopleAddressesTypeFactory.class);
		this._all = context
			.getTypeFactory(EPeopleAddresses.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.personId = _all.personId;
		this.addressRelationCode = _all.addressRelationCode;
		this.addressId = _all.addressId;
		this._selectById = query(p -> q -> {
			Param<ELong>   parampersonId            = context.param(ELong.class, "personId");
			Param<EString> paramaddressRelationCode = context.param(EString.class, "addressRelationCode");
			return q
				.where(this.personId.eq(parampersonId.getExpr())
						   .and(this.addressRelationCode.eq(paramaddressRelationCode.getExpr())))
				.selection(all())
				.one(parampersonId, paramaddressRelationCode);
		});
	}

	public TPeopleAddresses(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EPeopleAddresses> getTypeClass() {
		return EPeopleAddresses.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TPeopleAddresses as(String aliasName) {
		return new TPeopleAddresses(context, aliasName);
	}
	@Override
	public EPeopleAddresses all() {
		return _all;
	}

	public PgQuery query() {
		return new PgQueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EPeopleAddresses, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertPeopleAddresses insert() {
		return new InsertPeopleAddresses(context, this);
	}

	public DbWork<Long> insert(@Nullable Long personId, @Nullable String addressRelationCode,
							   @Nullable Long addressId) {
		return insert()
			.add(personId, addressRelationCode, addressId)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<PeopleAddresses> insert(PeopleAddresses p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EPeopleAddresses val(PeopleAddresses value) {
		return context.getTypeFactory(EPeopleAddresses.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(PeopleAddresses value) {
		EPeopleAddresses e = val(value);
		return update()
			.set(all(), e)
			.where(this.personId.eq(e.personId).and(this.addressRelationCode.eq(e.addressRelationCode)));
	}

	public DbWork<PeopleAddresses> selectById(long personId, String addressRelationCode) {
		return _selectById.with(personId, addressRelationCode);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long personId, String addressRelationCode) {
		return delete()
			.where(this.personId.eq(personId).and(this.addressRelationCode.eq(addressRelationCode)));
	}
}
