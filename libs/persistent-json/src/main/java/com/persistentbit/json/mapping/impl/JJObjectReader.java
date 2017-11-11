package com.persistentbit.json.mapping.impl;


import com.persistentbit.json.mapping.JJReader;
import com.persistentbit.json.nodes.JJNode;

import java.lang.reflect.Type;

/**
 * Represents code that can translate a {@link JJNode} to a java object.<br>
 *
 * @author Peter Muys
 * @since 23/10/2015
 * @see JJDefaultReader
 */

public interface JJObjectReader
{
    /**
     * Translate a JJNode to a java Object
     * @param type The result Type that is expected
     * @param node The Json node
     * @param masterReader The reader that can be used for other types of objects
     * @return a new Java Object corresponding to the provided Type.
     */
    Object read(Type type, JJNode node, JJReader masterReader);


}
