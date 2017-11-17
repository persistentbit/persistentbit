package com.persistentbit.json.mapping.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.json.mapping.JJReader;
import com.persistentbit.json.mapping.description.JJClass;
import com.persistentbit.json.mapping.description.JJPropertyDescription;
import com.persistentbit.json.mapping.description.JJTypeDescription;
import com.persistentbit.json.mapping.description.JJTypeSignature;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeNull;
import com.persistentbit.json.nodes.JJNodeObject;
import com.persistentbit.logging.Log;
import com.persistentbit.reflection.UReflect;
import com.persistentbit.reflection.properties.FieldNames;
import com.persistentbit.reflection.properties.PropertySetter;
import com.persistentbit.reflection.properties.PropertySetterField;
import com.persistentbit.reflection.properties.PropertySetterMethod;

import java.lang.reflect.*;
import java.util.*;

/**
 * @author Peter Muys
 * @since 29/10/2015
 */
public class JJReflectionObjectReader implements JJObjectReader, JJDescriber{

	private final Map<String, PropertyDef> properties = new HashMap<>();
	private final Class<?> objectClass;
	private List<PropertyDef> constructorProperities = new ArrayList<>();
	private Constructor<?> constructor;


	public JJReflectionObjectReader(Class<?> cls) {
		objectClass = cls;
		addFields();
		setBestConstructor();
	}

	public JJReflectionObjectReader addFields() {
		Class<?> cls = objectClass;
		while(cls != null && cls.equals(Object.class) == false) {
			for(Field f : cls.getDeclaredFields()) {
				if(Modifier.isTransient(f.getModifiers()) || Modifier.isStatic(f.getModifiers())) {
					continue;
				}
				String propName = f.getName();
				if(properties.containsKey(f.getName())) {
					throw new IllegalStateException("Can't add field prop '" + propName + "', property already exists!");
				}
				Method m = findMethod("set" + firstCharToUpper(propName));
				if(m == null || m.isAccessible() == false) {
					f.setAccessible(true);
					properties.put(propName, new PropertyDef(propName, propName, new PropertySetterField(f)));
				}
				else {
					m.setAccessible(true);
					properties.put(propName, new PropertyDef(propName, propName, new PropertySetterMethod(m)));
				}
			}
			cls = cls.getSuperclass();
		}
		return this;
	}

	public JJReflectionObjectReader setBestConstructor() {
		constructor = null;
		constructorProperities.clear();
		for(Constructor<?> c : objectClass.getDeclaredConstructors()) {
			if(constructor != null && constructor.getParameterCount() > c.getParameterCount()) {
				continue;
			}
			FieldNames  fn        = c.getDeclaredAnnotation(FieldNames.class);
			Parameter[] allParams = c.getParameters();
			if(allParams.length > 0 && Modifier.isPublic(c.getModifiers()) == false) {
				continue;
			}
			List<PropertyDef> paramProps = new ArrayList<>();
			for(int t = 0; t < allParams.length; t++) {
				Parameter p    = allParams[t];
				String    name = fn == null ? p.getName() : fn.names()[t];
				//String name = p.getName();
				PropertyDef found = null;
				for(PropertyDef pdef : properties.values()) {
					if(pdef.javaName.equals(name)) {
						if(pdef.setter.getPropertyClass().equals(p.getType())) {
							found = pdef;
							break;
						}
					}
				}
				if(found == null) {
					break;
				}
				paramProps.add(found);
			}
			if(paramProps.size() == allParams.length) {
				setConstructor(c, paramProps);
			}
		}

		return this;
	}

	private Method findMethod(String methodName) {
		Class<?> cls = objectClass;
		while(cls != null && cls.equals(Object.class) == false) {
			for(Method m : objectClass.getDeclaredMethods()) {
				if(Modifier.isStatic(m.getModifiers()) == false && m.getName()
																	.equals(methodName) && m.getParameterCount() == 1) {
					return m;

				}
			}
			cls = cls.getSuperclass();
		}
		return null;
	}

	private String firstCharToUpper(String name) {
		return Character.valueOf(name.charAt(0)).toString().toUpperCase() + name.substring(1);
	}

	private void setConstructor(Constructor<?> c, List<PropertyDef> l) {
		for(PropertyDef pd : constructorProperities) {
			properties.put(pd.propName, pd);
		}
		for(PropertyDef pd : l) {
			properties.remove(pd.propName);
		}
		constructorProperities = l;
		constructor = c;
		constructor.setAccessible(true);
	}

	public JJReflectionObjectReader reset() {
		constructorProperities.clear();
		properties.clear();
		constructor = null;
		return this;
	}

