package org.apache.cxf.rs.examples;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRegistration {
	
    private String login;
    
    public UserRegistration() {
        
    }
    
    public UserRegistration(String login) {
        this.login = login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    
}