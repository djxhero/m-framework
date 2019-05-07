package com.lancslot.morn.utils.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesConfig {
	
	Logger logger = LoggerFactory.getLogger(PropertiesConfig.class);
	
	protected Properties properties;
	
	public Properties load(String resource) {
		logger.info("load properties from file : {}", resource);
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource));
			Enumeration<?> pns = properties.propertyNames();
			while(pns.hasMoreElements()) {
				Object obj = pns.nextElement();
				if (obj != null) {
					logger.info("{} = {}" , obj ,  properties.getProperty(obj.toString()));
				}
			}
		} catch (IOException e) {
			logger.error("load properties from file : " + resource + " exception", e);
		}
		return properties;
	}

	public String getValue(String propertyName) {
		return getValue(propertyName, null);
	}
	
	public String getValue(String propertyName, String defaultValue) {
		if (!properties.containsKey(propertyName)){
			return defaultValue;
		} else {
			return properties.getProperty(propertyName);
		}
	}
	
	public Integer getIntegerValue(String propertyName) {
		return getIntegerValue(propertyName , null);
	}
	
	public Integer getIntegerValue(String propertyName, Integer defaultValue) {
		if (!properties.containsKey(propertyName)){
			return defaultValue;
		} else {
			return Integer.parseInt(properties.getProperty(propertyName));
		}
	}
	
	public Boolean getBooleanValue(String propertyName) {
		return getBooleanValue(propertyName , null);
	}
	
	public Boolean getBooleanValue(String propertyName, Boolean defaultValue) {
		if (!properties.containsKey(propertyName)){
			return defaultValue;
		} else {
			return Boolean.parseBoolean(properties.getProperty(propertyName));
		}
	}
	
	public Double getDoubleValue(String propertyName) {
		return getDoubleValue(propertyName , null);
	}
	
	public Double getDoubleValue(String propertyName, Double defaultValue) {
		if (!properties.containsKey(propertyName)){
			return defaultValue;
		} else {
			return Double.parseDouble(properties.getProperty(propertyName));
		}
	}
}