	@Override
	public JJTypeDescription describe(Type t, JJDescriber masterDescriber) {
		PList<String>                doc   = PList.empty();
		PList<JJPropertyDescription> props = PList.empty();
		for(PropertyDef pd : constructorProperities) {
			PList<String>     pdoc = PList.empty();
			JJTypeDescription td   = masterDescriber.describe(pd.setter.getPropertyType(), masterDescriber);
			props = props.plus(new JJPropertyDescription(pd.propName, td.getTypeSignature(), pdoc));
		}
		return new JJTypeDescription(new JJTypeSignature(new JJClass(objectClass), JJTypeSignature.JsonType.jsonObject, JJDescriber
			                                                                                                                .getGenericsParams(t, masterDescriber)), doc, props);
	}

	public JJReflectionObjectReader setCustomPropertyReader(String propName, PropertyDef.PropertyReader reader) {
		PropertyDef def = properties.get(propName);
		if(def == null) {
			for(PropertyDef pd : constructorProperities) {
				if(pd.propName.equals(propName)) {
					def = pd;
					break;
				}
			}
		}
		if(def == null) {
			throw new JJsonException("Can't find property '" + propName + "'");
		}
		def.setCustomReader(reader);
		return this;
	}

	public JJReflectionObjectReader removeProp(String propName) {
		properties.remove(propName);
		for(PropertyDef pd : constructorProperities) {
			if(pd.propName.equals(propName)) {
				throw new IllegalStateException("Can't remove propertie '" + propName + "', because it is used in the constructor for " + objectClass);
			}
		}
		return this;
	}

	public JJReflectionObjectReader addFieldProp(String propName) {
		return addFieldProp(propName, propName);
	}

	public JJReflectionObjectReader addFieldProp(String propName, String fieldName) {
		assertPropertyDoesNotExist(propName, "Can't add new field property");
		Class<?> cls = objectClass;
		while(cls != null && cls.equals(Object.class) == false) {
			for(Field f : cls.getDeclaredFields()) {
				if(f.getName().equals(fieldName) == false || Modifier.isTransient(f.getModifiers())) {
					continue;
				}
				f.setAccessible(true);
				properties.put(propName, new PropertyDef(propName, fieldName, new PropertySetterField(f)));
				return this;
			}
			cls = cls.getSuperclass();
		}
		throw new JJsonException("Can't find field " + fieldName + " in " + objectClass);
	}

	private void assertPropertyDoesNotExist(String propName, String message) {
		for(PropertyDef pd : constructorProperities) {
			if(pd.propName.equals(propName)) {
				throw new IllegalStateException("Property '" + propName + "' already exists in  " + objectClass + " : " + message);
			}
		}
		if(properties.containsKey(propName)) {
			throw new IllegalStateException("Property '" + propName + "' already exists in  " + objectClass + " : " + message);
		}
	}

	public JJReflectionObjectReader addMethodProp(String propName) {
		return addMethodProp(propName, "set" + firstCharToUpper(propName), propName);
	}

	public JJReflectionObjectReader addMethodProp(String propName, String methodName, String javaName) {
		assertPropertyDoesNotExist(propName, "Can't add new Method property");

		Method m = findMethod(methodName);
		if(m != null) {
			properties.put(propName, new PropertyDef(propName, javaName, new PropertySetterMethod(m)));
			return this;
		}

		throw new IllegalStateException("Can't find method '" + methodName + "' in " + objectClass);
	}

	public JJReflectionObjectReader addMethodProp(String propName, String methodName) {
		return addMethodProp(propName, "set" + firstCharToUpper(propName), propName);
	}

	public JJReflectionObjectReader addMethods() {

		Class<?> cls = objectClass;
		while(cls != null && cls.equals(Object.class) == false) {
			for(Method m : cls.getDeclaredMethods()) {
				if(m.getName().startsWith("set") == false || Modifier.isTransient(m.getModifiers()) || Modifier
					                                                                                       .isStatic(m.getModifiers())) {
					continue;
				}
				String propName = firstCharToLower(m.getName().substring(3));
				if(properties.containsKey(propName)) {
					throw new IllegalStateException("Can't add method prop '" + propName + "', property already exists!");
				}
				m.setAccessible(true);
				properties.put(propName, new PropertyDef(propName, propName, new PropertySetterMethod(m)));
			}
			cls = cls.getSuperclass();
		}
		return this;
	}

	private String firstCharToLower(String name) {
		return Character.valueOf(name.charAt(0)).toString().toLowerCase() + name.substring(1);
	}

