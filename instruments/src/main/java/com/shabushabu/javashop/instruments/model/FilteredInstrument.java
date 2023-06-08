package com.shabushabu.javashop.instruments.model;



import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;

import com.shabushabu.javashop.instruments.exceptions.InvalidLocaleException;


public class FilteredInstrument {
	
   private static final boolean  s_disabled = false; 
	
   private static Logger s_logger = LogManager.getLogger(FilteredInstrument.class);
	

	public Object filterInstruments( Object obj) throws InvalidLocaleException {
		
		
		if (s_disabled) {
			
			// See src/main/resources/application.properties ..
			s_logger.error("Trying to filter to disabled Region: Oregon");
			
			throw new InvalidLocaleException("Trying to filter to disabled Region: Oregon");
		}
		
		
		
		 /*RestTemplate restTemplate = new RestTemplate();

	        String uri = localhost:80; // or any other uri

	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

	        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
	        ResponseEntity<?> result =
	                restTemplate.exchange(uri, HttpMethod.GET, entity, returnClass);
	        return result.getBody();
	       */ 
	/*	
		  try {
	            HttpRequest request = HttpRequest
	                    .get("https://countriesnow.space/api/v0.1/countries")
	                    .connectTimeout(120000);
	            String res = request.body();
	            return new ResponseEntity<>(res, HttpStatus.OK);
	        }catch (Exception e){
	            e.printStackTrace();
	            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	*/
		
		// Execute Filter to Oregon ONLY data.
	    String url ="http://shop:8010";
		try {
			HttpClient client = HttpClient.newHttpClient();
			String theURL = url + "?name=Guest&location=Oregon";
		
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
			
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
			@SuppressWarnings("unused")
			String sResult = response.body().toString();
			return (Object)sResult;
		 
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
			
	}
}

