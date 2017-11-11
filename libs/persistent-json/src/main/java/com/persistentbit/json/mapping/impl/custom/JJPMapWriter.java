package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.core.collections.IPMap;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeArray;


/**
 * General PMap Writer.<br>
 * Writes out an array of entrie Arrays with [key,value]
 * @author Peter Muys
 * @since 25/08/16
 */
public class JJPMapWriter implements JJObjectWriter{

	@SuppressWarnings("unchecked")
	@Override
	public JJNode write(Object value, JJWriter masterWriter) {
		IPMap<Object, Object> v = (IPMap) value;
		return new JJNodeArray(
									  v.lazy().map(e -> new JJNodeArray(masterWriter.write(e._1), masterWriter.write(e._2)))
		);
	}
}