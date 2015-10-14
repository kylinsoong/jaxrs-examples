package org.apache.cxf.rs.examples;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Basic Person Representation
 */
@XmlRootElement(name = "PersonInfo", namespace = "http://ksoong.org")
public class PersonInfo {
    private long id;

    public PersonInfo() {
    	
    }
    
    public PersonInfo(long id) {
    	this.id = id;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
    
}
