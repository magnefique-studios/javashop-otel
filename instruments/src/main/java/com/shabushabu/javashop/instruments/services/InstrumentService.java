package com.shabushabu.javashop.instruments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shabushabu.javashop.instruments.model.FilteredInstrument;
import com.shabushabu.javashop.instruments.model.Instrument;
import com.shabushabu.javashop.instruments.repositories.InstrumentRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class InstrumentService {

	private static Logger s_logger = LogManager.getLogger(InstrumentService.class);
	 
    private InstrumentRepository instrumentRepo;

    @Autowired
    public InstrumentService(InstrumentRepository instrumentRepo) {
        this.instrumentRepo = instrumentRepo;
    }


    @SuppressWarnings("unchecked")
	public List<Instrument> getInstruments(String location) {
        	
    	Object obj = null;
    	
    	if (location.equalsIgnoreCase( "Oregon" )) {
    		obj = instrumentRepo.findInstrumentsOregon();
    		return  StreamSupport.stream(instrumentRepo.findAll().spliterator(), false)
					.collect(Collectors.toList());
    	}
    	else if (location.equalsIgnoreCase("Chicago")) {
    	
    		obj = instrumentRepo.findInstruments();
    		
    		if ( null == obj || !( obj instanceof List<?>) ) {
    			return null;
    		} else {
    			return  StreamSupport.stream(instrumentRepo.findAll().spliterator(), false)
    					.collect(Collectors.toList());
    		}	
    	}
    	else {
    		return  StreamSupport.stream(instrumentRepo.findAll().spliterator(), false)
                    .collect(Collectors.toList());
    		
    	}
    }
    
   /*
	public static Object findInstrumentsOregon(EntityManager entityManager) {
		
		Object obj = entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale, instruments-oregon").getResultList();  
    	
		return obj;
    	
    }*/
    

}
