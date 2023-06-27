package com.shabushabu.javashop.conductors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shabushabu.javashop.conductors.model.Instrument;
import com.shabushabu.javashop.conductors.repositories.InstrumentRepository;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;

import java.util.List;

import java.util.ArrayList;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManager;


@Service
public class ConductorsService {
	
	public enum Loc_ation {
		California, Utah, Oregon 
	}

	public enum VIP_Level {
	     Silver, Gold, Platinum
	}
	
	private static ArrayList<Instrument> s_precache = new ArrayList<Instrument>();

	private static Logger s_logger = LogManager.getLogger(ConductorsService.class);
	 
    private InstrumentRepository instrumentRepo;

    @Value("${instrumentsUri}")
    private String instrumentsUri;
    
    @Value("${shopUri}")
    private String shopUri;
    

   @Value("${conductorsUri}")
   private String conductorsUri;
    
    @Autowired
    private RestTemplate restTemplate;
    
    
    @Autowired
    public ConductorsService(InstrumentRepository instrumentRepo) {
        this.instrumentRepo = instrumentRepo;
    }

    // VIP Level = silver, gold, platinum
    

    @SuppressWarnings("unchecked")
    @WithSpan()
	public List<Instrument> getConductorsInstruments(String location, String vipLevel) {
        
    	// CONVERT LOCATION TO ENUM.
    	
    	//Object fullSalesList =  StreamSupport.stream(instrumentRepo.findAll().spliterator(), false).collect(Collectors.toList());
    	//return filterConductorsOrdersByLocation(fullSalesList, location, convertVipLevel(vipLevel));
    	
    	// Call Get By Location
    	// Pass Result to Get By VIP LEVEL
    	
    	Loc_ation loc = convertLocation(location);
    	
    	Object locationList = getConductorsInstrumentsForLocation( loc );
    	
    	VIP_Level vip_level = this.convertVipLevel(vipLevel);
    	
    	return getConductorsOrdersByVipLevel(loc, locationList, vip_level );
    	
    	// return (List<Instrument>) locationList; //  getConductorsOrdersByVipLevel(loc, locationList, vip_level );
    }
    
    public List<Instrument> getConductorsInstrumentsForLocation(Loc_ation location) {
    	// DO calls to Instruments by Location. 
        	s_logger.info("getConductorInstrument  by location ");
            ResponseEntity<List<Instrument>> instrumentsResponse =
                    restTemplate.exchange(instrumentsUri + "/instruments?" + "location=" + location.toString(),
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Instrument>>() {
                            });
            List<Instrument> instruments = instrumentsResponse.getBody();

            return instruments;
    }
    
    public List<Instrument> getConductorsOrdersByVipLevel(Loc_ation location, Object location_based_results, VIP_Level vipLevel) {
    	
    	s_logger.info("getConductor Instrument  by VipLevel and location ");
            ResponseEntity<List<Instrument>> conductorsResponse =
                    restTemplate.exchange(conductorsUri + "/conductors?" + "location=" + location.toString() + "&vipLevel=" + vipLevel.toString(), 
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Instrument>>() {
                            });
            List<Instrument> instruments = conductorsResponse.getBody();

            return instruments;
            
        }
    
    
    
    public  List<Instrument> filterConductorsOrdersByLocation(Object obj, String location, /*@SpanAttribute("vipLevel")*/  VIP_Level vipLevel) {
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
    
    
    public  List<Instrument> filterConductorsOrdersByLocationAndTheLevel(Object obj, String location, /*@SpanAttribute("vipLevel")*/ String vipLevel) {
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
    
    
	public  List<Instrument> filterConductorsOrdersByLocation(Object obj, String location, /*@SpanAttribute("vipLevel")*/ String vipLevel) {
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
}
