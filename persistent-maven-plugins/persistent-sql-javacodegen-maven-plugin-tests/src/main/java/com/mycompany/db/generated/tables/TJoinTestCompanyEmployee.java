package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EJoinTestCompanyEmployee;
import com.mycompany.db.generated.impl.typefactories.JoinTestCompanyEmployeeTypeFactory;
import com.mycompany.db.generated.inserts.InsertJoinTestCompanyEmployee;
import com.mycompany.db.generated.values.JoinTestCompanyEmployee;
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
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TJoinTestCompanyEmployee extends AbstractTable<EJoinTestCompanyEmployee, JoinTestCompanyEmployee>{

	private final TableName _tableName =
		new TableName("persistenttest", "persistenttest", "join_test_company_employee");
	private final DbWorkP2<Long, Long, JoinTestCompanyEmployee> _selectById;
	private final EJoinTestCompanyEmployee                      _all;
	public final  ELong                                         cmpId;
	public final  ELong                                         empId;
	public final  EString                                       function;


	private TJoinTestCompanyEmployee(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EJoinTestCompanyEmployee.class, JoinTestCompanyEmployeeTypeFactory.class);
		this._all = context
			.getTypeFactory(EJoinTestCompanyEmployee.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.cmpId = _all.cmpId;
		this.empId = _all.empId;
		this.function = _all.function;
		this._selectById = query(p -> q -> {
			Param<ELong> paramcmpId = context.param(ELong.class, "cmpId");
			Param<ELong> paramempId = context.param(ELong.class, "empId");
			return q
				.where(this.cmpId.eq(paramcmpId.getExpr()).and(this.empId.eq(paramempId.getExpr())))
				.selection(all())
				.one(paramcmpId, paramempId);
		});
	}

	public TJoinTestCompanyEmployee(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EJoinTestCompanyEmployee> getTypeClass() {
		return EJoinTestCompanyEmployee.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TJoinTestCompanyEmployee as(String aliasName) {
		return new TJoinTestCompanyEmployee(context, aliasName);
	}

	@Override
	public EJoinTestCompanyEmployee all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EJoinTestCompanyEmployee, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertJoinTestCompanyEmployee insert() {
		return new InsertJoinTestCompanyEmployee(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long cmpId, @Nullable Long empId, @Nullable String function) {
		return insert()
			.add(cmpId, empId, function)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<JoinTestCompanyEmployee> insert(JoinTestCompanyEmployee p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EJoinTestCompanyEmployee val(JoinTestCompanyEmployee value) {
		return context.getTypeFactory(EJoinTestCompanyEmployee.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(JoinTestCompanyEmployee value) {
		EJoinTestCompanyEmployee e = val(value);
		return update()
			.set(all(), e)
			.where(this.cmpId.eq(e.cmpId).and(this.empId.eq(e.empId)));
	}

	public DbWork<JoinTestCompanyEmployee> selectById(long cmpId, long empId) {
		return _selectById.with(cmpId, empId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long cmpId, long empId) {
		return delete()
			.where(this.cmpId.eq(cmpId).and(this.empId.eq(empId)));
	}
}
