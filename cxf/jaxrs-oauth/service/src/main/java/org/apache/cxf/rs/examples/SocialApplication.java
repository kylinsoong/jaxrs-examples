package org.apache.cxf.rs.examples;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.cxf.rs.examples.manager.OAuthManager;
import org.apache.cxf.rs.examples.manager.ThirdPartyAccessService;
import org.apache.cxf.rs.examples.service.SecurityContextFilter;
import org.apache.cxf.rs.examples.service.SocialService;
import org.apache.cxf.rs.examples.service.UserAccounts;
import org.apache.cxf.rs.examples.service.UserRegistrationService;
import org.apache.cxf.rs.security.oauth.services.AuthorizationRequestService;

@ApplicationPath("/social")
public class SocialApplication extends Application {

	private OAuthManager manager;
	
	private UserAccounts accounts;
	
	public void setAccounts(UserAccounts accounts) {
		this.accounts = accounts;
	}
	
	public void setOAuthManager(OAuthManager manager) {
        this.manager = manager;	
    }
	
	@Override
    public Set<Object> getSingletons() {
        Set<Object> classes = new HashSet<Object>();
        
        SocialService socialService = new SocialService();
        socialService.setAccounts(accounts);
        
        UserRegistrationService userRegService = new UserRegistrationService();
        userRegService.setAccounts(accounts);
        
        SecurityContextFilter scFilter = new SecurityContextFilter();
        scFilter.setUserRegistrationPath("registerUser");
        scFilter.setAccounts(accounts);
        
        ThirdPartyAccessService thirdPartyAccessService = new ThirdPartyAccessService();
        thirdPartyAccessService.setAccounts(accounts);
        
        AuthorizationRequestService authService = new AuthorizationRequestService();
        authService.setDataProvider(manager);
                
        classes.add(socialService);
        classes.add(userRegService);
        classes.add(scFilter);
        classes.add(authService);
        classes.add(thirdPartyAccessService);
        
        return classes;
    }
}
