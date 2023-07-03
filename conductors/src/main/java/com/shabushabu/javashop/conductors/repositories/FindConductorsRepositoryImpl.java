package com.shabushabu.javashop.conductors.repositories;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shabushabu.javashop.conductors.model.Instrument;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

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

public class FindConductorsRepositoryImpl implements FindConductorsRepository {

	private static Logger s_logger = LogManager.getLogger(FindConductorsRepositoryImpl.class);
	
	private static Object s_bigQueryResult = null;
	
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    
    @Override
    @WithSpan()
    public Object findConductorInstruments(@SpanAttribute("location") String location) {
     	
    	return entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_conductors_" + location).getResultList(); 	
    	
     }
    
    @SuppressWarnings("unchecked")
	@Override
	@WithSpan()
    public Object findConductorInstrumentsByVipLevel(Object instruments, @SpanAttribute("location") String location, @SpanAttribute("vipLevel") String vipLevel) {
     	
    	List<Instrument> vipResults = new ArrayList<Instrument>();
    	
    	String queryString = "SELECT * FROM instruments_for_sale_conductors_" + location + "_" + vipLevel;
    	vipResults = entityManager.createNativeQuery( queryString ).getResultList();	
	
    	return filterVipAndLocationData((List<Instrument>)instruments, vipResults);	
     }
    
    protected List<Instrument> filterVipAndLocationData( List<Instrument> instruments, List<Instrument> vipLevel) {
    	// Filter and Merge based on Locale and VipLevel data.
    	
    	List<Instrument> results = new ArrayList<Instrument>();
    	
    	// Join lists 
    	results.addAll(vipLevel);
    	results.addAll(instruments);
    	
    	return results;
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
 