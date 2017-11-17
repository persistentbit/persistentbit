package com.persistentbit.json.mapping.description;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;

/**
 * @author Peter Muys
 * @since 31/08/2016
 */
public class JJPropertyDescription{
    private final String name;
    private final JJTypeSignature   typeSignature;
	private final PList<String> doc;

    public JJPropertyDescription(String name, JJTypeSignature typeSignature, PList<String> doc) {
        this.name = name;
        this.typeSignature = typeSignature;
        this.doc = doc;
    }

    public String getName() {
        return name;
    }

    public JJTypeSignature getTypeSignature() {
        return typeSignature;
    }

    public PList<String> getDoc() {
        return doc;
    }

    public PSet<JJClass> getAllUsedClassNames(){
        return typeSignature.getAllUsedClassNames();
    }

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		JJPropertyDescription that = (JJPropertyDescription) o;

		if(name != null ? !name.equals(that.name) : that.name != null) return false;
		return typeSignature != null ? typeSignature.equals(that.typeSignature) : that.typeSignature == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (typeSignature != null ? typeSignature.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "JJPropertyDescription{" +
			"name='" + name + '\'' +
			", typeSignature=" + typeSignature +
			", doc=" + doc +
			'}';
	}

}
