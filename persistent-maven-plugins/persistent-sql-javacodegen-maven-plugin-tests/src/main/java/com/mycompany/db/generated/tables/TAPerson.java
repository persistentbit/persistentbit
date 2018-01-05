package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EAPerson;
import com.mycompany.db.generated.impl.typefactories.APersonTypeFactory;
import com.mycompany.db.generated.inserts.InsertAPerson;
import com.mycompany.db.generated.values.APerson;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EInt;
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
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TAPerson extends AbstractTable<EAPerson, APerson>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "person");
	private final DbWorkP1<Long, APerson> _selectById;
	private final EAPerson                _all;
	public final  ELong                   id;
	public final  EString                 userName;
	public final  EString                 password;
	public final  EString                 street;
	public final  EInt                    houseNumber;
	public final  EString                 busNumber;
	public final  EString                 postalcode;
	public final  EString                 city;
	public final  EString                 country;


	private TAPerson(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EAPerson.class, APersonTypeFactory.class);
		this._all = context
			.getTypeFactory(EAPerson.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.userName = _all.userName;
		this.password = _all.password;
		this.street = _all.street;
		this.houseNumber = _all.houseNumber;
		this.busNumber = _all.busNumber;
		this.postalcode = _all.postalcode;
		this.city = _all.city;
		this.country = _all.country;
		this._selectById = query(p -> q -> {
			Param<ELong> paramid = context.param(ELong.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TAPerson(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EAPerson> getTypeClass() {
		return EAPerson.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TAPerson as(String aliasName) {
		return new TAPerson(context, aliasName);
	}
	@Override
	public EAPerson all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EAPerson, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertAPerson insert() {
		return new InsertAPerson(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long id, @Nullable String userName, @Nullable String password,
								  @Nullable String street, @Nullable Integer houseNumber, @Nullable String busNumber,
								  @Nullable String postalcode, @Nullable String city, @Nullable String country) {
		return insert()
			.add(id, userName, password, street, houseNumber, busNumber, postalcode, city, country)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<APerson> insert(APerson p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EAPerson val(APerson value) {
		return context.getTypeFactory(EAPerson.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(APerson value) {
		EAPerson e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<APerson> selectById(long id) {
		return _selectById.with(id);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long id) {
		return delete()
			.where(this.id.eq(id));
	}
}
