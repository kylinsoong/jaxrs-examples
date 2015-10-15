package org.apache.cxf.rs.examples;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.security.oauth.data.OAuthAuthorizationData;

public class RESTClient {

	public static void main(String[] args) {

		registerClientApplication();
		
		createUserAccount();
		
		reserveTable();
	}

	private static void registerClientApplication() {
		
		WebClient wc = WebClient.create("http://localhost:8080/oauth/registerProvider");
		WebClient.getConfig(wc).getHttpConduit().getClient().setReceiveTimeout(10000000L);
		wc.form(new Form().param("appName", "Restaurant Reservations").param("appURI", "http://localhost:8080/reservations/reserve"));
	}

	private static void createUserAccount() {
		WebClient rs = WebClient.create("http://localhost:8080/social/registerUser");
    	WebClient.getConfig(rs).getHttpConduit().getClient().setReceiveTimeout(10000000L);
    	rs.form(new Form().param("user", "barry@social.com").param("password", "1234"));
    	
    	printUserCalendar();
		
	}

	private static void printUserCalendar() {
		WebClient client = createClient("http://localhost:8080/social/accounts/calendar", "barry@social.com", "1234");
    	Calendar calendar = client.get(Calendar.class);
    	System.out.println(calendar.toString());
		
	}

	private static WebClient createClient(String address, String username, String password) {
    	JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
    	bean.setAddress(address);
    	bean.setUsername(username);
    	bean.setPassword(password);
    	
    	bean.getOutInterceptors().add(new LoggingOutInterceptor());
    	bean.getInInterceptors().add(new LoggingInInterceptor());
    	
    	WebClient wc = bean.createWebClient();
    	WebClient.getConfig(wc).getHttpConduit().getClient().setReceiveTimeout(1000000L);
    	return wc;
    }

	private static void reserveTable() {
		WebClient rs = createClient("http://localhost:8080/reservations/reserve/table", "barry@restaurant.com", "5678");
		Response r = rs.form(new Form().param("name", "Barry").param("phone", "12345678").param("hour", "7"));
		
		int status = r.getStatus();
    	Object locationHeader = r.getMetadata().getFirst("Location");
    	if (status != 303 || locationHeader == null) {
    		System.out.println("OAuth flow is broken");
    	}
    	
    	WebClient authorizeClient = createClient(locationHeader.toString(), "barry@social.com", "1234");
		OAuthAuthorizationData data = authorizeClient.get(OAuthAuthorizationData.class);    	
    	Object authenticityCookie = authorizeClient.getResponse().getMetadata().getFirst("Set-Cookie");
    	
    	Form authorizationResult = getAuthorizationResult(data);
    	authorizeClient.reset();
    	authorizeClient.to(data.getReplyTo(), false);
    	if (authenticityCookie != null) {
    		authorizeClient.header("Cookie", (String)authenticityCookie);
    	}
    	Response r2 = authorizeClient.form(authorizationResult);
    	
    	int status2 = r2.getStatus();
    	Object locationHeader2 = r2.getMetadata().getFirst("Location");
    	if (status2 != 303 || locationHeader2 == null) {
    		System.out.println("OAuth flow is broken");
    	}
    	
    	WebClient finalClient = createClient(locationHeader2.toString(), "barry@restaurant.com", "5678");
    	finalClient.accept("application/xml");
    	ReservationConfirmation confirm = finalClient.get(ReservationConfirmation.class);
    	
    	if (confirm != null) {
    		updateAndGetUserCalendar(7, "Dinner at " + confirm.getAddress());
    	} else {
    		System.out.println("Reservation failed");
    	}
		
	}

	private static Form getAuthorizationResult(OAuthAuthorizationData data) {
		Form form = new Form();
        form.param("oauth_token", data.getOauthToken());
        // TODO: get the user confirmation, using a popup window or a blocking cmd input
        form.param("oauthDecision", "allow");
        form.param("session_authenticity_token", data.getAuthenticityToken());
        return form;
	}

	private static void updateAndGetUserCalendar(int hour, String event) {
		WebClient client = createClient("http://localhost:8080/social/accounts/calendar", "barry@social.com", "1234");
    	Form form = new Form().param("hour", Integer.toString(hour)).param("event", event);
    	client.form(form);
    	printUserCalendar();
	}

}
