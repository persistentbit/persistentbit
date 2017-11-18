package com.persistentbit.remoter;


import com.persistentbit.collections.PStream;
import com.persistentbit.reflection.properties.FieldNames;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;


public class MethodDefinition implements Serializable
{
    private final String methodName;
    private final Class<?> remotableClass;
    private final String[]   paramNames;
    private final Class<?>[] paramTypes;
    private final boolean    returnsRemotable;


    private static String[] getMethodParamNames(Method m) {
        FieldNames fn = m.getDeclaredAnnotation(FieldNames.class);
        if(fn != null){
            return fn.names();
        }
        Parameter[] params = m.getParameters();
        String[]    result = new String[params.length];
        for(int t=0; t<params.length;t++){
            result[t] = params[t].getName();
        }
        return result;
    }

    public MethodDefinition(Class<?> remotableClass, Method method){
        this(
            remotableClass,
            method.getName(),
            method.getParameterTypes(),
            getMethodParamNames(method),
            RemotableClasses.returnsRemotable(method)
        );

    }

    @Override
    public String toString() {
        String
			params = PStream.from(paramTypes).zip(PStream.from(paramNames)).map(t -> t._2.getSimpleName() + " " + t._1).toString(",");
        return "MethodDefinition[" + remotableClass.getSimpleName() + "#" + methodName + "(" + params + ")]";
    }

    public MethodDefinition(Class<?> remotableClass, String methodName, Class<?>[] paramTypes, String[] paramNames,
							boolean returnsRemotable
    )
    {
        this.remotableClass = Objects.requireNonNull(remotableClass);
        this.methodName = Objects.requireNonNull(methodName);
        this.paramTypes = Objects.requireNonNull(paramTypes);
        this.paramNames = Objects.requireNonNull(paramNames);
        this.returnsRemotable = returnsRemotable;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public Class<?> getRemotableClass() {
        return remotableClass;
    }

    public String[] getParamNames() {
        return paramNames;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public boolean returnsRemotable() {
        return returnsRemotable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodDefinition that = (MethodDefinition) o;

        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) return false;
        if (remotableClass != null ? !remotableClass.equals(that.remotableClass) : that.remotableClass != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(paramNames, that.paramNames)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(paramTypes, that.paramTypes);

    }

    @Override
    public int hashCode() {
        int result = methodName != null ? methodName.hashCode() : 0;
        result = 31 * result + (remotableClass != null ? remotableClass.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(paramNames);
        result = 31 * result + Arrays.hashCode(paramTypes);
        return result;
    }


}
