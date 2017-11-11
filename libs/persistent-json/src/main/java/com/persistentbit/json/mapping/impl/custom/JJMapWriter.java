package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.core.collections.PStream;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeArray;

import java.util.Map;

/**
 * General Java Map Writer.<br>
 * Writes out an Array of Array Entries [key,value]
 * @author Peter Muys
 * @since 1/09/2016
 */
public class JJMapWriter implements JJObjectWriter{

	@Override
	public JJNode write(Object value, JJWriter masterWriter) {
		Map v = (Map) value;

		PStream<Map.Entry<?, ?>> pstream = PStream.from(v.entrySet());
		return new JJNodeArray(
									  pstream.map(e -> new JJNodeArray(masterWriter.write(e.getKey()), masterWriter.write(e.getValue())))
		);

	}
}