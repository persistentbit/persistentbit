package com.pbtest.mysql.tables;

import com.pbtest.mysql.expressions.EGenData;
import com.pbtest.mysql.impl.typefactories.GenDataTypeFactory;
import com.pbtest.mysql.inserts.InsertGenData;
import com.pbtest.mysql.values.GenData;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

public class TGenData extends AbstractTable<EGenData, GenData>{

	private final TableName _tableName = new TableName("pbtest", null, "gen_data");
	private final DbWorkP1<Integer, GenData> _selectById;
	private final EGenData                   _all;
	public final  EInt                       genDataId;
	public final  EInt                       aInt;
	public final  EShort                     aShort;
	public final  ELong                      aLong;
	public final  EBigDecimal                aNum;
	public final  EDouble                    aDouble;
	public final  EDouble                    aReal;
	public final  EBool                      aBool;
	public final  EDate                      aDate;
	public final  ETime                      aTime;
	public final  EDateTime                  aTimestamp;
	public final  EString                    aString;


	private TGenData(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EGenData.class, GenDataTypeFactory.class);
		this._all = context
			.getTypeFactory(EGenData.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.genDataId = _all.genDataId;
		this.aInt = _all.aInt;
		this.aShort = _all.aShort;
		this.aLong = _all.aLong;
		this.aNum = _all.aNum;
		this.aDouble = _all.aDouble;
		this.aReal = _all.aReal;
		this.aBool = _all.aBool;
		this.aDate = _all.aDate;
		this.aTime = _all.aTime;
		this.aTimestamp = _all.aTimestamp;
		this.aString = _all.aString;
		this._selectById = query(p -> q -> {
			Param<EInt> paramgenDataId = context.param(EInt.class, "genDataId");
			return q
				.where(this.genDataId.eq(paramgenDataId.getExpr()))
				.selection(all())
				.one(paramgenDataId);
		});
	}

	public TGenData(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EGenData> getTypeClass() {
		return EGenData.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TGenData as(String aliasName) {
		return new TGenData(context, aliasName);
	}
	@Override
	public EGenData all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EGenData, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertGenData insert() {
		return new InsertGenData(context, this);
	}

	public DbWork<Object> insert(@Nullable Integer genDataId, @Nullable Integer aInt, @Nullable Short aShort,
								 @Nullable Long aLong, @Nullable BigDecimal aNum, @Nullable Double aDouble,
								 @Nullable Double aReal, @Nullable Boolean aBool, @Nullable LocalDate aDate,
								 @Nullable LocalTime aTime, @Nullable LocalDateTime aTimestamp,
								 @Nullable String aString) {
		return insert()
			.add(genDataId, aInt, aShort, aLong, aNum, aDouble, aReal, aBool, aDate, aTime, aTimestamp, aString)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<GenData> insert(GenData p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EGenData val(GenData value) {
		return context.getTypeFactory(EGenData.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(GenData value) {
		EGenData e = val(value);
		return update()
			.set(all(), e)
			.where(this.genDataId.eq(e.genDataId));
	}

	public DbWork<GenData> selectById(int genDataId) {
		return _selectById.with(genDataId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(int genDataId) {
		return delete()
			.where(this.genDataId.eq(genDataId));
	}
}
