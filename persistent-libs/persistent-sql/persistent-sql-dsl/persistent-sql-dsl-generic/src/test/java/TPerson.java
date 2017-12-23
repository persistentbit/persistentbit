import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.dsl.tables.TableName;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class TPerson extends AbstractTable<EPerson,Person>{
	public final EPerson _all;

	public final ELong    id;
	public final EString  firstName;
	public final EString  middleName;
	public final EString  lastName;
	public final EAddress home;

	public TPerson(ExprContext context, String alias) {
		super(context,alias);
		this._all = context
						.getTypeFactory(EPerson.class)
						.buildTableField(createFullTableNameOrAlias().toString(),"");
		this.id = _all.id;
		this.firstName = _all.firstName;
		this.middleName = _all.middleName;
		this.lastName = _all.lastName;
		this.home = _all.home;
	}
	public TPerson(ExprContext context){
		this(context,null);
	}

	@Override
	public Class<? extends Table<EPerson, Person>> getTypeClass() {
		return this.getClass();
	}

	@Override
	protected TableName getTableName() {
		return new TableName(null,"MYSCHEMA","PERSONS");
	}

	@Override
	public TPerson as(String aliasName) {
		return new TPerson(context,aliasName);
	}

	@Override
	public EPerson all() {
		return _all;
	}
	public Query query(){
		return new QueryImpl(context, PList.val(this));
	}
	public <R> R query(Function<Query,R> builder){
		return builder.apply(query());
	}
}
