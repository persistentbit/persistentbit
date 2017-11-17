package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.collections.PByteList;
import com.persistentbit.json.mapping.JJReader;
import com.persistentbit.json.mapping.description.JJClass;
import com.persistentbit.json.mapping.description.JJTypeDescription;
import com.persistentbit.json.mapping.description.JJTypeSignature;
import com.persistentbit.json.mapping.impl.JJDescriber;
import com.persistentbit.json.mapping.impl.JJObjectReader;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeString;
import com.persistentbit.reflection.UReflect;

import java.lang.reflect.Type;

/**
 * Json {@link PByteList} reader.<br>
 * returns a json base64 encoded string for the byte list.<br>
 *
 * @author petermuys
 * @see JJPByteListWriter
 * @since 7/11/16
 */
public class JJPByteListReader implements JJObjectReader, JJDescriber{


	@Override
	public Object read(Type type, JJNode node, JJReader masterReader) {
		if(node.getType() == JJNode.JType.jsonNull) {
			return null;
		}
		JJNodeString ns = node.asString().orElseThrow(() -> new IllegalStateException("Expected a JJNodeString"));

		return PByteList.fromBase64String(ns.getValue());

	}

	@Override
	public JJTypeDescription describe(Type type, JJDescriber masterDescriber) {
		return new JJTypeDescription(new JJTypeSignature(new JJClass(UReflect
																		 .classFromType(type)), JJTypeSignature.JsonType.jsonString));
	}
}