	public JJReflectionObjectReader setConstructor(String... names) {
		for(Constructor<?> c : objectClass.getConstructors()) {
			if(constructor.getParameterCount() != names.length) {
				continue;
			}
			//FieldNames fn = c.getDeclaredAnnotation(FieldNames.class);
			Parameter[]       allParams  = c.getParameters();
			List<PropertyDef> paramProps = new ArrayList<>();
			for(int t = 0; t < allParams.length; t++) {
				Parameter p = allParams[t];
				//String name = fn == null ? p.getName() : fn.names()[t];
				String      name  = p.getName();
				PropertyDef found = null;
				if(name.equals(names[t])) {
					for(PropertyDef pdef : properties.values()) {
						if(pdef.javaName.equals(name)) {
							if(pdef.setter.getPropertyClass().equals(p.getType())) {
								found = pdef;

								break;
							}
						}
					}
				}
				if(found == null) {
					break;
				}
				paramProps.add(found);
			}
			if(paramProps.size() == allParams.length) {
				setConstructor(c, paramProps);
				return this;
			}
		}
		throw new IllegalArgumentException("Can't find constructor with parameters " + Arrays
			                                                                               .asList(names) + " in " + objectClass);
	}

	@Override
	public Object read(Type type, JJNode node, JJReader reader) {
		return Log.function(type, node).code(l -> {
			if(node.getType() == JJNode.JType.jsonNull) {
				return null;
			}
			if(node instanceof JJNodeObject == false) {
				throw new JJsonException("Can't cast to JJNodeObject: " + node + " for type " + type);
			}
			JJNodeObject jobj = (JJNodeObject) node;

			try {
				Object result;
				if(constructor == null) {
					l.info("No Constructor found for " + type);
					l.info("Trying to invoke the default constructor...");
					result = objectClass.getDeclaredConstructor().newInstance();
				}
				else {
					Object[] args = new Object[constructorProperities.size()];
					for(int t = 0; t < args.length; t++) {
						PropertyDef pd = constructorProperities.get(t);
						args[t] = pd.read(type, jobj, reader);
						//args[t] = reader.read(jobj.getByType(pd.propName).getByType(),pd.setter.getPropertyClass(),pd.setter.getPropertyType());
					}
					if(args.length == 0) {
						result = constructor.newInstance();
					}
					else {
						result = constructor.newInstance(args);
					}
				}
				for(PropertyDef pd : properties.values()) {
					Object value = pd.read(type, jobj, reader);
					pd.setter.set(result, value);
				}
				return result;
			} catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
				throw new JJsonException("Reading " + objectClass, e);
			}
		});
	}

	public static class PropertyDef{

		private final String propName;
		private final String javaName;
		private final PropertySetter setter;
		private PropertyReader propertyReader;

		public PropertyDef(String propName, String javaName, PropertySetter setter) {
			this.propName = propName;
			this.javaName = javaName;
			this.setter = setter;
		}

		public PropertyDef setCustomReader(PropertyReader propertyReader) {
			this.propertyReader = propertyReader;
			return this;
		}

		public Object read(Type containerType, JJNodeObject node, JJReader reader) {
			Class cls  = setter.getPropertyClass();
			Type  type = setter.getPropertyType();
			if(cls == Object.class) {
				//Let's try matching the container generic types to the property type
				//to resolve Object...

				//System.out.println("Container objectType " + containerType);
				//System.out.println("Property name " + javaName);
				//System.out.println("cls " + cls);
				//System.out.println("type " + type);

				if(containerType instanceof ParameterizedType) {
					Type[] params         = ((ParameterizedType) containerType).getActualTypeArguments();
					Class  containerClass = UReflect.classFromType(containerType);
					for(int i = 0; i < params.length; i++) {
						TypeVariable tv = containerClass.getTypeParameters()[i];
						//System.out.println("Typevar:  " + tv);
						if(type.toString().equals(tv.toString())) {
							//System.out.println("Found class: " + params[i]);
							cls = UReflect.classFromType(params[i]);
							type = params[i];
							break;
						}
					}
					//System.out.println("found  cls " + cls);
					//System.out.println("found type " + type);
				}
			}

			if(propertyReader != null) {
				return propertyReader.read(reader, node, node.get(propName).get(), cls, type);
			}
			return reader.read(node.get(propName).orElse(JJNodeNull.Null), cls, type);
		}

		@FunctionalInterface
		public interface PropertyReader{

			Object read(JJReader reader, JJNodeObject propertyContainerNode, JJNode propertyNode,
						Class<?> propertyClass,
						Type propertyType
			);
		}

	}
}
