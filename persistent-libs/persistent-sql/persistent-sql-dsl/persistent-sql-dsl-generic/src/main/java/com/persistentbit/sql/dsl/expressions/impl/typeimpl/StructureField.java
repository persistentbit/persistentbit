package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/12/17
 */
public class StructureField<E extends DExpr<J>,J>{
	public final ExprContext context;
	public final Class<? extends DExpr> typeClass;
	public final String columnName;
	public final String fieldName;
	public final Function<J,Object>	valueGetter;
	public final Function<E,DExpr> expressionGetter;
	private ExprTypeFactory<E,J> typeFactory;

	public StructureField(ExprContext context, Class<? extends DExpr> typeClass, String columnName, String fieldName,
						  Function<J, Object> valueGetter,
						  Function<E, DExpr> expressionGetter, ExprTypeFactory<E,J> typeFactory
	) {
		this.context = context;
		this.typeClass = typeClass;
		this.columnName = columnName;
		this.fieldName = fieldName;
		this.valueGetter = valueGetter;
		this.expressionGetter = expressionGetter;
		this.typeFactory = typeFactory;
	}

	@Override
	public String toString() {
		return "StructureField[" + columnName + ", " + fieldName + "]";
	}

	@SuppressWarnings("unchecked")
	public ExprTypeFactory getTypeFactory() {
		if(typeFactory == null){
			typeFactory = context.getTypeFactory(typeClass);
		}
		return typeFactory;
	}


}
