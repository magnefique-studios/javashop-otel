package com.shabushabu.javashop.instruments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shabushabu.javashop.instruments.model.Instrument;
import com.shabushabu.javashop.instruments.repositories.InstrumentRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManager;


@Service
public class InstrumentService {
	
	private static ArrayList<Instrument> s_precache = new ArrayList<Instrument>();

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
    
   
	@SuppressWarnings("unchecked")
	public static Object findInstrumentsOregon(EntityManager entityManager) {
		
		Object obj = entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_us_central").getResultList();  
		
		s_precache.addAll( (List) obj);
		
		obj = entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_us_west").getResultList();  
		
		s_precache.addAll( (List) obj);
		
		obj = entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_us_east").getResultList();  
		
		s_precache.addAll( (List) obj);
		
			
		return filterByLocation( "Oregon", s_precache);
    	
    }
	
	public static Object filterByLocation(String location, Object obj) {
		Object result = obj;
		List bigList = (List)obj;
		ArrayList<Instrument> reducedList = new ArrayList<Instrument>(); 
		if (location.compareToIgnoreCase("Oregon") == 0) {
			for (int i=0; i<bigList.size(); i++) {
				// For now just grab the first 100
				if (i<= 100) {
					reducedList.add((Instrument)bigList.get(i));
				}
			}
		}
		return result;
	
	}
    

}
