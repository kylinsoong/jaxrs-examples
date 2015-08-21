package org.jboss.resteasy.examples.petstore.model;

public class HelloWorld {

    private final String value;
    
    public HelloWorld(String value) {
        this.value = value ;
    }

    public String getValue() {
        return value;
    }
}
