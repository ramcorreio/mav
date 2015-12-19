package com.stefanini.mav.es;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MapAtributo {
	
	String path() default "";
	
	int tamanho() default 1;
	
	boolean obrigatorio() default true;
	
	int scale() default 2;
	
	boolean trim() default true;
	
	String formato() default "ddMMyyyy";
	
	String comparadorPositivo() default "1";
	
	String comparadorNegativo() default "0";
	
	boolean zeroEsquerda() default true;
}
