package com.shabushabu.javashop.shop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import com.shabushabu.javashop.shop.controllers.HomeController;

public class Exercises {
	
	private static final String SHOP_ENV_FILE = "/container/shop/data/.env";
	public static final String TRACES_SENT = "TRACES_SENT";
	private static final int MIN_TRACES_EXPECTED = 180;
	public static boolean exceptionThrownForUser = false;
	public static final int NUM_EXERCISES = 15;
	
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
		m_props.setProperty(TRACES_SENT, "0");
	}
	
	protected void finalize() throws Throwable {
        try {
        	resetTracesSent();
        	 try {     
                 FileOutputStream outputStream = new FileOutputStream(SHOP_ENV_FILE) ;
                 m_props.store(outputStream, null);
                 System.out.println("Properties written: " + m_props);
     		} catch (Exception e) {
     			e.printStackTrace();
     		}
        }
        catch (Throwable e) {
 
            throw e;
        }
        finally {
            // Calling finalize() of Object class
            super.finalize();
        }
    }
	
	public static boolean checkExercise(int exercise, String data, HomeController controller ) {
		
		boolean bResult = false;
		if ( null == data  ) {
			data = "";
		}
		switch(exercise) {
			case 1:
				// 1 is free always returns true !
				bResult = true;
			break;
			case 2: 
				bResult = checkExercise2(controller);
			break;
			
			case 3: 
				bResult = checkExercise3(controller);
			break;
				
			case 4: 
				bResult = !checkExercise4(controller);	
			break;
			
			case 5:
				bResult = checkExercise5(controller);
			break;
			
			case 6: 
				bResult =  (data.compareToIgnoreCase("Authorization")==0);
			break;
			case 7: 
				bResult =  (data.compareToIgnoreCase("Shop;Products")==0);
			break;
			case 8: 
				bResult = (data.compareToIgnoreCase("Not Authorized")==0);
			break;
			case 9: 
				bResult = (data.compareToIgnoreCase("getAllProducts")==0);
			break;
			case 10: 
				if (data.compareToIgnoreCase("getAllProducts")!=0) {
					if (data.startsWith("myCool") || data.startsWith("lookup")) {
						bResult = true;
					}
				}
				bResult = false;
				
			break;
			case 11: 
				bResult = checkExercise11(controller);
			break;
			
			case 12: 
				bResult = checkExercise3(controller);
			break;
			case 13:
				bResult = (data.compareToIgnoreCase("myCoolFunction234234234")==0);
			break;
			case 14: 
				 bResult = (data.startsWith("@SpanAttribute"));
			break;
			case 15: 
				bResult = checkExercise4(controller);
			break;
		}
		
		return bResult;
		
		
		
	}
	
	public static int getTracesSent() {
		return Integer.valueOf((String)s_instance.m_props.get(TRACES_SENT));
	};
	
	 protected void resetTracesSent() {
		m_props.setProperty(TRACES_SENT, "0");
	 }
	
	public static boolean incrementTracesSent() {
		Properties properties = s_instance.m_props;
		boolean result = false;
		
        String traces = (String) properties.get(TRACES_SENT);
        Integer iTraces = Integer.parseInt( traces );
            
        iTraces++;
        
        properties.setProperty(TRACES_SENT, iTraces.toString());
		
		return result;
		
	}
	
	public static boolean checkExercise11( HomeController controller) {
		
		Properties properties = s_instance.m_props;
		
		String bVal = (String) properties.getProperty("Annotated");
		
		return bVal.compareToIgnoreCase("true") == 0;
	}
	
	public static boolean checkExercise5(HomeController controller) {
		boolean result = false;
		// C0000010
		try {
			controller.checkIfRestricted("C0000010");
		} catch(Exception e) {
			result = true;	
		}
		
		return result;
	}
	
	public static boolean checkExercise4 (HomeController controller) {
		boolean result = false;
		
		System.out.println("LATECNY COLORADO MAX: " + HomeController.s_coloradoLatency);
		System.out.println("LATECNY UTAH MAX: " + HomeController.s_utahLatency);
		
		if ( HomeController.s_coloradoLatency < HomeController.s_utahLatency * 2 ) {
			result = true;
		}
		
		return result;
	}
	
	
	public static boolean checkExercise3(HomeController controller) {

		Properties properties = s_instance.m_props;
		boolean result = false;
		
        String traces = (String) properties.get(TRACES_SENT);
        Integer iTraces = Integer.parseInt( traces );
        
        if (iTraces != null && iTraces <= MIN_TRACES_EXPECTED ) {
        	result = true ; 
        }

		return result;
	}
	
	
	public static boolean checkExercise2(HomeController controller) {
		// Can I send a metric to O11y cloud using .env information ?
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
