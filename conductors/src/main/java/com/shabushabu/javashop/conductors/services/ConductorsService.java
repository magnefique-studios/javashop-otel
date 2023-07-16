package com.shabushabu.javashop.conductors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shabushabu.javashop.conductors.model.Instrument;
import com.shabushabu.javashop.conductors.repositories.ConductorsInstrumentRepository;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.ArrayList;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManager;
import javax.swing.Spring;


@Service
public class ConductorsService {
	
	/*public enum Loc_ation {
		California, Utah, Oregon, vip
	}

	public enum VIP_Level {
	     Silver, Gold, Platinum, NONE
	}
	*/
	
	private static ArrayList<Instrument> s_precache = new ArrayList<Instrument>();

	private static Logger s_logger = LogManager.getLogger(ConductorsService.class);
	 
    private ConductorsInstrumentRepository instrumentRepo;

    @Value("${instrumentsUri}")
    private String instrumentsUri;
    
    @Value("${shopUri}")
    private String shopUri;
    

   @Value("${conductorsUri}")
   private String conductorsUri;
    
    @Autowired
    private RestTemplate restTemplate;
    
    
    @Autowired
    public ConductorsService(ConductorsInstrumentRepository instrumentRepo) {
        this.instrumentRepo = instrumentRepo;
    }
    
    // Location plus a 
    
    /*
     *  Location-> call Instruments Location Functions with String
     *  
     *  */
    
    
    /*@WithSpan()*/
	//public List<Instrument> getConductorsInstruments(/* @SpanAttribute("location")*/ String location) {
        
    	// CONVERT LOCATION TO ENUM.
    	
    //	Object fullSalesList =  StreamSupport.stream(instrumentRepo.findAll().spliterator(), false).collect(Collectors.toList());
   // 	return (List<Instrument>) fullSalesList;
   // }

   @SuppressWarnings("unchecked")
   @WithSpan()
	public ResponseEntity<List<Instrument>> getConductorsInstruments( @SpanAttribute("location") String location,   @SpanAttribute("vipLevel")  String vipLevel) throws Exception {
        
    	System.out.println("Enerting getConductorsInstruments : location=" + location + "vipLevel=" + vipLevel );
    	// Call Get By Location
    	// Pass Result to Get By VIP LEVEL
    	
    	Object locationList = getConductorsInstrumentsForLocation( location );
    	//VIP_Level vip_level = this.convertVipLevel(vipLevel);
    	
    	return getConductorsOrdersByVipLevel(location, locationList, vipLevel );
    	
   } 	
   
   @SuppressWarnings("unchecked")
   @WithSpan()
   public ResponseEntity<List<Instrument>> getConductorsInstrumentsForLocation(  @SpanAttribute("location")  String location) throws Exception {
	   
	   
       	s_logger.info("getConductorInstrument  by location ");
        
        HttpStatus httpStatus = HttpStatus.OK;
       	
       	/*if (location.contains("italy")) {
       	
       		ResponseEntity<List<Instrument>> instrumentsResponse =
        
       				restTemplate.exchange(instrumentsUri + "/instruments?" + "location=" + location.toString(),
       					HttpMethod.GET, null, new ParameterizedTypeReference<List<Instrument>>() {
       				});
       				List<Instrument> instruments = instrumentsResponse.getBody();
       				
       				return instrumentsResponse;
       	} else {

		// DJD Note: When the conductors specific data is loaded in database, complete breakup 
		// of Instruments Service into 2 Services
		// Remove calls to Instruments Service and replace with database calls to conductordsDB directly.
		
		// See code below:
		*/
         return instrumentRepo.findConductorInstruments(location);
       	//}
   }
   
    @SuppressWarnings("unchecked")
	@WithSpan()
    public ResponseEntity<List<Instrument>> getConductorsOrdersByVipLevel( @SpanAttribute("location") String location, Object location_based_results,  @SpanAttribute("vipLevel") String vipLevel) throws Exception {
    
		/* s_logger.info("getConductor Instrument  by VipLevel and location ");
	        ResponseEntity<List<Instrument>> conductorsResponse =
	                restTemplate.exchange(instrumentsUri + "/instruments?" + "location=" + location + "&vipLevel=" + vipLevel.toString(), 
	                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Instrument>>() {
	                        });
	        List<Instrument> instruments = conductorsResponse.getBody();
	
	        return instruments;
	 */       
	        // DJD Note: When the conductors specific data is loaded in database, complete breakup 
			// of Instruments Service into 2 Services
			// Remove calls to Instruments Service and replace with database calls to conductordsDB directly.
			
			// See return statement below:
			
	        return instrumentRepo.findConductorInstrumentsByVipLevel(location_based_results, location, vipLevel);
	        
	   }
    
   
   /*
    public  List<Instrument> filterConductorsOrdersByLocation(Object obj, String location, String vipLevel) {
		Object result = obj;
		List bigList = (List)obj;
		ArrayList<Instrument> reducedList = new ArrayList<Instrument>(); 
		if (vipLevel.toString().compareToIgnoreCase("Silver") == 0) {
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
    
    
    public  List<Instrument> filterConductorsOrdersByLocationAndTheLevel(Object obj, String location,  String vipLevel) {
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
	
	public VIP_Level convertVipLevel(String vipLevel) {
		return VIP_Level.valueOf(vipLevel);
	}
	
	public Loc_ation convertLocation(String location) {
		return Loc_ation.valueOf(location);
	}
	*/
	
}
