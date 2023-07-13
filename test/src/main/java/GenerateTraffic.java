

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class GenerateTraffic {

	
	public static void main(String[] args) {
                try {	
		  Thread.sleep(45000);
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
				String theURL = url + "?name=Guest&location=Utah";
			
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
				String theURL =  url + "?name=Guest&location=California";
			
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
				
				@SuppressWarnings("unused")
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
				//String sResult = response.body().toString();
			 
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		/*
		System.out.println("Oregon Location");
		for (int j=0;  j<80; j++) {
			try {
		
				HttpClient client = HttpClient.newHttpClient();
				String theURL = url + "?name=Guest&location=Oregon";
			
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
				
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
				@SuppressWarnings("unused")
				String sResult = response.body().toString();
			 
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		*/
		
		
		String location = "";
		String vipLevel = "";
		System.out.println("Test Locations and VipLevels");
		
			for (int zz=0; zz<=16; zz++ ) {
				
				switch (zz) {
					case 0:
					case 1:
					case 2:
					case 3:
						 location = "us";
					break;
					
					
					case 4:
					case 5:
					case 6:
					case 7:
						 location = "japan";
					break;
					
					case 8:
					case 9:
					case 10:
					case 11:
						 location = "italy";
					break;
					
					default:
						 location = "canada";
					break;
				}
				
				for (int jjj=0; jjj<=2; jjj++) {
					
					switch (jjj) {
					case 0:
						 vipLevel = "silver";
					break;
					
					case 1:
						 vipLevel = "gold";
					break;
					
					case 2:
						 vipLevel = "platinum";
					break;
					
					}
					
					for (int zzz=0; zzz<=10; zzz++) {
						try {
							
							System.out.println("conductors = true " + "Location= " + location + " vipLevel= "  + vipLevel);
					
							HttpClient client = HttpClient.newHttpClient();
							String theURL = url + "?conductors=true&name=Guest&location=" + location + "&vipLevel=" + vipLevel;
						
							HttpRequest request = HttpRequest.newBuilder().uri(URI.create(theURL)).build();
							
							HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
						
							@SuppressWarnings("unused")
							String sResult = response.body().toString();
						 
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		
		
	
		
		
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);
		
		System.out.println("CALIFORNIA DURATION IS: " + duration /  1000000000);

		if (!chicago) {	
			/* startTime = System.nanoTime();
		
			System.out.println("Colorado Location");
		
			for (int l=0; l<40; l++) {
				try {
					HttpClient client = HttpClient.newHttpClient();
					String theURL =  url + "?name=Guest&location=Colorado";
			
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
	        */
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
