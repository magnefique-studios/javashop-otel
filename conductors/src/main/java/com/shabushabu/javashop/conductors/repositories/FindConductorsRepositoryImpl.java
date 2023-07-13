package com.shabushabu.javashop.conductors.repositories;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<List<Instrument>> findConductorInstruments(@SpanAttribute("location") String location) throws Exception {
     	
    	List<Instrument> vipResults = new ArrayList<Instrument>();
    	
    	String sTablename = "instruments_for_sale_conductors_";
   
    		sTablename += location;
    	
    	return 	runQuery (sTablename, location);
    	
     }
    
    @SuppressWarnings("unchecked")
	@Override
	@WithSpan()
    public ResponseEntity<List<Instrument>> findConductorInstrumentsByVipLevel(Object instruments, @SpanAttribute("location") String location, @SpanAttribute("vipLevel") String vipLevel) throws Exception  {
     	
    	
    	List<Instrument> listInstruments = new ArrayList<Instrument> ();
    	ResponseEntity<List<Instrument>> vipResults ;
    	
    	 ResponseEntity<List<Instrument>> incomingResults = ( ResponseEntity<List<Instrument>>) instruments;
    	
    	//String queryString = "SELECT * FROM instruments_for_sale_conductors_" + location + "_" + vipLevel;
    	
    	String sTablename = "instruments_for_sale_conductors_";
    	
    	
    		sTablename += location + "_" + vipLevel;
    		vipResults = runQuery (sTablename, location, vipLevel);
    		
    		return vipResults;
     }
    
   /* @WithSpan()
    protected ResponseEntity<List<Instrument>> filterVipAndLocationData(  List<Instrument> vipList) throws Exception {
    	// Filter and Merge based on Locale and VipLevel data.
    	
    	ResponseEntity<List<Instrument>> results; 
    	
    	List<Instrument> theList = new ArrayList<Instrument>();
    	
    	
    	// Join lists 
    	theList.addAll(vipList);
    	
    	return new ResponseEntity<List<Instrument>> (theList, HttpStatus.OK);
    }
    */
    
    @SuppressWarnings("unchecked")
	protected ResponseEntity<List<Instrument>> runQuery(String tableName, String location) throws Exception {
    	ResponseEntity<List<Instrument>> results=null;
    	
    		if (!bFirstTimeResults) {
    			 entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_conductors_" + location ).getResultList();
    			bFirstTimeResults = true;
    		} else {
    			results =  VipTestData.s_istance.getInstrumentsByLocation(location);
    		}
    	
    	return results;
    }
	
    
    @SuppressWarnings("unchecked")
	protected ResponseEntity<List<Instrument>> runQuery(String tableName, String location, String vipLevel) throws Exception {
    	ResponseEntity<List<Instrument>> results=null;
    	
    	try  { 
    		if (!bFirstTimeResults) {
    			entityManager.createNativeQuery( "SELECT * FROM instruments_for_sale_conductors_" + location + "_" + "vipLevel").getResultList();
    			bFirstTimeResults = true;
    		} else {
    			System.out.println("Calling VipTestData.s_istance.getInstrumentsByLocationAndLevel");
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
 