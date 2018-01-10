package com.vasservice.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vasservice.outputsenders.OutputJSONSender;
import com.vasservice.outputsenders.OutputXMLSender;

@Configuration
public class AppConfig {
	
	@Bean
	public OutputJSONSender getJSONSender() {
		return new OutputJSONSender(); 
	}
	
	@Bean
	public OutputXMLSender getXMLSender() {
		return new OutputXMLSender(); 
	}


}
