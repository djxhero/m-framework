package com.lancslot.morn.web.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Pattern {
	
	String name() default "";

	String regex();
	
	String tips() default "格式不匹配";
}
