package com.mycompany.db.generated.tables;

import com.mycompany.db.generated.expressions.EInvoice;
import com.mycompany.db.generated.impl.typefactories.InvoiceTypeFactory;
import com.mycompany.db.generated.inserts.InsertInvoice;
import com.mycompany.db.generated.values.Invoice;
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

public class TInvoice extends AbstractTable<EInvoice, Invoice>{

	private final TableName _tableName = new TableName("persistenttest", "persistenttest", "invoice");
	private final DbWorkP1<Long, Invoice> _selectById;
	private final EInvoice                _all;
	public final  ELong                   id;
	public final  EString                 invoiceNummer;
	public final  ELong                   fromCompanyId;
	public final  ELong                   toCompanyId;


	private TInvoice(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EInvoice.class, InvoiceTypeFactory.class);
		this._all = context
			.getTypeFactory(EInvoice.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.id = _all.id;
		this.invoiceNummer = _all.invoiceNummer;
		this.fromCompanyId = _all.fromCompanyId;
		this.toCompanyId = _all.toCompanyId;
		this._selectById = query(p -> q -> {
			Param<ELong> paramid = context.param(ELong.class, "id");
			return q
				.where(this.id.eq(paramid.getExpr()))
				.selection(all())
				.one(paramid);
		});
	}

	public TInvoice(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EInvoice> getTypeClass() {
		return EInvoice.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TInvoice as(String aliasName) {
		return new TInvoice(context, aliasName);
	}
	@Override
	public EInvoice all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EInvoice, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertInvoice insert() {
		return new InsertInvoice(context, this);
	}

	public DbWork<Integer> insert(@Nullable Long id, @Nullable String invoiceNummer, @Nullable Long fromCompanyId,
								  @Nullable Long toCompanyId) {
		return insert()
			.add(id, invoiceNummer, fromCompanyId, toCompanyId)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<Invoice> insert(Invoice p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EInvoice val(Invoice value) {
		return context.getTypeFactory(EInvoice.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Invoice value) {
		EInvoice e = val(value);
		return update()
			.set(all(), e)
			.where(this.id.eq(e.id));
	}

	public DbWork<Invoice> selectById(long id) {
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
