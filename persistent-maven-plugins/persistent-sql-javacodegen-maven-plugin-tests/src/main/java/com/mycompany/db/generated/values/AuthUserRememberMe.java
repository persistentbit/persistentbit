package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

public class AuthUserRememberMe{

	private final long          id;
	private final long          authUserId;
	private final String        code;
	private final LocalDateTime validUntil;
	private final String        passwordCode;


	@Generated
	public AuthUserRememberMe(long id, long authUserId, String code, LocalDateTime validUntil, String passwordCode) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.authUserId = Objects.requireNonNull(authUserId, "authUserId can not be null");
		this.code = Objects.requireNonNull(code, "code can not be null");
		this.validUntil = Objects.requireNonNull(validUntil, "validUntil can not be null");
		this.passwordCode = Objects.requireNonNull(passwordCode, "passwordCode can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5>{

		private long          id;
		private long          authUserId;
		private String        code;
		private LocalDateTime validUntil;
		private String        passwordCode;


		public Builder<SET, _T2, _T3, _T4, _T5> setId(long id) {
			this.id = id;
			return (Builder<SET, _T2, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5> setAuthUserId(long authUserId) {
			this.authUserId = authUserId;
			return (Builder<_T1, SET, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5> setCode(String code) {
			this.code = code;
			return (Builder<_T1, _T2, SET, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5> setValidUntil(LocalDateTime validUntil) {
			this.validUntil = validUntil;
			return (Builder<_T1, _T2, _T3, SET, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET> setPasswordCode(String passwordCode) {
			this.passwordCode = passwordCode;
			return (Builder<_T1, _T2, _T3, _T4, SET>) this;
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
	 * Create a copy of this AuthUserRememberMe object with a new value for field {@link #id}.<br>
	 *
	 * @param id The new value for field {@link #id}
	 *
	 * @return A new instance of {@link AuthUserRememberMe}
	 */
	@Generated
	public AuthUserRememberMe withId(long id) {
		return new AuthUserRememberMe(id, authUserId, code, validUntil, passwordCode);
	}

	/**
	 * Get the value of field {@link #authUserId}.<br>
	 *
	 * @return {@link #authUserId}
	 */
	@Generated
	public long getAuthUserId() {
		return this.authUserId;
	}

	/**
	 * Create a copy of this AuthUserRememberMe object with a new value for field {@link #authUserId}.<br>
	 *
	 * @param authUserId The new value for field {@link #authUserId}
	 *
	 * @return A new instance of {@link AuthUserRememberMe}
	 */
	@Generated
	public AuthUserRememberMe withAuthUserId(long authUserId) {
		return new AuthUserRememberMe(id, authUserId, code, validUntil, passwordCode);
	}

	/**
	 * Get the value of field {@link #code}.<br>
	 *
	 * @return {@link #code}
	 */
	@Generated
	public String getCode() {
		return this.code;
	}

	/**
	 * Create a copy of this AuthUserRememberMe object with a new value for field {@link #code}.<br>
	 *
	 * @param code The new value for field {@link #code}
	 *
	 * @return A new instance of {@link AuthUserRememberMe}
	 */
	@Generated
	public AuthUserRememberMe withCode(String code) {
		return new AuthUserRememberMe(id, authUserId, code, validUntil, passwordCode);
	}

	/**
	 * Get the value of field {@link #validUntil}.<br>
	 *
	 * @return {@link #validUntil}
	 */
	@Generated
	public LocalDateTime getValidUntil() {
		return this.validUntil;
	}

	/**
	 * Create a copy of this AuthUserRememberMe object with a new value for field {@link #validUntil}.<br>
	 *
	 * @param validUntil The new value for field {@link #validUntil}
	 *
	 * @return A new instance of {@link AuthUserRememberMe}
	 */
	@Generated
	public AuthUserRememberMe withValidUntil(LocalDateTime validUntil) {
		return new AuthUserRememberMe(id, authUserId, code, validUntil, passwordCode);
	}

	/**
	 * Get the value of field {@link #passwordCode}.<br>
	 *
	 * @return {@link #passwordCode}
	 */
	@Generated
	public String getPasswordCode() {
		return this.passwordCode;
	}

	/**
	 * Create a copy of this AuthUserRememberMe object with a new value for field {@link #passwordCode}.<br>
	 *
	 * @param passwordCode The new value for field {@link #passwordCode}
	 *
	 * @return A new instance of {@link AuthUserRememberMe}
	 */
	@Generated
	public AuthUserRememberMe withPasswordCode(String passwordCode) {
		return new AuthUserRememberMe(id, authUserId, code, validUntil, passwordCode);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof AuthUserRememberMe == false) return false;
		AuthUserRememberMe obj = (AuthUserRememberMe) o;
		if(id != obj.id) return false;
		if(authUserId != obj.authUserId) return false;
		if(!code.equals(obj.code)) return false;
		if(!validUntil.equals(obj.validUntil)) return false;
		if(!passwordCode.equals(obj.passwordCode)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.id ^ (this.id >>> 32));
		result = 31 * result + (int) (this.authUserId ^ (this.authUserId >>> 32));
		result = 31 * result + (this.code != null ? this.code.hashCode() : 0);
		result = 31 * result + (this.validUntil != null ? this.validUntil.hashCode() : 0);
		result = 31 * result + (this.passwordCode != null ? this.passwordCode.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AuthUserRememberMe[" +
			"id=" + id +
			", authUserId=" + authUserId +
			", code=" + (code == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(code), 32, "...") + '\"') +
			", validUntil=" + validUntil +
			", passwordCode=" + (passwordCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(passwordCode), 32, "...") + '\"') +
			']';
	}

	@Generated
	public AuthUserRememberMe updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setAuthUserId(this.authUserId);
		b.setCode(this.code);
		b.setValidUntil(this.validUntil);
		b.setPasswordCode(this.passwordCode);
		b = updater.apply(b);
		return new AuthUserRememberMe(b.id, b.authUserId, b.code, b.validUntil, b.passwordCode);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static AuthUserRememberMe build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new AuthUserRememberMe(b.id, b.authUserId, b.code, b.validUntil, b.passwordCode);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<AuthUserRememberMe> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new AuthUserRememberMe(b.id, b.authUserId, b.code, b.validUntil, b.passwordCode));
	}
}
