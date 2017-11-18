package com.persistentbit.remoter.api;


import com.persistentbit.json.mapping.JJMapper;
import com.persistentbit.logging.entries.LogEntry;
import com.persistentbit.remoter.impl.RProxy;
import com.persistentbit.remoter.RServer;
import com.persistentbit.remoter.impl.RemoteServiceLogger;
import com.persistentbit.remoter.data.RCall;
import com.persistentbit.remoter.data.RCallResult;
import com.persistentbit.remoter.impl.JSonRemoteService;
import com.persistentbit.remoter.impl.RemoteServiceHttpClient;
import com.persistentbit.result.Result;
import com.persistentbit.tuples.Tuple2;

import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * A RemoteService provides a standard way to call remote or local services.<br>
 * All calls are executed async and return a {@link CompletableFuture}.<br>
 * The implementation (remote, local, http, tcp,...) is completely hidden for the client.<br>
 * @author Peter Muys
 * @see RServer
 * @see RemoteServiceHttpClient
 */
public interface RemoteService {

    default Result<RCallResult> getRoot() {
        return call(new RCall(null,null));
    }

    Result<RCallResult> call(RCall call);

    default void close(){
        close(Integer.MAX_VALUE, TimeUnit.DAYS);
    }

    void close(long timeOut, TimeUnit timeUnit);

    default RemoteService usingJson(JJMapper jsonMapper){
    	return new JSonRemoteService(this,jsonMapper);
	}

	default RemoteService usingJson() {
    	return usingJson(new JJMapper());
	}

	default <C> C createProxy(){
    	return RProxy.create(this);
	}

	default RemoteService withLogging(Consumer<Tuple2<RCall, LogEntry>> logger) {
    	return new RemoteServiceLogger(this,logger);
	}

	static RemoteService	httpClient(URL url, ExecutorService executor, JJMapper mapper) {
    	return new RemoteServiceHttpClient(url, executor, mapper);
	}
}
