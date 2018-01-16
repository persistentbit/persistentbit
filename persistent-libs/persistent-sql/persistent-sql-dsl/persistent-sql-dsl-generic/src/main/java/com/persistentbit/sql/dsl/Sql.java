package com.persistentbit.sql.dsl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.impl.PrepStatParam;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class Sql{

	static private abstract class Item{

		abstract int setParams(PMap<String, Object> extParams, PreparedStatement stat, int index) throws SQLException;

		abstract String toSqlString();
	}

	private final PList<Item> items;

	private Sql(PList<Item> items) {
		this.items = items;
	}


	private static class ParamItem extends Item{

		private final PrepStatParam prepStatParam;

		private ParamItem(PrepStatParam prepStatParam) {
			this.prepStatParam = prepStatParam;
		}

		@Override
		public String toString() {
			return "?(" + prepStatParam + ")";
		}

		@Override
		int setParams(PMap<String, Object> extParams, PreparedStatement stat, int index) throws SQLException {
			return index + prepStatParam._setPrepStatement(extParams, stat, index);

		}

		@Override
		String toSqlString() {
			return "?";
		}
	}

	private static class StringItem extends Item{

		private final String sql;

		private StringItem(String sql) {
			this.sql = sql;
		}

		@Override
		public String toString() {
			return sql;
		}

		@Override
		int setParams(PMap<String, Object> extParams, PreparedStatement stat, int index) {
			return index;
		}

		@Override
		String toSqlString() {
			return sql;
		}
	}

	static public final Sql empty = new Sql(PList.empty());
	static public final Sql space = empty.add(" ");
	static public final Sql nl    = empty.add(System.lineSeparator());

	static public Sql sql(String sql) {
		return Sql.empty.add(sql);
	}

	static public Sql param(PrepStatParam param) {
		return Sql.empty.add(param);
	}

	public Sql add(Sql other) {
		if(this.items.isEmpty()) {
			return other;
		}
		return new Sql(items.plusAll(other.items));
	}

	public Sql add(String sql) {
		if(sql.isEmpty()) {
			return this;
		}
		return new Sql(items.plus(new StringItem(sql)));
	}

	public Sql add(PrepStatParam param) {
		return new Sql(items.plus(new ParamItem(param)));
	}

	public Sql add(PStream<Sql> collection, String sep) {
		//return collection.fold(this, (a,b) -> a.add(sep).add(b));
		if(collection.isEmpty()) {
			return this;
		}
		return add(collection.tail().fold(collection.headOpt().get(), (a, b) -> a.add(sep).add(b)));

	}


	@Override
	public String toString() {
		return items.map(Object::toString).fold("", (a, b) -> a + b);
	}

	public void setParams(PMap<String, Object> extParams, PreparedStatement stat) throws SQLException {
		int t = 1;
		for(Item i : items) {
			t = i.setParams(extParams, stat, t);
		}
	}

	public String getSql() {
		return items.map(Item::toSqlString).fold("", (a, b) -> a + b);
	}


}
