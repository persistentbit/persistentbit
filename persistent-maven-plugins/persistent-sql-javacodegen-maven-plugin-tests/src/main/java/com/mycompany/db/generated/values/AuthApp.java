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

public class AuthApp{

	private final long    id;
	private final String  name;
	private final String  password;
	private final boolean isRoot;
	private final boolean isActive;
	@Nullable
	private final Integer maxWrongPasswordCount;


	@Generated
	public AuthApp(long id, String name, String password, boolean isRoot, boolean isActive,
				   @Nullable Integer maxWrongPasswordCount) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.name = Objects.requireNonNull(name, "name can not be null");
		this.password = Objects.requireNonNull(password, "password can not be null");
		this.isRoot = Objects.requireNonNull(isRoot, "isRoot can not be null");
		this.isActive = Objects.requireNonNull(isActive, "isActive can not be null");
		this.maxWrongPasswordCount = maxWrongPasswordCount;
	}

	@Generated
	public AuthApp(long id, String name, String password, boolean isRoot, boolean isActive) {
		this(id, name, password, isRoot, isActive, null);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5>{

		private long    id;
		private String  name;
		private String  password;
		private boolean isRoot;
		private boolean isActive;
		private Integer maxWrongPasswordCount;


		public Builder<SET, _T2, _T3, _T4, _T5> setId(long id) {
			this.id = id;
			return (Builder<SET, _T2, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5> setName(String name) {
			this.name = name;
			return (Builder<_T1, SET, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5> setPassword(String password) {
			this.password = password;
			return (Builder<_T1, _T2, SET, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5> setIsRoot(boolean isRoot) {
			this.isRoot = isRoot;
			return (Builder<_T1, _T2, _T3, SET, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET> setIsActive(boolean isActive) {
			this.isActive = isActive;
			return (Builder<_T1, _T2, _T3, _T4, SET>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setMaxWrongPasswordCount(@Nullable Integer maxWrongPasswordCount) {
			this.maxWrongPasswordCount = maxWrongPasswordCount;
			return this;
		}
	}

	/**
	 * Get the value of field {@link #id}.<br>
	 *
	 * @return {@link #id}
	 */
	@Generated
	public long getId() {
		return this.id;
	}

	/**
	 * Create a copy of this AuthApp object with a new value for field {@link #id}.<br>
	 *
	 * @param id The new value for field {@link #id}
	 *
	 * @return A new instance of {@link AuthApp}
	 */
	@Generated
	public AuthApp withId(long id) {
		return new AuthApp(id, name, password, isRoot, isActive, maxWrongPasswordCount);
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
	 * Create a copy of this AuthApp object with a new value for field {@link #name}.<br>
	 *
	 * @param name The new value for field {@link #name}
	 *
	 * @return A new instance of {@link AuthApp}
	 */
	@Generated
	public AuthApp withName(String name) {
		return new AuthApp(id, name, password, isRoot, isActive, maxWrongPasswordCount);
	}

	/**
	 * Get the value of field {@link #password}.<br>
	 *
	 * @return {@link #password}
	 */
	@Generated
	public String getPassword() {
		return this.password;
	}

	/**
	 * Create a copy of this AuthApp object with a new value for field {@link #password}.<br>
	 *
	 * @param password The new value for field {@link #password}
	 *
	 * @return A new instance of {@link AuthApp}
	 */
	@Generated
	public AuthApp withPassword(String password) {
		return new AuthApp(id, name, password, isRoot, isActive, maxWrongPasswordCount);
	}

	/**
	 * Get the value of field {@link #isRoot}.<br>
	 *
	 * @return {@link #isRoot}
	 */
	@Generated
	public boolean getIsRoot() {
		return this.isRoot;
	}

	/**
	 * Create a copy of this AuthApp object with a new value for field {@link #isRoot}.<br>
	 *
	 * @param isRoot The new value for field {@link #isRoot}
	 *
	 * @return A new instance of {@link AuthApp}
	 */
	@Generated
	public AuthApp withIsRoot(boolean isRoot) {
		return new AuthApp(id, name, password, isRoot, isActive, maxWrongPasswordCount);
	}

	/**
	 * Get the value of field {@link #isActive}.<br>
	 *
	 * @return {@link #isActive}
	 */
	@Generated
	public boolean getIsActive() {
		return this.isActive;
	}

	/**
	 * Create a copy of this AuthApp object with a new value for field {@link #isActive}.<br>
	 *
	 * @param isActive The new value for field {@link #isActive}
	 *
	 * @return A new instance of {@link AuthApp}
	 */
	@Generated
	public AuthApp withIsActive(boolean isActive) {
		return new AuthApp(id, name, password, isRoot, isActive, maxWrongPasswordCount);
	}

	/**
	 * Get the value of field {@link #maxWrongPasswordCount}.<br>
	 *
	 * @return {@link #maxWrongPasswordCount}
	 */
	@Generated
	public Optional<Integer> getMaxWrongPasswordCount() {
		return Optional.ofNullable(this.maxWrongPasswordCount);
	}

	/**
	 * Create a copy of this AuthApp object with a new value for field {@link #maxWrongPasswordCount}.<br>
	 *
	 * @param maxWrongPasswordCount The new value for field {@link #maxWrongPasswordCount}
	 *
	 * @return A new instance of {@link AuthApp}
	 */
	@Generated
	public AuthApp withMaxWrongPasswordCount(@Nullable Integer maxWrongPasswordCount) {
		return new AuthApp(id, name, password, isRoot, isActive, maxWrongPasswordCount);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof AuthApp == false) return false;
		AuthApp obj = (AuthApp) o;
		if(id != obj.id) return false;
		if(!name.equals(obj.name)) return false;
		if(!password.equals(obj.password)) return false;
		if(isRoot != obj.isRoot) return false;
		if(isActive != obj.isActive) return false;
		if(maxWrongPasswordCount != null ? !maxWrongPasswordCount
			.equals(obj.maxWrongPasswordCount) : obj.maxWrongPasswordCount != null) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.id ^ (this.id >>> 32));
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
		result = 31 * result + (this.isRoot ? 1 : 0);
		result = 31 * result + (this.isActive ? 1 : 0);
		result = 31 * result + (this.maxWrongPasswordCount != null ? this.maxWrongPasswordCount.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AuthApp[" +
			"id=" + id +
			", name=" + (name == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(name), 32, "...") + '\"') +
			", password=" + (password == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(password), 32, "...") + '\"') +
			", isRoot=" + isRoot +
			", isActive=" + isActive +
			", maxWrongPasswordCount=" + maxWrongPasswordCount +
			']';
	}

	@Generated
	public AuthApp updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setName(this.name);
		b.setPassword(this.password);
		b.setIsRoot(this.isRoot);
		b.setIsActive(this.isActive);
		b.setMaxWrongPasswordCount(this.maxWrongPasswordCount);
		b = updater.apply(b);
		return new AuthApp(b.id, b.name, b.password, b.isRoot, b.isActive, b.maxWrongPasswordCount);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static AuthApp build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new AuthApp(b.id, b.name, b.password, b.isRoot, b.isActive, b.maxWrongPasswordCount);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<AuthApp> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new AuthApp(b.id, b.name, b.password, b.isRoot, b.isActive, b.maxWrongPasswordCount));
	}
}
