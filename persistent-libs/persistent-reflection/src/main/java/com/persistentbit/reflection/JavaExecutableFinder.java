package com.persistentbit.reflection;

import com.persistentbit.result.OK;
import com.persistentbit.collections.IPMap;
import com.persistentbit.collections.IPSet;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.result.Result;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.utils.UNumber;

import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * Utility class to dynamically find java Executables(Method/Constructors) by resolved argument values.
 *
 * @author petermuys
 * @since 2/03/17
 */
public class JavaExecutableFinder{

	public enum MatchLevel{
		full, partial, not
	}

	@FunctionalInterface
	public interface Caster extends BiFunction<Object, Class, Optional<Object>>{

	}

	public static Tuple2<MatchLevel, Object[]> tryMatch(Caster caster, Class[] paramTypes, Object[] arguments,
														boolean withVarArgs
	) {
		if(withVarArgs == false) {
			if(paramTypes.length != arguments.length) {
				return Tuple2.of(MatchLevel.not, new Object[0]);
			}
			return tryMatch(caster, paramTypes, arguments, paramTypes.length);

		}
		//Try match with varargs...

		if(paramTypes.length > arguments.length) {
			return Tuple2.of(MatchLevel.not, new Object[0]);
		}
		Tuple2<MatchLevel, Object[]> matchResult =
			tryMatch(caster, paramTypes, arguments, paramTypes.length - 1);
		if(matchResult._1 == JavaExecutableFinder.MatchLevel.not) {
			return Tuple2.of(MatchLevel.not, new Object[0]);
		}
		Class    itemClass  = paramTypes[paramTypes.length - 1].getComponentType();
		int      restLength = arguments.length - (paramTypes.length - 1);
		Object[] varArgs    = (Object[]) Array.newInstance(itemClass, restLength);
		boolean  ok         = true;
		for(int t = 0; t < varArgs.length; t++) {
			Object           value  = arguments[paramTypes.length - 1 + t];
			Optional<Object> casted = caster.apply(value, itemClass);
			if(casted.isPresent() == false) {
				return Tuple2.of(MatchLevel.not, new Object[0]);
			}
			varArgs[t] = casted.get();
		}
		Object[] newSet = new Object[paramTypes.length];
		System.arraycopy(matchResult._2, 0, newSet, 0, paramTypes.length - 1);
		newSet[paramTypes.length - 1] = varArgs;
		return Tuple2.of(matchResult._1, newSet);
	}

	public static Tuple2<MatchLevel, Object[]> tryMatch(Caster caster, Class[] paramTypes, Object[] arguments,
														int compareCount
	) {
		MatchLevel level     = MatchLevel.full;
		Object[]   converted = new Object[compareCount];
		for(int t = 0; t < compareCount; t++) {
			if(arguments[t] == null){
				converted[t] = null;
			} else {
				Optional<Object> convertedArg = caster.apply(arguments[t], paramTypes[t]);
				if(convertedArg.isPresent() == false) {
					return Tuple2.of(MatchLevel.not, null);
				}
				converted[t] = convertedArg.get();
				if(converted[t] != arguments[t]) {
					level = MatchLevel.partial;
				}

			}
		}
		return Tuple2.of(level, converted);
	}


	public static final Caster defaultCaster = (Object value, Class cls) -> {
		if(value == null) {
			return Optional.empty();
		}
		Class valueCls = value.getClass();
		if(cls.isAssignableFrom(valueCls)) {
			return Optional.of(value);
		}
		cls = UReflect.convertPrimitiveClassToObjectClass(cls).orElse(cls);
		if(cls.isAssignableFrom(valueCls)) {
			return Optional.of(value);
		}
		if(value instanceof Number && UNumber.isNumberClass(cls)) {
			return UNumber.convertTo((Number) value, cls)
						  .getOpt();
		}
		if(cls.isAssignableFrom(List.class)) {
			if(value instanceof PStream) {
				return Optional.of(((PStream) value).list());
			}
		}
		if(cls.isAssignableFrom(Map.class)) {
			if(value instanceof IPMap) {
				return Optional.of(((IPMap) value).map());
			}
		}
		if(cls.isAssignableFrom(Set.class)) {
			if(value instanceof IPSet) {
				return Optional.of(((PStream) value).pset().toSet());
			}
		}

		return Optional.empty();
	};


