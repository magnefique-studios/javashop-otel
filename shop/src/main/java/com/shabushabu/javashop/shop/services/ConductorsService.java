package com.shabushabu.javashop.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.shabushabu.javashop.shop.model.Product;
import com.shabushabu.javashop.shop.model.Instrument;
import com.shabushabu.javashop.shop.repo.ConductorsRepo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ConductorsService {


    @Autowired
    private ConductorsRepo conductorsRepo;

    public List<Instrument> getConductorInstruments(String location, String vipLevel) {
         conductorsRepo.getConductorInstrumentsByLocationAndLevel(location, vipLevel);
        
         ArrayList<Instrument> results = new ArrayList<Instrument>(10);
         return results;
        
        /*
        return instrumentsDTO.values().stream()
                .map(instrumentDTO -> {
                	  try {
  						return new Instrument().buildForLocale(instrumentDTO.getId(),  
  								instrumentDTO.getTitle(), instrumentDTO.getPrice(), instrumentDTO.getInstrumentType(),
  								instrumentDTO.getCondition(), instrumentDTO.getSellerType(), instrumentDTO.getPublishedDate());
  					} catch (InvalidLocaleException e) {
  						
  						e.printStackTrace();
  						return  new Instrument().buildIt(instrumentDTO.getId(),   instrumentDTO.getPrice(), instrumentDTO.getInstrumentType(),
  								instrumentDTO.getCondition(), instrumentDTO.getSellerType(), instrumentDTO.getPublishedDate());	
  					}
                   })
                  .collect(Collectors.toList());
                  */
    }

    public List<Product> productsNotFound() {
        return Collections.emptyList();
    }
}
