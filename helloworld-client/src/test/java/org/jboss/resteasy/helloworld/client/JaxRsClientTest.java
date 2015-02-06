package org.jboss.resteasy.helloworld.client;

import static org.junit.Assert.*;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit4 Test class which makes a request to the RESTful helloworld-rs web service.
 * 
 * 
 */
@Ignore
public class JaxRsClientTest {
    /**
     * Request URLs pulled from system properties in pom.xml
     */
    private static String XML_URL;
    private static String JSON_URL;

    /**
     * Property names used to pull values from system properties in pom.xml
     */
    private static final String XML_PROPERTY = "xmlUrl";
    private static final String JSON_PROPERTY = "jsonUrl";

    /**
     * Responses of the RESTful web service
     */
    private static final String XML_RESPONSE = "<xml><result>Hello World!</result></xml>";
    private static final String JSON_RESPONSE = "{\"result\":\"Hello World!\"}";

    /**
     * Method executes BEFORE the test method. Values are read from system properties that can be modified in the pom.xml.
     */
    @BeforeClass
    public static void beforeClass() {
        JaxRsClientTest.XML_URL = System.getProperty(JaxRsClientTest.XML_PROPERTY);
        JaxRsClientTest.JSON_URL = System.getProperty(JaxRsClientTest.JSON_PROPERTY);
    }

    /**
     * Test method which executes the runRequest method that calls the RESTful helloworld-rs web service.
     */
    @Test
    public void test() {
        assertEquals("XML Response", JaxRsClientTest.XML_RESPONSE, runRequest(JaxRsClientTest.XML_URL, MediaType.APPLICATION_XML_TYPE));

        assertEquals("JSON Response", JaxRsClientTest.JSON_RESPONSE, runRequest(JaxRsClientTest.JSON_URL, MediaType.APPLICATION_JSON_TYPE));
    }
    
    private static final String XML_URL_DEFAULT = "http://localhost:8080/helloworld/rest/xml";
    private static final String JSON_URL_DEFAULT = "http://localhost:8080/helloworld/rest/json";
    
    @Test
    @Ignore("Not Ready to Run")
    public void testDefault() {
    	assertEquals("XML Response", JaxRsClientTest.XML_RESPONSE, runRequest(XML_URL_DEFAULT, MediaType.APPLICATION_XML_TYPE));
    	assertEquals("JSON Response", JaxRsClientTest.JSON_RESPONSE, runRequest(JSON_URL_DEFAULT, MediaType.APPLICATION_JSON_TYPE));
    }

    /**
     * The purpose of this method is to run the external REST request.
     * 
     * @param url The url of the RESTful service
     * @param mediaType The mediatype of the RESTful service
     */
    private String runRequest(String url, MediaType type) {
        String result = null;

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
        
        result = response.readEntity(String.class);

        return result;
    }

}
