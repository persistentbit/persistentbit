package com.persistentbit.sql.dsl.tables;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.work.DbWork;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public abstract class AbstractTable<EALL extends DExpr<J>,J> implements TableImpl<EALL,J>{
	protected final ExprContext	context;
	@Nullable
	protected final String alias;


	public AbstractTable(ExprContext context,@Nullable String alias) {
		this.context = context;
		this.alias = alias;
		context.addTable(this);
	}
	public AbstractTable(ExprContext context){
		this(context,null);
	}


	protected abstract TableName	getTableName();


	@Override
	public Sql getFromName(String defaultCatalog, String defaultSchema) {
		Sql res = createFullTableName(defaultCatalog, defaultSchema);
		if(alias != null){
			res = res.add(" " + alias);
		}
		return res;
	}

	protected Sql createFullTableNameOrAlias() {
		if(alias != null){
			return Sql.sql(alias);
		}
		return createFullTableName(context.getDefaultCatalogName().orElse(null),context.getDefaultSchemaName().orElse(null));
	}

	@Override
	public DbWork<PList<J>> selectAll() {
		return query().selection(all()).list();
	}

	protected Sql createFullTableName(String defaultCatalog, String defaultSchema) {
		String catName = getTableName().getCatalogName().orElse("");
		if(defaultCatalog != null){
			if(catName.equals(defaultCatalog)){
				catName = "";
			}
		}
		if(catName.isEmpty() == false){
			catName += ".";
		}
		String schemaName = getTableName().getSchemaName().orElse("");
		if(defaultSchema != null){
			if(schemaName.equals(defaultSchema)){
				schemaName = "";
			}
		}
		if(schemaName.isEmpty() == false){
			schemaName += ".";
		}
		return Sql.sql(catName + schemaName + getTableName().getTableName());
	}




}
