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
    	
    	if (userid.equalsIgnoreCase("C0000010")) {
    		throw new NoPermissionException("User does not have permissions for this opearation");
    	}
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