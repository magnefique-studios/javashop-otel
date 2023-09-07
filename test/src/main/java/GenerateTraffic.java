

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class GenerateTraffic {

	
	public static void main(String[] args) {
                try {	
		  Thread.sleep(120000);
		}catch(Exception e){
		}
		  
	        String url ="http://shop:8010";
		boolean chicago = false;
		
		if (null != args && args.length >0 ) {
				chicago = args[0].equalsIgnoreCase("-chicago");
		}
		
		
	
		System.out.println("Utah Location");
		for (int j=0; j<40; j++) {
			try {
		
				HttpClient client = HttpClient.newHttpClient();
				String theURL = url + "?name=Guest&location=Utah&userid=U" + "00000" + j;
			
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
				
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
				@SuppressWarnings("unused")
				String sResult = response.body().toString();
			 
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("California Location");
		long startTime = System.nanoTime();
	
		
		for (int k=0; k<40; k++) {
				
			try {
			
				HttpClient client = HttpClient.newHttpClient();
				String theURL =  url + "?name=Guest&location=California&userid=C" + "00000" + k;
			
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
				
				@SuppressWarnings("unused")
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
				//String sResult = response.body().toString();
			 
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Oregon Location");
		for (int j=0;  j<30; j++) {
			try {
		
				HttpClient client = HttpClient.newHttpClient();
				String theURL = url + "?name=Guest&location=Oregon&userid=O" + "00000" + j;
			
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
				
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
				@SuppressWarnings("unused")
				String sResult = response.body().toString();
			 
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
		
		
		
	
		
		
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);
		
		System.out.println("CALIFORNIA DURATION IS: " + duration /  1000000000);

		if (!chicago) {	
			 startTime = System.nanoTime();
		
			System.out.println("Colorado Location");
		
			for (int l=0; l<40; l++) {
				try {
					HttpClient client = HttpClient.newHttpClient();
					String theURL =  url + "?name=Guest&location=Colorado&userid=CC" + "00000" + l;
			
					HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
				
					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
					String sResult = response.body().toString();
			 
				} catch(Exception e) {
					e.printStackTrace();
				}
			
			}
		
		
			endTime = System.nanoTime();

			duration = (endTime - startTime);
		
			System.out.println("COLORADO DURATION IS: " + duration/ 1000000000);
	        
		} else {
			startTime = System.nanoTime();
			
			System.out.println("Chicago Location");
			
			for (int l=0; l<2; l++) {
				try {
			
					HttpClient client = HttpClient.newHttpClient();
					String theURL =  url + "?name=Guest&location=Chicago";
				
					HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
					
					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				
					String sResult = response.body().toString();
				 
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			 endTime = System.nanoTime();
	
			 duration = (endTime - startTime);
			
			System.out.println("CHICAGO DURATION IS: " + duration/ 1000000000);
			
			
	     }
	}
}