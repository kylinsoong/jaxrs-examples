package org.jboss.resteasy.helloworld.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class HelloWorldRestEasyClient {
	
	static final String XML_URL_DEFAULT = "http://localhost:8080/helloworld/rest/xml";
    static final String JSON_URL_DEFAULT = "http://localhost:8080/helloworld/rest/json";

   public static void main(String[] args) throws Exception {
	   
	   runRequest(XML_URL_DEFAULT, MediaType.APPLICATION_XML_TYPE);
	   
	   runRequest(JSON_URL_DEFAULT, MediaType.APPLICATION_JSON_TYPE);
   }
   
   private static void runRequest(String url, MediaType mediaType) {

       System.out.println("===============================================");
       System.out.println("URL: " + url);
       System.out.println("MediaType: " + mediaType.toString());

       try {
           // Using the RESTEasy libraries, initiate a client request
           // using the url as a parameter
           ClientRequest request = new ClientRequest(url);

           // Be sure to set the mediatype of the request
           request.accept(mediaType);

           // Request has been made, now let's get the response
           ClientResponse<String> response = request.get(String.class);

           // Check the HTTP status of the request
           // HTTP 200 indicates the request is OK
           if (response.getStatus() != 200) {
               throw new RuntimeException("Failed request with HTTP status: " + response.getStatus());
           }

           // We have a good response, let's now read it
           BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));

           // Loop over the br in order to print out the contents
           System.out.println("\n*** Response from Server ***\n");
           String output = null;
           while ((output = br.readLine()) != null) {
               System.out.println(output);
           }
       } catch (ClientProtocolException cpe) {
           System.err.println(cpe);
       } catch (IOException ioe) {
           System.err.println(ioe);
       } catch (Exception e) {
           System.err.println(e);
       }

       System.out.println();

   }

}
