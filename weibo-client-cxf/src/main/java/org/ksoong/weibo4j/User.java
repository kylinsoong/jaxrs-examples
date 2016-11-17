package org.ksoong.weibo4j;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class User extends Weibo {
    
    final static String UID = "uid";
    final static String UIDS = "uids";
    final static String SCREEN_NAME = "screen_name";
    final static String DOMAIN = "domain";
    final static String USER_SHOW = API_BASE +  "/users/show.json";
    final static String USER_DOMAIN_SHOW = API_BASE +  "/users/domain_show.json";
    final static String USER_COUNTS = API_BASE +  "/users/counts.json";

    public User(String access_token) {
        super(access_token);
    }
    
    
    public String show(String uid) throws IOException {
        WebClient wc = WebClient.create(USER_SHOW);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(UID, uid)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/users/show
     * @param uid
     * @param screen_name
     * @return
     * @throws IOException
     */
    public String show(String uid, String screen_name) throws IOException {
        WebClient wc = WebClient.create(USER_SHOW);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(UID, uid)
                .query(SCREEN_NAME, screen_name)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/users/domain_show
     * @param domain
     * @return
     * @throws IOException
     */
    public String domain_show(String domain) throws IOException {
        WebClient wc = WebClient.create(USER_DOMAIN_SHOW);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(DOMAIN, domain)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/users/counts
     * @param uids
     * @return
     * @throws IOException
     */
    public String counts(String uids) throws IOException {
        WebClient wc = WebClient.create(USER_COUNTS);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(UIDS, uids)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }

}
