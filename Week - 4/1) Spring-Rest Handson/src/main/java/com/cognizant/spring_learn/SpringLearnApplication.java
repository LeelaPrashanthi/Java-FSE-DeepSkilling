package com.cognizant.spring_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SpringLearnApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		LOGGER.info("START");
		SpringApplication.run(SpringLearnApplication.class, args);
		displayDate();
		displayCountries();
		LOGGER.info("END");
	}
	
	public static void displayDate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
		try {
			Date date = format.parse("31/12/2018");
			System.out.println("Parsed Date: " + date);
		} catch (Exception e) {
			System.out.println("Error parsing date: " + e.getMessage());
		}
	}

	public static void displayCountries() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		java.util.List<com.cognizant.springlearn.Country> countryList = (java.util.List<com.cognizant.springlearn.Country>) context.getBean("countryList");
		LOGGER.debug("Country List : {}", countryList);
		LOGGER.info("END");
	}

}
