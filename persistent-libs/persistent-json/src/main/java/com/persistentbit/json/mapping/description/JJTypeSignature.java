package com.persistentbit.json.mapping.description;

import com.persistentbit.collections.PMap;
import com.persistentbit.collections.PSet;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Peter Muys
 * @since 31/08/2016
 */
public class JJTypeSignature{
    public enum JsonType{
        jsonArray,jsonBoolean,jsonNull,jsonNumber,jsonObject,jsonString, jsonSet, jsonMap;
        public boolean isJsonPrimitive() {
            return this == jsonString || this == jsonBoolean || this == jsonNull || this == jsonNumber;
        }
        public boolean isJsonCollection() {
            return this == jsonArray || this == jsonSet || this == jsonMap;
        }
    }
    private final JJClass cls;
    private final JsonType  jsonType;
    private final PMap<String,JJTypeSignature> generics;

    public JJTypeSignature(JJClass cls, JsonType jsonType, PMap<String,JJTypeSignature> generics) {
        this.cls = cls;
        this.jsonType = jsonType;
        this.generics = Objects.requireNonNull(generics);
    }

    public JJTypeSignature(JJClass cls, JsonType jsonType){
        this(cls,jsonType, PMap.empty());
    }

    public JJClass getCls() {
        return cls;
    }

    public JsonType getJsonType() {
        return jsonType;
    }

    public PMap<String,JJTypeSignature> getGenerics() {
        return generics;
    }

    public PSet<JJClass> getAllUsedClassNames(){
        Predicate<JJTypeSignature> include = c -> c.getJsonType() == JsonType.jsonObject && c.getCls().equals(Object.class.getName()) == false;
        PSet<JJClass>              res     =  getGenerics().values().filter(include).map(JJTypeSignature::getAllUsedClassNames).join((a, b)-> a.plusAll(b)).orElse(PSet
			.empty());
        if(include.test(this)){
            res = res.plus(cls);
        }
        return res;
    }

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		JJTypeSignature that = (JJTypeSignature) o;

		if(cls != null ? !cls.equals(that.cls) : that.cls != null) return false;
		if(jsonType != that.jsonType) return false;
		return generics != null ? generics.equals(that.generics) : that.generics == null;
	}

	@Override
	public int hashCode() {
		int result = cls != null ? cls.hashCode() : 0;
		result = 31 * result + (jsonType != null ? jsonType.hashCode() : 0);
		result = 31 * result + (generics != null ? generics.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "JJTypeSignature{" +
			"cls=" + cls +
			", jsonType=" + jsonType +
			", generics=" + generics +
			'}';
	}

}
