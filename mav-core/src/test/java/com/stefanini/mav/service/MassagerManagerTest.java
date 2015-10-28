package com.stefanini.mav.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class MassagerManagerTest {
	
	@Test
	@Ignore
	public void createMessage() {
		
		Assert.fail();
	}

}
