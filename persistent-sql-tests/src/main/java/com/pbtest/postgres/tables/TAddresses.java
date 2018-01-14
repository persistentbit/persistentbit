package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EAddresses;
import com.pbtest.postgres.impl.typefactories.AddressesTypeFactory;
import com.pbtest.postgres.inserts.InsertAddresses;
import com.pbtest.postgres.values.Addresses;
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
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TAddresses extends AbstractTable<EAddresses, Addresses>{

	private final TableName _tableName = new TableName(null, "pbtest", "addresses");
	private final DbWorkP1<Long, Addresses> _selectById;
	private final EAddresses                _all;
	public final  ELong                     addressId;
	public final  EString                   streetLine1;
	public final  EString                   streetLine2;
	public final  EString                   postalCode;
	public final  EString                   cityName;
	public final  EString                   countryCode;
	public final  EString                   district;


	private TAddresses(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EAddresses.class, AddressesTypeFactory.class);
		this._all = context
			.getTypeFactory(EAddresses.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.addressId = _all.addressId;
		this.streetLine1 = _all.streetLine1;
		this.streetLine2 = _all.streetLine2;
		this.postalCode = _all.postalCode;
		this.cityName = _all.cityName;
		this.countryCode = _all.countryCode;
		this.district = _all.district;
		this._selectById = query(p -> q -> {
			Param<ELong> paramaddressId = context.param(ELong.class, "addressId");
			return q
				.where(this.addressId.eq(paramaddressId.getExpr()))
				.selection(all())
				.one(paramaddressId);
		});
	}

	public TAddresses(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EAddresses> getTypeClass() {
		return EAddresses.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TAddresses as(String aliasName) {
		return new TAddresses(context, aliasName);
	}
	@Override
	public EAddresses all() {
		return _all;
	}

	public PgQuery query() {
		return new PgQueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EAddresses, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertAddresses insert() {
		return new InsertAddresses(context, this);
	}

	public DbWork<Long> insert(@Nullable Long addressId, @Nullable String streetLine1, @Nullable String streetLine2,
							   @Nullable String postalCode, @Nullable String cityName, @Nullable String countryCode,
							   @Nullable String district) {
		return insert()
			.add(addressId, streetLine1, streetLine2, postalCode, cityName, countryCode, district)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<Addresses> insert(Addresses p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EAddresses val(Addresses value) {
		return context.getTypeFactory(EAddresses.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Addresses value) {
		EAddresses e = val(value);
		return update()
			.set(all(), e)
			.where(this.addressId.eq(e.addressId));
	}

	public DbWork<Addresses> selectById(long addressId) {
		return _selectById.with(addressId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long addressId) {
		return delete()
			.where(this.addressId.eq(addressId));
	}
}
