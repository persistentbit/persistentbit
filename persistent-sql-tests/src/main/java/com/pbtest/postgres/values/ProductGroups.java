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

public class ProductGroups{

	private final int    groupId;
	private final String groupName;
	
	
	@Generated
	public ProductGroups(int groupId, String groupName) {
		this.groupId = Objects.requireNonNull(groupId, "groupId can not be null");
		this.groupName = Objects.requireNonNull(groupName, "groupName can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private int    groupId;
		private String groupName;


		public Builder<SET, _T2> setGroupId(int groupId) {
			this.groupId = groupId;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setGroupName(String groupName) {
			this.groupName = groupName;
			return (Builder<_T1, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #groupId}.<br>
	 * @return {@link #groupId}
	 */
	@Generated
	public int getGroupId() {
		return this.groupId;
	}
	/**
	 * Create a copy of this ProductGroups object with a new value for field {@link #groupId}.<br>
	 * @param groupId The new value for field {@link #groupId}
	 * @return A new instance of {@link ProductGroups}
	 */
	@Generated
	public ProductGroups withGroupId(int groupId) {
		return new ProductGroups(groupId, groupName);
	}
	/**
	 * Get the value of field {@link #groupName}.<br>
	 * @return {@link #groupName}
	 */
	@Generated
	public String getGroupName() {
		return this.groupName;
	}
	/**
	 * Create a copy of this ProductGroups object with a new value for field {@link #groupName}.<br>
	 * @param groupName The new value for field {@link #groupName}
	 * @return A new instance of {@link ProductGroups}
	 */
	@Generated
	public ProductGroups withGroupName(String groupName) {
		return new ProductGroups(groupId, groupName);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof ProductGroups == false) return false;
		ProductGroups obj = (ProductGroups) o;
		if(groupId != obj.groupId) return false;
		if(!groupName.equals(obj.groupName)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = this.groupId;
		result = 31 * result + (this.groupName != null ? this.groupName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "ProductGroups[" +
			"groupId=" + groupId +
			", groupName=" + (groupName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(groupName), 32, "...") + '\"') +
			']';
	}
	@Generated
	public ProductGroups updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setGroupId(this.groupId);
		b.setGroupName(this.groupName);
		b = updater.apply(b);
		return new ProductGroups(b.groupId, b.groupName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static ProductGroups build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new ProductGroups(b.groupId, b.groupName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<ProductGroups> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new ProductGroups(b.groupId, b.groupName));
	}
}
