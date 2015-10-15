package org.apache.cxf.rs.examples;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservationFailure {
	
	private String message;
	
    public ReservationFailure() {
        
    }
    
    public ReservationFailure(String message) {
        this.setMessage(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
	
}
