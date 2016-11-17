package org.ksoong.weibo4j;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class Common extends Weibo {
    
    final static String CODES = "codes";
    final static String LANGUAGE = "language";
    final static String COUNTRY = "country";
    final static String COMMON_CODE_TO_LOCATION = API_BASE + "/common/code_to_location.json";
    final static String COMMON_GET_CITY = API_BASE + "/common/get_city.json";
    final static String COMMON_GET_PROVINCE = API_BASE + "/common/get_province.json";
    final static String COMMON_GET_COUNTRY = API_BASE + "/common/get_country.json";
    final static String COMMON_GET_TIMEZONE = API_BASE + "/common/get_timezone.json";
    
    
    public Common(String access_token) {
        super(access_token);
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/code_to_location
     * @param codes
     * @return
     * @throws IOException
     */
    public String code_to_location(String codes) throws IOException {
        WebClient wc = WebClient.create(COMMON_CODE_TO_LOCATION);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(CODES, codes)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    public String get_city(String province) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_CITY);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(PROVINCE, province)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    public String get_city(String province, String capital) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_CITY);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(PROVINCE, province)
                .query(CAPITAL, capital)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/get_city
     * @param province
     * @param capital
     * @param language
     * @return
     * @throws IOException
     */
    public String get_city(String province, String capital, String language) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_CITY);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(PROVINCE, province)
                .query(CAPITAL, capital)
                .query(LANGUAGE, language)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    public String get_province(String country) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_PROVINCE);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(COUNTRY, country)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    public String get_province(String country, String language) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_PROVINCE);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(COUNTRY, country)
                .query(LANGUAGE, language)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/get_province
     * @param country
     * @param capital
     * @param language
     * @return
     * @throws IOException
     */
    public String get_province(String country, String capital, String language) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_PROVINCE);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(COUNTRY, country)
                .query(CAPITAL, capital)
                .query(LANGUAGE, language)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    public String get_country() throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_COUNTRY);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    public String get_country(String language) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_COUNTRY);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(LANGUAGE, language)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/get_country
     * @param language
     * @param capital
     * @return
     * @throws IOException
     */
    public String get_country(String language, String capital) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_COUNTRY);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(LANGUAGE, language)
                .query(CAPITAL, capital)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    public String getTimeZone() throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_TIMEZONE);
        Response resp = wc.query(ACCESS_TOKEN, access_token()).get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/get_timezone
     * @param language
     * @return
     * @throws IOException
     */
    public String getTimeZone(String language) throws IOException {
        WebClient wc = WebClient.create(COMMON_GET_TIMEZONE);
        Response resp = wc.query(ACCESS_TOKEN, access_token())
                .query(LANGUAGE, language)
                .get();
        String result = IOUtils.toString((InputStream) resp.getEntity());
        handleResponse(resp, wc);
        return result;
    }

}
