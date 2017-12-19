import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
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
public class AddressTypeFactory implements ExprTypeFactory<EAddress, Address>{

	private final ExprContext context;
	private final Lazy<ExprTypeFactory<EString, String>> tfStreet;
	private final Lazy<ExprTypeFactory<EString, String>> tfPostalCode;
	private final Lazy<ExprTypeFactory<EString, String>> tfCity;

	public AddressTypeFactory(ExprContext context) {
		this.context = context;
		this.tfStreet = Lazy.code(() -> context.getTypeFactory(EString.class));
		this.tfPostalCode = Lazy.code(() -> context.getTypeFactory(EString.class));
		this.tfCity = Lazy.code(() -> context.getTypeFactory(EString.class));
	}

	@Override
	public <V extends Address> EAddress buildVal(V value) {
		return new AddressTypeFactory.EAddressImpl(
			tfStreet.get().buildVal(value.getStreet())
			, tfPostalCode.get().buildVal(value.getPostalCode())
			, tfCity.get().buildVal(value.getCity())
		);
	}

	@Override
	public EAddress buildAlias(String alias) {

		return new AddressTypeFactory.EAddressImpl(
			tfStreet.get().buildAlias(alias + "street")
			, tfPostalCode.get().buildAlias(alias + "postalCode")
			, tfCity.get().buildAlias(alias + "city")
		);
	}

	@Override
	public EAddress buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		throw new UnsupportedOperationException(op.name());
	}

	@Override
	public EAddress buildSingleOp(DExpr expr, SingleOpOperator op) {
		throw new UnsupportedOperationException(op.name());
	}

	@Override
	public EAddress buildTableField(String fieldSelectionName, String fieldName) {
		return new AddressTypeFactory.EAddressImpl(
			tfStreet.get().buildTableField(fieldSelectionName+"street",fieldName+"street"),
			tfPostalCode.get().buildTableField(fieldSelectionName+"postalCode",fieldName+"postalCode"),
			tfCity.get().buildTableField(fieldSelectionName+"city",fieldName+"city")
		);
	}

	@Override
	public EAddress buildSelection(EAddress expr, String prefixAlias) {
		return new AddressTypeFactory.EAddressImpl(
			tfStreet.get().buildSelection(expr.street,prefixAlias)
			, tfPostalCode.get().buildSelection(expr.postalCode,prefixAlias)
			, tfCity.get().buildSelection(expr.city,prefixAlias)
		);
	}

	@Override
	public PList<DExpr> expand(EAddress expr) {
		return PList.val(expr.street,expr.postalCode,expr.city);
	}

	@Override
	public SqlWithParams toSql(EAddress expr) {
		return tfStreet.get().toSql(expr.street)
				   .add(", ").add(tfPostalCode.get().toSql(expr.postalCode))
				   .add(", ").add(tfCity.get().toSql(expr.city));
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	@Override
	public Class<? extends DExpr<Address>> getTypeClass() {
		return EAddress.class;
	}

	private class EAddressImpl extends EAddress implements ExprTypeImpl<EAddress, Address>{

		private EAddressImpl(EString Street, EString PostalCode, EString city) {
			super(Street, PostalCode, city);
		}

		@Override
		public ExprTypeFactory<EAddress, Address> getTypeFactory() {
			return AddressTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EAddress[" + street + ", " + postalCode + ", " + city + "]";
		}
	}
}
