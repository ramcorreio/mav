package com.stefanini.mav.es;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface MapLista {
	
	MapBean bean() default @MapBean(tamanho = 0);
	
	MapAtributo attr() default @MapAtributo(tamanho = 0);
	
	int maxSize();
	
}
