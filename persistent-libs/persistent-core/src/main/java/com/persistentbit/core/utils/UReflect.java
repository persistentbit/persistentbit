package com.persistentbit.core.utils;

import com.persistentbit.core.collections.PList;
import com.persistentbit.core.collections.PMap;
import com.persistentbit.core.collections.PStream;
import com.persistentbit.core.collections.UPStreams;
import com.persistentbit.core.io.IOClassPath;
import com.persistentbit.functions.UNamed;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Helper Utilities for using Reflection.
 *
 * @author Peter Muys
 * @since 13/07/2016
 */
public final class UReflect{

	public static Class<?> classFromType(Type t) {
		return classFromType(t,genName -> Object.class);
	}
	public static Class<?> classFromType(Type t, Class<?> genericsDefinitionClass){
		return classFromType(t, name -> getGenericTypeVarClass(genericsDefinitionClass,name).orElse(Object.class));
	}
	public static Class<?> classFromType(Type t, Function<String,Class<?>> genericNameToClass) {
		if(t instanceof Class) {
			return (Class<?>) t;
		}
		if(t instanceof ParameterizedType) {
			return classFromType(((ParameterizedType) t).getRawType());
		}
		if(t instanceof GenericArrayType) {
			GenericArrayType gat = (GenericArrayType) t;
			throw new RuntimeException(gat.getTypeName());
		}
		if(t instanceof WildcardType) {
			WildcardType wct = (WildcardType) t;
			return classFromType(wct.getUpperBounds()[0]);
		}
		if(t instanceof TypeVariable) {
			TypeVariable tv = (TypeVariable)t;
			return genericNameToClass.apply(tv.getName());
		}
		throw new RuntimeException("Don't know how to handle " + t);
	}

	public static String typeToSimpleString(Type t){
		if(t instanceof Class){
			return ((Class<?>)t).getSimpleName();
		}
		if(t instanceof ParameterizedType){
			ParameterizedType pt = (ParameterizedType)t;
			return typeToSimpleString(pt.getRawType()) + PStream.from(pt.getActualTypeArguments()).map(v -> typeToSimpleString(v)).toString("<",", ",">");
		}
		if(t instanceof GenericArrayType){
			GenericArrayType gat = (GenericArrayType) t;
			return typeToSimpleString(gat.getGenericComponentType()) + "[]";
		}
		if(t instanceof WildcardType){
			WildcardType wct = (WildcardType) t;
			return PStream.from(wct.getUpperBounds())
						  .map(ub -> "* extends " + typeToSimpleString(ub))
						  .plusAll(PStream.from(wct.getLowerBounds())
										  .map(lb -> "* super " + typeToSimpleString(lb))
				).toString("<" ,", " , ">");
		}
		if(t instanceof TypeVariable) {
			TypeVariable tv = (TypeVariable)t;
			return tv.getName();
		}
		throw new RuntimeException("Don't know how to handle " + t);
	}

	private static final PMap<Class, Class> primitiveClassToObjectClassLookup;

	static {
		primitiveClassToObjectClassLookup = PMap.<Class, Class>empty()
			.put(boolean.class, Boolean.class)
			.put(char.class, Character.class)
			.put(byte.class, Byte.class)
			.put(short.class, Short.class)
			.put(int.class, Integer.class)
			.put(long.class, Long.class)
			.put(float.class, Float.class)
			.put(double.class, Double.class)
		;
	}

	public static Optional<Class> convertPrimitiveClassToObjectClass(Class cls) {
		return primitiveClassToObjectClassLookup.getOpt(cls);
	}

	public static boolean isPrimitiveClass(Class cls) {
		return convertPrimitiveClassToObjectClass(cls).isPresent();
	}

	/**
	 * Try loading a java class using this class (UReflect) classLoader
	 *
	 * @param name The name of the class to load
	 *
	 * @return Result.empty if the class is not found, Result.failure on exception, Result.success when we have a class
	 *
	 * @see #getClass(String, ClassLoader)
	 */
	public static Result<Class> getClass(String name) {
		return getClass(name, UReflect.class.getClassLoader());
	}

	/**
	 * Try loading a java class
	 *
	 * @param name        The name of the class to load
	 * @param classLoader The classloader to use
	 *
	 * @return Result.empty if the class is not found, Result.failure on exception, Result.success when we have a class
	 */
	public static Result<Class> getClass(String name, ClassLoader classLoader) {
		return getClass(name, classLoader, true);
	}

	/**
	 * Try loading a java class
	 *
	 * @param name        The name of the class to load
	 * @param classLoader The classloader to use
	 * @param initClass   Initialize the class ?
	 *
	 * @return Result.empty if the class is not found, Result.failure on exception, Result.success when we have a class
	 */
	public static Result<Class> getClass(String name, ClassLoader classLoader, boolean initClass) {
		return Result.function(name, classLoader).code(l -> {
			try {
				if(name == null) {
					return Result.failure("Class name is null");
				}
				if(classLoader == null) {
					return Result.failure("Class loader is null");
				}
				return Result.result(Class.forName(name, initClass, classLoader));
			} catch(ClassNotFoundException cnf) {
				return Result.empty(cnf);
			} catch(NoClassDefFoundError e) {
				return Result.failure(e);
			} catch(Exception e) {
				return Result.failure(e);
			}
		});
	}

