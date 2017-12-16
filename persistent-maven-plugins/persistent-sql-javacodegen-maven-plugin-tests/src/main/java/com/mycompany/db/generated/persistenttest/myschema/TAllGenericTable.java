package com.mycompany.db.generated.persistenttest.myschema;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.inserts.Insert;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.dsl.generic.updates.Update;
import com.persistentbit.sql.work.DbWork;

public class TAllGenericTable extends TAllGeneric implements DExprTable<AllGeneric> {
	
	
	public TAllGenericTable(DbTableContext tableContext){
		super(tableContext.createExprString("id_part1"),tableContext.createExprLong("id_part2"),tableContext.createExprShort( "ser_small"),tableContext.createExprInt( "ser"),tableContext.createExprLong("ser_big"),tableContext.createExprInt( "an_integer"),tableContext.createExprLong("a_bigint"),tableContext.createExprBigDecimal("a_decimal_7_2"),tableContext.createExprBigDecimal("a_numeric_6"),tableContext.createExprBigDecimal("a_numeric"),tableContext.createExprFloat( "a_real"),tableContext.createExprDouble( "a_double"),tableContext.createExprShort( "an_int2"),tableContext.createExprInt( "an_int4"),tableContext.createExprLong("an_int8"),tableContext.createExprString("a_varchar"),tableContext.createExprString("a_varchar_10"),tableContext.createExprString("a_text"),tableContext.createExprString("a_char"),tableContext.createExprString("a_char_10"),tableContext.createExprByteList("a_bytea"),tableContext.createExprDateTime("a_timestamp_3"),tableContext.createExprDateTime("a_timestamp"),tableContext.createExprZonedDateTime("a_timestamp_with_zone"),tableContext.createExprLocalDate("a_date"),tableContext.createExprTime("a_time"),tableContext.createExprTime("a_time_with_zone"),tableContext.createExprBoolean("a_boolean"));
		this._tableContext = tableContext;
		this._insertFieldNames = PList.val("id_part1", "id_part2", "ser_small", "ser", "ser_big", "an_integer", "a_bigint", "a_decimal_7_2", "a_numeric_6", "a_numeric", "a_real", "a_double", "an_int2", "an_int4", "an_int8", "a_varchar", "a_varchar_10", "a_text", "a_char", "a_char_10", "a_bytea", "a_timestamp_3", "a_timestamp", "a_timestamp_with_zone", "a_date", "a_time", "a_time_with_zone", "a_boolean");
		this._autoGenKeyFieldNames = PList.val();
	}
	public  TAllGenericTable	alias(String tableAlias){
		return new TAllGenericTable(_tableContext.withTableAlias(tableAlias));
	}
	@Override
	public  Query	query(){
		return _tableContext.createQuery(this);
	}
	@Override
	public  TAllGeneric	all(){
		return this;
	}
	public  TAllGeneric	val(AllGeneric v){
		DbContext db = _tableContext.getDbContext();
		return new TAllGeneric(
			db.val(v.getIdPart1()), db.val(v.getIdPart2()), db.val(v.getSerSmall()), db.val(v.getSer()), db.val(v.getSerBig()), db.val(v.getAnInteger().orElse(null)), db.val(v.getABigint().orElse(null)), db.val(v.getADecimal72().orElse(null)), db.val(v.getANumeric6().orElse(null)), db.val(v.getANumeric().orElse(null)), db.val(v.getAReal().orElse(null)), db.val(v.getADouble().orElse(null)), db.val(v.getAnInt2().orElse(null)), db.val(v.getAnInt4().orElse(null)), db.val(v.getAnInt8().orElse(null)), db.val(v.getAVarchar().orElse(null)), db.val(v.getAVarchar10().orElse(null)), db.val(v.getAText().orElse(null)), db.val(v.getAChar().orElse(null)), db.val(v.getAChar10().orElse(null)), db.val(v.getABytea().orElse(null)), db.val(v.getATimestamp3().orElse(null)), db.val(v.getATimestamp().orElse(null)), db.val(v.getATimestampWithZone().orElse(null)), db.val(v.getADate().orElse(null)), db.val(v.getATime().orElse(null)), db.val(v.getATimeWithZone().orElse(null)), db.val(v.getABoolean().orElse(null))
		);
	}
	public  Update	update(){
		return new Update(_tableContext.getDbContext(),this);
	}
	public  DbWork<Integer>	update(AllGeneric record){
		DbContext db = _tableContext.getDbContext();
		return update()
			.set(this.serSmall, db.val(record.getSerSmall()))
			.set(this.ser, db.val(record.getSer()))
			.set(this.serBig, db.val(record.getSerBig()))
			.set(this.anInteger, db.val(record.getAnInteger().orElse(null)))
			.set(this.aBigint, db.val(record.getABigint().orElse(null)))
			.set(this.aDecimal72, db.val(record.getADecimal72().orElse(null)))
			.set(this.aNumeric6, db.val(record.getANumeric6().orElse(null)))
			.set(this.aNumeric, db.val(record.getANumeric().orElse(null)))
			.set(this.aReal, db.val(record.getAReal().orElse(null)))
			.set(this.aDouble, db.val(record.getADouble().orElse(null)))
			.set(this.anInt2, db.val(record.getAnInt2().orElse(null)))
			.set(this.anInt4, db.val(record.getAnInt4().orElse(null)))
			.set(this.anInt8, db.val(record.getAnInt8().orElse(null)))
			.set(this.aVarchar, db.val(record.getAVarchar().orElse(null)))
			.set(this.aVarchar10, db.val(record.getAVarchar10().orElse(null)))
			.set(this.aText, db.val(record.getAText().orElse(null)))
			.set(this.aChar, db.val(record.getAChar().orElse(null)))
			.set(this.aChar10, db.val(record.getAChar10().orElse(null)))
			.set(this.aBytea, db.val(record.getABytea().orElse(null)))
			.set(this.aTimestamp3, db.val(record.getATimestamp3().orElse(null)))
			.set(this.aTimestamp, db.val(record.getATimestamp().orElse(null)))
			.set(this.aTimestampWithZone, db.val(record.getATimestampWithZone().orElse(null)))
			.set(this.aDate, db.val(record.getADate().orElse(null)))
			.set(this.aTime, db.val(record.getATime().orElse(null)))
			.set(this.aTimeWithZone, db.val(record.getATimeWithZone().orElse(null)))
			.set(this.aBoolean, db.val(record.getABoolean().orElse(null)))
			.where(this.idPart1.eq(db.val(record.getIdPart1())).and(this.idPart2.eq(db.val(record.getIdPart2()))));
	}
	public  DbWork<AllGeneric>	insert(AllGeneric record){
		return new Insert(_tableContext.getDbContext(),this)
			.row(val(record))
			.map(ir -> record);
	}
}
