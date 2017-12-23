import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.utils.Lazy;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class PersonTypeFactory implements ExprTypeFactory<EPerson, Person>{

	private final ExprContext                              context;

	private final Lazy<ExprTypeFactory[]> tfs;

	public PersonTypeFactory(ExprContext context) {
		this.context = context;
		this.tfs = Lazy.code(() -> buildTypeFactories());
	}

	private ExprTypeFactory[] buildTypeFactories() {
		return new ExprTypeFactory[]{
			context.getTypeFactory(ELong.class),
			context.getTypeFactory(EString.class),
			context.getTypeFactory(EString.class),
			context.getTypeFactory(EAddress.class)
		};
	}

	private final Function[] valueGetters = new Function[]{
		v -> v == null ? null : ((Person) v).getId(),
		v -> v == null ? null : ((Person) v).getFirstName(),
		v -> v == null ? null : ((Person) v).getMiddleName().orElse(null),
		v -> v == null ? null : ((Person) v).getLastName(),
		v -> v == null ? null : ((Person) v).getHome()
	};


	@Override
	public <V extends Person> EPerson buildVal(V value) {
		DExpr[] values = new DExpr[tfs.get().length];
		for(int t = 0; t < tfs.get().length; t++) {
			values[t] = tfs.get()[t].buildVal(valueGetters[t].apply(value));
		}
		return new EPersonImpl(values);
	}
	@Override
	public EPerson buildParam(Function<PMap<String, Object>, Object> paramGetter) {
		DExpr[] values = new DExpr[tfs.get().length];
		for(int t = 0; t < tfs.get().length; t++) {
			Function vg = valueGetters[t];
			values[t] = tfs.get()[t].buildParam(map -> vg.apply(paramGetter.apply(map)));
		}
		return new EPersonImpl(values);

	}

	@Override
	public ExprTypeJdbcConvert<Person> getJdbcConverter(EPerson expr) {
		return ExprTypeJdbcConvert.<Person>createMultiColumn(
			PList.val(
				tfId.get().getJdbcConverter(expr.id),
				tfFirstName.get().getJdbcConverter(expr.firstName),
				tfMiddleName.get().getJdbcConverter(expr.middleName),
				tfLastName.get().getJdbcConverter(expr.lastName),
				tfAddress.get().getJdbcConverter(expr.home)
			),
			ol -> ol == null || (ol[0] == null && ol[1] == null && ol[2] == null && ol[3] == null && ol[4] != null)
				? null
				: new Person((Long) ol[0], (String) ol[1], (String) ol[2], (String) ol[3], (Address) ol[4])
		);
	}


	@Override
	public EPerson buildCall(String callName, DExpr[] params) {
		throw new UnsupportedOperationException("call " + callName + " on " + getTypeClass().getSimpleName());
	}

	private final Function<PMap<String,Object>,Object> createGetter(Function<PMap<String, Object>, Object> pg, Function<Person,Object> fieldGetter){
		return map -> {
			Person v = (Person) pg.apply(map);
			if(v == null){
				return null;
			}
			return fieldGetter.apply(v);
		};
	}
	@Override
	public EPerson buildAlias(String alias) {

		return new EPersonImpl(
			tfId.get().buildAlias(alias + "id")
			, tfFirstName.get().buildAlias(alias + "firstName")
			, tfMiddleName.get().buildAlias(alias + "middleName")
			, tfLastName.get().buildAlias(alias + "lastName")
			, tfAddress.get().buildAlias(alias + "home")
		);
	}

	@Override
	public EPerson buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		throw new UnsupportedOperationException(op.name());
	}

	@Override
	public EPerson buildSingleOp(DExpr expr, SingleOpOperator op) {
		throw new UnsupportedOperationException(op.name());
	}

	@Override
	public EPerson buildTableField(String fieldSelectionName, String fieldName) {
		return new EPersonImpl(
			tfId.get().buildTableField(fieldSelectionName + ".id", fieldName + "id"),
			tfFirstName.get().buildTableField(fieldSelectionName + ".first_Name", fieldName + "firstName"),
			tfMiddleName.get().buildTableField(fieldSelectionName + ".middle_name", fieldName + "middleName"),
			tfLastName.get().buildTableField(fieldSelectionName + ".last_Name", fieldName + "lastName"),
			tfAddress.get().buildTableField(fieldSelectionName + ".home_", fieldName + "home")
		);
	}

	@Override
	public EPerson buildSelection(EPerson expr, String prefixAlias) {
		return new EPersonImpl(
			tfId.get().buildSelection(expr.id,prefixAlias)
			, tfFirstName.get().buildSelection(expr.firstName,prefixAlias)
			, tfMiddleName.get().buildSelection(expr.middleName, prefixAlias)
			, tfLastName.get().buildSelection(expr.lastName,prefixAlias)
			, tfAddress.get().buildSelection(expr.home,prefixAlias)
		);
	}

	@Override
	public PList<DExpr> expand(EPerson expr) {
		return PList.<DExpr>empty()
			.plusAll(tfId.get().expand(expr.id))
			.plusAll(tfFirstName.get().expand(expr.firstName))
			.plusAll(tfMiddleName.get().expand(expr.middleName))
			.plusAll(tfLastName.get().expand(expr.lastName))
			.plusAll(tfAddress.get().expand(expr.home)
		);
	}

	@Override
	public SqlWithParams toSql(EPerson expr) {
		return tfId.get().toSql(expr.id)
			.add(", ").add(tfFirstName.get().toSql(expr.firstName))
			.add(", ").add(tfMiddleName.get().toSql(expr.middleName))
			.add(", ").add(tfLastName.get().toSql(expr.lastName))
			.add(", ").add(tfAddress.get().toSql(expr.home));
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	@Override
	public Class<? extends DExpr<Person>> getTypeClass() {
		return EPerson.class;
	}

	private class EPersonImpl extends EPerson implements ExprTypeImpl<EPerson, Person>{

		private EPersonImpl(DExpr[] exprs) {
			super((ELong) exprs[0], (EString) exprs[1], (EString) exprs[2], (EString) exprs[3], (EAddress) exprs[4]);
		}

		@Override
		public ExprTypeFactory<EPerson, Person> getTypeFactory() {
			return PersonTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPerson[" + id + ", " + firstName + ", " + middleName + ", " + lastName + ", " + home + "]";
		}
	}
}
