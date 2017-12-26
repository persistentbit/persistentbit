package com.generated.mydb.myschema;

import com.generated.catalog.schema.EAddress;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.time.LocalDateTime;
import java.util.function.Function;

public class TPersons extends AbstractTable<EPersons, Persons>{

	private final TableName _tableName = new TableName("mydb", "myschema", "persons");
	private final DbWorkP1<Long, Persons> _selectById;
	private final EPersons                _all;
	public final  ELong                   personId;
	public final  EString                 firstName;
	public final  EString                 middleName;
	public final  EString                 lastName;
	public final  EAddress                home;
	public final  EDateTime               created;


	private TPersons(ExprContext context, String alias) {
		super(context, alias);
		this._all = context
			.getTypeFactory(EPersons.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.personId = _all.personId;
		this.firstName = _all.firstName;
		this.middleName = _all.middleName;
		this.lastName = _all.lastName;
		this.home = _all.home;
		this.created = _all.created;
		this._selectById = query(p -> q -> {
			Param<ELong> parampersonId = context.param(ELong.class, "personId");
			return q
				.where(this.personId.eq(parampersonId.getExpr()))
				.selection(all())
				.one(parampersonId);
		});
	}

	public TPersons(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<? extends Table<EPersons, Persons>> getTypeClass() {
		return this.getClass();
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TPersons as(String aliasName) {
		return new TPersons(context, aliasName);
	}

	@Override
	public EPersons all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EPersons, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertPersons insert() {
		return new InsertPersons(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long personId, @Nullable String firstName, @Nullable String middleName,
								  @Nullable String lastName, @Nullable String street, @Nullable String postalCode,
								  @Nullable String city, @Nullable LocalDateTime created
	) {
		return insert()
			.add(personId, firstName, middleName, lastName, street, postalCode, city, created)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<Persons> insert(Persons p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EPersons val(Persons value) {
		return context.getTypeFactory(EPersons.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Persons value) {
		EPersons e = val(value);
		return update()
			.set(all(), e)
			.where(this.personId.eq(e.personId));
	}

	public DbWork<Persons> selectById(long personId) {
		return _selectById.with(personId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long personId) {
		return delete()
			.where(this.personId.eq(personId));
	}
}
