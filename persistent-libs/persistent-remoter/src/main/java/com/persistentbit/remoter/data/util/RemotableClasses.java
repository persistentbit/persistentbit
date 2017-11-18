package com.persistentbit.remoter.data.util;


import com.persistentbit.reflection.UReflect;
import com.persistentbit.remoter.annotations.Remotable;
import com.persistentbit.result.Result;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * User: petermuys
 * Date: 30/10/15
 * Time: 17:23
 */
public final class RemotableClasses{

    private static final Map<Class<?>, Optional<Class<?>>> remoteClasses = new HashMap<>();


    public static boolean returnsRemotable(Method m) {
        if(m.getReturnType().equals(Result.class) == false) {
            throw new RuntimeException("Expected a result type");
        }
        ParameterizedType pt = (ParameterizedType) m.getGenericReturnType();
		return getRemotableClass(UReflect.classFromType(pt.getActualTypeArguments()[0])) != null;
	}

    public static Class<?> getRemotableClass(Class<?> cls) {
        if(cls == CompletableFuture.class){
            throw new RuntimeException("Unreference ComputableFuture");
        }

        Optional<Class<?>> res = remoteClasses.get(cls);
        if(res != null){
            return res.orElse(null);
        }
        if(cls.getDeclaredAnnotation(Remotable.class) != null){
            remoteClasses.put(cls, Optional.of(cls));
            return cls;

        }
        for(Class<?> i : cls.getInterfaces()){
            Class<?> resCls = getRemotableClass(i);
            if(resCls != null){
                return resCls;
            }
        }
        remoteClasses.put(cls, Optional.empty());
        return null;
    }
}
