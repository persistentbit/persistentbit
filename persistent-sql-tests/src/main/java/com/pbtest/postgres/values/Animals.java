package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.function.Function;

public class Animals{

	private final int    id;
	private final String type;
	private final String name;


	@Generated
	public Animals(int id, String type, String name) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.type = Objects.requireNonNull(type, "type can not be null");
		this.name = Objects.requireNonNull(name, "name can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private int    id;
		private String type;
		private String name;


		public Builder<SET, _T2, _T3> setId(int id) {
			this.id = id;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setType(String type) {
			this.type = type;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setName(String name) {
			this.name = name;
			return (Builder<_T1, _T2, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #id}.<br>
	 *
	 * @return {@link #id}
	 */
	@Generated
	public int getId() {
		return this.id;
	}

	/**
	 * Create a copy of this Animals object with a new value for field {@link #id}.<br>
	 *
	 * @param id The new value for field {@link #id}
	 *
	 * @return A new instance of {@link Animals}
	 */
	@Generated
	public Animals withId(int id) {
		return new Animals(id, type, name);
	}

	/**
	 * Get the value of field {@link #type}.<br>
	 *
	 * @return {@link #type}
	 */
	@Generated
	public String getType() {
		return this.type;
	}

	/**
	 * Create a copy of this Animals object with a new value for field {@link #type}.<br>
	 *
	 * @param type The new value for field {@link #type}
	 *
	 * @return A new instance of {@link Animals}
	 */
	@Generated
	public Animals withType(String type) {
		return new Animals(id, type, name);
	}

	/**
	 * Get the value of field {@link #name}.<br>
	 *
	 * @return {@link #name}
	 */
	@Generated
	public String getName() {
		return this.name;
	}

	/**
	 * Create a copy of this Animals object with a new value for field {@link #name}.<br>
	 *
	 * @param name The new value for field {@link #name}
	 *
	 * @return A new instance of {@link Animals}
	 */
	@Generated
	public Animals withName(String name) {
		return new Animals(id, type, name);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Animals == false) return false;
		Animals obj = (Animals) o;
		if(id != obj.id) return false;
		if(!type.equals(obj.type)) return false;
		if(!name.equals(obj.name)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = this.id;
		result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Animals[" +
			"id=" + id +
			", type=" + (type == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(type), 32, "...") + '\"') +
			", name=" + (name == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(name), 32, "...") + '\"') +
			']';
	}

	@Generated
	public Animals updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setType(this.type);
		b.setName(this.name);
		b = updater.apply(b);
		return new Animals(b.id, b.type, b.name);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Animals build(ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Animals(b.id, b.type, b.name);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Animals> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new Animals(b.id, b.type, b.name));
	}
}
