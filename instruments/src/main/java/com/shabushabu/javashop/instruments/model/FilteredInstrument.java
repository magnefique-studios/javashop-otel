package com.shabushabu.javashop.instruments.model;




import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shabushabu.javashop.instruments.InstrumentsApplication;

//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;

import com.shabushabu.javashop.instruments.exceptions.InvalidLocaleException;

import io.opentelemetry.instrumentation.annotations.WithSpan;


public class FilteredInstrument {
	
   private static final boolean  s_disabled = false; 
	
   private final Logger s_logger = LoggerFactory.getLogger(FilteredInstrument.class);
	
@WithSpan
	public Object filterInstruments( Object obj) throws InvalidLocaleException {
		
		if (s_disabled) {
			
			// See src/main/resources/application.properties ..
			s_logger.error("Trying to filter to disabled Region: Oregon");
			
			throw new InvalidLocaleException("Trying to filter to disabled Region: Oregon");
		} else {		  		
	    		@SuppressWarnings("unchecked")
				List<Instrument> list = (List<Instrument>) obj;
	            for (Instrument i : list) {		     
	            	System.out.println(i.getTitle());
	            }
	            for (Instrument i : list) {		     
	            	System.out.println(i.getSubTitle());
	            }
	            for (Instrument i : list) {		     
	            	System.out.println(i.getInstrumentType());
	            }
	            for (Instrument i : list) {		     
	            	System.out.println(i.getPublishedDate());
	            }
	            for (Instrument i : list) {		     
	            	System.out.println(i.getSellerType());
	            }
	            for (Instrument i : list) {		     
	            	System.out.println(i.getPrice());
	            }
	            for (Instrument i : list) {		     
	            	System.out.println(i.getId());
	            }
	            
		}
		return obj;	
	}
}

