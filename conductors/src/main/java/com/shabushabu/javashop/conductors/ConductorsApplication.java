package com.shabushabu.javashop.conductors;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.shabushabu.javashop.conductors.model.Instrument;
import com.shabushabu.javashop.conductors.repositories.InstrumentRepository;

@SpringBootApplication
public class ConductorsApplication {
	 
	@Autowired private InstrumentRepository repository; 
	 private final Logger logger = LoggerFactory.getLogger(ConductorsApplication.class);
	 
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        Iterable<Instrument> instruments = this.repository.findAll(); 
        logger.error("Number of instruments: " + ((Collection<?>) instruments).size());
        logger.error("Number of instruments: " + ((Collection<?>) instruments).size());
        logger.info("Number of instruments: " + ((Collection<?>) instruments).size());   
    }
	    
    public static void main(String[] args) {
        SpringApplication.run( ConductorsApplication.class, args);
    }
}
