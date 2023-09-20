package com.shabushabu.javashop.shop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class Exercises {
	
	private static final String SHOP_ENV_FILE = "/container/shop/data/.env";
	public static final String TRACES_SENT = "TRACES_SENT";
	private static final int MIN_TRACES_EXPECTED = 180;
	
	private Properties m_props;
	private static final Exercises s_instance = new Exercises();
	
	protected Exercises() {
		Properties properties = new Properties();
	 
		try (FileInputStream inputStream = new FileInputStream(SHOP_ENV_FILE)) {
            properties.load(inputStream);
            System.out.println("Properties read: " + properties);
        } catch (IOException e) {
            System.err.println("Failed to read properties file: " + e.getMessage());
            return;
        }
		m_props = properties;
	}
	
	public static boolean checkExercise(int exercise ) {
		
		boolean bResult = false;
		
		switch(exercise) {
			case 2: 
				bResult = checkExercise2();
			break;
			
//			case3: 
//				
//			break;
//				
//			case 4: 
//				
//			break;
//				
//				
//			case 5: 
//				
//			break;
//				
//				
//			case 6: 
//				
//			break;
//				
//				
//			case 7: 
//				
//			break;
//				
//				
//			case 8: 
//				
//			break;
//				
//				
//			case 9: 
//				
//			break;
//				
//				
//			case 10: 
//				
//			break;
		}
		
		return bResult;
		
		
		
	}
	
	public static int getTracesSent() {
		return Integer.valueOf((String)s_instance.m_props.get(TRACES_SENT));
	};
	
	 public static void resetTracesSent() {
		 s_instance.m_props.setProperty(TRACES_SENT, "0");
	 }
	
	public static boolean incrementTracesSent() {
		Properties properties = s_instance.m_props;
		boolean result = false;
		
		
        String traces = (String) properties.get(TRACES_SENT);
        Integer iTraces = Integer.parseInt( traces );
            
        iTraces++;
       
        try {     
            FileOutputStream outputStream = new FileOutputStream(SHOP_ENV_FILE) ;
            properties.store(outputStream, null);
            System.out.println("Properties written: " + properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	public static boolean checkExercise3() {

		Properties properties = s_instance.m_props;
		boolean result = false;
		
        String traces = (String) properties.get(TRACES_SENT);
        Integer iTraces = Integer.parseInt( traces );
        
        if (iTraces != null && iTraces <= MIN_TRACES_EXPECTED ) {
        	result = true ; 
        }

		return result;
	}
	
	
	public static boolean checkExercise2() {
		boolean bResult = false;
		
		Properties properties = s_instance.m_props;
		 
		try (FileInputStream inputStream = new FileInputStream(SHOP_ENV_FILE)) {
            properties.load(inputStream);
            String token = (String) properties.get("SPLUNK_ACCESS_TOKEN");
            String realm = (String) properties.get("SPLUNK_REALM");
            
            if (token == null ||  realm == null ) {
            	return false;
            } else {
            
           	 try {
                    URL url = new URL("https://ingest." + realm + ".signalfx.com/v2/datapoint" ); 
                    
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("X-SF-Token", token);
                    conn.setDoOutput(true);
                   
                    String payload = "{\"counter\":[{\"metric\":\"jam_metric\",\"value\":\"999\", \"timestamp\":\"100000000\"}]}";

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
                        //System.out.println("Lambda function output:");
                       
                        if (response.toString().compareToIgnoreCase("OK") == 0 ) {
                        	System.out.println("Succesfully sent datapoint !!!!!");
                        	System.out.println("Succesfully sent datapoint !!!!!");
                        	System.out.println("Succesfully sent datapoint !!!!!");
                        	
                        	bResult = true;
                        }
                        System.out.println(response.toString());
                    }

                    // Close the connection
                    conn.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
           	 
           	 return bResult;
            	
            }
            
        } catch (IOException e) {
            System.err.println("Failed to read properties file: " + e.getMessage());
            return false;
        }
	}
}
