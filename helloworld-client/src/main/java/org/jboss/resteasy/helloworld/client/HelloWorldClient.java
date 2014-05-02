package org.jboss.resteasy.helloworld.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class HelloWorldClient {
	
	static final String XML_URL_DEFAULT = "http://localhost:8080/helloworld/rest/xml";
    static final String JSON_URL_DEFAULT = "http://localhost:8080/helloworld/rest/json";

   public static void main(String[] args) throws Exception {
	   
	   System.out.println("===============================================");
       System.out.println("URL: " + XML_URL_DEFAULT);
       System.out.println("MediaType: " + MediaType.APPLICATION_XML_TYPE);
       
	   ClientRequest request = new ClientRequest(XML_URL_DEFAULT);
	   request.accept(MediaType.APPLICATION_XML_TYPE);
	   ClientResponse<String> response = request.get(String.class);
	   BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
	   System.out.println("\n*** Response from Server ***\n");
       String output = null;
	   while ((output = br.readLine()) != null) {
           System.out.println(output);
       }
	   
	   System.out.println();
	   System.out.println("===============================================");
       System.out.println("URL: " + JSON_URL_DEFAULT);
       System.out.println("MediaType: " + MediaType.APPLICATION_JSON_TYPE);
       
	   request = new ClientRequest(JSON_URL_DEFAULT);
	   request.accept(MediaType.APPLICATION_JSON_TYPE);
	   response = request.get(String.class);
	   br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
	   System.out.println("\n*** Response from Server ***\n");
	   while ((output = br.readLine()) != null) {
           System.out.println(output);
       }
   }

}
