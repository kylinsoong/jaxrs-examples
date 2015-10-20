package org.apache.cxf.rs.examples.weibo.read;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.examples.Token;

/**
 *  http://open.weibo.com/wiki/2/statuses/public_timeline
 * 
 * @author kylin
 *
 */
public class WeiboStatusesPublicTimeline {
    
    static String public_timeline = "https://api.weibo.com/2/statuses/public_timeline.json";

    public static void main(String[] args) throws IOException {

        WebClient wc = WebClient.create(public_timeline);
        wc.query("access_token", Token.TOKEN);
        wc.query("count", 50);
        wc.query("page", 1);
        wc.query("base_app", 0);
        
        Response resp = wc.get(); 
        if(resp.getStatus() == 200) {
            IOUtils.copy((InputStream) resp.getEntity(), System.out);
        }        
    }

}
