package com.persistentbit.dsl.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */
public class TagInsert extends Insert<TTag, Long>{

	static private final PList<String> columnNames  = PList.val(
		"id",
		"name",
		"created"
	);
	static private final PSet<String>  withDefaults = PSet.val("created");

	private TagInsert(ExprContext context, TTag into, PList<String> columnNames,
					  PSet<String> withDefaults,
					  String autoGenKeyName,
					  PList<Object[]> rows
	) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public TagInsert(ExprContext context, TTag into) {
		this(context, into, columnNames, withDefaults, "id", PList.empty());
	}


	public TagInsert noDefaultForCreated() {
		return new TagInsert(
			context, into, columnNames,
			withDefaults.filter(n -> n.equals("created")),
			autoGenKeyName, rows
		);
	}


	public TagInsert add(
		Long id,
		String name,
		LocalDateTime created
	) {
		Object[] row = new Object[3];
		row[0] = id;
		row[1] = name;
		row[2] = created;
		return new TagInsert(
			context, into, columnNames, withDefaults, autoGenKeyName,
			rows.plus(row)
		);
	}

	public TagInsert add(Tag Tag) {
		return add(
			Tag.getId(),
			Tag.getName(),
			Tag.getCreated()
		);
	}

}
