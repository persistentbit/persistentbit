package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EAllGenericNulls;
import com.mycompany.db.generated.impl.typefactories.AllGenericNullsTypeFactory;
import com.mycompany.db.generated.inserts.InsertAllGenericNulls;
import com.mycompany.db.generated.values.AllGenericNulls;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

public class TAllGenericNulls extends AbstractTable<EAllGenericNulls, AllGenericNulls>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "all_generic_nulls");
	private final DbWorkP2<String, Long, AllGenericNulls> _selectById;
	private final EAllGenericNulls                        _all;
	public final  EString                                 idPart1;
	public final  ELong                                   idPart2;
	public final  EShort                                  serSmall;
	public final  EInt                                    ser;
	public final  ELong                                   serBig;
	public final  EInt                                    anInteger;
	public final  ELong                                   aBigint;
	public final  EBigDecimal                             aDecimal72;
	public final  EBigDecimal                             aNumeric6;
	public final  EBigDecimal                             aNumeric;
	public final  EFloat                                  aReal;
	public final  EDouble                                 aDouble;
	public final  EShort                                  anInt2;
	public final  EInt                                    anInt4;
	public final  ELong                                   anInt8;
	public final  EString                                 aVarchar;
	public final  EString                                 aVarchar10;
	public final  EString                                 aText;
	public final  EString                                 aChar;
	public final  EString                                 aChar10;
	public final  EByteList                               aBytea;
	public final  EDateTime                               aTimestamp3;
	public final  EDateTime                               aTimestamp;
	public final  EDateTime                               aTimestampWithZone;
	public final  EDate                                   aDate;
	public final  ETime                                   aTime;
	public final  ETime                                   aTimeWithZone;
	public final  EBool                                   aBoolean;


	private TAllGenericNulls(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EAllGenericNulls.class, AllGenericNullsTypeFactory.class);
		this._all = context
			.getTypeFactory(EAllGenericNulls.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.idPart1 = _all.idPart1;
		this.idPart2 = _all.idPart2;
		this.serSmall = _all.serSmall;
		this.ser = _all.ser;
		this.serBig = _all.serBig;
		this.anInteger = _all.anInteger;
		this.aBigint = _all.aBigint;
		this.aDecimal72 = _all.aDecimal72;
		this.aNumeric6 = _all.aNumeric6;
		this.aNumeric = _all.aNumeric;
		this.aReal = _all.aReal;
		this.aDouble = _all.aDouble;
		this.anInt2 = _all.anInt2;
		this.anInt4 = _all.anInt4;
		this.anInt8 = _all.anInt8;
		this.aVarchar = _all.aVarchar;
		this.aVarchar10 = _all.aVarchar10;
		this.aText = _all.aText;
		this.aChar = _all.aChar;
		this.aChar10 = _all.aChar10;
		this.aBytea = _all.aBytea;
		this.aTimestamp3 = _all.aTimestamp3;
		this.aTimestamp = _all.aTimestamp;
		this.aTimestampWithZone = _all.aTimestampWithZone;
		this.aDate = _all.aDate;
		this.aTime = _all.aTime;
		this.aTimeWithZone = _all.aTimeWithZone;
		this.aBoolean = _all.aBoolean;
		this._selectById = query(p -> q -> {
			Param<EString> paramidPart1 = context.param(EString.class, "idPart1");
			Param<ELong>   paramidPart2 = context.param(ELong.class, "idPart2");
			return q
				.where(this.idPart1.eq(paramidPart1.getExpr()).and(this.idPart2.eq(paramidPart2.getExpr())))
				.selection(all())
				.one(paramidPart1, paramidPart2);
		});
	}

	public TAllGenericNulls(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EAllGenericNulls> getTypeClass() {
		return EAllGenericNulls.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TAllGenericNulls as(String aliasName) {
		return new TAllGenericNulls(context, aliasName);
	}

	@Override
	public EAllGenericNulls all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EAllGenericNulls, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertAllGenericNulls insert() {
		return new InsertAllGenericNulls(context, this);
	}

	public DbWork<Integer> insert(@Nullable String idPart1, @Nullable Long idPart2, @Nullable Short serSmall,
								  @Nullable Integer ser, @Nullable Long serBig, @Nullable Integer anInteger,
								  @Nullable Long aBigint, @Nullable BigDecimal aDecimal72,
								  @Nullable BigDecimal aNumeric6, @Nullable BigDecimal aNumeric, @Nullable Float aReal,
								  @Nullable Double aDouble, @Nullable Short anInt2, @Nullable Integer anInt4,
								  @Nullable Long anInt8, @Nullable String aVarchar, @Nullable String aVarchar10,
								  @Nullable String aText, @Nullable String aChar, @Nullable String aChar10,
								  @Nullable PByteList aBytea, @Nullable LocalDateTime aTimestamp3,
								  @Nullable LocalDateTime aTimestamp, @Nullable LocalDateTime aTimestampWithZone,
								  @Nullable LocalDate aDate, @Nullable LocalTime aTime,
								  @Nullable LocalTime aTimeWithZone, @Nullable Boolean aBoolean) {
		return insert()
			.add(idPart1, idPart2, serSmall, ser, serBig, anInteger, aBigint, aDecimal72, aNumeric6, aNumeric, aReal, aDouble, anInt2, anInt4, anInt8, aVarchar, aVarchar10, aText, aChar, aChar10, aBytea, aTimestamp3, aTimestamp, aTimestampWithZone, aDate, aTime, aTimeWithZone, aBoolean)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<AllGenericNulls> insert(AllGenericNulls p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EAllGenericNulls val(AllGenericNulls value) {
		return context.getTypeFactory(EAllGenericNulls.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(AllGenericNulls value) {
		EAllGenericNulls e = val(value);
		return update()
			.set(all(), e)
			.where(this.idPart1.eq(e.idPart1).and(this.idPart2.eq(e.idPart2)));
	}

	public DbWork<AllGenericNulls> selectById(String idPart1, long idPart2) {
		return _selectById.with(idPart1, idPart2);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(String idPart1, long idPart2) {
		return delete()
			.where(this.idPart1.eq(idPart1).and(this.idPart2.eq(idPart2)));
	}
}
