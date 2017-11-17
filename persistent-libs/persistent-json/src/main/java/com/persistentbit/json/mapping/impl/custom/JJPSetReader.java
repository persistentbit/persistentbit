package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.collections.IPSet;
import com.persistentbit.json.mapping.JJReader;
import com.persistentbit.json.mapping.description.JJClass;
import com.persistentbit.json.mapping.description.JJTypeDescription;
import com.persistentbit.json.mapping.description.JJTypeSignature;
import com.persistentbit.json.mapping.impl.JJDescriber;
import com.persistentbit.json.mapping.impl.JJObjectReader;
import com.persistentbit.json.mapping.impl.JJsonException;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeArray;
import com.persistentbit.reflection.UReflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * User: petermuys
 * Date: 26/08/16
 * Time: 08:57
 */
public class JJPSetReader implements JJObjectReader, JJDescriber{
    private final Supplier<IPSet> supplier;

    public JJPSetReader(Supplier<IPSet> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Object read(Type type, JJNode node, JJReader reader) {
        if(node.getType() == JJNode.JType.jsonNull){
            return null;
        }
        if(type instanceof ParameterizedType == false){
            throw new JJsonException("Expected a parameterized PSet, not just a PSet or PSet<Object>");
        }
        ParameterizedType pt       = (ParameterizedType)type;
        Type              itemType = pt.getActualTypeArguments()[0];
		Class             cls      = UReflect.classFromType(itemType);
		JJNodeArray       arr      = node.asArray().orElseThrow();
        return  supplier.get().plusAll(arr.pstream().map(n -> reader.read(n,cls,itemType)));
    }
    @Override
    public JJTypeDescription describe(Type type, JJDescriber masterDescriber) {

		Class cls = UReflect.classFromType(type);
		return new JJTypeDescription(new JJTypeSignature(new JJClass(cls), JJTypeSignature.JsonType.jsonSet, JJDescriber.getGenericsParams(type,masterDescriber)));
    }
}
