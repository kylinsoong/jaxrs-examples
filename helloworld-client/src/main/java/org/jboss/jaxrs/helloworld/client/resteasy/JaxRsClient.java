package org.jboss.jaxrs.helloworld.client.resteasy;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

public class JaxRsClient {

    public static void main(String[] args) {

        Client client = ClientBuilder.newBuilder().build();
        String authString = getBasicAuthentication();
        WebTarget target = client.target("http://localhost:8080/helloworld/rest/xml");
        Response response = target.request()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML)
                .header(HttpHeaders.AUTHORIZATION, authString)
                .get();
        if(response.getStatus() == 200){
            System.out.println(response.readEntity(String.class));
        } else {
            System.out.println(response.getStatus() + ", " + response.getStatusInfo());
        }
        response.close();
    }
    
   static String getBasicAuthentication() {
        
        String token = "restUser:password1!" ;
        String base64encoded = Base64.encodeBase64String(token.getBytes());
        return "Basic " + base64encoded ;
    }

}
