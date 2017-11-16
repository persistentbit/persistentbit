package com.persistentbit.core.collections;

import com.persistentbit.result.Result;
import com.persistentbit.tuples.Tuple2;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * A Persistent {@link IPMap} where the order of adding elements is preserved when iterating keys orOf values
 *
 * @author Peter Muys
 * @see IPMap
 * @since 13/07/2016
 */
public final class POrderedMap<K, V> extends AbstractPStreamDirect<Tuple2<K, V>, POrderedMap<K, V>>
	implements IPMap<K, V>{

    @SuppressWarnings("unchecked")
    private static final POrderedMap sEmpty = new POrderedMap(PMap.empty(), PList.empty());
    private final PMap<K, V> map;
    private final PList<K> order;

    private POrderedMap(PMap<K, V> map, PList<K> order) {
        this.map = map;
        this.order = order;
    }

    @Override
    public PStream<Tuple2<K, V>> lazy() {
        return new AbstractPStreamLazy<Tuple2<K, V>>() {
            @Override
            public Iterator<Tuple2<K, V>> iterator() {
                return POrderedMap.this.iterator();
            }

        };
    }

    @Override
    public Iterator<Tuple2<K, V>> iterator() {
        return new Iterator<>() {
            private final Iterator<K> keys = order.iterator();

            @Override
            public boolean hasNext() {
                return keys.hasNext();
            }

            @Override
            public Tuple2<K, V> next() {
                K key = keys.next();
                return new PMapEntry<>(key, map.get(key));
            }
        };
    }

    @Override
    protected POrderedMap<K, V> toImpl(PStream<Tuple2<K, V>> lazy) {
        POrderedMap<K, V> r = empty();
        return r.plusAll(lazy);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> POrderedMap<K, V> empty() {
        return (POrderedMap<K, V>) sEmpty;
    }

    @Override
    public POrderedMap<K, V> plusAll(Iterable<? extends Tuple2<K, V>> iter) {
        POrderedMap<K, V> r = this;
        for (Tuple2<K, V> t : iter) {
            r = r.plus(t);
        }
        return r;
    }

    @Override
    public POrderedMap<K, V> plus(Tuple2<K, V> value) {
        return this.put(value._1, value._2);
    }

    @Override
    public POrderedMap<K, V> put(K key, V val) {
        PList<K> kl = this.order;
        if (map.containsKey(key) == false) {
            kl = kl.plus(key);
        }
        return new POrderedMap<>(map.put(key, val), kl);
    }

    @Override
    public <K2, V2> POrderedMap<K2, V2> mapKeyValues(Function<? super Tuple2<K, V>, ? extends Tuple2<K2, V2>> items) {
        POrderedMap<K2, V2> res = POrderedMap.empty();
        return with(res, (r, t) -> r.plus(items.apply(t)));

    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public <M> POrderedMap<K, M> mapValues(Function<? super V, ? extends M> mapper) {

        POrderedMap<K, M> r = POrderedMap.empty();
        return with(r, (m, e) -> m = m.put(e._1, mapper.apply(e._2)));
    }

    @Override
    public V getOrDefault(Object key, V notFound) {
        return map.getOrDefault(key, notFound);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public Optional<V> getOpt(Object key) {
        return map.getOpt(key);
    }

    @Override
    public Result<V> getResult(Object key) {
        if (containsKey(key)) {
            return Result.result(get(key));
        }
        return Result.empty("No value for key " + key);
    }

    @Override
    public POrderedMap<K, V> removeKey(Object key) {
        PList<K> kl = this.order;
        if (map.containsKey(key)) {
            kl = kl.filter(e -> Objects.equals(e, key) == false);
        }
        return new POrderedMap<>(map.removeKey(key), kl);
    }

    @Override
    public PStream<K> keys() {
        return map(e -> e._1);
    }

    @Override
    public PStream<V> values() {
        return map(e -> e._2);
    }

    /**
     * Returns this ordered map as an unordered persistent map
     *
     * @return The Unordered map internally used by this ordered map
     */
    public PMap<K, V> pmap() {
        return map;
    }

    @Override
    public Map<K, V> map() {
        return new PMapMap<>(this);
    }

    @Override
    public boolean contains(Object value) {
        return map.contains(value);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof IPMap == false) {
            return false;
        }
        IPMap other = (IPMap) o;
        if (other.size() != size()) {
            return false;
        }
        for (Tuple2 entry : this) {
            Object v1 = entry._2;
            Object v2 = other.get(entry._1);
            if (v1 == null) {
                return v2 == null;
            }
            if (v1.equals(v2) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return map.size();
    }

}
