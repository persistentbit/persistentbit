package com.persistentbit.json.mapping.impl.custom;

import com.persistentbit.collections.PByteList;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeString;


/**
 * Json {@link PByteList} writer
 *
 * @author petermuys
 * @see JJPByteListReader
 * @since 7/11/16
 */
public class JJPByteListWriter implements JJObjectWriter{

	@Override
	public JJNode write(Object value, JJWriter masterWriter) {
		PByteList v = (PByteList) value;
		return new JJNodeString(v.toBase64String());

	}
}