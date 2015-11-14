package com.stefanini.mav.simulador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Criada para simular o comportamento do ambiente losando para validação das mensagens
 *
 */
public class LosangoBootstrap {

	private static Logger _LOGGER = LoggerFactory.getLogger(LosangoBootstrap.class);
	
	private static final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mav-init-context.xml");

	public static void main(String[] args) {

		_LOGGER.info("Simulador " + ctx.getDisplayName() + " iniciado.");
	}
}