package org.apache.cxf.rs.examples;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.cxf.rs.examples.manager.OAuthManager;
import org.apache.cxf.rs.examples.manager.ThirdPartyAccessService;
import org.apache.cxf.rs.examples.service.UserAccounts;
import org.apache.cxf.rs.security.oauth.filters.OAuthRequestFilter;

@ApplicationPath("/thirdPartyAccess")
public class ThirdPartyAccessApplication extends Application {

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
        
        ThirdPartyAccessService thirdPartyAccessService = new ThirdPartyAccessService();
        thirdPartyAccessService.setAccounts(accounts);
        
        classes.add(thirdPartyAccessService);
        
        OAuthRequestFilter filter = new OAuthRequestFilter();
        filter.setDataProvider(manager);
        classes.add(filter);
        
        return classes;
    }
}
