package com.persistentbit.parser;

import com.persistentbit.parser.source.StrPos;

/**
 * TODO: Add comment
 *
 * @author Peter Muys
 * @since 20/02/2017
 */
public class WithPos<T> {
    public final StrPos pos;
    public final T          value;

    public WithPos(StrPos pos, T value) {
        this.pos = pos;
        this.value = value;
    }

    @Override
    public String toString() {
        return "WithPos{" +
                "pos=" + pos +
                ", value=" + value +
                '}';
    }

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		WithPos<?> withPos = (WithPos<?>) o;

		if(!pos.equals(withPos.pos)) return false;
		return value.equals(withPos.value);
	}

	@Override
	public int hashCode() {
		int result = pos.hashCode();
		result = 31 * result + value.hashCode();
		return result;
	}
}
