package org.apache.cxf.rs.examples;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.cxf.rs.examples.manager.OAuthManager;
import org.apache.cxf.rs.examples.manager.ThirdPartyRegistrationService;
import org.apache.cxf.rs.security.oauth.services.AccessTokenService;
import org.apache.cxf.rs.security.oauth.services.RequestTokenService;

@ApplicationPath("/oauth")
public class OAuthManagerApplication extends Application {
	
	private OAuthManager manager;
	
	public void setOAuthManager(OAuthManager manager) {
        this.manager = manager;	
    }
	
	@Override
    public Set<Object> getSingletons() {
        Set<Object> classes = new HashSet<Object>();
        
        ThirdPartyRegistrationService thirdPartyService = new ThirdPartyRegistrationService();
        thirdPartyService.setDataProvider(manager);
        
        RequestTokenService rts = new RequestTokenService(); 
        rts.setDataProvider(manager);
        
        AccessTokenService ats = new AccessTokenService();
        ats.setDataProvider(manager);
        
        classes.add(thirdPartyService);
        classes.add(rts);
        classes.add(ats);
        return classes;
    }

}
