package com.shabushabu.javashop.instruments.model;



import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;

import com.shabushabu.javashop.instruments.exceptions.InvalidLocaleException;


public class FilteredInstrument {
	
   private static final boolean  s_disabled = false; 
	
   private static Logger s_logger = LogManager.getLogger(FilteredInstrument.class);
	

	public Object filterInstruments( Object obj) throws InvalidLocaleException {
		
		
		if (s_disabled) {
			
			// See src/main/resources/application.properties ..
			s_logger.error("Trying to filter to disabled Region: Oregon");
			
			throw new InvalidLocaleException("Trying to filter to disabled Region: Oregon");
		}
		
		
		
		return null;	
	}
}

