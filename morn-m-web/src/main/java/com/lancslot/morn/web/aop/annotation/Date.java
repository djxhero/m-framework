package com.lancslot.morn.web.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
	
	String name() default "";

	String format() default "yyyy-MM-dd";
	
	String tips() default "日期格式错误";
	
}
