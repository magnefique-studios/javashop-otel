package com.shabushabu.javashop.conductors;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shabushabu.javashop.conductors.model.Instrument;

import io.opentelemetry.instrumentation.annotations.WithSpan;

import java.util.List;

public class VipTestData {

	public static final VipTestData s_istance = new VipTestData();
	
	ArrayList<Instrument> m_instrumentsUsa = new ArrayList<Instrument>(); 
	ArrayList<Instrument> m_instrumentsJapan = new ArrayList<Instrument>(); 
	ArrayList<Instrument> m_instrumentsCanada = new ArrayList<Instrument>(); 
	ArrayList<Instrument> m_instrumentsItaly = new ArrayList<Instrument>(); 
	
	@WithSpan()
	public ResponseEntity<List<Instrument>> getInstrumentsByLocationAndLevel(String location, String vipLevel) throws Exception {
		ArrayList<Instrument> results = new ArrayList<Instrument>();
		
		
		
		HttpStatus httpStatus = HttpStatus.OK;
		System.out.println("In VipTestData::getInstrumentsByLocationAndLevel");
		if (location.contains("us")) {
			results = m_instrumentsUsa;
			
		} else if (location.contains("japan") ) {
			results = m_instrumentsJapan;
		}else if (location.contains("canada") ) {
			results = m_instrumentsCanada;
			
			if (vipLevel.contains("gold")) {
				System.out.println(" THROWING EXCEPTION ");
				// Remove anything over 10 items, this is MAX GOLD orders for Canada
				//results.get(100);
				
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else if (location.contains("italy") ) {
			
			results = m_instrumentsItaly;
		}
		
		return new ResponseEntity<List<Instrument>> (results, httpStatus );
	}
	
	public ResponseEntity<List<Instrument>> getInstrumentsByLocation(String location) throws Exception {
		ArrayList<Instrument> results = new ArrayList<Instrument>();

		HttpStatus httpStatus = HttpStatus.OK;
		
		if (location.contains("us")) {
			results = m_instrumentsUsa;
		} else if (location.contains("japan") ) {
			results = m_instrumentsJapan;
		}else if (location.contains("canada") ) {
			results = m_instrumentsCanada;
		}else if (location.contains("italy") ) {
			results = m_instrumentsItaly;
		}
		
		return new ResponseEntity<List<Instrument>> (results, httpStatus );
	}
	protected VipTestData() {
		// TODO Auto-generated constructor stub
	
		// usa sample data
		m_instrumentsUsa.add(new Instrument("Guitar", "Small Guitar", "55.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		m_instrumentsUsa.add(new Instrument("Trombone", "Small Trombone", "155.00", "wind", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsUsa.add(new Instrument("Trumpet", "Small Trumpet", "355.00", "wind", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsUsa.add(new Instrument("Keyboard", "digital", "5345.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsUsa.add(new Instrument("Lute", "Small Guitar", "255.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsUsa.add(new Instrument("Drum", "Small Drum", "55.00", "percussion", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		// japan sample data
		
		m_instrumentsJapan.add(new Instrument("Guitar", "Small Guitar", "55.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		m_instrumentsJapan.add(new Instrument("Trombone", "Small Trombone", "155.00", "wind", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsJapan.add(new Instrument("Trumpet", "Small Trumpet", "355.00", "wind", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsJapan.add(new Instrument("Keyboard", "digital", "5345.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));

		m_instrumentsJapan.add(new Instrument("Lute", "Small Guitar", "255.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsJapan.add(new Instrument("Drum", "Small Drum", "55.00", "percussion", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		
		// canada sample data
		
		m_instrumentsCanada.add(new Instrument("Guitar", "Small Guitar", "55.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		m_instrumentsCanada.add(new Instrument("Trombone", "Small Trombone", "155.00", "wind", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsCanada.add(new Instrument("Trumpet", "Small Trumpet", "355.00", "wind", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsCanada.add(new Instrument("Keyboard", "digital", "5345.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsCanada.add(new Instrument("Lute", "Small Guitar", "255.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsCanada.add(new Instrument("Drum", "Small Drum", "55.00", "percussion", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		
	    // Italy sample data
		
		m_instrumentsItaly.add(new Instrument("Guitar", "Small Guitar", "55.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		m_instrumentsItaly.add(new Instrument("Trombone", "Small Trombone", "155.00", "wind", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsItaly.add(new Instrument("Trumpet", "Small Trumpet", "355.00", "wind", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsItaly.add(new Instrument("Keyboard", "digital", "5345.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsItaly.add(new Instrument("Lute", "Small Guitar", "255.00", "string", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
		
		m_instrumentsItaly.add(new Instrument("Drum", "Small Drum", "55.00", "percussion", "LikeNew", "TheOrg", "Location", "http://instruments:8010", "today" ));
	
	}
	
	

}
