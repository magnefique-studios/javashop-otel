package com.shabushabu.javashop.conductors.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.shabushabu.javashop.conductors.exceptions.InstrumentNotFoundException;
import com.shabushabu.javashop.conductors.model.Instrument;
import com.shabushabu.javashop.conductors.services.ConductorsService;

import io.opentelemetry.instrumentation.annotations.WithSpan;

import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping()
public class ConductorsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConductorsResource.class);

  
    
    @Autowired
    private ConductorsService conductorsService;
    
    @RequestMapping("/healthcheck")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public String healthCheck() {
        return "HTTP Status OK (CODE 200)\n";
    }    
    
    @RequestMapping("/conductors")
    @WithSpan()
    public ResponseEntity<List<Instrument>> getInstruments(@DefaultValue("California") @RequestParam("location") String location, 
    									   @DefaultValue("NONE") @RequestParam("vipLevel") String vipLevel) throws Exception {
    	LOGGER.info("Conductors (All) at location: " + location);
    	LOGGER.info("Conductors (All) at level "+ vipLevel );
    	
    	
    	ResponseEntity<List<Instrument>> response;
    	ArrayList<Instrument> returnList = new ArrayList<Instrument>();
        HttpStatus httpStatus = HttpStatus.OK;

    	try {
    		if ( vipLevel.compareToIgnoreCase("NONE") == 0 ) {
    			response =  conductorsService.getConductorsInstrumentsForLocation(location);
    		} else {
    			response = conductorsService.getConductorsInstruments( location, vipLevel );
    		}
    		
    		if (response.getStatusCodeValue() != 200) {
    			System.out.println("Code is NOT OK !!!!! : " + response.getStatusCodeValue());
    		}
    		
    			
    	} catch (Exception e) {
    		System.out.println("EXCEPTION THROWN");
    		httpStatus = HttpStatus.NOT_ACCEPTABLE;
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    	}
    	
    	return response;
    	
    }
    
    
    protected List<Instrument> decorateInstruments( List<Instrument> incomingInstruments ) {
    	return new ArrayList<Instrument>();
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleInstrumentNotFound(InstrumentNotFoundException snfe) {
    }
}
