package com.stefanini.mav.es;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfiguracaoMensagem {
	
	int inicio() default 0;
	
	Fluxo sentido();
}
