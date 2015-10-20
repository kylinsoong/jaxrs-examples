package org.apache.cxf.rs.examples;

import java.net.MalformedURLException;
import java.util.Collections;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.security.oauth.client.OAuthClientUtils;

public class CXFRestfulClient {
    
    static {
        System.setProperty("http.proxyHost", "squid.apac.redhat.com");
        System.setProperty("http.proxyPort", "3128");
    }
    
    static String consumerKey = "nHhsL5Dm5Zi77eqWDDcQ";
    static String consumerSecret = "VovoE7R5RJOI9GSAXTx25yppfDJ6y5SUKPy2vlrVCDM";
    static String accessToken = "209490245-cUtcom3nEnV53mYZfhWegZpnm90nmPds0icguwzL";
    static String accessSecret = "WKIYjXmplrdssy382iJnoNzd0vVDCa13nCWywKzKrE";

    public static void main(String[] args) {

        invoke("https://api.twitter.com/1.1/statuses/user_timeline.json", "GET");
        
    }
    
    public static Object invoke(String endpoint, String httpMethod) {
        
        System.out.println("invoke: " + endpoint);
        
        Bus bus = BusFactory.getThreadDefaultBus();
        
        WebClient client = createWebClient(endpoint, bus);
        client.header("User-Agent", Collections.singletonList("Teiid Server").toArray());
        client.header("Content-Type", Collections.singletonList("text/xml; charset=utf-8").toArray());
        client.header("Authorization", getAuthorizationHeader(endpoint, httpMethod));
        
        javax.ws.rs.core.Response response = client.invoke(httpMethod, null);
        
        System.out.println("=========== Print Response===========");
        System.out.println("Status: " + response.getStatus());
        System.out.println("Metadata: " + response.getMetadata());
        System.out.println("Entity:" + response.getEntity());
        
        client.close();
        
        return null;
    }
    
    private static String getAuthorizationHeader(String resourceURI, String httpMethod) {
        OAuthClientUtils.Consumer consumer = new OAuthClientUtils.Consumer(consumerKey, consumerSecret);
        OAuthClientUtils.Token token = new OAuthClientUtils.Token(accessToken, accessSecret);
        return OAuthClientUtils.createAuthorizationHeader(consumer, token, httpMethod, resourceURI);        
    }

    private static WebClient createWebClient(String baseAddress, Bus bus) {
        
        JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
        bean.setBus(bus);
        bean.setAddress(baseAddress);
        return bean.createWebClient();
    } 

}
