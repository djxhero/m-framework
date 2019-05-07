package com.lancslot.morn.web.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsValid {
	
	NotBlank notBlank() default @NotBlank;
	
	Length[] lengths() default {};
	
	Date[] dates() default {};
	
	Pattern[] patterns() default {};
	
	Range[] ranges() default {};

}
