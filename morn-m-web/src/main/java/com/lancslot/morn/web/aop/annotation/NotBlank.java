package com.lancslot.morn.web.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {
	
	String[] names() default {};
	
	String tips() default "不能为空";
}
