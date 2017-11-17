package com.persistentbit.json.mapping.description;

/**
 * User: petermuys
 * Date: 9/09/16
 * Time: 23:14
 *
 */
public class JJClass{
    private final String packageName;
    private final String className;

    public JJClass(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
    }

    public JJClass(String fullClassName){
        int i = fullClassName.lastIndexOf('.');

        this.packageName = i == -1 ? "" : fullClassName.substring(0,i);
        this.className = i == -1 ? fullClassName : fullClassName.substring(i+1);
    }

    public JJClass(Class<?> cls){
        this(cls.getName());
    }

    public String getFullJavaName() {
        return this.packageName + "." + this.className;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public String toString() {
        return getPackageName() + "/" + getClassName();
    }

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		JJClass jjClass = (JJClass) o;

		if(packageName != null ? !packageName.equals(jjClass.packageName) : jjClass.packageName != null) return false;
		return className != null ? className.equals(jjClass.className) : jjClass.className == null;
	}

	@Override
	public int hashCode() {
		int result = packageName != null ? packageName.hashCode() : 0;
		result = 31 * result + (className != null ? className.hashCode() : 0);
		return result;
	}
}
