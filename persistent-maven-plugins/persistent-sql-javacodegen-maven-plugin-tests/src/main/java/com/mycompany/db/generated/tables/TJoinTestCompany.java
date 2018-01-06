package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EJoinTestCompany;
import com.mycompany.db.generated.impl.typefactories.JoinTestCompanyTypeFactory;
import com.mycompany.db.generated.inserts.InsertJoinTestCompany;
import com.mycompany.db.generated.values.JoinTestCompany;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
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

public class TJoinTestCompany extends AbstractTable<EJoinTestCompany, JoinTestCompany>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "join_test_company");
	private final DbWorkP1<Long, JoinTestCompany> _selectById;
	private final EJoinTestCompany                _all;
	public final  ELong                           cmpId;
	public final  EString                         name;
	public final  ELong                           ownerEmpId;


	private TJoinTestCompany(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EJoinTestCompany.class, JoinTestCompanyTypeFactory.class);
		this._all = context
			.getTypeFactory(EJoinTestCompany.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.cmpId = _all.cmpId;
		this.name = _all.name;
		this.ownerEmpId = _all.ownerEmpId;
		this._selectById = query(p -> q -> {
			Param<ELong> paramcmpId = context.param(ELong.class, "cmpId");
			return q
				.where(this.cmpId.eq(paramcmpId.getExpr()))
				.selection(all())
				.one(paramcmpId);
		});
	}

	public TJoinTestCompany(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EJoinTestCompany> getTypeClass() {
		return EJoinTestCompany.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TJoinTestCompany as(String aliasName) {
		return new TJoinTestCompany(context, aliasName);
	}

	@Override
	public EJoinTestCompany all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EJoinTestCompany, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertJoinTestCompany insert() {
		return new InsertJoinTestCompany(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long cmpId, @Nullable String name, @Nullable Long ownerEmpId) {
		return insert()
			.add(cmpId, name, ownerEmpId)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<JoinTestCompany> insert(JoinTestCompany p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EJoinTestCompany val(JoinTestCompany value) {
		return context.getTypeFactory(EJoinTestCompany.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(JoinTestCompany value) {
		EJoinTestCompany e = val(value);
		return update()
			.set(all(), e)
			.where(this.cmpId.eq(e.cmpId));
	}

	public DbWork<JoinTestCompany> selectById(long cmpId) {
		return _selectById.with(cmpId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long cmpId) {
		return delete()
			.where(this.cmpId.eq(cmpId));
	}
}
