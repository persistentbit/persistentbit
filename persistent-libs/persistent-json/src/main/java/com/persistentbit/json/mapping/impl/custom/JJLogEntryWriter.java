package com.persistentbit.json.mapping.impl.custom;


import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.mapping.impl.JJReflectionObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeObject;
import com.persistentbit.json.nodes.JJNodeString;
import com.persistentbit.logging.entries.LogEntry;


/**
 * TODO: Add comment
 *
 * @author Peter Muys
 * @since 17/01/2017
 */
public class JJLogEntryWriter implements JJObjectWriter{
    private JJObjectWriter realWriter;

    public JJLogEntryWriter(JJObjectWriter realWriter) {
        this.realWriter = realWriter;
    }
    public JJLogEntryWriter(Class<? extends LogEntry> reflectionClass){
        this(new JJReflectionObjectWriter(reflectionClass));
    }

    @Override
    public JJNode write(Object value, JJWriter masterWriter) {
        JJNodeObject res = new JJNodeObject();
        res = res.plus("type",new JJNodeString(value.getClass().getName()));
        res = res.plus("entry", realWriter.write(value,masterWriter));
        return res;
    }
}
