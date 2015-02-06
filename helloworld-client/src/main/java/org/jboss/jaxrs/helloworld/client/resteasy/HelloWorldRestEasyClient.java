package org.jboss.jaxrs.helloworld.client.resteasy;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class HelloWorldRestEasyClient {
	
	static final String XML_URL_DEFAULT = "http://localhost:8080/helloworld/rest/xml";
    static final String JSON_URL_DEFAULT = "http://localhost:8080/helloworld/rest/json";

   public static void main(String[] args) throws Exception {
	   
	   runRequest(XML_URL_DEFAULT, MediaType.APPLICATION_XML_TYPE);
	   
	   runRequest(JSON_URL_DEFAULT, MediaType.APPLICATION_JSON_TYPE);
   }
   
   private static void runRequest(String url, MediaType type) {

       System.out.println("===============================================");
       System.out.println("URL: " + url);
       System.out.println("MediaType: " + type.toString());
       
       ResteasyClient client = new ResteasyClientBuilder().build();
       ResteasyWebTarget target = client.target(url);
       Response response = target.request().accept(type).get();
       
       if (response.getStatus() != 200) {
           throw new RuntimeException("Failed request with HTTP status: " + response.getStatus());
       }
       
       response.bufferEntity();
       
       Object obj = response.readEntity(String.class);

       System.out.println(obj);

   }

}
