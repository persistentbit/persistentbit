package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EGroupByTest;
import com.mycompany.db.generated.impl.typefactories.GroupByTestTypeFactory;
import com.mycompany.db.generated.inserts.InsertGroupByTest;
import com.mycompany.db.generated.values.GroupByTest;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
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

public class TGroupByTest extends AbstractTable<EGroupByTest, GroupByTest>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "group_by_test");
	private final DbWorkP1<Long, GroupByTest> _selectById;
	private final EGroupByTest                _all;
	public final  ELong                       empId;
	public final  EInt                        hiringYear;
	public final  EInt                        hiringQuarter;
	public final  EBool                       active;


	private TGroupByTest(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EGroupByTest.class, GroupByTestTypeFactory.class);
		this._all = context
			.getTypeFactory(EGroupByTest.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.empId = _all.empId;
		this.hiringYear = _all.hiringYear;
		this.hiringQuarter = _all.hiringQuarter;
		this.active = _all.active;
		this._selectById = query(p -> q -> {
			Param<ELong> paramempId = context.param(ELong.class, "empId");
			return q
				.where(this.empId.eq(paramempId.getExpr()))
				.selection(all())
				.one(paramempId);
		});
	}

	public TGroupByTest(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EGroupByTest> getTypeClass() {
		return EGroupByTest.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TGroupByTest as(String aliasName) {
		return new TGroupByTest(context, aliasName);
	}

	@Override
	public EGroupByTest all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EGroupByTest, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertGroupByTest insert() {
		return new InsertGroupByTest(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long empId, @Nullable Integer hiringYear, @Nullable Integer hiringQuarter,
								  @Nullable Boolean active) {
		return insert()
			.add(empId, hiringYear, hiringQuarter, active)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<GroupByTest> insert(GroupByTest p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EGroupByTest val(GroupByTest value) {
		return context.getTypeFactory(EGroupByTest.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(GroupByTest value) {
		EGroupByTest e = val(value);
		return update()
			.set(all(), e)
			.where(this.empId.eq(e.empId));
	}

	public DbWork<GroupByTest> selectById(long empId) {
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
