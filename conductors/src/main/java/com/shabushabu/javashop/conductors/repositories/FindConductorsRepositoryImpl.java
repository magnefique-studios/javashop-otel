package com.shabushabu.javashop.conductors.repositories;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.shabushabu.javashop.conductors.VipTestData;
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

	private static boolean bFirstTimeResults = false;
	
	private static Logger s_logger = LogManager.getLogger(FindConductorsRepositoryImpl.class);
	
	private static Object s_bigQueryResult = null;
	
    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    
    @Override
    @WithSpan()
    public Object findConductorInstruments(@SpanAttribute("location") String location) {
     	
    	List<Instrument> vipResults = new ArrayList<Instrument>();
    	
    	String sTablename = "instruments_for_sale_conductors_";
    	
    
    	try {
    		sTablename += location;
    		vipResults = runQuery (sTablename, location);
    	}catch ( Exception e ) {
    		
    	}
    	return 	vipResults;
    	
     }
    
    @SuppressWarnings("unchecked")
	@Override
	@WithSpan()
    public Object findConductorInstrumentsByVipLevel(Object instruments, @SpanAttribute("location") String location, @SpanAttribute("vipLevel") String vipLevel) {
     	
    	List<Instrument> vipResults = new ArrayList<Instrument>();
    	
    	//String queryString = "SELECT * FROM instruments_for_sale_conductors_" + location + "_" + vipLevel;
    	
    	String sTablename = "instruments_for_sale_conductors_";
    	
    	try {
    		sTablename += location + "_" + vipLevel;
    		vipResults = runQuery (sTablename, location, vipLevel);
    		//entityManager.createNativeQuery( queryString ).getResultList();	
    	}catch ( Exception e ) {
    		
    	}
    	return filterVipAndLocationData((List<Instrument>)instruments, vipResults);	
     }
    
    protected List<Instrument> filterVipAndLocationData( List<Instrument> instruments, List<Instrument> vipList) {
    	// Filter and Merge based on Locale and VipLevel data.
    	
    	List<Instrument> results = new ArrayList<Instrument>();
    	
    	// Join lists 
    	results.addAll(vipList);
    	results.addAll(instruments);
    	
    	return results;
    }
    
    @SuppressWarnings("unchecked")
	protected List<Instrument> runQuery(String tableName, String location) {
    	List<Instrument> results = new ArrayList<Instrument>();
    	try  { 
    		if (bFirstTimeResults) {
    			results = entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_conductors_" + location ).getResultList();
    			bFirstTimeResults = true;
    		} else {
    			results =  VipTestData.s_istance.getInstrumentsByLocation(location);
    		}
    	} catch (Throwable t ) {
    		
    	}
    	return results;
    }
	
    
    @SuppressWarnings("unchecked")
	protected List<Instrument> runQuery(String tableName, String location, String vipLevel) {
    	List<Instrument> results = new ArrayList<Instrument>();
    	try  { 
    		if (bFirstTimeResults) {
    			results = entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_conductors_" + location + "_" + "vipLevel").getResultList();
    			bFirstTimeResults = true;
    		} else {
    			results =  VipTestData.s_istance.getInstrumentsByLocationAndLevel(location, vipLevel);
    		}
    	} catch (Throwable t ) {
    		
    	}
    	return results;
    }
	
	@Override
    public Object findInstrumentsAll() {
    	s_logger.info("findInstruments Called (All)");
    	
    	//Object obj = entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale").getResultList(); 
	 
		return null;
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
 