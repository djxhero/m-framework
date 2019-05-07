package com.lancslot.morn.web.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
	
	String name() default "";

	int min() default Integer.MIN_VALUE;
	
	int max() default Integer.MAX_VALUE;
	
	String tips() default "范围不合法";
}
