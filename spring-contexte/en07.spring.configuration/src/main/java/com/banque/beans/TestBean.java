package com.banque.beans;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TestBean {

	private Logger LOG = LogManager.getLogger();
	
	private String test;

	@PostConstruct
	public void initMethod() {
		LOG.info("initMethod - testBean");
		this.test = "gildas rouault";
	}
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
}
