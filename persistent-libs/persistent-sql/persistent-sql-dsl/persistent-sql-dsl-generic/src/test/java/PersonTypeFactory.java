import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.utils.Lazy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class PersonTypeFactory implements ExprTypeFactory<EPerson, Person>{

	private final ExprContext context;
	private final Lazy<ExprTypeFactory<ELong, Long>> tfId;
	private final Lazy<ExprTypeFactory<EString, String>> tfFirstName;
	private final Lazy<ExprTypeFactory<EString, String>> tfLastName;
	private final Lazy<ExprTypeFactory<EAddress, Address>> tfAddress;

	public PersonTypeFactory(ExprContext context) {
		this.context = context;
		this.tfId = Lazy.code(() -> context.getTypeFactory(ELong.class));
		this.tfFirstName = Lazy.code(() -> context.getTypeFactory(EString.class));
		this.tfLastName = Lazy.code(() -> context.getTypeFactory(EString.class));
		this.tfAddress = Lazy.code(() -> context.getTypeFactory(EAddress.class));
	}

	@Override
	public <V extends Person> EPerson buildVal(V value) {
		return new EPersonImpl(
			tfId.get().buildVal(value.getId())
			, tfFirstName.get().buildVal(value.getFirstName())
			, tfLastName.get().buildVal(value.getLastName())
			, tfAddress.get().buildVal(value.getHome())
		);
	}

	@Override
	public EPerson buildAlias(String alias) {

		return new EPersonImpl(
			tfId.get().buildAlias(alias + "id")
			, tfFirstName.get().buildAlias(alias + "firstName")
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
			tfId.get().buildTableField(fieldSelectionName+"id",fieldName+"id"),
			tfFirstName.get().buildTableField(fieldSelectionName+"firstName",fieldName+"firstName"),
			tfLastName.get().buildTableField(fieldSelectionName+"lastName",fieldName+"lastName"),
			tfAddress.get().buildTableField(fieldSelectionName+"home_",fieldName+"home")
		);
	}

	@Override
	public EPerson buildSelection(EPerson expr, String prefixAlias) {
		return new EPersonImpl(
			tfId.get().buildSelection(expr.id,prefixAlias)
			, tfFirstName.get().buildSelection(expr.firstName,prefixAlias)
			, tfLastName.get().buildSelection(expr.lastName,prefixAlias)
			, tfAddress.get().buildSelection(expr.home,prefixAlias)
		);
	}

	@Override
	public PList<DExpr> expand(EPerson expr) {
		return PList.<DExpr>empty()
			.plusAll(tfId.get().expand(expr.id))
			.plusAll(tfFirstName.get().expand(expr.firstName))
			.plusAll(tfLastName.get().expand(expr.lastName))
			.plusAll(tfAddress.get().expand(expr.home)
		);
	}

	@Override
	public SqlWithParams toSql(EPerson expr) {
		return tfId.get().toSql(expr.id)
			.add(", ").add(tfFirstName.get().toSql(expr.firstName))
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

		private EPersonImpl(ELong id, EString firstName, EString lastName,EAddress home) {
			super(id, firstName, lastName, home);
		}

		@Override
		public ExprTypeFactory<EPerson, Person> getTypeFactory() {
			return PersonTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPerson[" + id + ", " + firstName + ", " + lastName + ", " + home + "]";
		}
	}
}
