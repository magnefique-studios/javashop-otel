package com.shabushabu.javashop.instruments.model;




import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shabushabu.javashop.instruments.InstrumentsApplication;

//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;

import com.shabushabu.javashop.instruments.exceptions.InvalidLocaleException;
import com.shabushabu.javashop.instruments.services.InstrumentService;

import io.opentelemetry.instrumentation.annotations.WithSpan;


public class FilteredInstrument {
	
   private static final boolean  s_disabled = true; 
	
   private final Logger s_logger = LoggerFactory.getLogger(FilteredInstrument.class);
	
@WithSpan
public Object filterInstruments( EntityManager entityManager, Object obj) throws InvalidLocaleException {
	
	Object result = obj;
	
	if (s_disabled) {
			
			// See src/main/resources/application.properties ..
			s_logger.error("Trying to filter to disabled Region: Oregon");
			
			throw new InvalidLocaleException("Trying to filter to disabled Region: Oregon");
		} else {	
			
			// See src/main/resources/application.properties ..
			s_logger.error("Calling Oregon Specific data.....");
			
			result = InstrumentService.findInstrumentsOregon(entityManager);	
		 
		}
		return result;	
	}
}

