package com.common.utilities;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

	private static final Logger log = LoggerFactory.getLogger(Config.class);
	private static final String DEFAULT_PROPERTIES = "./configurations/default.properties";
	private static Properties properties;

	public static void initialize() {
		properties = loadProperties();

		// check for any override from the mvn command
		for(String key: properties.stringPropertyNames()) {
			if(System.getProperties().containsKey(key)) {
				properties.setProperty(key, System.getProperty(key));
			}
		}

		log.info("Test Properties");
		log.info("************************************");
		for (String key : properties.stringPropertyNames()) {
			log.info("{}={}",key,properties.getProperty(key));
		}
		log.info("************************************");

	}
	
	public static String getPropertyOf(String key) {
		return properties.getProperty(key);
	}
	

	private static Properties loadProperties() {
		Properties properties = new Properties();
		try (InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
			properties.load(stream);
		} catch(Exception e) {
			log.error("Unable to read the property file {}",DEFAULT_PROPERTIES, e);
		}
		return properties;
	}
}
