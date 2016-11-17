package org.ksoong.weibo4j;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class Weibo {
    
    final static String PROVINCE = "province";
    final static String CAPITAL = "capital";
    static final String ACCESS_TOKEN = "access_token";
    static final String API_BASE =  "https://api.weibo.com/2";

    protected String access_token;
    
    protected Weibo(String access_token) {
        this.access_token = access_token;
    }
    
    String access_token() {
        return this.access_token;
    }
    
    void handleResponse(Response resp, WebClient wc) {
        System.out.println(resp.getStatus() + " - " + resp.getStatusInfo().getReasonPhrase());
        wc.close();
    }
}
