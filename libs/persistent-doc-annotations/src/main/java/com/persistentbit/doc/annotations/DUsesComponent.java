package com.persistentbit.doc.annotations;

import java.lang.annotation.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/04/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE})
@Repeatable(DUsesComponents.class)
public @interface DUsesComponent{
	String name() default "";
	String packageName();
	String label() default "";
	String thisLabel() default "";
	String otherLabel() default "";
}
