package com.stefanini.mav.es;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({FIELD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MapAtributo {
	
	MapBean bean() default @MapBean({});
	
	Mapper value() default @Mapper;
}
