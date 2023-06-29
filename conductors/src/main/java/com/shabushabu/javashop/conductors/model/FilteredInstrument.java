package com.shabushabu.javashop.conductors.model;




import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shabushabu.javashop.conductors.ConductorsApplication;
import com.shabushabu.javashop.conductors.exceptions.InvalidLocaleException;
import com.shabushabu.javashop.conductors.services.ConductorsService;

import io.opentelemetry.instrumentation.annotations.WithSpan;


public class FilteredInstrument {
	
   private static final boolean  s_OregonDataEnabled = false; 
	
   private final Logger s_logger = LoggerFactory.getLogger(FilteredInstrument.class);
	
@WithSpan
public Object filterInstruments( EntityManager entityManager, Object obj) throws InvalidLocaleException {
	
	Object result = obj;
	
	if (!s_OregonDataEnabled) {
			
			// See src/main/resources/application.properties ..
			s_logger.error("Trying to filter to disabled Region: Oregon");
			
			throw new InvalidLocaleException("Trying to filter to disabled Region: Oregon");
		} else {	
			
			
			//result = ConductorsService.findInstrumentsOregon(entityManager);	
			//System.out.println("Orgegon Location Enabled.");
			//System.out.println(obj.getClass().getName());
		/*
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
	      */      
		}
		return result;	
	}
}

