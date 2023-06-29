package com.shabushabu.javashop.conductors.repositories;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shabushabu.javashop.conductors.model.FilteredInstrument;
import com.shabushabu.javashop.conductors.model.Instrument;
import com.shabushabu.javashop.conductors.services.ConductorsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/*
 * 
 *  Object findConductorInstruments( String location);
	 Object findVipInstruments(Object obj);
	 Object findInstrumentsAll();
	 Instrument findInstrumentByID(String id);
}
 * */

public class FindInstrumentRepositoryImpl implements FindInstrumentRepository {

	private static Logger s_logger = LogManager.getLogger(FindInstrumentRepositoryImpl.class);
	
	private static Object s_bigQueryResult = null;
	
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    
    @Override
    public Object findConductorInstruments(String location) {
     	return entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale").getResultList(); 	
     }

    
    @Override
	public Object findVipInstruments(Object obj, String vipLevel) {
    	
    	return entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_conductors").getResultList(); 	
    	
    }
	
	@Override
    public Object findInstrumentsAll() {
    	s_logger.info("findInstruments Called (All)");
    	
    	Object obj = entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale").getResultList(); 
	 
		return obj;
    }
    
    @Override
    public Instrument findInstrumentByID(String id) {
	    Instrument result = (Instrument) entityManager.createQuery("FROM instruments_for_sale i WHERE i.ID = " + id.toString()).getSingleResult(); 
    	return result;
    }

    @PostConstruct
    public void postConstruct() {
        Objects.requireNonNull(entityManager);
    }
    
	
}
 