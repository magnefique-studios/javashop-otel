
package com.shabushabu.javashop.shop.repo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.shabushabu.javashop.shop.services.dto.InstrumentDTO;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;





@Component
public class ConductorsRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConductorsRepo.class);

    @Value("${conductorsUri}")
    private String conductorsUri;

    
    @Bean 
    public RestTemplate restTemplate(RestTemplateBuilder builder){
    return builder.build();
    }
    
    @Autowired
    private RestTemplate restTemplate;
    
    

    @HystrixCommand(fallbackMethod = "instrumentsNotFound") 
    public Map<Long, InstrumentDTO> getinstrumentDTOs() { 
        LOGGER.info("getInstrument DTOS");
        ResponseEntity<List<InstrumentDTO>> instrumentsResponse =
                restTemplate.exchange(conductorsUri + "/conductors",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<InstrumentDTO>>() {
                        });
        List<InstrumentDTO> instrumentDTOs = instrumentsResponse.getBody();

        return instrumentDTOs.stream()
                .collect(Collectors.toMap(InstrumentDTO::getId, Function.identity()));
    }
    
    
    public Map<Long, InstrumentDTO> getConductorInstrumentsByLocationAndLevel(String location, String vipLevel) {
        LOGGER.info("getConductor Instrument  by location ");
        ResponseEntity<List<InstrumentDTO>> conductorsResponse =
                restTemplate.exchange(conductorsUri + "/conductors?" + "location=" + location + "&vipLevel=" + vipLevel, 
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<InstrumentDTO>>() {
                        });
       // List<InstrumentDTO> instrumentDTOs = conductorsResponse.getBody();

        return null;
    }

    public Map<Long, InstrumentDTO> instrumentsNotFound() {
        LOGGER.info("Instruments Empty NOT FOUND  *** FALLBACK ***");
        return Collections.emptyMap();
    }
}
