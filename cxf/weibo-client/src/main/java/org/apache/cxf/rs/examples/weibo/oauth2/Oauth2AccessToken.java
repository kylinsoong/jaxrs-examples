package org.apache.cxf.rs.examples.weibo.oauth2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.examples.Token;

public class Oauth2AccessToken {
    
    static String clientId = "3876415151";
    
    static String clientSecret = "5e4f21ac2bd6596689fe16d8215a24e5";
    
    static String redirectUri = "https://api.weibo.com/oauth2/default.html";
    
    public static void main(String[] args) throws IOException {
        
        Scanner in = new Scanner(System.in);

        String access_token = "https://api.weibo.com/oauth2/access_token";
        
        System.out.println("Open your broswer, access below URL to execute authorization:");
        System.out.println("https://api.weibo.com/oauth2/authorize?client_id=" + clientId + "&response_type=code&redirect_uri=" + redirectUri + "&forcelogin=true");
        
        System.out.println("Input authorization code");
//        String input = in.nextLine();
//        input = input.trim();
//        
        // Get access_token
        WebClient wc = WebClient.create(access_token);
//        wc.query("client_id", clientId);
//        wc.query("client_secret", clientSecret);
//        wc.query("grant_type", "authorization_code");
//        wc.query("code", "66e82f3790648c709b7267726d4963a5");
//        wc.query("redirect_uri", redirectUri);
        
        Response resp = wc.form(new Form().param("client_id", clientId).param("client_secret", clientSecret).param("grant_type", "authorization_code").param("code", "66e82f3790648c709b7267726d4963a5").param("redirect_uri", redirectUri));
        if(resp.getStatus() == 200) {
            IOUtils.copy((InputStream) resp.getEntity(), System.out);
        } else {
            IOUtils.copy((InputStream) resp.getEntity(), System.out);
        }

        
    }

}