	public static Result<Tuple2<Executable, Object[]>> findExecutableForArguments(Caster caster,
																				  PList<? extends Executable> executables,
																				  Object... resolvedArgs
	) {
		return Result.function(executables, PList.val(resolvedArgs)).code(l -> {
			if(resolvedArgs.length == 0) {
				for(Executable c : executables) {
					if(c.getParameterCount() == resolvedArgs.length && isPublic(c)) {
						return Result.success(Tuple2.of(c, resolvedArgs));
					}
				}
			}
			if(executables.size() == 1) {
				if(isPublic(executables.get(0)) == false) {
					return Result.failure("No match");
				}
				Class[] paramTypes = executables.get(0).getParameterTypes();
				Tuple2<MatchLevel, Object[]> matchResult =
					tryMatch(caster, paramTypes, resolvedArgs, executables.get(0).isVarArgs());
				return checkMatch(matchResult, resolvedArgs, paramTypes)
					.map(ok -> Tuple2.of(executables.get(0), matchResult._2));
			}

			Executable        first          = null;
			PList<Executable> otherPossibles = PList.empty();
			for(Executable m : executables) {
				if((m.isVarArgs() || m.getParameterCount() == resolvedArgs.length) && isPublic(m)) {
					if(first == null) {
						first = m;
					}
					else {
						otherPossibles = otherPossibles.plus(m);
					}
				}
			}
			if(first == null) {
				return Result.failure("Can't find executable");
			}

			if(otherPossibles.isEmpty()) {
				//We have only 1 method with the same number of arguments
				Class[] paramTypes = first.getParameterTypes();
				Tuple2<MatchLevel, Object[]> matchResult =
					tryMatch(caster, paramTypes, resolvedArgs, first.isVarArgs());
				Executable finalFirst = first;
				return checkMatch(matchResult, resolvedArgs, paramTypes)
					.map(ok -> Tuple2.of(finalFirst, matchResult._2));
			}
			otherPossibles = otherPossibles.plus(first);

			//otherPossibles is now all methods with the same number of arguments
			//So we have to select the correct one for the argument types;

			Class[] argClasses = new Class[resolvedArgs.length];
			for(int t = 0; t < resolvedArgs.length; t++) {
				argClasses[t] = resolvedArgs[t] == null ? null : resolvedArgs[t].getClass();
			}
			Executable partial            = null;
			Object[]   partialArgs        = null;
			boolean    hasMultiplePartial = false;


			for(Executable m : otherPossibles) {
				if(isPublic(m) == false) {
					continue;
				}
				Tuple2<MatchLevel, Object[]> matchResult =
					tryMatch(caster, m.getParameterTypes(), resolvedArgs, m.isVarArgs());
				if(matchResult._1 == JavaExecutableFinder.MatchLevel.full) {
					return Result.success(Tuple2.of(m, matchResult._2));
				}
				if(matchResult._1 == JavaExecutableFinder.MatchLevel.partial) {
					if(partial != null) {
						hasMultiplePartial = true;
					}
					else {
						partial = m;
						partialArgs = matchResult._2;
					}
				}

			}

			if(hasMultiplePartial) {
				return Result.failure("Multiple overloaded methods!");
			}
			if(partial != null) {
				return Result.success(Tuple2.of(partial, partialArgs));
			}

			//Try match with varargs...
			for(Executable m : executables) {
				if(m.isVarArgs() == false) {
					continue;
				}
				Class[] paramTypes = m.getParameterTypes();
				if(paramTypes.length > resolvedArgs.length) {
					continue;
				}
				Tuple2<MatchLevel, Object[]> matchResult =
					tryMatch(caster, paramTypes, resolvedArgs, paramTypes.length - 1);
				if(matchResult._1 == JavaExecutableFinder.MatchLevel.not) {
					continue;
				}
				//Found one...
				Class    itemClass  = paramTypes[paramTypes.length - 1].getComponentType();
				int      restLength = resolvedArgs.length - (paramTypes.length - 1);
				Object[] varArgs    = (Object[]) Array.newInstance(itemClass, restLength);
				boolean  ok         = true;
				for(int t = 0; t < varArgs.length; t++) {
					Object           value  = resolvedArgs[paramTypes.length - 1 + t];
					Optional<Object> casted = caster.apply(value, itemClass);
					if(casted.isPresent() == false) {
						ok = false;
						break;
					}
					varArgs[t] = casted.get();
				}
				if(ok) {
					Object[] newSet = new Object[paramTypes.length];
					System.arraycopy(matchResult._2, 0, newSet, 0, paramTypes.length - 1);
					newSet[paramTypes.length - 1] = varArgs;

					return Result.success(Tuple2.of(m, newSet));
				}
			}

			return Result.failure("Executable arguments don't match");
		});

	}

	private static boolean isPublic(Executable c) {
		return Modifier.isPublic(c.getModifiers());
	}


	private static Result<OK> checkMatch(Tuple2<MatchLevel, Object[]> mr, Object[] arguments,
										 Class[] types
	) {
		if(mr._1 == JavaExecutableFinder.MatchLevel.not) {
			String args   = PStream.from(arguments).toString(", ");
			String params = PStream.from(types).map(Class::getName).toString(", ");
			return Result.failure("Can't match parameters (" + args + ") with (" + params + ")");
		}
		return OK.result;
	}

}
