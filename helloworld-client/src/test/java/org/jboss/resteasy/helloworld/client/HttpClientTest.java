package org.jboss.resteasy.helloworld.client;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class HttpClientTest {

    private static String XML_URL;
    private static String JSON_URL;

    private static final String XML_PROPERTY = "xmlUrl";
    private static final String JSON_PROPERTY = "jsonUrl";


    private static final String XML_RESPONSE = "<xml><result>Hello World!</result></xml>";
    private static final String JSON_RESPONSE = "{\"result\":\"Hello World!\"}";
    
    private static final String XML_URL_DEFAULT = "http://localhost:8080/helloworld/rest/xml";
    private static final String JSON_URL_DEFAULT = "http://localhost:8080/helloworld/rest/json";
    
    static HttpClient httpclient;

    @BeforeClass
    public static void beforeClass() {
        XML_URL = System.getProperty(XML_PROPERTY);
        JSON_URL = System.getProperty(JSON_PROPERTY);
        
        httpclient = new HttpClient();
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(60 * 1000);
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(60 * 1000);
    }
    
    @AfterClass
    public static void afterClass() {
    }
    
    @Test
    public void test() throws HttpException, IOException {
    	assertEquals("XML Response", XML_RESPONSE, runHttpRequest(httpclient, XML_URL_DEFAULT));
    	assertEquals("JSON Response", JSON_RESPONSE, runHttpRequest(httpclient, JSON_URL_DEFAULT));
    }
    
    @Test
    @Ignore("Not Ready to Run")
    public void testDefault() throws HttpException, IOException {
    	assertEquals("XML Response", XML_RESPONSE, runHttpRequest(httpclient, XML_URL));
    	assertEquals("JSON Response", JSON_RESPONSE, runHttpRequest(httpclient, JSON_URL));
    }
    
	private String runHttpRequest(HttpClient httpclient, String url) throws HttpException, IOException {
		
		String result = "";
	
		System.out.println("===============================================");
		System.out.println("URL: " + url);

		HttpMethod method = new GetMethod(url);
		int responseCode = httpclient.executeMethod(method);
		if (responseCode >= 200 && responseCode < 300) {
			String resp = method.getResponseBodyAsString();
			System.out.println("\n*** Response from Server ***\n");
			System.out.println(resp);
			result = resp;
		} else {
			throw new HttpException("HTTP " + responseCode + ", Rest request return error");
		}
		
		System.out.println();
		
		return result;
	}

}
