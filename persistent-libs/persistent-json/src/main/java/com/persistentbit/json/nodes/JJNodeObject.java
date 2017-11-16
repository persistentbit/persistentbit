package com.persistentbit.json.nodes;


import com.persistentbit.core.collections.IPMap;
import com.persistentbit.core.collections.POrderedMap;
import com.persistentbit.core.collections.PStream;
import com.persistentbit.core.collections.PStreamable;
import com.persistentbit.core.lenses.Lens;
import com.persistentbit.core.lenses.LensImpl;
import com.persistentbit.core.result.Result;
import com.persistentbit.tuples.Tuple2;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * A JJNode representing a json Object.
 * @author Peter Muys
 * @since 21/10/2015
 */
public class JJNodeObject implements JJNode,PStreamable<Tuple2<String,JJNode>>
{
    private final IPMap<String,JJNode> items;

    /**
     * Create a new instance with the provided property map
     * @param items properties of the object
     */
    public JJNodeObject(IPMap<String, JJNode> items)
    {
        this.items = Objects.requireNonNull(items);
    }


    public JJNodeObject() {
        this(POrderedMap.empty());
    }


    public Optional<JJNode> get(String name){
        return Optional.ofNullable(items.get(name));
    }

    public IPMap<String,JJNode> getValue() {
        return items;
    }

    public JJNodeObject plus(String name, JJNode node){
        return new JJNodeObject(items.put(name,node));
    }

    @Override
    public JType getType()
    {
        return JType.jsonObject;
    }

    @Override
    public String toString() {
        return JJPrinter.print(false,this);
    }

    @Override
    public Result<JJNodeObject> asObject()
    {
        return Result.success(this);
    }


    @Override
    public PStream<Tuple2<String,JJNode>> pstream() {
        return items;
    }

    public static Lens<JJNodeObject,JJNodeString> stringLens(String name){
        return createLens(JJNodeString.class,name);
    }
    public static Lens<JJNodeObject,JJNodeNumber> numberLens(String name){
        return createLens(JJNodeNumber.class,name);
    }

    public static Lens<JJNodeObject,JJNodeObject> objectLens(String name){
        return createLens(JJNodeObject.class,name);
    }
    public static Lens<JJNodeObject,JJNodeArray> arrayLens(String name){
        return createLens(JJNodeArray.class,name);
    }
    public static Lens<JJNodeObject,JJNodeBoolean> booleanLens(String name){
        return createLens(JJNodeBoolean.class,name);
    }
    public static Lens<JJNodeObject,JJNode> nodeLens(String name){
        return createLens(JJNode.class,name);
    }

    private static <C extends JJNode> Lens<JJNodeObject,C> createLens(Class<C> cls, String name){
        return new LensImpl<>((p)->orNull(cls,p.get(name).orElse(null)),
                (p,c)-> {
                    IPMap<String,JJNode> nm = p.items;
                    nm = nm.put(name,c);
                    return new JJNodeObject(nm);
                });
    }
    @SuppressWarnings("unchecked")
    private static <C extends JJNode> C orNull(Class<C> cls, JJNode value){
        if(value == null || value instanceof JJNodeNull){
            return null;
        }
        if(cls != value.getClass()){
            throw new RuntimeException("Expected " + cls + ",  got" + value.getClass() + " : " + value);
        }
        return (C)value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JJNodeObject that = (JJNodeObject) o;

        return items.equals(that.items);

    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public <T> T match(Function<JJNodeArray, T> anArray, Function<JJNodeBoolean, T> aBoolean,
					   Function<JJNodeNull, T> aNull, Function<JJNodeNumber, T> aNumber,
					   Function<JJNodeObject, T> anObject, Function<JJNodeString, T> aString
    ) {
        return anObject.apply(this);
    }
}
