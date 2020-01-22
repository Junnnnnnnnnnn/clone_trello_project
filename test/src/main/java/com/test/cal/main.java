package com.test.cal;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String configLocation = "classpath:spring/application-config.xml";
		
		AbstractApplicationContext ctx = 
	            new GenericXmlApplicationContext(configLocation);
		
		Calculation cal = ctx.getBean("cal",Calculation.class);
		
		
		cal.print_();
	}

}
