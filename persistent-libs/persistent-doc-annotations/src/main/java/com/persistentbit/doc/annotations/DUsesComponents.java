package com.persistentbit.doc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/04/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE})
public @interface DUsesComponents{
	DUsesComponent[] value();
}
