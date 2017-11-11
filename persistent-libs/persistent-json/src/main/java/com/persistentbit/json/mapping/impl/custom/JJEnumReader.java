package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.core.collections.PList;
import com.persistentbit.core.utils.UReflect;
import com.persistentbit.json.mapping.JJReader;
import com.persistentbit.json.mapping.description.JJClass;
import com.persistentbit.json.mapping.description.JJTypeDescription;
import com.persistentbit.json.mapping.description.JJTypeSignature;
import com.persistentbit.json.mapping.impl.JJDescriber;
import com.persistentbit.json.mapping.impl.JJObjectReader;
import com.persistentbit.json.mapping.impl.JJsonException;
import com.persistentbit.json.nodes.JJNode;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * @author Peter Muys
 * @since 29/08/2016
 */
public class JJEnumReader implements JJObjectReader, JJDescriber{

    @Override
    public Object read(Type type, JJNode node, JJReader reader) {
        if(node.getType() == JJNode.JType.jsonNull){
            return null;
        }
        String name = node.asString().orElseThrow().getValue();
		Class  cls  = UReflect.classFromType(type);
		try {
            return cls.getDeclaredField(name).get(null);
        }catch(Exception e){
            throw new JJsonException("Error reading field " + name + " for enum " + cls.getName(),e);
        }
    }

    @Override
    public JJTypeDescription describe(Type t, JJDescriber masterDescriber) {
        PList<String> doc    = PList.empty();
		Class<?>      cls    = UReflect.classFromType(t);
		PList<String> values = PList.empty();
        for(Field f : cls.getDeclaredFields()){
            if(Modifier.isStatic(f.getModifiers()) && cls.isAssignableFrom(f.getType())){
                values = values.plus(f.getName());
            }
        }
        doc = doc.plus("This is an enum with following possible values: " + values.toString(", "));
        return new JJTypeDescription(new JJTypeSignature(new JJClass(cls),JJTypeSignature.JsonType.jsonString),doc);
    }
}
