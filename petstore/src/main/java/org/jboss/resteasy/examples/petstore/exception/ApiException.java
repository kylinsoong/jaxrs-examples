package org.jboss.resteasy.examples.petstore.exception;

public class ApiException extends Exception{

    private static final long serialVersionUID = -3038095295013356600L;
    
    private int code;
    
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
