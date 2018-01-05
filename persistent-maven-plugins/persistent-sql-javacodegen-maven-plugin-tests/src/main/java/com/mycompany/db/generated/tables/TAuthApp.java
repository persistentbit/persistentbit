package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EAuthApp;
import com.mycompany.db.generated.impl.typefactories.AuthAppTypeFactory;
import com.mycompany.db.generated.inserts.InsertAuthApp;
import com.mycompany.db.generated.values.AuthApp;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.*;
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

public class TAuthApp extends AbstractTable<EAuthApp, AuthApp>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "auth_app");
	private final DbWorkP1<Long, AuthApp> _selectById;
	private final EAuthApp                _all;
	public final  ELong                   id;
	public final  EString                 name;
	public final  EString                 password;
	public final  EBool                   isRoot;
	public final  EBool                   isActive;
	public final  EInt                    maxWrongPasswordCount;


	private TAuthApp(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EAuthApp.class, AuthAppTypeFactory.class);
		this._all = context
			.getTypeFactory(EAuthApp.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.name = _all.name;
		this.password = _all.password;
		this.isRoot = _all.isRoot;
		this.isActive = _all.isActive;
		this.maxWrongPasswordCount = _all.maxWrongPasswordCount;
		this._selectById = query(p -> q -> {
			Param<ELong> paramid = context.param(ELong.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TAuthApp(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EAuthApp> getTypeClass() {
		return EAuthApp.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TAuthApp as(String aliasName) {
		return new TAuthApp(context, aliasName);
	}

	@Override
	public EAuthApp all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EAuthApp, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertAuthApp insert() {
		return new InsertAuthApp(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long id, @Nullable String name, @Nullable String password,
								  @Nullable Boolean isRoot, @Nullable Boolean isActive,
								  @Nullable Integer maxWrongPasswordCount) {
		return insert()
			.add(id, name, password, isRoot, isActive, maxWrongPasswordCount)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<AuthApp> insert(AuthApp p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EAuthApp val(AuthApp value) {
		return context.getTypeFactory(EAuthApp.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(AuthApp value) {
		EAuthApp e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<AuthApp> selectById(long id) {
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
