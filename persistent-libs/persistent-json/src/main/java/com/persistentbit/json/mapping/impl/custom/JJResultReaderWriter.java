package com.persistentbit.json.mapping.impl.custom;


import com.persistentbit.result.Empty;
import com.persistentbit.result.Failure;
import com.persistentbit.result.Result;
import com.persistentbit.result.Success;
import com.persistentbit.core.utils.UReflect;
import com.persistentbit.json.mapping.JJReader;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectReader;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.mapping.impl.JJsonException;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeNull;
import com.persistentbit.json.nodes.JJNodeObject;
import com.persistentbit.json.nodes.JJNodeString;
import com.persistentbit.logging.Log;
import com.persistentbit.logging.entries.LogEntry;
import com.persistentbit.logging.entries.LogEntryEmpty;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * TODO: Add comment
 *
 * @author Peter Muys
 * @since 17/01/2017
 */
public class JJResultReaderWriter implements JJObjectReader, JJObjectWriter{
    @Override
    public JJNode write(Object value, JJWriter masterWriter) {
        if(value == null){
            return JJNodeNull.Null;
        }
        Result result =(Result)value;
        result = result.completed();
        JJNodeObject res = new JJNodeObject();
        LogEntry     log = result.getLog();
        if(log.isEmpty() == false) {
            res = res.plus("log", masterWriter.write(result.getLog()));
        }
        if(result.isPresent()){
            res = res.plus("type", new JJNodeString("Success"));
            res = res.plus("value",masterWriter.write(result.orElseThrow()));
        } else if(result.isEmpty()){
            Empty empty = (Empty)result;
            res = res.plus("type", new JJNodeString("Empty"));
            res = res.plus("exception", masterWriter.write(empty.getException()));
        } else if(result.isError()){
            Failure failure = (Failure)result;
            res = res.plus("type", new JJNodeString("Failure"));
            res = res.plus("exception", masterWriter.write(failure.getException()));
        } else {
            throw new RuntimeException("Unknown: " + result);
        }
        return res;
    }

    @Override
    public Object read(Type type, JJNode node, JJReader masterReader) {
        return Log.function(type, node).code(l -> {
            if(node.getType() == JJNode.JType.jsonNull) {
                return null;
            }
            if(type instanceof ParameterizedType == false) {
                throw new JJsonException("Expected a parameterized Result, not just a Result");
            }
            ParameterizedType pt       = (ParameterizedType) type;
            Type              itemType = pt.getActualTypeArguments()[0];
            JJNodeObject      obj      = node.asObject().orElseThrow();
            LogEntry log      =
                obj.get("log").map(logNode -> masterReader.read(logNode, LogEntry.class)).orElse(LogEntryEmpty.inst);

            switch(obj.get("type").get().asString().orElseThrow().getValue()) {
                case "Success":
                    Object value =
						masterReader.read(obj.get("value").get(), UReflect.classFromType(itemType), itemType);
					return new Success(value, log);
                case "Empty":
                    Throwable emptyException =
                        (Throwable) masterReader.read(obj.get("exception").get(), Throwable.class);
                    return new Empty(emptyException, log);
                case "Failure":
                    Throwable failureException =
                        (Throwable) masterReader.read(obj.get("exception").get(), Throwable.class);
                    return new Failure(failureException, log);
                default:
                    throw new RuntimeException("Unknown: " + obj.get("type").get());
            }
        });
    }
}
