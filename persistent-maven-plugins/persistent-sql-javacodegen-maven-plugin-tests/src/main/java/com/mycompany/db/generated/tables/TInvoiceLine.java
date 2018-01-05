package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EInvoiceLine;
import com.mycompany.db.generated.impl.typefactories.InvoiceLineTypeFactory;
import com.mycompany.db.generated.inserts.InsertInvoiceLine;
import com.mycompany.db.generated.values.InvoiceLine;
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

public class TInvoiceLine extends AbstractTable<EInvoiceLine, InvoiceLine>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "invoice_line");
	private final DbWorkP1<Long, InvoiceLine> _selectById;
	private final EInvoiceLine                _all;
	public final  ELong                       id;
	public final  ELong                       invoiceId;
	public final  EString                     product;


	private TInvoiceLine(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EInvoiceLine.class, InvoiceLineTypeFactory.class);
		this._all = context
			.getTypeFactory(EInvoiceLine.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.invoiceId = _all.invoiceId;
		this.product = _all.product;
		this._selectById = query(p -> q -> {
			Param<ELong> paramid = context.param(ELong.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TInvoiceLine(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EInvoiceLine> getTypeClass() {
		return EInvoiceLine.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TInvoiceLine as(String aliasName) {
		return new TInvoiceLine(context, aliasName);
	}

	@Override
	public EInvoiceLine all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EInvoiceLine, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertInvoiceLine insert() {
		return new InsertInvoiceLine(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long id, @Nullable Long invoiceId, @Nullable String product) {
		return insert()
			.add(id, invoiceId, product)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<InvoiceLine> insert(InvoiceLine p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EInvoiceLine val(InvoiceLine value) {
		return context.getTypeFactory(EInvoiceLine.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(InvoiceLine value) {
		EInvoiceLine e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<InvoiceLine> selectById(long id) {
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
