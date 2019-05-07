package com.lancslot.morn.web.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Length {
	
	String name() default "";

	int size();
	
	String tips() default "长度不正确";
	
}
