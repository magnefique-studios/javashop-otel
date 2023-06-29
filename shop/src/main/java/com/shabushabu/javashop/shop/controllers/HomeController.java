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

import com.shabushabu.javashop.shop.services.ConductorsService;
import com.shabushabu.javashop.shop.services.InstrumentService;
import com.shabushabu.javashop.shop.services.ProductService;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private InstrumentService instrumentService;
    
    @Autowired
    private ConductorsService conductorsService;
   
    boolean bEnableConductors = true;
   
    
    @RequestMapping(value="/")
    public String getProductsAllLocations(Model model,  @RequestParam(value="conductors",required=false) String conductors, 
    													@RequestParam(value="name",required=false) String theName, 
    													@RequestParam(value="location", required=false) String theLocation,
    													@RequestParam(value="vipLevel", required=false) String vipLevel) {

     
		if (null == theName ) {
		
			theName = "Guest";
		}	
		
		if (null == theLocation ) {
			theLocation="California";
		}
		
		if (null == conductors ) {
			conductors = "false";
		}
		
		if (null == vipLevel) {
			vipLevel = "NONE";
		}
		
		User user = new User();
		user.setLocation(theLocation);
		user.setName(theName);
		model.addAttribute("user", user);
		
		
		model.addAttribute("products", productService.getProducts(theLocation));
	
		model.addAttribute("instruments", instrumentService.getInstruments(theLocation));
	/*
		if (bEnableConductors && conductors.compareToIgnoreCase("true") == 0 ) {
			 model.addAttribute("conductors", conductorsService.getConductorInstruments(theLocation, vipLevel ));
		} 
	*/	
		return "index";
    
    } 
    
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
    } 
    
    @RequestMapping("/healthcheck")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public String healthCheck() {
        return "HTTP Status OK (CODE 200)\n";
    }  
    
}
