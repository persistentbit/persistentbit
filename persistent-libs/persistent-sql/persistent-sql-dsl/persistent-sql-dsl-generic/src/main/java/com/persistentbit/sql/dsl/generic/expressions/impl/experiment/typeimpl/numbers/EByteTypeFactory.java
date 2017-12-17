package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.numbers;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EByteTypeFactory extends AbstractTypeFactory<EByte,Byte>{

	public EByteTypeFactory(ExprContext context,
						   Class<EByte> typeClass,
						   ExprTypeJdbcConvert<Byte> jdbcConvert
	) {
		super(context, typeClass, jdbcConvert);
	}

	@Override
	protected EByte buildWithStrategy(TypeStrategy<Byte> strategy
	) {
		return new EByteImpl(context, EByte.class, strategy);
	}
	private class EByteImpl extends AbstractNumberTypeImpl<EByte,EShort,EInt,ELong,EFloat,EDouble,EBigDecimal,EByte,EByte,Byte> implements EByte{


		public EByteImpl(ExprContext context, Class typeClass,
						TypeStrategy typeStrategy
		) {
			super(context, typeClass, typeStrategy);
		}


		@Override
		protected EByte beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EByte.class).buildBinOp(this,op,other);
		}

		@Override
		protected EShort seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EShort.class).buildBinOp(this,op,other);
		}

		@Override
		protected EInt ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EInt.class).buildBinOp(this,op,other);
		}

		@Override
		protected ELong leBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this,op,other);
		}

		@Override
		protected EFloat feBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this,op,other);
		}

		@Override
		protected EDouble deBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this,op,other);
		}

		@Override
		protected EBigDecimal bdeBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this,op,other);
		}

		@Override
		protected EByte nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EByte.class).buildBinOp(this,op,other);
		}

		@Override
		protected EByte val(Byte value) {
			return EByteTypeFactory.this.buildVal(value);
		}

	}
}
