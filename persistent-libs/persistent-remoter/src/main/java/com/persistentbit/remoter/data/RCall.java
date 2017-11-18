package com.persistentbit.remoter.data;


import com.persistentbit.code.annotations.Immutable;

/**
 * @author Peter Muys
 * @since 30/08/2016
 */
@Immutable
public class RCall{
    private final RSessionData sessionData;
    private final RCallStack  callStack;
    private final RMethodCall thisCall;

    public RCall(RSessionData sessionData,RCallStack callStack, RMethodCall thisCall) {
        this.sessionData = sessionData;
        this.callStack = callStack;
        this.thisCall = thisCall;
    }
    public RCall(RSessionData sessionData,RCallStack callStack) {
        this(sessionData,callStack,null);
    }

    public RCallStack getCallStack() {
        return callStack;
    }

    public RMethodCall getThisCall() {
        return thisCall;
    }

    public RSessionData getSessionData() {
        return sessionData;
    }

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		RCall rCall = (RCall) o;

		if(sessionData != null ? !sessionData.equals(rCall.sessionData) : rCall.sessionData != null) return false;
		if(callStack != null ? !callStack.equals(rCall.callStack) : rCall.callStack != null) return false;
		return thisCall != null ? thisCall.equals(rCall.thisCall) : rCall.thisCall == null;
	}

	@Override
	public int hashCode() {
		int result = sessionData != null ? sessionData.hashCode() : 0;
		result = 31 * result + (callStack != null ? callStack.hashCode() : 0);
		result = 31 * result + (thisCall != null ? thisCall.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "RCall{" +
			"sessionData=" + sessionData +
			", callStack=" + callStack +
			", thisCall=" + thisCall +
			'}';
	}

}
