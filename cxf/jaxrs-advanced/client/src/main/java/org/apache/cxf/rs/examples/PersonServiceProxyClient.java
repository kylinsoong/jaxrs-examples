package org.apache.cxf.rs.examples;

public class PersonServiceProxyClient {
	
    private PersonService proxy;
    
    public PersonServiceProxyClient() {
        
    }
    
    public PersonServiceProxyClient(PersonService personService) {
        this.proxy = personService;
    }
    
    public void setPersonService(PersonService proxy) {
        this.proxy = proxy;
        useService();
    }

	public void useService() {

		System.out.println("Using a simple JAX-RS proxy to get all the persons...");
		
		
	}

}
