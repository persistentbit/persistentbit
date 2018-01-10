package com.persistentbit.people;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.DefaultEmpty;
import com.persistentbit.javacodegen.annotations.DefaultValue;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.result.Result;

import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/01/18
 */
@CaseClass
public class PersonInTime{

	@DefaultValue("1L")
	private final long                 id;
	@Nullable
	private final PersonBaseInfo       baseInfo;
	@DefaultEmpty
	private final PList<PersonAddress> addresses;
	
	
	@Generated
	public PersonInTime(@Nullable Long id, @Nullable PersonBaseInfo baseInfo,
						@Nullable PList<PersonAddress> addresses) {
		this.id = id == null ? 1L : id;
		this.baseInfo = baseInfo;
		this.addresses = addresses == null ? PList.empty() : addresses;
	}
	@Generated
	public PersonInTime() {
		this(null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder{

		private long                 id;
		private PersonBaseInfo       baseInfo;
		private PList<PersonAddress> addresses;


		public Builder setId(@Nullable Long id) {
			this.id = id;
			return this;
		}

		public Builder setBaseInfo(@Nullable PersonBaseInfo baseInfo) {
			this.baseInfo = baseInfo;
			return this;
		}

		public Builder setAddresses(@Nullable PList<PersonAddress> addresses) {
			this.addresses = addresses;
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
	 * Create a copy of this PersonInTime object with a new value for field {@link #id}.<br>
	 * @param id The new value for field {@link #id}
	 * @return A new instance of {@link PersonInTime}
	 */
	@Generated
	public PersonInTime withId(@Nullable Long id) {
		return new PersonInTime(id, baseInfo, addresses);
	}
	/**
	 * Get the value of field {@link #baseInfo}.<br>
	 * @return {@link #baseInfo}
	 */
	@Generated
	public Optional<PersonBaseInfo> getBaseInfo() {
		return Optional.ofNullable(this.baseInfo);
	}
	/**
	 * Create a copy of this PersonInTime object with a new value for field {@link #baseInfo}.<br>
	 * @param baseInfo The new value for field {@link #baseInfo}
	 * @return A new instance of {@link PersonInTime}
	 */
	@Generated
	public PersonInTime withBaseInfo(@Nullable PersonBaseInfo baseInfo) {
		return new PersonInTime(id, baseInfo, addresses);
	}

	/**
	 * Get the value of field {@link #addresses}.<br>
	 *
	 * @return {@link #addresses}
	 */
	@Generated
	public PList<PersonAddress> getAddresses() {
		return this.addresses;
	}

	/**
	 * Create a copy of this PersonInTime object with a new value for field {@link #addresses}.<br>
	 * @param addresses The new value for field {@link #addresses}
	 * @return A new instance of {@link PersonInTime}
	 */
	@Generated
	public PersonInTime withAddresses(@Nullable PList<PersonAddress> addresses) {
		return new PersonInTime(id, baseInfo, addresses);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof PersonInTime == false) return false;
		PersonInTime obj = (PersonInTime) o;
		if(id != obj.id) return false;
		if(baseInfo != null ? !baseInfo.equals(obj.baseInfo) : obj.baseInfo != null) return false;
		if(!addresses.equals(obj.addresses)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.id ^ (this.id>>> 32));
		result = 31 * result + (this.baseInfo != null ? this.baseInfo.hashCode() : 0);
		result = 31 * result + (this.addresses != null ? this.addresses.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "PersonInTime[" +
			"id=" + id +
			", baseInfo=" + baseInfo +
			", addresses=" + addresses + 
			']';
	}

	@Generated
	public PersonInTime updated(Function<Builder, Builder> updater){
		Builder b = new Builder();
		b.setId(this.id);
		b.setBaseInfo(this.baseInfo);
		b.setAddresses(this.addresses);
		b = updater.apply(b);
		return new PersonInTime(b.id, b.baseInfo, b.addresses);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static PersonInTime build(ThrowingFunction<Builder, Builder, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PersonInTime(b.id, b.baseInfo, b.addresses);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PersonInTime> buildExc(ThrowingFunction<Builder, Builder, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder())).mapExc(b -> new PersonInTime(b.id, b.baseInfo, b.addresses));
	}
}
