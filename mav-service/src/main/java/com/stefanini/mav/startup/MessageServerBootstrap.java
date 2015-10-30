package com.stefanini.mav.startup;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageServerBootstrap {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mav-init-context.xml");
		System.out.println(ctx.getDisplayName());
	}
}