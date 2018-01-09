package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EAddressRelations;
import com.pbtest.postgres.impl.typefactories.AddressRelationsTypeFactory;
import com.pbtest.postgres.inserts.InsertAddressRelations;
import com.pbtest.postgres.values.AddressRelations;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TAddressRelations extends AbstractTable<EAddressRelations, AddressRelations>{

	private final TableName _tableName = new TableName("postgres", "pbtest", "address_relations");
	private final DbWorkP1<Object, AddressRelations> _selectById;
	private final EAddressRelations                  _all;
	public final  EObject                            addressRelationCode;
	public final  EString                            description;


	private TAddressRelations(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EAddressRelations.class, AddressRelationsTypeFactory.class);
		this._all = context
			.getTypeFactory(EAddressRelations.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.addressRelationCode = _all.addressRelationCode;
		this.description = _all.description;
		this._selectById = query(p -> q -> {
			Param<EObject> paramaddressRelationCode = context.param(EObject.class, "addressRelationCode");
			return q
				.where(this.addressRelationCode.eq(paramaddressRelationCode.getExpr()))
				.selection(all())
				.one(paramaddressRelationCode);
		});
	}

	public TAddressRelations(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EAddressRelations> getTypeClass() {
		return EAddressRelations.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TAddressRelations as(String aliasName) {
		return new TAddressRelations(context, aliasName);
	}

	@Override
	public EAddressRelations all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EAddressRelations, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertAddressRelations insert() {
		return new InsertAddressRelations(context, this);
	}

	public DbWork<Integer> insert(@Nullable Object addressRelationCode, @Nullable String description) {
		return insert()
			.add(addressRelationCode, description)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<AddressRelations> insert(AddressRelations p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EAddressRelations val(AddressRelations value) {
		return context.getTypeFactory(EAddressRelations.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(AddressRelations value) {
		EAddressRelations e = val(value);
		return update()
			.set(all(), e)
			.where(this.addressRelationCode.eq(e.addressRelationCode));
	}

	public DbWork<AddressRelations> selectById(Object addressRelationCode) {
		return _selectById.with(addressRelationCode);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(Object addressRelationCode) {
		return delete()
			.where(this.addressRelationCode.eq(addressRelationCode));
	}
}
