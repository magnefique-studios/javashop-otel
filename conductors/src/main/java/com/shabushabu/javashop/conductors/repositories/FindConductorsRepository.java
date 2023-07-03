package com.shabushabu.javashop.conductors.repositories;

import java.util.List;

import com.shabushabu.javashop.conductors.model.Instrument;

public interface FindConductorsRepository {
	 Object findConductorInstruments( String location);
	 Object findVipInstruments(Object obj, String vipLevel);
	 Object findInstrumentsAll();
	 Instrument findInstrumentByID(String id);
	Object findConductorInstrumentsByVipLevel(Object instruments, String location, String vipLevel);
}


