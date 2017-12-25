import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */
public class TTag extends AbstractTable<ETag, Tag>{

	private final TableName _tableName = new TableName(null, "MYSCHEMA", "tags");
	public final ETag _all;

	public final ELong     id;
	public final EString   name;
	public final EDateTime created;

	public TTag(ExprContext context, String alias) {
		super(context, alias);
		this._all = context
			.getTypeFactory(ETag.class)
			.buildTableField(createFullTableNameOrAlias().toString() + ".", "", "");
		this.id = _all.id;
		this.name = _all.name;
		this.created = _all.created;
	}

	public TTag(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<? extends Table<ETag, Tag>> getTypeClass() {
		return this.getClass();
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TTag as(String aliasName) {
		return new TTag(context, aliasName);
	}

	@Override
	public ETag all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ETag, Function<Query, R>> builder) {
		return builder.apply(_all).apply(query());
	}

	public TagInsert insert() {
		return new TagInsert(context, this);
	}

	public DbWork<InsertResult<Long>> insert(
		Long id,
		String name,
		LocalDateTime created
	) {
		return insert()
			.add(id, name, created)
			.flatMap(irList -> Result.fromOpt(irList.headOpt()));
	}

	public DbWork<Tag> insert(Tag p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p.withId(ir.getAutoGenKey()))
				)
			);
	}
}
