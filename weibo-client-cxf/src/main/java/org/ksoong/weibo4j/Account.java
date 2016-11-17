package org.ksoong.weibo4j;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

/**
 * http://open.weibo.com/wiki/2/account/profile/school_list
 * http://open.weibo.com/wiki/2/account/rate_limit_status
 * @author kylin
 *
 */
public class Account extends Weibo {
    
    
    final static String CITY = "city";
    final static String AREA = "area";
    final static String TYPE = "type";
    final static String KEYWORD = "keyword";
    final static String COUNT = "count";
    final static String ACCOUNT_PROFILE_SCHOOL_LIST = API_BASE + "/account/profile/school_list.json";
    final static String ACCOUNT_RATE_LIMIT_STATUS = API_BASE + "/account/rate_limit_status.json";
    final static String ACCOUNT_PROFILE_EMAIL = API_BASE + "/account/profile/email.json";
    final static String ACCOUNT_GET_UID = API_BASE + "/account/get_uid.json";
    
    public Account(String access_token) {
        super(access_token);
    }
    
    public String profile_school_list(int province, String capital) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(PROVINCE, province)
          .query(CAPITAL, capital);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;         
    }
    
    public String profile_school_list(int province, String capital, int type) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(PROVINCE, province)
          .query(CAPITAL, capital)
          .query(TYPE, type);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;         
    }
    
    public String profile_school_list(String keyword) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(KEYWORD, keyword);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;  
    }
    
    public String profile_school_list(String keyword, int count) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(KEYWORD, keyword)
          .query(COUNT, count);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;  
    }
    
    public String profile_school_list(String keyword, String province, int count) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(PROVINCE, province)
          .query(KEYWORD, keyword)
          .query(COUNT, count);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;  
    }
    
    public String profile_school_list(String keyword, String province, String city, int count) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(PROVINCE, province)
          .query(CITY, city)
          .query(KEYWORD, keyword)
          .query(COUNT, count);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;  
    }
    
    public String profile_school_list(String keyword, String province, String city, String area, int count) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(PROVINCE, province)
          .query(CITY, city)
          .query(AREA, area)
          .query(KEYWORD, keyword)
          .query(COUNT, count);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;  
    }
    
    public String profile_school_list(String keyword, int province, int city, int area, int type, int count) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(PROVINCE, province)
          .query(CITY, city)
          .query(AREA, area)
          .query(TYPE, type)
          .query(KEYWORD, keyword)
          .query(COUNT, count);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;  
    }
    
    /**
     * http://open.weibo.com/wiki/2/account/profile/school_list
     * @param keyword
     * @param province
     * @param city
     * @param area
     * @param type
     * @param capital
     * @param count
     * @return
     * @throws IOException
     */
    public String profile_school_list(String keyword, int province, int city, int area, int type, String capital, int count) throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_SCHOOL_LIST);
        wc.query(ACCESS_TOKEN, access_token())
          .query(PROVINCE, province)
          .query(CITY, city)
          .query(AREA, area)
          .query(TYPE, type)
          .query(CAPITAL, capital)
          .query(KEYWORD, keyword)
          .query(COUNT, count);
        Response resp = wc.get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;  
    }

    /**
     * http://open.weibo.com/wiki/2/account/rate_limit_status
     * @return
     * @throws IOException
     */
    public String rate_limit_status() throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_RATE_LIMIT_STATUS);
        Response resp = wc.query(ACCESS_TOKEN, access_token()).get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/account/profile/email
     * @return
     * @throws IOException
     */
    public String profile_email() throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_PROFILE_EMAIL);
        Response resp = wc.query(ACCESS_TOKEN, access_token()).get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/account/get_uid
     * @return
     * @throws IOException
     */
    public String get_uid() throws IOException {
        WebClient wc = WebClient.create(ACCOUNT_GET_UID);
        Response resp = wc.query(ACCESS_TOKEN, access_token()).get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
}
