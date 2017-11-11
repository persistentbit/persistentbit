package com.persistentbit.json.mapping.impl.custom;


import com.persistentbit.core.collections.PList;
import com.persistentbit.core.collections.PMap;
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
import java.util.Map;
import java.util.function.Supplier;

/**
 * General Map Collection reader.<br>
 * Reads an array of arrays per entry with [key,value]
 * @author Peter Muys
 * @since 24/10/15
 */
public class JJMapReader implements JJObjectReader, JJDescriber{

	private final Supplier<Map> supplier;

	public JJMapReader(Supplier<Map> supplier) {
		this.supplier = supplier;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object read(Type t, JJNode node, JJReader reader) {
		if (node.getType() == JJNode.JType.jsonNull) {
			return null;
		}
		if (t instanceof ParameterizedType == false) {
			throw new JJsonException("Expected a parameterized Map, not just a Map");
		}
		ParameterizedType   pt         = (ParameterizedType) t;
		Type                keyType    = pt.getActualTypeArguments()[0];
		Type                valueType  = pt.getActualTypeArguments()[1];
		Class<?>            keyClass   = UReflect.classFromType(keyType);
		Class<?>            valueClass = UReflect.classFromType(valueType);
		Map<Object, Object> result     = supplier.get();
		JJNodeArray         arr        = node.asArray().orElseThrow(() -> new RuntimeException("Expected an array for map entries"));
		for (JJNode entry : arr) {
			JJNodeArray entryArray = entry.asArray().orElseThrow(() -> new RuntimeException("Expected an array"));
			Object      key        = reader.read(entryArray.pstream().get(0), keyClass, keyType);
			Object      value      = reader.read(entryArray.pstream().get(1), valueClass, valueType);
			result.put(key, value);
		}

		return result;
	}

	@Override
	public JJTypeDescription describe(Type t, JJDescriber masterDescriber) {
		Class cls = UReflect.classFromType(t);


		PMap<String, JJTypeSignature> td  = JJDescriber.getGenericsParams(t, masterDescriber);
		JJTypeSignature               sig = new JJTypeSignature(new JJClass(cls), JJTypeSignature.JsonType.jsonMap, td);
		PList<String>                 doc = PList.empty();

		return new JJTypeDescription(sig, doc);
	}
}
