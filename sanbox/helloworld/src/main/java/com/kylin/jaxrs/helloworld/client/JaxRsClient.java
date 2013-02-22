package com.kylin.jaxrs.helloworld.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

/**
 * JAXRS Client use RestEasy Client API
 * 
 * @author kylin
 *
 */
public class JaxRsClient {
	
	private static String XML_URL = "http://localhost:8080/jaxrs-helloworld/rest/xml";
    private static String JSON_URL = "http://localhost:8080/jaxrs-helloworld/rest/json";
	
	public void test() {
		
		runRequest(XML_URL, MediaType.APPLICATION_XML_TYPE);
		
		System.out.println("\n\n");
		
		runRequest(JSON_URL, MediaType.APPLICATION_JSON_TYPE);
	}
	
	private void runRequest(String url, MediaType mediaType) {
       
		String result = null;

        System.out.println("===============================================");
        System.out.println("URL: " + url);
        System.out.println("MediaType: " + mediaType.toString());

        try {
            // Using the RESTEasy libraries, initiate a client request using the url as a parameter
            ClientRequest request = new ClientRequest(url);

            // Be sure to set the mediatype of the request
            request.accept(mediaType);

            // Request has been made, now let's get the response
            ClientResponse<String> response = request.get(String.class);

            // Check the HTTP status of the request, HTTP 200 indicates the request is OK
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

        System.out.println("\n===============================================");

    }

	public static void main(String[] args) {

		new JaxRsClient().test();
	}

}
