package org.ksoong.weibo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

/**
 * API - http://open.weibo.com/wiki/API%E6%96%87%E6%A1%A3_V2
 * @author kylin
 *
 */
public class WeiboClient {
    
    static final String STATUSES = "status";
    static final String PIC = "pic";
    static final String URL = "url";
    static final String PIC_ID = "pic_id";
    static final String ACCESS_TOKEN = "access_token";
    static final String STATUSES_UPDATE = "https://api.weibo.com/2/statuses/update.json";
    static final String STATUSES_UPLOAD = "https://upload.api.weibo.com/2/statuses/upload.json";
    static final String STATUSES_UPLOAD_URL_TEXT = "https://api.weibo.com/2/statuses/upload_url_text.json";
    private String token;
    
    public WeiboClient(String token) {
        this.token = token;
    }
    
    /**
     * statuses/update
     * @param content
     * @throws UnsupportedEncodingException 
     */
    public void statuses_update(String status) {
        WebClient wc = WebClient.create(STATUSES_UPDATE);
        Response resp = wc.form(new Form().param(STATUSES, status).param(ACCESS_TOKEN, token));
        handleResponse(resp, wc);
    }
    
    /**
     * statuses/upload
     * @param content
     * @param pic
     * @throws FileNotFoundException 
     */
    public void statuses_upload(String status, String pic) throws FileNotFoundException {
        WebClient wc = WebClient.create(STATUSES_UPLOAD);
        wc.type("multipart/mixed").accept("multipart/mixed");
        List<Attachment> atts = new LinkedList<Attachment>();
        atts.add(new Attachment(ACCESS_TOKEN, "application/json", token));
        atts.add(new Attachment(STATUSES, "application/json", status));
        atts.add(new Attachment(PIC, "application/octet-stream", new FileInputStream(new File(pic))));
        MultipartBody body = new MultipartBody(atts);
        Response resp = wc.post(body);
        handleResponse(resp, wc);
    }
    
    public void statuses_upload_url_text(String status, String url, String pic_id) {
        WebClient wc = WebClient.create(STATUSES_UPDATE);
        Response resp = wc.form(new Form().param(STATUSES, status).param(ACCESS_TOKEN, token).param(URL, url).param(PIC_ID, pic_id));
        handleResponse(resp, wc);
    }

    private void handleResponse(Response resp, WebClient wc) {
        System.out.println(resp.getStatus() + " - " + resp.getStatusInfo().getReasonPhrase());
        wc.close();
    }

}
