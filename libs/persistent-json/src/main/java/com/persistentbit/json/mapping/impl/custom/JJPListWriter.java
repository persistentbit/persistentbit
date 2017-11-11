package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.core.collections.IPList;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeArray;


/**
 * User: petermuys
 * Date: 25/08/16
 * Time: 18:58
 */
public class JJPListWriter implements JJObjectWriter{
    @Override
    public JJNode write(Object value, JJWriter masterWriter) {
        IPList v = (IPList)value;
        return new JJNodeArray(v.lazy().map(i -> masterWriter.write(i)));

    }
}
