package com.shabushabu.javashop.conductors.repositories;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shabushabu.javashop.conductors.model.Instrument;

public interface FindConductorsRepository {
	 ResponseEntity<List<Instrument>> findConductorInstruments( String location) throws Exception;
	 Object findInstrumentsAll();
	 Instrument findInstrumentByID(String id);
	 ResponseEntity<List<Instrument>> findConductorInstrumentsByVipLevel(Object instruments, String location, String vipLevel) throws Exception;
}


