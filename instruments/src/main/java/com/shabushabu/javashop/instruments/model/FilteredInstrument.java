package com.shabushabu.javashop.instruments.model;




import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

