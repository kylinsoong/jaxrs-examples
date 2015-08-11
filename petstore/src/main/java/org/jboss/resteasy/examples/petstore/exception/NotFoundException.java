package org.jboss.resteasy.examples.petstore.exception;

public class NotFoundException extends ApiException {

    private static final long serialVersionUID = 4090933289043909912L;
    
    private int code;
  
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
