package com.persistentbit.json.mapping.impl.custom;


import com.persistentbit.json.mapping.JJReader;
import com.persistentbit.json.mapping.impl.JJObjectReader;
import com.persistentbit.json.mapping.impl.JJsonException;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeObject;

import java.lang.reflect.Type;

/**
 * @author Peter Muys
 * @since 28/10/2015
 */
public class JJExceptionReader implements JJObjectReader
{


    @Override
    public Object read(Type type, JJNode node, JJReader reader)
    {
        if(node.getType() ==JJNode.JType.jsonNull ){ return null; }
        JJNodeObject obj       = node.asObject().orElseThrow();
        String       className = obj.get("exceptionType").get().asString().orElseThrow().getValue();
        String       message   = obj.get("message").get().asString().orElseThrow().getValue();
        try{
            return getClass().getClassLoader().loadClass(className).getDeclaredConstructor(String.class).newInstance(message);
        }catch(Exception e){
            throw new JJsonException(e);
        }
    }


}
