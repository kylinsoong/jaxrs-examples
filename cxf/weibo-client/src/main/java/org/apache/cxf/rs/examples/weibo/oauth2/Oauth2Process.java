package org.apache.cxf.rs.examples.weibo.oauth2;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.examples.Token;

public class Oauth2Process {
    
    static String clientId = "3876415151";
    
    static String clientSecret = "5e4f21ac2bd6596689fe16d8215a24e5";
    
    static String redirectUri = "https://api.weibo.com/oauth2/default.html";
    
    public static void main(String[] args) throws IOException {

        String authorize = "https://api.weibo.com/oauth2/authorize";
        String access_token = "https://api.weibo.com/oauth2/access_token";
        
        WebClient wc = WebClient.create(authorize);
        wc.query("client_id", clientId);
        wc.query("redirect_uri", redirectUri);
        wc.query("forcelogin", true);
        
        Response resp = wc.get(); 
        if(resp.getStatus() == 200) {
            IOUtils.copy((InputStream) resp.getEntity(), System.out);
        } 
    }

}
