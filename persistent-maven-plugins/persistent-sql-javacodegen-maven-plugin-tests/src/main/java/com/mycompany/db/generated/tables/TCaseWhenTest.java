package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.ECaseWhenTest;
import com.mycompany.db.generated.impl.typefactories.CaseWhenTestTypeFactory;
import com.mycompany.db.generated.inserts.InsertCaseWhenTest;
import com.mycompany.db.generated.values.CaseWhenTest;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TCaseWhenTest extends AbstractTable<ECaseWhenTest, CaseWhenTest>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "case_when_test");
	private final ECaseWhenTest _all;
	public final  EInt          id;
	public final  ELong         value;


	private TCaseWhenTest(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(ECaseWhenTest.class, CaseWhenTestTypeFactory.class);
		this._all = context
			.getTypeFactory(ECaseWhenTest.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.value = _all.value;
	}

	public TCaseWhenTest(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<ECaseWhenTest> getTypeClass() {
		return ECaseWhenTest.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TCaseWhenTest as(String aliasName) {
		return new TCaseWhenTest(context, aliasName);
	}

	@Override
	public ECaseWhenTest all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ECaseWhenTest, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertCaseWhenTest insert() {
		return new InsertCaseWhenTest(context, this);
	}

	public DbWork<Integer> insert(@Nullable Integer id, @Nullable Long value) {
		return insert()
			.add(id, value)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<CaseWhenTest> insert(CaseWhenTest p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public ECaseWhenTest val(CaseWhenTest value) {
		return context.getTypeFactory(ECaseWhenTest.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public Delete delete() {
		return new Delete(context, this);
	}
}
