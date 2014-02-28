import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;


public class MyJSONObj 
{
	
	private GeoObj[] myGEOOBJ;
	


	private int counter=0;
	
	public MyJSONObj(String address)
	{
		// 
        SSLContext ctx;
		try {
			System.out.println("Connecting to: "+address);
			ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { new TrustAll() }, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
			URL url = new URL(address);
	        InputStream is = url.openStream();
        	JsonObject jsonObject = Json.createReader(is).readObject();
        	counter = Count(jsonObject);
        	myGEOOBJ = new GeoObj[counter+1];
        	for (int e=0;e<counter;e++)
        	{
        				JsonObject res = jsonObject.getJsonArray("results").getJsonObject(e);
        				myGEOOBJ[e] = new GeoObj();
        				try {
							myGEOOBJ[e].set_type(res.getString("_type"));
						} catch (NullPointerException e1) {
							myGEOOBJ[e].set_type("null");
							
						}
        				
        					try {
								myGEOOBJ[e].set_id(res.getInt("_id"));
							} catch (NullPointerException e1) {
								myGEOOBJ[e].set_id(0);
							}
        			
        					try {
								myGEOOBJ[e].setName(res.getString("name"));
							} catch (NullPointerException e1) {
			
								myGEOOBJ[e].setName("null");
							}
        			
        					try {
								myGEOOBJ[e].setType(res.getString("type"));
							} catch (NullPointerException e1) {
								myGEOOBJ[e].setType("null");
							}
        				
        	        	JsonObject innerJsonObject = res.getJsonObject("geo_position");
        	        	myGEOOBJ[e].setLatitude(innerJsonObject.get("latitude"));
        	        	myGEOOBJ[e].setLongitude(innerJsonObject.get("longitude"));    
        	}
		} catch (NoSuchAlgorithmException e1) 
		{
			System.out.println("Error: Immpossible to create the context.");
		} catch (KeyManagementException e) {
			System.out.println("Error: Immpossible to initialize the context.");
		} catch (MalformedURLException e1) {
			System.out.println("Error: check the URL.");
		} catch (IOException e1) {
			System.out.println("Error: IO Error impossible to open input stream");
		}

	}

	
	private int Count(JsonObject jsonObject)
	{
		Iterator<JsonValue> it = jsonObject.getJsonArray("results").iterator();
		int i = 0;
		while(it.hasNext()) {
		    i++;
		    it.next();
		}
		return i;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void printGeoOBj()
	{
    	for (int e=0;e<counter;e++)
    	{
	    	System.out.println(myGEOOBJ[e].get_type());
	    	System.out.println(myGEOOBJ[e].get_id());
	    	System.out.println(myGEOOBJ[e].getName());
	    	System.out.println(myGEOOBJ[e].getType());
	    	System.out.println(myGEOOBJ[e].getLatitude());
	    	System.out.println(myGEOOBJ[e].getLongitude());
    	}
	}
	
	 public void generateCsvFile(String sFileName)
	   {
	 try
	 {
	     FileWriter writer = new FileWriter(sFileName);
   	     writer.append("_type");
	     writer.append(',');
	     writer.append("_id");
	     writer.append(',');
	     writer.append("name");
	     writer.append(',');
	     writer.append("type");
	     writer.append(',');
	     writer.append("latitude");
	     writer.append(',');
	     writer.append("longitude");
	     writer.append('\n');
	     for (int e=0;e<counter;e++)
	    	{
		     writer.append(myGEOOBJ[e].get_type());
		       writer.append(',');
		     writer.append(Integer.toString(myGEOOBJ[e].get_id()));
		     writer.append(',');
		     writer.append(myGEOOBJ[e].getName());
		     writer.append(',');
		     writer.append(myGEOOBJ[e].getType());
		     writer.append(',');
		       if(myGEOOBJ[e].getLatitude()==null)
		    	   writer.append("null");
		       else
		    	   writer.append(myGEOOBJ[e].getLatitude().toString());
		     writer.append(',');
		     if(myGEOOBJ[e].getLongitude()==null)
		    	 writer.append("null");
		     else
		    	 writer.append(myGEOOBJ[e].getLongitude().toString());
		     writer.append('\n');
	    	}
	     writer.flush();
	     writer.close();
	 	}
		catch(IOException e)
		{
		   System.out.println("Error: impossibile to write the csv file");
		} 
 }
	
}
