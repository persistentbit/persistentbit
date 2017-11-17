package com.persistentbit.json.mapping.impl.custom;


import com.persistentbit.collections.PMap;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeNull;
import com.persistentbit.json.nodes.JJNodeObject;

import java.util.Optional;

/**
 * User: petermuys
 * Date: 24/10/15
 * Time: 19:29
 */
public class JJOptionalWriter implements JJObjectWriter{
    @Override
    public JJNode write(Object value, JJWriter masterWriter) {
        Optional v = (Optional)value;
        if(v == null){
            return JJNodeNull.Null;
        }
        if(v.isPresent()){
            return new JJNodeObject(PMap.<String,JJNode>empty().put("optionalValue",masterWriter.write(v.get())));
        }
        return new JJNodeObject(PMap.<String,JJNode>empty().put("optionalEmpty",JJNodeNull.Null));
    }
}
