package com.persistentbit.tuples;

import com.persistentbit.code.annotations.Immutable;
import com.persistentbit.code.annotations.Nullable;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

/**
 * Contains 4 nullable values with possible different types.
 * @param <T1> Type of value 1
 * @param <T2> Type of value 2
 * @param <T3> Type of value 3
 * @param <T4> Type of value 4
 * @author Peter Muys
 * @since 30/09/2016
 * @see Tuple2
 * @see Tuple3
 * @see Tuple5
 * @see Tuple6
 * @see Tuple7
 */
@Immutable
public class Tuple4<T1, T2, T3, T4> implements Comparable<Tuple4<T1, T2, T3, T4>>, Serializable{

    @Nullable
    public final T1 _1;
    @Nullable
    public final T2 _2;
    @Nullable
    public final T3 _3;
    @Nullable
    public final T4 _4;


    public Tuple4(@Nullable T1 _1, @Nullable T2 _2, @Nullable T3 _3, @Nullable T4 _4) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 v1, T2 v2, T3 v3, T4 v4) {
        return new Tuple4<>(v1, v2, v3, v4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple4<?, ?, ?, ?> tuple4 = (Tuple4<?, ?, ?, ?>) o;

        if (_1 != null ? !_1.equals(tuple4._1) : tuple4._1 != null) return false;
        if (_2 != null ? !_2.equals(tuple4._2) : tuple4._2 != null) return false;
        if (_3 != null ? !_3.equals(tuple4._3) : tuple4._3 != null) return false;
        return _4 != null ? _4.equals(tuple4._4) : tuple4._4 == null;

    }

    @Override
    public int hashCode() {
        int result = _1 != null ? _1.hashCode() : 0;
        result = 31 * result + (_2 != null ? _2.hashCode() : 0);
        result = 31 * result + (_3 != null ? _3.hashCode() : 0);
        result = 31 * result + (_4 != null ? _4.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "(" + this._1 + "," + this._2 + ", " + this._3 + "," + this._4 + ")";
    }

    public Optional<T1> get1() {
        return Optional.ofNullable(this._1);
    }

    public Optional<T2> get2() {
        return Optional.ofNullable(this._2);
    }

    public Optional<T3> get3() {
        return Optional.ofNullable(this._3);
    }

    public Optional<T4> get4() {
        return Optional.ofNullable(this._4);
    }

    public <T5> Tuple5<T1, T2, T3, T4, T5> add(T5 v5) {
        return Tuple5.of(_1, _2, _3, _4, v5);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public int compareTo(Tuple4<T1, T2, T3, T4> o) {
        int r = this.dropLast().compareTo(o.dropLast());
        if (r != 0) {
            return r;
        } else {
            if (this._4 == null) {
                return o._4 == null ? 0 : -1;
            }
            return ((Comparable) this._4).compareTo(o._4);
        }
    }

    public Tuple3<T1, T2, T3> dropLast() {
        return Tuple3.of(_1, _2, _3);
    }

    public Tuple4<T1, T2, T3, T4> with_1(T1 value) {
        return new Tuple4<>(value, this._2, this._3, this._4);
    }

    public Tuple4<T1, T2, T3, T4> with_2(T2 value) {
        return new Tuple4<>(this._1, value, this._3, this._4);
    }

    public Tuple4<T1, T2, T3, T4> with_3(T3 value) {
        return new Tuple4<>(this._1, this._2, value, this._4);
    }

    public Tuple4<T1, T2, T3, T4> with_4(T4 value) {
        return new Tuple4<>(this._1, this._2, this._3, value);
    }

    public <R> R map(Function<T1,Function<T2,Function<T3,Function<T4,R>>>> map){
        return map.apply(_1).apply(_2).apply(_3).apply(_4);
    }

    public <R1> Tuple4<R1, T2, T3, T4> map1(Function<T1, R1> map) {
        return Tuple4.of(map.apply(_1), _2, _3, _4);
    }

    public <R2> Tuple4<T1, R2, T3, T4> map2(Function<T2, R2> map) {
        return Tuple4.of(_1, map.apply(_2), _3, _4);
    }

    public <R3> Tuple4<T1, T2, R3, T4> map3(Function<T3, R3> map) {
        return Tuple4.of(_1, _2, map.apply(_3), _4);
    }

    public <R4> Tuple4<T1, T2, T3, R4> map4(Function<T4, R4> map) {
        return Tuple4.of(_1, _2, _3, map.apply(_4));
    }

}
