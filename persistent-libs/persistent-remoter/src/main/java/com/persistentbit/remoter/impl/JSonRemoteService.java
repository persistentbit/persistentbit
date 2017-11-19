package com.persistentbit.remoter.impl;

import com.persistentbit.json.mapping.JJMapper;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.remoter.api.RemoteService;
import com.persistentbit.remoter.api.data.RCall;
import com.persistentbit.remoter.api.data.RCallResult;
import com.persistentbit.result.Result;

import java.util.concurrent.TimeUnit;

/**
 * @author Peter Muys
 * @since 30/08/2016
 */
public class JSonRemoteService implements RemoteService{
	private final RemoteService   service;
    private final JJMapper mapper;

    public JSonRemoteService(RemoteService service,JJMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    public JSonRemoteService(RemoteService service) {
        this(service,new JJMapper());
    }


    @Override
    public void close(long timeOut, TimeUnit timeUnit) {
        service.close();
    }

    @Override
    public Result<RCallResult> call(RCall call) {
        return Result.function(call).code(l -> {
            JJNode callNode = mapper.write(call);
            l.info("JSON call: " + callNode);
            RCall               callFromJson   = mapper.read(callNode, RCall.class);
            Result<RCallResult> resRCallResult = service.call(callFromJson).completed();
            return resRCallResult.map(callResult -> {
                JJNode node = mapper.write(callResult);
                l.info("CallResult", node);
                RCallResult fromJson = mapper.read(node, RCallResult.class);
                return callResult;
            });
        });


    }

    @Override
    public String toString() {
        return "JSONRemoteService[" + service + "]";
    }
}
