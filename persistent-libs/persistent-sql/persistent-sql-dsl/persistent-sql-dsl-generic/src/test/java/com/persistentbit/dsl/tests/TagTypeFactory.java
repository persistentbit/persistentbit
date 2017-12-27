package com.persistentbit.dsl.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */
public class TagTypeFactory extends AbstractStructureTypeFactory<ETag, Tag>{

	public TagTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<ETag, Tag>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", Tag::getId, v -> v.id)
			, createField(EString.class, "name", "name", Tag::getName, v -> v.name)
			, createField(EDateTime.class, "created", "created", Tag::getCreated, v -> v.created)
		);
	}


	@Override
	protected Tag buildValue(Object[] fieldValues) {
		return new Tag((Long) fieldValues[0], (String) fieldValues[1], (LocalDateTime) fieldValues[2]);
	}

	@Override
	protected ETagImpl createExpression(PStream<DExpr> fieldValues) {
		return new ETagImpl(fieldValues.iterator());
	}

	@Override
	public Class<? extends DExpr<Tag>> getTypeClass() {
		return ETag.class;
	}

	private final class ETagImpl extends ETag implements ExprTypeImpl<ETag, Tag>{

		private ETagImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next(),
				(EString) iter.next(),
				(EDateTime) iter.next()
			);
		}

		@Override
		public ExprTypeFactory<ETag, Tag> getTypeFactory() {
			return TagTypeFactory.this;
		}

		@Override
		public String toString() {
			return "ETag[" + id + ", " + name + ", " + created + "]";
		}
	}
}
