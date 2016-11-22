package com.lvg.resttest;

import org.apache.log4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainTest {
	private static final Logger LOG = Logger.getLogger(MainTest.class);
	
	public static void main(String[] args) {
		 LOG.info("STARTING TEST APPLICATION");

	     GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("META-INF/spring/datasource-jpa-context.xml");
	    

	     LOG.info("END OF TEST APPLICATION");
	}
	
	

}
