package com.shabushabu.javashop.conductors.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.shabushabu.javashop.conductors.exceptions.InstrumentNotFoundException;
import com.shabushabu.javashop.conductors.model.Instrument;
import com.shabushabu.javashop.conductors.services.ConductorsService;

import io.opentelemetry.instrumentation.annotations.WithSpan;

import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping()
public class InstrumentResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstrumentResource.class);

  
    
    @Autowired
    private ConductorsService conductorsService;
    
    @RequestMapping("/healthcheck")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public String healthCheck() {
        return "HTTP Status OK (CODE 200)\n";
    }    
    
    @RequestMapping("/conductors")
    @WithSpan()
    public List<Instrument> getInstruments(@DefaultValue("California") @RequestParam("location") String location, 
    									   @DefaultValue("NONE") @RequestParam("vipLevel") String vipLevel) {
    	LOGGER.info("Conductors (All) at location: " + location);
    	LOGGER.info("Conductors (All) at level "+ vipLevel );
    	
    	if ( vipLevel.compareToIgnoreCase("NONE") == 0 ) {
    		// StreamSupport.stream(instrumentRepo.findAll().spliterator(), false)
			// .collect(Collectors.toList());
    		return StreamSupport.stream(conductorsService.getConductorsInstrumentsForLocation( location ).spliterator(), false).collect(Collectors.toList());
    	} else {
    		return StreamSupport.stream(conductorsService.getConductorsInstruments( location, vipLevel ).spliterator(), false).collect(Collectors.toList());
    	}
    }
    
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleInstrumentNotFound(InstrumentNotFoundException snfe) {
    }
}
