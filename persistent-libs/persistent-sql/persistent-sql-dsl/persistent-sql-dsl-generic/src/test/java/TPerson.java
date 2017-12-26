import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
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
 * @since 19/12/17
 */
public class TPerson extends AbstractTable<EPerson, Person>{

	private final TableName _tableName = new TableName(null, "MYSCHEMA", "PERSONS");
	public final EPerson _all;

	public final ELong    id;
	public final EString  firstName;
	public final EString  middleName;
	public final EString  lastName;
	public final EAddress home;

	public TPerson(ExprContext context, String alias) {
		super(context, alias);
		this._all = context
			.getTypeFactory(EPerson.class)
			.buildTableField(createFullTableNameOrAlias().toString() + ".", "", "");
		this.id = _all.id;
		this.firstName = _all.firstName;
		this.middleName = _all.middleName;
		this.lastName = _all.lastName;
		this.home = _all.home;

		this._selectById = query(p -> q -> {
			Param<ELong> paramId = context.param(ELong.class, "id");
			return q
				.where(p.id.eq(paramId.getExpr()))
				.selection(all())
				.one(paramId);
		});
	}

	public TPerson(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<? extends Table<EPerson, Person>> getTypeClass() {
		return this.getClass();
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TPerson as(String aliasName) {
		return new TPerson(context, aliasName);
	}

	@Override
	public EPerson all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EPerson, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public PersonInsert insert() {
		return new PersonInsert(context, this);
	}


	public DbWork<Integer> insert(
		Long id,
		String firstName,
		String middleName,
		String lastName,
		String homeStreet,
		String homePostalCode,
		String homeCity,
		LocalDateTime created
	) {
		return insert()
			.add(id, firstName, middleName, lastName, homeStreet, homePostalCode, homeCity, created)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getUpdateCount)));
	}

	public DbWork<Person> insert(Person p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)
				)

			);
	}

	public EPerson val(Person p) {
		return context.getTypeFactory(EPerson.class).buildVal(p);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Person value) {
		EPerson e = val(value);
		return update()
			.set(all(), e)
			.where(id.eq(e.id));
	}

	private final DbWorkP1<Long, Person> _selectById;


	public DbWork<Person> selectById(long id) {
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
