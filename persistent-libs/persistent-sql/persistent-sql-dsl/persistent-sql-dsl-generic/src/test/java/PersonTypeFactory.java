import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.ELong;
import com.persistentbit.sql.dsl.generic.expressions.EString;
import com.persistentbit.sql.dsl.generic.expressions.impl.*;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.Lazy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class PersonTypeFactory implements ExprTypeFactory<EPerson,Person> {
	private final ExprContext context;
	private final Lazy<ExprTypeFactory<ELong,Long>> tfId;
	private final Lazy<ExprTypeFactory<EString, String>> tfFirstName;
	private final Lazy<ExprTypeFactory<EString, String>> tfLastName;

	public PersonTypeFactory(ExprContext context) {
		this.context = context;
		this.tfId = Lazy.code(() -> context.getTypeFactory(ELong.class));
		this.tfFirstName = Lazy.code(()-> context.getTypeFactory(EString.class));
		this.tfLastName = Lazy.code(()-> context.getTypeFactory(EString.class));
	}

	@Override
	public <V extends Person> EPerson buildVal(V value) {
		return new EPersonImpl(
			tfId.get().buildVal(value.getId())
			, tfFirstName.get().buildVal(value.getFirstName())
			, tfLastName.get().buildVal(value.getLastName())
		);
	}

	@Override
	public EPerson buildAlias(String alias) {

		return new EPersonImpl(
			tfId.get().buildAlias(alias + "_id")
			, tfFirstName.get().buildAlias(alias + "_firstName")
			, tfLastName.get().buildAlias(alias + "_lastName")
		);
	}

	@Override
	public EPerson buildBinOp(DExpr left, BinOpOperator op, DExpr right
	) {
		return null;
	}

	@Override
	public EPerson buildSingleOp(DExpr expr, SingleOpOperator op) {
		return null;
	}

	@Override
	public EPerson buildTableField(String fieldSelectionName, String fieldName) {
		return null;
	}

	@Override
	public EPerson buildSelection(EPerson expr, String prefixAlias) {
		return null;
	}

	@Override
	public PList<DExpr> expand(EPerson expr) {
		return null;
	}

	@Override
	public SqlWithParams toSql(EPerson expr) {
		return null;
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	@Override
	public Class<? extends DExpr<Person>> getTypeClass() {
		return EPerson.class;
	}

	private class EPersonImpl extends EPerson implements ExprTypeImpl<EPerson,Person>{

		public EPersonImpl(ELong id, EString firstName, EString lastName) {
			super(id, firstName, lastName);
		}

		@Override
		public ExprTypeFactory<EPerson, Person> getTypeFactory() {
			return PersonTypeFactory.this;
		}
	}
}
