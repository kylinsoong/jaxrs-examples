package org.jboss.resteasy.helloworld.client;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class HelloWorldHttpClient {
	
	static final String XML_URL_DEFAULT = "http://localhost:8080/helloworld/rest/xml";
    static final String JSON_URL_DEFAULT = "http://localhost:8080/helloworld/rest/json";

	public static void main(String[] args) throws HttpException, IOException {
		
		HttpClient httpclient = new HttpClient();
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(60 * 1000);
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(60 * 1000);
		
        runHttpRequest(httpclient, XML_URL_DEFAULT);
        runHttpRequest(httpclient, JSON_URL_DEFAULT);
		
	}

	private static void runHttpRequest(HttpClient httpclient, String url) throws HttpException, IOException {
		
		System.out.println("===============================================");
		System.out.println("URL: " + url);

		HttpMethod method = new GetMethod(url);
		int responseCode = httpclient.executeMethod(method);
		if (responseCode >= 200 && responseCode < 300) {
			String resp = method.getResponseBodyAsString();
			System.out.println("\n*** Response from Server ***\n");
			System.out.println(resp);
		} else {
			throw new HttpException("HTTP " + responseCode + ", Rest request return error");
		}
		
		System.out.println();
	}

}
