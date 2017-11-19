package com.persistentbit.remoter.api.data;

import com.persistentbit.collections.PList;
import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.nodes.JJPrinter;
import com.persistentbit.json.security.JJSigning;
import com.persistentbit.result.Result;

import java.util.Objects;

/**
 * @author Peter Muys
 * @since 2/09/2016
 */
public class RCallStack {
    private final PList<RMethodCall> callStack;
    private final String signature;

    public RCallStack(String signature){
        this(signature,PList.empty());
    }
    public RCallStack(String signature, PList<RMethodCall> callStack) {

        this.callStack = Objects.requireNonNull(callStack);
        this.signature = Objects.requireNonNull(signature);

    }

    public PList<RMethodCall> getCallStack() {
        return callStack;
    }


    public String getSignature() {
        return signature;
    }

    static public RCallStack    createAndSign(PList<RMethodCall> methods, JJWriter jsonWriter, String secret){
        String msg = JJPrinter.print(false,jsonWriter.write(methods))+secret;
        return new RCallStack(JJSigning.sign(msg,"SHA-256").orElseThrow(),methods);
    }
    public boolean verifySignature(String secret, JJWriter jsonWriter){
        String         msg    = JJPrinter.print(false, jsonWriter.write(callStack)) + secret;
        Result<String> signed = JJSigning.sign(msg, "SHA-256");
        return signed.map(s -> s.equals(signature)).orElse(false);
    }

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		RCallStack that = (RCallStack) o;

		if(callStack != null ? !callStack.equals(that.callStack) : that.callStack != null) return false;
		return signature != null ? signature.equals(that.signature) : that.signature == null;
	}

	@Override
	public int hashCode() {
		int result = callStack != null ? callStack.hashCode() : 0;
		result = 31 * result + (signature != null ? signature.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "RCallStack{" +
			"callStack=" + callStack +
			'}';
	}


}
