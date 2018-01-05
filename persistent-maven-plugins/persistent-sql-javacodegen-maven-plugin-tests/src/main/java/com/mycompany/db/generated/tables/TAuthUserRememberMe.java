package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EAuthUserRememberMe;
import com.mycompany.db.generated.impl.typefactories.AuthUserRememberMeTypeFactory;
import com.mycompany.db.generated.inserts.InsertAuthUserRememberMe;
import com.mycompany.db.generated.values.AuthUserRememberMe;
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
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.time.LocalDateTime;
import java.util.function.Function;

public class TAuthUserRememberMe extends AbstractTable<EAuthUserRememberMe, AuthUserRememberMe>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "auth_user_remember_me");
	private final DbWorkP1<Long, AuthUserRememberMe> _selectById;
	private final EAuthUserRememberMe                _all;
	public final  ELong                              id;
	public final  ELong                              authUserId;
	public final  EString                            code;
	public final  EDateTime                          validUntil;
	public final  EString                            passwordCode;


	private TAuthUserRememberMe(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EAuthUserRememberMe.class, AuthUserRememberMeTypeFactory.class);
		this._all = context
			.getTypeFactory(EAuthUserRememberMe.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.authUserId = _all.authUserId;
		this.code = _all.code;
		this.validUntil = _all.validUntil;
		this.passwordCode = _all.passwordCode;
		this._selectById = query(p -> q -> {
			Param<ELong> paramid = context.param(ELong.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TAuthUserRememberMe(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EAuthUserRememberMe> getTypeClass() {
		return EAuthUserRememberMe.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TAuthUserRememberMe as(String aliasName) {
		return new TAuthUserRememberMe(context, aliasName);
	}
	@Override
	public EAuthUserRememberMe all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EAuthUserRememberMe, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertAuthUserRememberMe insert() {
		return new InsertAuthUserRememberMe(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long id, @Nullable Long authUserId, @Nullable String code,
								  @Nullable LocalDateTime validUntil, @Nullable String passwordCode) {
		return insert()
			.add(id, authUserId, code, validUntil, passwordCode)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<AuthUserRememberMe> insert(AuthUserRememberMe p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EAuthUserRememberMe val(AuthUserRememberMe value) {
		return context.getTypeFactory(EAuthUserRememberMe.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(AuthUserRememberMe value) {
		EAuthUserRememberMe e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<AuthUserRememberMe> selectById(long id) {
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
