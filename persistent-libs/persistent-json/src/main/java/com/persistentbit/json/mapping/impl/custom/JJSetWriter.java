package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.collections.PStream;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeArray;

import java.util.Set;

/**
 * @author Peter Muys
 * @since 1/09/2016
 */
public class JJSetWriter implements JJObjectWriter{
    @Override
    public JJNode write(Object value, JJWriter masterWriter) {
        Set v = (Set) value;
        return new JJNodeArray(PStream.from(v).map(i -> masterWriter.write(i)));

    }
}