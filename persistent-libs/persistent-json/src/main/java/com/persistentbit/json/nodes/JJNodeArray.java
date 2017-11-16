package com.persistentbit.json.nodes;

import com.persistentbit.core.collections.PList;
import com.persistentbit.core.collections.PStream;
import com.persistentbit.core.collections.PStreamable;
import com.persistentbit.result.Result;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a JJNode for an array.<br>
 * @author Peter Muys
 * @since 21/10/2015
 */
public class JJNodeArray implements Iterable<JJNode>, JJNode, PStreamable<JJNode>
{
    private final PList<JJNode> elements;


    /**
     * create an empty JJNodeArray
     */
    public JJNodeArray() {
        this.elements = PList.empty();
    }


    /**
     * Initialize this node with an Iterable
     * @param nodes the initial nodes
     */
    public JJNodeArray(Iterable<JJNode> nodes){
        this.elements = PStream.from(Objects.requireNonNull(nodes)).plist();
    }


    /**
     * Initialize this node with the provided elements
     * @param elements The array elements
     */
    public JJNodeArray(JJNode...elements){
        this.elements = PList.val(elements);
    }

    @Override
    public PList<JJNode> pstream() {
        return elements;
    }

    @Override
    public Iterator<JJNode> iterator()
    {
        return elements.iterator();
    }

    /**
     * Create a new JJNodeArray with the provided node added
     * @param node The node to add
     * @return a new JJNodeArray
     */
    public JJNodeArray plus(JJNode node){
        return new JJNodeArray(elements.plus(Objects.requireNonNull(node)));
    }
    @Override
    public String toString() {
        return JJPrinter.print(false,this);
    }


    @Override
    public JType getType()
    {
        return JType.jsonArray;
    }



    @Override
    public Result<JJNodeArray> asArray()
    {
        return Result.success(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JJNodeArray array = (JJNodeArray) o;

        return elements.equals(array.elements);

    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    @Override
    public <T> T match(Function<JJNodeArray, T> anArray, Function<JJNodeBoolean, T> aBoolean,
					   Function<JJNodeNull, T> aNull, Function<JJNodeNumber, T> aNumber,
					   Function<JJNodeObject, T> anObject, Function<JJNodeString, T> aString
    ) {
        return anArray.apply(this);
    }
}
