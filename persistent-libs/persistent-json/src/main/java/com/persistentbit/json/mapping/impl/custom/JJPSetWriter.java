package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.collections.PSet;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeArray;


/**
 * User: petermuys
 * Date: 25/08/16
 * Time: 19:09
 */
public class JJPSetWriter implements JJObjectWriter{
    @Override
    public JJNode write(Object value, JJWriter masterWriter) {
        PSet v = (PSet) value;
        return new JJNodeArray(v.lazy().map(i -> masterWriter.write(i)));

    }
}