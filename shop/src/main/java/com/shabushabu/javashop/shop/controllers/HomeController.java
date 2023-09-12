package com.shabushabu.javashop.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.shabushabu.javashop.shop.services.InstrumentService;
import com.shabushabu.javashop.shop.services.ProductService;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.naming.NoPermissionException;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private InstrumentService instrumentService;
    
    
    @RequestMapping(value="/")
    public String getProductsAllLocations(Model model,  
    													@RequestParam(value="name",required=false) String theName, 
    													@RequestParam(value="location", required=false) String theLocation,													
    													@RequestParam(value="userid", required=false) String userid) throws Exception {

    	
    	
		if (null == theName ) {
		
			theName = "Guest";
		}	
		
		if (null == theLocation ) {
			theLocation="California";
		}
		
		if (null == userid) {
			userid="X0000";
		}
		
		allParameters(theName, theLocation, userid);
		
		User user = new User();
		user.setLocation(theLocation);
		user.setName(theName);
		model.addAttribute("user", user);
		
		
		model.addAttribute("products", productService.getProducts(theLocation));
	
		model.addAttribute("instruments", instrumentService.getInstruments(theLocation));

		
		return "index";
    
    } 
    
    @WithSpan
    public void allParameters( @SpanAttribute("name") String name, @SpanAttribute("location") String location, 
    		 @SpanAttribute("userid")String userid ) throws NoPermissionException {
    	
    	// 
    	System.out.println("userid = " + userid);
    	
    	//if (userid.equalsIgnoreCase("C0000010")) {
    	//	throw new NoPermissionException("User does not have permissions for this opearation");
    	//}
    	checkIfRestricted(userid);
    }
    
    public String checkIfRestricted(String userId) {
   	 try {
            // Create a URL object with the API Gateway URL
            URL url = new URL("https://mofi2flod5cpeismodr7eonuiu0gkoli.lambda-url.us-west-1.on.aws/?userId=" + userId); 

            // Open a connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // Set the request type to POST
            conn.setRequestMethod("POST");
            
            // Set Content-Type to application/json
            conn.setRequestProperty("Content-Type", "application/json");

            // Enable sending data
            conn.setDoOutput(true);
            
            // Create JSON payload (e.g., {"user_id": "user1"})
            String payload = "{\"userId\":\"user1\"}";

            // Write the payload to the request body
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Lambda function output:");
                System.out.println(response.toString());
            }

            // Close the connection
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
   	 
   	 return "";
    }
    
    /*
    @RequestMapping(value="/conductors")
    public String getProductsConductorsAllLocations(Model model, @RequestParam(value="name",required=false) String theName, 
			@RequestParam(value="location", required=false) String theLocation,
			@RequestParam(value="vipLevel", required=false) String vipLevel) {


			if (null == theName ) {
			
			theName = "Guest";
			}	
			
			if (null == theLocation ) {
			theLocation="California";
			}
			
			User user = new User();
			user.setLocation(theLocation);
			user.setName(theName);
			model.addAttribute("user", user);
			
			
			//model.addAttribute("products", productService.getProducts(theLocation));
			
			//model.addAttribute("instruments", instrumentService.getInstruments(theLocation));
			
			if (null == vipLevel ) {
				vipLevel = "NONE";
			}
			if (bEnableConductors) {
				System.out.println(" WE ARE SENDING TRAFFIC TO CONDUCTORS !!!!!! -- ONE TIME");
				model.addAttribute("conductors", conductorsService.getConductorInstruments(theLocation, vipLevel ));
			}
			return "index";
    } */
    
    @RequestMapping("/healthcheck")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public String healthCheck() {
        return "HTTP Status OK (CODE 200)\n";
    }  
    
}