	public static Optional<Method> getGetter(Class cls, String name) {
		name = UString.firstUpperCase(name);
		try {
			return Optional.of(cls.getMethod("get" + name));
		} catch(NoSuchMethodException e) {
			try {
				return Optional.of(cls.getMethod("is" + name));
			} catch(NoSuchMethodException e2) {
				return Optional.empty();
			}
		}

	}

	public static Result<Object> invokeMethod(Method m, Object parent, Object... args) {
		return Result.noExceptions(() -> m.invoke(parent, args));
	}

	public static Result<Object> invokeConstructor(Constructor c, Object... args) {
		return Result.noExceptions(() -> c.newInstance(args));
	}

	public static PList<Executable> findInstanceMethods(Class cls, String name) {
		PList<Executable> methods = PList.empty();
		for(Method m : cls.getMethods()) {
			if(Modifier.isStatic(m.getModifiers()) == false && Modifier.isPublic(m.getModifiers()) && m.getName()
																									   .equals(name)) {
				methods = methods.plus(m);
			}
		}
		return methods;
	}

	public static PList<Executable> findClassMethods(Class cls, String name) {
		PList<Executable> methods = PList.empty();
		for(Method m : cls.getDeclaredMethods()) {
			if(Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers()) && m.getName().equals(name)) {
				methods = methods.plus(m);
			}
		}
		return methods;
	}

	public static <E extends Enum> PList<E> getEnumInstances(Class<E> cls){
		PList<E> res = PList.empty();
		for(Field f : cls.getDeclaredFields()){
			if(f.isEnumConstant()){
				try {
					res = res.plus((E)f.get(null));
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Getting value from " + f, e);
				}
			}
		}
		return res;
	}

	public static PList<Executable> findConstructors(Class cls) {
		return PList.val(cls.getConstructors());
	}


	public static Result<Object> executeStaticMethod(Class cls, String name, Object... arguments) {
		return Result.function(cls, name, arguments).code(l -> {
			PList<Executable> methods = findClassMethods(cls, name);
			return JavaExecutableFinder
				.findExecutableForArguments(JavaExecutableFinder.defaultCaster, methods, arguments)
				.flatMap(t -> {
					Method m = (Method) t._1;
					return invokeMethod(m, null, t._2);
				});
		});


	}

	public static Result<Object> executeMethod(Object parent, Class cls, String name, Object... arguments) {
		return Result.function(parent, cls, name, arguments).code(l -> {
			PList<Executable> methods = findInstanceMethods(cls, name);
			return JavaExecutableFinder
				.findExecutableForArguments(JavaExecutableFinder.defaultCaster, methods, arguments)
				.flatMap(t -> {
					Method m = (Method) t._1;
					return invokeMethod(m, parent, t._2);
				});
		});


	}

	public static <R> Result<R> executeConstructor(Class<R> cls, Object... arguments) {
		return Result.function(cls, arguments).code(l -> {
			PList<Executable> constructors = findConstructors(cls);
			return JavaExecutableFinder
				.findExecutableForArguments(JavaExecutableFinder.defaultCaster, constructors, arguments)
				.flatMap(t -> {
					Constructor c = (Constructor) t._1;
					return (Result<R>) invokeConstructor(c, t._2);
				});
		});
	}

	public static Optional<Field> getField(Class cls, String name) {
		try {
			return Optional.of(cls.getField(name));
		} catch(NoSuchFieldException e) {
			return Optional.empty();
		}
	}
	public static Optional<Field> getDeclaredField(Class cls, String name) {
		try {
			return Optional.of(cls.getDeclaredField(name));
		} catch(NoSuchFieldException e) {
			return Optional.empty();
		}
	}


	public static Optional<Method> getFunctionalInterfaceMethod(Class functionalInterfaceClass) {
		for(Method m : functionalInterfaceClass.getMethods()) {
			if(m.isDefault() == false) {
				return Optional.of(m);
			}
		}
		return Optional.empty();
	}

	public static <T> T createProxyForFunctionalInterface(Class<T> clsFunctionalInterface,
														  Function<Object[], Object> implementation
	) {
		InvocationHandler handler = (proxy, method, args) -> {
			if(method.isDefault()) {
				final Class<?> declaringClass = method.getDeclaringClass();
				return
					MethodHandles.lookup()
								 .in(declaringClass)
								 .unreflectSpecial(method, declaringClass)
								 .bindTo(proxy)
								 .invokeWithArguments(args);
			}
			return implementation.apply(args);
		};
		Class[] clsList = new Class[]{clsFunctionalInterface};
		return (T) Proxy.newProxyInstance(clsFunctionalInterface.getClassLoader(), clsList, handler);
	}

	public static final InvocationHandler invocationHandlerWithDefaults = (proxy, method, args) -> {
		if(method.isDefault()) {
			final Class<?> declaringClass = method.getDeclaringClass();
			return
				MethodHandles.lookup()
							 .in(declaringClass)
							 .unreflectSpecial(method, declaringClass)
							 .bindTo(proxy)
							 .invokeWithArguments(args);
		}

		// proxy impl of not defaults methods
		return null;
	};

	/**
	 * Create a human version of a Class name
	 *
	 * @param cls The class to present
	 *
	 * @return A human readable version of the class name
	 */
	public static String present(Class cls) {
		String name = cls.getName();
		if(name.startsWith("java.lang")) {
			name = name.substring(10);
		}
		return name;
	}


	/**
	 * Find all Classes for a package using the current classpath.
	 *
	 * @param packageName        The package name
	 * @param includeSubPackages Include subpackages ?
	 *
	 * @return Result with list of classes
	 */
	public static Result<PList<Class>> findClasses(String packageName, boolean includeSubPackages) {
		return Result.function(packageName, includeSubPackages).code(l -> {
			String resourcePath = "/" + packageName.replace('.', '/');
			if(includeSubPackages) {
				resourcePath += "/***.class";
			}
			else {
				resourcePath += "/*.class";
			}
			l.info("Resource path: " + resourcePath);
			return IOClassPath.find(resourcePath)
							  //CREATE CLASSNAME FROM RESOURCENAME
							  .map(resourceNameList -> resourceNameList.map(resourceName -> {
									  String res = resourceName
										  .substring(1)
										  .replace('/', '.')
										  .replace(File.separatorChar, '.')
										  //.replace('$','.')
										  ;
									  res = res.substring(0, res.length() - ".class".length());
									  l.info("converted " + resourceName + " -> " + res);
									  return res;
								  })
							  )
							  //GET THE CLASSES
							  .map(classNameList -> classNameList
								  .map(name -> UReflect.getClass(name, UReflect.class.getClassLoader(), false)))
							  .flatMap(classResultList -> UPStreams.fromSequence(classResultList).map(PStream::plist));
		});
	}

	/**
	 * Find all the classes and interfaces that a given Class implements or extends.
	 *
	 * @param cls The Class to find the parents of
	 *
	 * @return A List of parent classes and interfaces
	 */
	public static PList<Class> getExtendsImplementsClasses(Class cls) {

		PList<Class> res        = PList.empty();
		Class        superClass = cls.getSuperclass();
		if(superClass != null) {
			res = res.plusAll(getExtendsImplementsClasses(superClass)).plus(superClass);
		}
		for(Class inter : cls.getInterfaces()) {
			if(inter != null) {
				res = res.plusAll(getExtendsImplementsClasses(inter)).plus(inter);
			}
		}
		return res;
	}


	/**
	 * Creates a {@link Predicate} that checks if a class has a declared annotation
	 *
	 * @param classAnnotation The annotation to find
	 *
	 * @return The Predicate
	 */
	public static Predicate<Class> hasClassAnnotation(Class<? extends Annotation> classAnnotation) {
		return UNamed.namedPredicate("hasClassAnnotation(" + classAnnotation.getName() + ")",
			cls -> cls.getDeclaredAnnotation(classAnnotation) != null
		);
	}

	public static Predicate<Package> hasPackageAnnotation(Class<? extends Annotation> classAnnotation) {
		return UNamed.namedPredicate("hasPackageAnnotation(" + classAnnotation.getName() + ")",
			pack -> pack.getDeclaredAnnotation(classAnnotation) != null
		);
	}

	public static boolean isOverrideMethod(Method method){
		Class methodClass = method.getDeclaringClass();
		return getExtendsImplementsClasses(methodClass).find(scls -> {
			try{
				scls.getDeclaredMethod(method.getName(),method.getParameterTypes());
				return true;
			}catch (NoSuchMethodException nsm){
				return false;
			}
		}).isPresent();
	}




	public static Result<Class<?>> getGenericTypeVarClass(Class cls, String genericVarName){
		for(TypeVariable tv : cls.getTypeParameters()){
			if(tv.getName().equals(genericVarName)){
				if(tv.getBounds().length!= 1){
					return Result.success(Object.class);
				}
				return Result.success(classFromType(tv.getBounds()[0],cls));
			}
		}
		Result<Class<?>> res = getGenericTypeVarClass(cls.getSuperclass(),genericVarName);
		if(res.isPresent()){
			return res;
		}
		for(Class inter : cls.getInterfaces()){
			res = getGenericTypeVarClass(inter,genericVarName);
			if(res.isPresent()){
				return res;
			}
		}
		return Result.empty("Not found in " + cls.getName() + ": " + genericVarName );
	}

	/**
	 * Convert a full classname not a {@link Path} by using
	 * a rootPath and a fully defined class name.
	 * @param rootPath  The root of the path
	 * @param fullClassName the package name and class, separated by '.'
	 * @param extension the file extension (without '.')
	 * @return The new Path
	 */
	public static Result<Path> convertClassNameToPath(Path rootPath, String fullClassName, String extension){
		return Result.function(rootPath,fullClassName).code(l -> {

			String classPath = fullClassName.replace(".",rootPath.getFileSystem().getSeparator()) + "." + extension;
			Path   endPath   = rootPath.resolve(classPath);
			return Result.success(endPath);
		});
	}
}
