package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EJoinTestEmployee;
import com.mycompany.db.generated.impl.typefactories.JoinTestEmployeeTypeFactory;
import com.mycompany.db.generated.inserts.InsertJoinTestEmployee;
import com.mycompany.db.generated.values.JoinTestEmployee;
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

public class TJoinTestEmployee extends AbstractTable<EJoinTestEmployee, JoinTestEmployee>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "join_test_employee");
	private final DbWorkP1<Long, JoinTestEmployee> _selectById;
	private final EJoinTestEmployee                _all;
	public final  ELong                            empId;
	public final  EString                          name;


	private TJoinTestEmployee(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EJoinTestEmployee.class, JoinTestEmployeeTypeFactory.class);
		this._all = context
			.getTypeFactory(EJoinTestEmployee.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.empId = _all.empId;
		this.name = _all.name;
		this._selectById = query(p -> q -> {
			Param<ELong> paramempId = context.param(ELong.class, "empId");
			return q
				.where(this.empId.eq(paramempId.getExpr()))
				.selection(all())
				.one(paramempId);
		});
	}

	public TJoinTestEmployee(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EJoinTestEmployee> getTypeClass() {
		return EJoinTestEmployee.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TJoinTestEmployee as(String aliasName) {
		return new TJoinTestEmployee(context, aliasName);
	}

	@Override
	public EJoinTestEmployee all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EJoinTestEmployee, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertJoinTestEmployee insert() {
		return new InsertJoinTestEmployee(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long empId, @Nullable String name) {
		return insert()
			.add(empId, name)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<JoinTestEmployee> insert(JoinTestEmployee p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EJoinTestEmployee val(JoinTestEmployee value) {
		return context.getTypeFactory(EJoinTestEmployee.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(JoinTestEmployee value) {
		EJoinTestEmployee e = val(value);
		return update()
			.set(all(), e)
			.where(this.empId.eq(e.empId));
	}

	public DbWork<JoinTestEmployee> selectById(long empId) {
		return _selectById.with(empId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long empId) {
		return delete()
			.where(this.empId.eq(empId));
	}
}
