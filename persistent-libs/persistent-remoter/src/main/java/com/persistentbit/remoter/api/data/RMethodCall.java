package com.persistentbit.remoter.api.data;


import com.persistentbit.collections.PList;
import com.persistentbit.json.mapping.impl.JJObjectReader;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeObject;
import com.persistentbit.remoter.api.data.util.RemotableMethods;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

public class RMethodCall {

    private final MethodDefinition methodToCall;
    private final Object[]          arguments;



    public RMethodCall(MethodDefinition methodToCall, Object[] arguments){
        this.methodToCall = methodToCall;
        this.arguments = arguments;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public MethodDefinition getMethodToCall() {
        return methodToCall;
    }





    @Override
    public String toString() {
        return "RMethodCall{" +
                "methodToCall=" + methodToCall +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }

    static public final JJObjectReader jsonReader = (type, node, masterReader) -> {
		if(node.getType() == JJNode.JType.jsonNull){
			return null;
		}
		JJNodeObject     obj     = node.asObject().orElseThrow();
		MethodDefinition md      = masterReader.read(obj.get("methodToCall").get(), MethodDefinition.class);
		JJNode           argNode = obj.get("arguments").get();
		if(argNode.asNull().isPresent()){
			return new RMethodCall(md,new Object[0]);
		}
		Method        m         = RemotableMethods.getRemotableMethod(md);
		Type[]        genParams = m.getGenericParameterTypes();
		PList<JJNode> items     = argNode.asArray().orElseThrow().pstream().plist();
		Object[]      res       = new Object[md.getParamTypes().length];
		for(int t=0; t<md.getParamTypes().length; t++){
			JJNode n = items.get(t);
			res[t] = masterReader.read(n,md.getParamTypes()[t],genParams[t]);
		}
		return new RMethodCall(md,res);
	};
}
