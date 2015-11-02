package com.stefanini.mav.startup;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageServerBootstrap {
	
	private static final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mav-init-context.xml");

	public static void main(String[] args) {

		System.out.println(ctx.getDisplayName());
	}
}