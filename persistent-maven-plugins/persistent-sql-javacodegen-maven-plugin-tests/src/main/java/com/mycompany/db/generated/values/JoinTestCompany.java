package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class JoinTestCompany{

	private final long   cmpId;
	private final String name;
	@Nullable
	private final Long   ownerEmpId;


	@Generated
	public JoinTestCompany(long cmpId, String name, @Nullable Long ownerEmpId) {
		this.cmpId = Objects.requireNonNull(cmpId, "cmpId can not be null");
		this.name = Objects.requireNonNull(name, "name can not be null");
		this.ownerEmpId = ownerEmpId;
	}

	@Generated
	public JoinTestCompany(long cmpId, String name) {
		this(cmpId, name, null);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private long   cmpId;
		private String name;
		private Long   ownerEmpId;


		public Builder<SET, _T2> setCmpId(long cmpId) {
			this.cmpId = cmpId;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setName(String name) {
			this.name = name;
			return (Builder<_T1, SET>) this;
		}

		public Builder<_T1, _T2> setOwnerEmpId(@Nullable Long ownerEmpId) {
			this.ownerEmpId = ownerEmpId;
			return this;
		}
	}

	/**
	 * Get the value of field {@link #cmpId}.<br>
	 *
	 * @return {@link #cmpId}
	 */
	@Generated
	public long getCmpId() {
		return this.cmpId;
	}

	/**
	 * Create a copy of this JoinTestCompany object with a new value for field {@link #cmpId}.<br>
	 *
	 * @param cmpId The new value for field {@link #cmpId}
	 *
	 * @return A new instance of {@link JoinTestCompany}
	 */
	@Generated
	public JoinTestCompany withCmpId(long cmpId) {
		return new JoinTestCompany(cmpId, name, ownerEmpId);
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
	 * Create a copy of this JoinTestCompany object with a new value for field {@link #name}.<br>
	 *
	 * @param name The new value for field {@link #name}
	 *
	 * @return A new instance of {@link JoinTestCompany}
	 */
	@Generated
	public JoinTestCompany withName(String name) {
		return new JoinTestCompany(cmpId, name, ownerEmpId);
	}

	/**
	 * Get the value of field {@link #ownerEmpId}.<br>
	 *
	 * @return {@link #ownerEmpId}
	 */
	@Generated
	public Optional<Long> getOwnerEmpId() {
		return Optional.ofNullable(this.ownerEmpId);
	}

	/**
	 * Create a copy of this JoinTestCompany object with a new value for field {@link #ownerEmpId}.<br>
	 *
	 * @param ownerEmpId The new value for field {@link #ownerEmpId}
	 *
	 * @return A new instance of {@link JoinTestCompany}
	 */
	@Generated
	public JoinTestCompany withOwnerEmpId(@Nullable Long ownerEmpId) {
		return new JoinTestCompany(cmpId, name, ownerEmpId);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof JoinTestCompany == false) return false;
		JoinTestCompany obj = (JoinTestCompany) o;
		if(cmpId != obj.cmpId) return false;
		if(!name.equals(obj.name)) return false;
		if(ownerEmpId != null ? !ownerEmpId.equals(obj.ownerEmpId) : obj.ownerEmpId != null) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.cmpId ^ (this.cmpId >>> 32));
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.ownerEmpId != null ? this.ownerEmpId.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "JoinTestCompany[" +
			"cmpId=" + cmpId +
			", name=" + (name == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(name), 32, "...") + '\"') +
			", ownerEmpId=" + ownerEmpId +
			']';
	}

	@Generated
	public JoinTestCompany updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setCmpId(this.cmpId);
		b.setName(this.name);
		b.setOwnerEmpId(this.ownerEmpId);
		b = updater.apply(b);
		return new JoinTestCompany(b.cmpId, b.name, b.ownerEmpId);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static JoinTestCompany build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new JoinTestCompany(b.cmpId, b.name, b.ownerEmpId);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<JoinTestCompany> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new JoinTestCompany(b.cmpId, b.name, b.ownerEmpId));
	}
}
