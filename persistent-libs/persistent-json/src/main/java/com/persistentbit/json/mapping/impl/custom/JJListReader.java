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
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Peter Muys
 * @since 23/10/2015
 */

public class JJListReader implements JJObjectReader, JJDescriber
{

    private final Supplier<List<?>> supplier;

    public JJListReader(Supplier<List<?>> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Object read(Type t, JJNode node, JJReader reader)
    {
        if(node.getType() == JJNode.JType.jsonNull){
            return null;
        }
        if(t instanceof ParameterizedType == false){
            throw new JJsonException("Expected a parameterized List, not just a List");
        }

        ParameterizedType pt       = (ParameterizedType)t;
        Type              itemType = pt.getActualTypeArguments()[0];
		Class             cls      = UReflect.classFromType(itemType);
		JJNodeArray       arr      = node.asArray().orElseThrow();
        List<Object>      result   = (List<Object>) supplier.get();
        for(JJNode i : arr){
            result.add(reader.read(i,cls,itemType));
        }
        return result;
    }

    @Override
    public JJTypeDescription describe(Type type, JJDescriber masterDescriber) {

		Class cls = UReflect.classFromType(type);
		return new JJTypeDescription(new JJTypeSignature(new JJClass(cls), JJTypeSignature.JsonType.jsonArray, JJDescriber.getGenericsParams(type,masterDescriber)));
    }
}
