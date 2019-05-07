package com.lancslot.morn.config;


public class ApplicationProperties extends PropertiesConfig {
	
	private final String resource = "application.properties";
	
	private static ApplicationProperties arp = new ApplicationProperties();
	
	private ApplicationProperties() {
		properties = load(resource);
	}
	
	public static ApplicationProperties getInstance() {
		return arp;
	}

	public String getResource() {
		return resource;
	}
}
