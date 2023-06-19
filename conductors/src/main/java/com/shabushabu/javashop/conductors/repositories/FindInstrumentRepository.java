package com.shabushabu.javashop.conductors.repositories;

import java.util.List;

import com.shabushabu.javashop.conductors.model.Instrument;

public interface FindInstrumentRepository {
	 Object findInstrumentsOregon();
	 Object findInstrumentsAll();
	 Object findInstruments();
	 Instrument findInstrumentByID(String id);
}


