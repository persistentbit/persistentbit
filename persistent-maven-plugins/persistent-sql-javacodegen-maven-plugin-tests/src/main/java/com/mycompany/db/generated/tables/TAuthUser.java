package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EAuthUser;
import com.mycompany.db.generated.impl.typefactories.AuthUserTypeFactory;
import com.mycompany.db.generated.inserts.InsertAuthUser;
import com.mycompany.db.generated.values.AuthUser;
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

import java.time.LocalDateTime;
import java.util.function.Function;

public class TAuthUser extends AbstractTable<EAuthUser, AuthUser>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "auth_user");
	private final DbWorkP1<Long, AuthUser> _selectById;
	private final EAuthUser                _all;
	public final  ELong                    id;
	public final  ELong                    authAppId;
	public final  EString                  userName;
	public final  EString                  password;
	public final  EInt                     wrongPasswordCount;
	public final  EDateTime                created;
	public final  EDateTime                lastLogin;
	public final  EDateTime                verified;
	public final  EString                  resetPasswordCode;
	public final  EDateTime                resetPasswordValidUntil;
	public final  EString                  verifyCode;
	public final  EDateTime                verifyCodeValidUntil;


	private TAuthUser(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EAuthUser.class, AuthUserTypeFactory.class);
		this._all = context
			.getTypeFactory(EAuthUser.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.authAppId = _all.authAppId;
		this.userName = _all.userName;
		this.password = _all.password;
		this.wrongPasswordCount = _all.wrongPasswordCount;
		this.created = _all.created;
		this.lastLogin = _all.lastLogin;
		this.verified = _all.verified;
		this.resetPasswordCode = _all.resetPasswordCode;
		this.resetPasswordValidUntil = _all.resetPasswordValidUntil;
		this.verifyCode = _all.verifyCode;
		this.verifyCodeValidUntil = _all.verifyCodeValidUntil;
		this._selectById = query(p -> q -> {
			Param<ELong> paramid = context.param(ELong.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TAuthUser(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EAuthUser> getTypeClass() {
		return EAuthUser.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TAuthUser as(String aliasName) {
		return new TAuthUser(context, aliasName);
	}
	@Override
	public EAuthUser all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EAuthUser, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertAuthUser insert() {
		return new InsertAuthUser(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long id, @Nullable Long authAppId, @Nullable String userName,
								  @Nullable String password, @Nullable Integer wrongPasswordCount,
								  @Nullable LocalDateTime created, @Nullable LocalDateTime lastLogin,
								  @Nullable LocalDateTime verified, @Nullable String resetPasswordCode,
								  @Nullable LocalDateTime resetPasswordValidUntil, @Nullable String verifyCode,
								  @Nullable LocalDateTime verifyCodeValidUntil) {
		return insert()
			.add(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<AuthUser> insert(AuthUser p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EAuthUser val(AuthUser value) {
		return context.getTypeFactory(EAuthUser.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(AuthUser value) {
		EAuthUser e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<AuthUser> selectById(long id) {
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
