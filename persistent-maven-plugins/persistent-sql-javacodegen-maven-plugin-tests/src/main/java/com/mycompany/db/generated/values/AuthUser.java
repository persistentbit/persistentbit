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
import java.util.Optional;
import java.util.function.Function;

public class AuthUser{

	private final long          id;
	private final long          authAppId;
	private final String        userName;
	private final String        password;
	private final int           wrongPasswordCount;
	@Nullable
	private final LocalDateTime created;
	@Nullable
	private final LocalDateTime lastLogin;
	@Nullable
	private final LocalDateTime verified;
	@Nullable
	private final String        resetPasswordCode;
	@Nullable
	private final LocalDateTime resetPasswordValidUntil;
	@Nullable
	private final String        verifyCode;
	@Nullable
	private final LocalDateTime verifyCodeValidUntil;
	
	
	@Generated
	public AuthUser(long id, long authAppId, String userName, String password, int wrongPasswordCount,
					@Nullable LocalDateTime created, @Nullable LocalDateTime lastLogin,
					@Nullable LocalDateTime verified, @Nullable String resetPasswordCode,
					@Nullable LocalDateTime resetPasswordValidUntil, @Nullable String verifyCode,
					@Nullable LocalDateTime verifyCodeValidUntil) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.authAppId = Objects.requireNonNull(authAppId, "authAppId can not be null");
		this.userName = Objects.requireNonNull(userName, "userName can not be null");
		this.password = Objects.requireNonNull(password, "password can not be null");
		this.wrongPasswordCount = Objects.requireNonNull(wrongPasswordCount, "wrongPasswordCount can not be null");
		this.created = created;
		this.lastLogin = lastLogin;
		this.verified = verified;
		this.resetPasswordCode = resetPasswordCode;
		this.resetPasswordValidUntil = resetPasswordValidUntil;
		this.verifyCode = verifyCode;
		this.verifyCodeValidUntil = verifyCodeValidUntil;
	}
	@Generated
	public AuthUser(long id, long authAppId, String userName, String password, int wrongPasswordCount) {
		this(id, authAppId, userName, password, wrongPasswordCount, null, null, null, null, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5>{

		private long          id;
		private long          authAppId;
		private String        userName;
		private String        password;
		private int           wrongPasswordCount;
		private LocalDateTime created;
		private LocalDateTime lastLogin;
		private LocalDateTime verified;
		private String        resetPasswordCode;
		private LocalDateTime resetPasswordValidUntil;
		private String        verifyCode;
		private LocalDateTime verifyCodeValidUntil;


		public Builder<SET, _T2, _T3, _T4, _T5> setId(long id) {
			this.id = id;
			return (Builder<SET, _T2, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5> setAuthAppId(long authAppId) {
			this.authAppId = authAppId;
			return (Builder<_T1, SET, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5> setUserName(String userName) {
			this.userName = userName;
			return (Builder<_T1, _T2, SET, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5> setPassword(String password) {
			this.password = password;
			return (Builder<_T1, _T2, _T3, SET, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET> setWrongPasswordCount(int wrongPasswordCount) {
			this.wrongPasswordCount = wrongPasswordCount;
			return (Builder<_T1, _T2, _T3, _T4, SET>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setCreated(@Nullable LocalDateTime created) {
			this.created = created;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setLastLogin(@Nullable LocalDateTime lastLogin) {
			this.lastLogin = lastLogin;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setVerified(@Nullable LocalDateTime verified) {
			this.verified = verified;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setResetPasswordCode(@Nullable String resetPasswordCode) {
			this.resetPasswordCode = resetPasswordCode;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setResetPasswordValidUntil(
			@Nullable LocalDateTime resetPasswordValidUntil) {
			this.resetPasswordValidUntil = resetPasswordValidUntil;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setVerifyCode(@Nullable String verifyCode) {
			this.verifyCode = verifyCode;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setVerifyCodeValidUntil(@Nullable LocalDateTime verifyCodeValidUntil) {
			this.verifyCodeValidUntil = verifyCodeValidUntil;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #id}.<br>
	 * @return {@link #id}
	 */
	@Generated
	public long getId() {
		return this.id;
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #id}.<br>
	 * @param id The new value for field {@link #id}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withId(long id) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #authAppId}.<br>
	 * @return {@link #authAppId}
	 */
	@Generated
	public long getAuthAppId() {
		return this.authAppId;
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #authAppId}.<br>
	 * @param authAppId The new value for field {@link #authAppId}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withAuthAppId(long authAppId) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #userName}.<br>
	 * @return {@link #userName}
	 */
	@Generated
	public String getUserName() {
		return this.userName;
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #userName}.<br>
	 * @param userName The new value for field {@link #userName}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withUserName(String userName) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #password}.<br>
	 * @return {@link #password}
	 */
	@Generated
	public String getPassword() {
		return this.password;
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #password}.<br>
	 * @param password The new value for field {@link #password}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withPassword(String password) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #wrongPasswordCount}.<br>
	 * @return {@link #wrongPasswordCount}
	 */
	@Generated
	public int getWrongPasswordCount() {
		return this.wrongPasswordCount;
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #wrongPasswordCount}.<br>
	 * @param wrongPasswordCount The new value for field {@link #wrongPasswordCount}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withWrongPasswordCount(int wrongPasswordCount) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #created}.<br>
	 * @return {@link #created}
	 */
	@Generated
	public Optional<LocalDateTime> getCreated() {
		return Optional.ofNullable(this.created);
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #created}.<br>
	 * @param created The new value for field {@link #created}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withCreated(@Nullable LocalDateTime created) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #lastLogin}.<br>
	 * @return {@link #lastLogin}
	 */
	@Generated
	public Optional<LocalDateTime> getLastLogin() {
		return Optional.ofNullable(this.lastLogin);
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #lastLogin}.<br>
	 * @param lastLogin The new value for field {@link #lastLogin}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withLastLogin(@Nullable LocalDateTime lastLogin) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #verified}.<br>
	 * @return {@link #verified}
	 */
	@Generated
	public Optional<LocalDateTime> getVerified() {
		return Optional.ofNullable(this.verified);
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #verified}.<br>
	 * @param verified The new value for field {@link #verified}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withVerified(@Nullable LocalDateTime verified) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #resetPasswordCode}.<br>
	 * @return {@link #resetPasswordCode}
	 */
	@Generated
	public Optional<String> getResetPasswordCode() {
		return Optional.ofNullable(this.resetPasswordCode);
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #resetPasswordCode}.<br>
	 * @param resetPasswordCode The new value for field {@link #resetPasswordCode}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withResetPasswordCode(@Nullable String resetPasswordCode) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #resetPasswordValidUntil}.<br>
	 * @return {@link #resetPasswordValidUntil}
	 */
	@Generated
	public Optional<LocalDateTime> getResetPasswordValidUntil() {
		return Optional.ofNullable(this.resetPasswordValidUntil);
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #resetPasswordValidUntil}.<br>
	 * @param resetPasswordValidUntil The new value for field {@link #resetPasswordValidUntil}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withResetPasswordValidUntil(@Nullable LocalDateTime resetPasswordValidUntil) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #verifyCode}.<br>
	 * @return {@link #verifyCode}
	 */
	@Generated
	public Optional<String> getVerifyCode() {
		return Optional.ofNullable(this.verifyCode);
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #verifyCode}.<br>
	 * @param verifyCode The new value for field {@link #verifyCode}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withVerifyCode(@Nullable String verifyCode) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	/**
	 * Get the value of field {@link #verifyCodeValidUntil}.<br>
	 * @return {@link #verifyCodeValidUntil}
	 */
	@Generated
	public Optional<LocalDateTime> getVerifyCodeValidUntil() {
		return Optional.ofNullable(this.verifyCodeValidUntil);
	}
	/**
	 * Create a copy of this AuthUser object with a new value for field {@link #verifyCodeValidUntil}.<br>
	 * @param verifyCodeValidUntil The new value for field {@link #verifyCodeValidUntil}
	 * @return A new instance of {@link AuthUser}
	 */
	@Generated
	public AuthUser withVerifyCodeValidUntil(@Nullable LocalDateTime verifyCodeValidUntil) {
		return new AuthUser(id, authAppId, userName, password, wrongPasswordCount, created, lastLogin, verified, resetPasswordCode, resetPasswordValidUntil, verifyCode, verifyCodeValidUntil);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof AuthUser == false) return false;
		AuthUser obj = (AuthUser) o;
		if(id != obj.id) return false;
		if(authAppId != obj.authAppId) return false;
		if(!userName.equals(obj.userName)) return false;
		if(!password.equals(obj.password)) return false;
		if(wrongPasswordCount != obj.wrongPasswordCount) return false;
		if(created != null ? !created.equals(obj.created) : obj.created != null) return false;
		if(lastLogin != null ? !lastLogin.equals(obj.lastLogin) : obj.lastLogin != null) return false;
		if(verified != null ? !verified.equals(obj.verified) : obj.verified != null) return false;
		if(resetPasswordCode != null ? !resetPasswordCode.equals(obj.resetPasswordCode) : obj.resetPasswordCode != null)
			return false;
		if(resetPasswordValidUntil != null ? !resetPasswordValidUntil
			.equals(obj.resetPasswordValidUntil) : obj.resetPasswordValidUntil != null) return false;
		if(verifyCode != null ? !verifyCode.equals(obj.verifyCode) : obj.verifyCode != null) return false;
		if(verifyCodeValidUntil != null ? !verifyCodeValidUntil
			.equals(obj.verifyCodeValidUntil) : obj.verifyCodeValidUntil != null) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.id ^ (this.id >>> 32));
		result = 31 * result + (int) (this.authAppId ^ (this.authAppId >>> 32));
		result = 31 * result + (this.userName != null ? this.userName.hashCode() : 0);
		result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
		result = 31 * result + this.wrongPasswordCount;
		result = 31 * result + (this.created != null ? this.created.hashCode() : 0);
		result = 31 * result + (this.lastLogin != null ? this.lastLogin.hashCode() : 0);
		result = 31 * result + (this.verified != null ? this.verified.hashCode() : 0);
		result = 31 * result + (this.resetPasswordCode != null ? this.resetPasswordCode.hashCode() : 0);
		result = 31 * result + (this.resetPasswordValidUntil != null ? this.resetPasswordValidUntil.hashCode() : 0);
		result = 31 * result + (this.verifyCode != null ? this.verifyCode.hashCode() : 0);
		result = 31 * result + (this.verifyCodeValidUntil != null ? this.verifyCodeValidUntil.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "AuthUser[" +
			"id=" + id +
			", authAppId=" + authAppId +
			", userName=" + (userName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(userName), 32, "...") + '\"') +
			", password=" + (password == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(password), 32, "...") + '\"') +
			", wrongPasswordCount=" + wrongPasswordCount +
			", created=" + created +
			", lastLogin=" + lastLogin +
			", verified=" + verified +
			", resetPasswordCode=" + (resetPasswordCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(resetPasswordCode), 32, "...") + '\"') +
			", resetPasswordValidUntil=" + resetPasswordValidUntil +
			", verifyCode=" + (verifyCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(verifyCode), 32, "...") + '\"') +
			", verifyCodeValidUntil=" + verifyCodeValidUntil + 
			']';
	}
	@Generated
	public AuthUser updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setAuthAppId(this.authAppId);
		b.setUserName(this.userName);
		b.setPassword(this.password);
		b.setWrongPasswordCount(this.wrongPasswordCount);
		b.setCreated(this.created);
		b.setLastLogin(this.lastLogin);
		b.setVerified(this.verified);
		b.setResetPasswordCode(this.resetPasswordCode);
		b.setResetPasswordValidUntil(this.resetPasswordValidUntil);
		b.setVerifyCode(this.verifyCode);
		b.setVerifyCodeValidUntil(this.verifyCodeValidUntil);
		b = updater.apply(b);
		return new AuthUser(b.id, b.authAppId, b.userName, b.password, b.wrongPasswordCount, b.created, b.lastLogin, b.verified, b.resetPasswordCode, b.resetPasswordValidUntil, b.verifyCode, b.verifyCodeValidUntil);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static AuthUser build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new AuthUser(b.id, b.authAppId, b.userName, b.password, b.wrongPasswordCount, b.created, b.lastLogin, b.verified, b.resetPasswordCode, b.resetPasswordValidUntil, b.verifyCode, b.verifyCodeValidUntil);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<AuthUser> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new AuthUser(b.id, b.authAppId, b.userName, b.password, b.wrongPasswordCount, b.created, b.lastLogin, b.verified, b.resetPasswordCode, b.resetPasswordValidUntil, b.verifyCode, b.verifyCodeValidUntil));
	}
}
