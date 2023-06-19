package com.shabushabu.javashop.conductors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shabushabu.javashop.conductors.model.Instrument;
import com.shabushabu.javashop.conductors.repositories.InstrumentRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManager;


@Service
public class ConductorsService {
	
	private static ArrayList<Instrument> s_precache = new ArrayList<Instrument>();

	private static Logger s_logger = LogManager.getLogger(ConductorsService.class);
	 
    private InstrumentRepository instrumentRepo;

    @Autowired
    public ConductorsService(InstrumentRepository instrumentRepo) {
        this.instrumentRepo = instrumentRepo;
    }

    // VIP Level = silver, gold, platinum
    

    @SuppressWarnings("unchecked")
	public List<Instrument> getConductorsInstruments(String location, String vipLevel) {
        	
    	Object fullSalesList =  StreamSupport.stream(instrumentRepo.findAll().spliterator(), false)
					.collect(Collectors.toList());
    	return filterConductorsOrdersByLocation(fullSalesList, location, vipLevel);
    }
    
   
	
	
	public  List<Instrument> filterConductorsOrdersByLocation(Object obj, String location, String vipLevel) {
		Object result = obj;
		List bigList = (List)obj;
		ArrayList<Instrument> reducedList = new ArrayList<Instrument>(); 
		if (vipLevel.compareToIgnoreCase("Silver") == 0) {
			for (int i=0; i<bigList.size(); i++) {
				
				Instrument instrument = (Instrument)bigList.get(i);
				
				if ( instrument.getVipLevel().compareToIgnoreCase("PLATINUM") == 0 ) {
					reducedList.add((Instrument)bigList.get(i));
				} else if ( instrument.getVipLevel().compareToIgnoreCase("GOLD") == 0 ) {
				
				} else if ( instrument.getVipLevel().compareToIgnoreCase("SILVER") == 0 ) {
			
				} else {
			
				}
			}
	
		}
		
		return (List<Instrument>) result;
    
	}
}
