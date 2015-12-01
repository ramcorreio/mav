package com.stefanini.mav.es;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapper {
	
	String path() default "";
	
	int tamanho() default 1;
	
	boolean obrigatorio() default false;
	
	int scale() default 2;
	
	boolean trim() default true;
	
	String formato() default "ddMMyyyy";
}
