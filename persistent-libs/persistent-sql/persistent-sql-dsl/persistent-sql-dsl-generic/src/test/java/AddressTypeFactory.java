import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class AddressTypeFactory extends AbstractStructureTypeFactory<EAddress, Address>{


	public AddressTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EAddress, Address>> buildFields() {
		return PList.val(
			createField(EString.class,"street","street",v -> v.getStreet(),v -> v.street)
			,createField(EString.class,"postalCode","postalCode",v -> v.getPostalCode(),v -> v.postalCode)
			,createField(EString.class,"city","city",v -> v.getCity(),v -> v.city)
		);
	}

	@Override
	protected EAddress createExpression(PStream<DExpr> fieldValues
	) {
		return new EAddressImpl(fieldValues.iterator());
	}

	@Override
	protected Address buildValue(Object[] fieldValues) {
		return new Address((String)fieldValues[0],(String)fieldValues[1],(String)fieldValues[2]);
	}

	@Override
	public Class<? extends DExpr<Address>> getTypeClass() {
		return null;
	}

	private class EAddressImpl extends EAddress implements ExprTypeImpl<EAddress, Address>{

		private EAddressImpl(Iterator<DExpr> iter) {
			super((EString)iter.next(), (EString)iter.next(), (EString)iter.next());
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

/*
	private final ExprContext                            context;
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
	public ExprTypeJdbcConvert<Address> getJdbcConverter(EAddress expr) {
		return ExprTypeJdbcConvert.createMultiColumn(
			PList.val(
				context.getJdbcConverter(expr.street),
				context.getJdbcConverter(expr.postalCode),
				context.getJdbcConverter(expr.city)
			),
			ol -> ol == null || (ol[0] == null && ol[1] == null && ol[2] == null)
				? null
				: new Address((String) ol[0], (String) ol[1], (String) ol[2])
		);
	}

	@Override
	public EAddress buildParam(Function<PMap<String, Object>, Object> paramGetter) {
		return new EAddressImpl(
			tfStreet.get().buildParam(createGetter(paramGetter, Address::getStreet))
			, tfPostalCode.get().buildParam(createGetter(paramGetter, Address::getPostalCode))
			, tfCity.get().buildParam(createGetter(paramGetter, Address::getCity))
		);
	}

	private final Function<PMap<String, Object>, Object> createGetter(Function<PMap<String, Object>, Object> pg,
																	  Function<Address, Object> fieldGetter
	) {
		return map -> {
			Address v = (Address) pg.apply(map);
			if(v == null) {
				return null;
			}
			return fieldGetter.apply(v);
		};
	}

	@Override
	public EAddress buildCall(String callName, DExpr[] params) {
		throw new UnsupportedOperationException("Can't call function " + callName + " of type " + getTypeClass());
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
			tfStreet.get().buildTableField(fieldSelectionName + "street", fieldName + "street"),
			tfPostalCode.get().buildTableField(fieldSelectionName + "postalCode", fieldName + "postalCode"),
			tfCity.get().buildTableField(fieldSelectionName + "city", fieldName + "city")
		);
	}

	@Override
	public EAddress buildSelection(EAddress expr, String prefixAlias) {
		return new AddressTypeFactory.EAddressImpl(
			tfStreet.get().buildSelection(expr.street, prefixAlias)
			, tfPostalCode.get().buildSelection(expr.postalCode, prefixAlias)
			, tfCity.get().buildSelection(expr.city, prefixAlias)
		);
	}

	@Override
	public PList<DExpr> expand(EAddress expr) {
		return PList.val(expr.street, expr.postalCode, expr.city);
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
	*/



}
