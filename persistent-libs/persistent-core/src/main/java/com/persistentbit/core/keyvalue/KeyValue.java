package com.persistentbit.core.keyvalue;

import com.persistentbit.core.tuples.Tuple2;

import java.util.Objects;

/**
 * A Key and a Value Container
 *
 * @author petermuys
 * @since 5/04/17
 * @see NamedString
 */
public class KeyValue<KEY,VALUE>{
	public final KEY key;
	public final VALUE value;

	public KeyValue(KEY key, VALUE value) {
		this.key = Objects.requireNonNull(key);
		this.value = Objects.requireNonNull(value);
	}

	public KEY getKey() {
		return key;
	}

	public VALUE getValue() {
		return value;
	}

	public KeyValue<KEY,VALUE> withValue(VALUE value){
		return new KeyValue<>(key,value);
	}

	@Override
	public String toString() {
		return "(" + key + " -> " + value + ")";
	}
	public Tuple2<KEY,VALUE> tuple(){
		return Tuple2.of(key,value);
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		KeyValue<?, ?> keyValue = (KeyValue<?, ?>) o;

		return key.equals(keyValue.key);
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

}
