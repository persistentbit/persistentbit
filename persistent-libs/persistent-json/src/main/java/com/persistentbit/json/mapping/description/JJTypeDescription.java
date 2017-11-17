package com.persistentbit.json.mapping.description;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;

/**
 */
public class JJTypeDescription{
    private final JJTypeSignature typeSignature;
	private final PList<String> doc;
	private final PList<JJPropertyDescription> properties;

	public JJTypeDescription(JJTypeSignature typeSignature, PList<String> doc, PList<JJPropertyDescription> properties) {
		this.typeSignature = typeSignature;
		this.doc = doc;
		this.properties = properties;
	}
    public JJTypeDescription(JJTypeSignature typeSignature){
        this(typeSignature, PList.empty());
    }

    public JJTypeDescription(JJTypeSignature typeSignature, PList<String> doc){
        this(typeSignature,doc, PList.empty());
    }



	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		JJTypeDescription that = (JJTypeDescription) o;

		return typeSignature != null ? typeSignature.equals(that.typeSignature) : that.typeSignature == null;
	}

	@Override
	public int hashCode() {
		return typeSignature != null ? typeSignature.hashCode() : 0;
	}

	/*public JJTypeDescription withIsArray(boolean isArray){
    	return new JJTypeDescription(typeSignature,doc,)
        ImTools<JJTypeDescription> im = ImTools.get(JJTypeDescription.class);
        return im.copyWith(this,"isArray",isArray);
    }*/

    public JJTypeSignature getTypeSignature() {
        return typeSignature;
    }

    public PList<String> getDoc() {
        return doc;
    }

    public PList<JJPropertyDescription> getProperties() {
        return properties;
    }


    public PSet<JJClass> getAllUsedClassNames() {
        return properties.map(i -> i.getAllUsedClassNames()).join((a,b)-> a.plusAll(b)).orElse(PSet.empty()).plusAll(typeSignature.getAllUsedClassNames());
    }
}
