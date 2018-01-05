package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.ECompany;
import com.mycompany.db.generated.impl.typefactories.CompanyTypeFactory;
import com.mycompany.db.generated.inserts.InsertCompany;
import com.mycompany.db.generated.values.Company;
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

public class TCompany extends AbstractTable<ECompany, Company>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "company");
	private final DbWorkP1<Long, Company> _selectById;
	private final ECompany                _all;
	public final  ELong                   id;
	public final  EString                 companyName;
	public final  EString                 adresStreet;
	public final  EInt                    adresHouseNumber;
	public final  EString                 adresBusNumber;
	public final  EString                 adresPostalcode;
	public final  EString                 adresCity;
	public final  EString                 adresCountry;
	public final  ELong                   ownerPersonId;


	private TCompany(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(ECompany.class, CompanyTypeFactory.class);
		this._all = context
			.getTypeFactory(ECompany.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.companyName = _all.companyName;
		this.adresStreet = _all.adresStreet;
		this.adresHouseNumber = _all.adresHouseNumber;
		this.adresBusNumber = _all.adresBusNumber;
		this.adresPostalcode = _all.adresPostalcode;
		this.adresCity = _all.adresCity;
		this.adresCountry = _all.adresCountry;
		this.ownerPersonId = _all.ownerPersonId;
		this._selectById = query(p -> q -> {
			Param<ELong> paramid = context.param(ELong.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TCompany(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<ECompany> getTypeClass() {
		return ECompany.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TCompany as(String aliasName) {
		return new TCompany(context, aliasName);
	}

	@Override
	public ECompany all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ECompany, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertCompany insert() {
		return new InsertCompany(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long id, @Nullable String companyName, @Nullable String adresStreet,
								  @Nullable Integer adresHouseNumber, @Nullable String adresBusNumber,
								  @Nullable String adresPostalcode, @Nullable String adresCity,
								  @Nullable String adresCountry, @Nullable Long ownerPersonId) {
		return insert()
			.add(id, companyName, adresStreet, adresHouseNumber, adresBusNumber, adresPostalcode, adresCity, adresCountry, ownerPersonId)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<Company> insert(Company p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public ECompany val(Company value) {
		return context.getTypeFactory(ECompany.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Company value) {
		ECompany e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<Company> selectById(long id) {
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
