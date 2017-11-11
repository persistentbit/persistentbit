package com.persistentbit.json.mapping;


import com.persistentbit.json.nodes.JJNode;

import java.lang.reflect.Type;

/**
 * Interface representing a Reader that can translate a {@link JJNode} object to a Java Object.
 *
 * @author Peter Muys
 * @see JJWriter
 * @see JJNode
 * @see JJDefaultReader
 */

public interface JJReader
{
    default <T>T read(JJNode node, Class<T> cls){
        return read(node,cls,cls);
    }

    <T>T read(JJNode node, Class<T> cls, Type type);
}
