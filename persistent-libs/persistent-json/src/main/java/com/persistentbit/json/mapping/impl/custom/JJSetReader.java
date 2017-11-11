package com.persistentbit.json.mapping.impl.custom;


import com.persistentbit.core.utils.UReflect;
import com.persistentbit.json.mapping.JJReader;
import com.persistentbit.json.mapping.description.JJClass;
import com.persistentbit.json.mapping.description.JJTypeDescription;
import com.persistentbit.json.mapping.description.JJTypeSignature;
import com.persistentbit.json.mapping.impl.JJDescriber;
import com.persistentbit.json.mapping.impl.JJObjectReader;
import com.persistentbit.json.mapping.impl.JJsonException;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeArray;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.Supplier;

/**
 * User: petermuys
 * Date: 24/10/15
 * Time: 13:11
 */
public class JJSetReader implements JJObjectReader, JJDescriber
{
    public final Supplier<Set> supplier;


    public JJSetReader(Supplier<Set> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Object read(Type t, JJNode node, JJReader reader)
    {
        if(node.getType() == JJNode.JType.jsonNull){
            return null;
        }
        if(t instanceof ParameterizedType == false){
            throw new JJsonException("Expected a parameterized Set, not just a Set");
        }
        ParameterizedType pt       = (ParameterizedType)t;
        Type              itemType = pt.getActualTypeArguments()[0];
		Class             cls      = UReflect.classFromType(itemType);
		JJNodeArray       arr      = node.asArray().orElseThrow();
        Set               result   = supplier.get();
        for(JJNode i : arr){
            result.add(reader.read(i,cls,itemType));
        }
        return result;
    }


    @Override
    public JJTypeDescription describe(Type type, JJDescriber masterDescriber) {

		Class cls = UReflect.classFromType(type);
		return new JJTypeDescription(new JJTypeSignature(new JJClass(cls), JJTypeSignature.JsonType.jsonSet, JJDescriber.getGenericsParams(type,masterDescriber)));
    }
}
