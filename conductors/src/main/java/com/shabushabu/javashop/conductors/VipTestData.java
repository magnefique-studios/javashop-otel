package com.shabushabu.javashop.conductors;

import java.util.ArrayList;
import com.shabushabu.javashop.conductors.model.Instrument;
public class VipTestData {

	public static final VipTestData s_istance = new VipTestData();
	
	ArrayList<Instrument> m_instrumentsUsa = new ArrayList<Instrument>(); 
	ArrayList<Instrument> m_instrumentsJapan = new ArrayList<Instrument>(); 
	ArrayList<Instrument> m_instrumentsCanada = new ArrayList<Instrument>(); 
	ArrayList<Instrument> m_instrumentsItaly = new ArrayList<Instrument>(); 
	
	
	public ArrayList<Instrument> getInstrumentsByLocationAndLevel(String location, String vipLevel) {
		ArrayList<Instrument> results = new ArrayList<Instrument>();
		
		if (location.contains("us")) {
			results = m_instrumentsUsa;
			
		} else if (location.contains("Japan") ) {
			results = m_instrumentsJapan;
		}else if (location.contains("Canada") ) {
			results = m_instrumentsCanada;
			
			if (vipLevel.contains("gold")) {
				// Remove anything over 10 items, this is MAX GOLD orders for Canada
				results.remove(10);
			}
		}else if (location.contains("Italy") ) {
			
			results = m_instrumentsItaly;
		}
		
		return results;
	}
	
	public ArrayList<Instrument> getInstrumentsByLocation(String location) {
		ArrayList<Instrument> results = new ArrayList<Instrument>();
		
		if (location.contains("us")) {
			results = m_instrumentsUsa;
		} else if (location.contains("Japan") ) {
			results = m_instrumentsJapan;
		}else if (location.contains("Canada") ) {
			results = m_instrumentsCanada;
		}else if (location.contains("Italy") ) {
			results = m_instrumentsItaly;
		}
		
		return results;
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
