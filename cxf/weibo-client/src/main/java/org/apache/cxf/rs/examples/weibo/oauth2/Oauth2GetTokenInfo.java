package org.apache.cxf.rs.examples.weibo.oauth2;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.examples.Token;

public class Oauth2GetTokenInfo {

    public static void main(String[] args) throws IOException {

        String get_token_info = "https://api.weibo.com/oauth2/get_token_info";
        WebClient wc = WebClient.create(get_token_info);
        Response resp = wc.form(new Form().param("access_token", Token.TOKEN));
        if(resp.getStatus() == 200) {
            IOUtils.copy((InputStream) resp.getEntity(), System.out);
        }
    }

}
