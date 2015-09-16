package org.jboss.resteasy.examples;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

public class JAXRSClient {
	
	static final String USERNAME = "testUser";
	static final String PASSWORD = "password1!";

	public static void main(String[] args) {
		

		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target("http://localhost:8080/sample_1/view/g1/1a");
		Response response = target.request()
//								.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML)
				.header(HttpHeaders.AUTHORIZATION, getBasicAuthentication())
								.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
								.get();
		String value = response.readEntity(String.class);
		System.out.println(value);
		response.close();
	}
	
	private static String getBasicAuthentication() {

        String token = USERNAME + ":" + PASSWORD ;
        String base64encoded = Base64.encodeBase64String(token.getBytes());
        return "BASIC " + base64encoded ;
}


}
