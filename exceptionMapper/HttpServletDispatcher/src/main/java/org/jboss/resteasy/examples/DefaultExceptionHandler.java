package org.jboss.resteasy.examples;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {
	
	@Context 
	protected Providers providers;
	  
	@Context
	protected UriInfo uriInfo;
	
	@Context
	protected HttpHeaders httpHeaders;

	@Override
	public Response toResponse(Exception e) {
		
		System.out.println(httpHeaders.getAcceptableMediaTypes());
		System.out.println(uriInfo.getPath());
		System.out.println(providers);
		System.out.println();
		
		ResponseError error = new ResponseError();
		
		String status = "ERROR";
        if(e instanceof NotFoundException){
                status = "404";
        } else if(e instanceof InternalServerErrorException){
                status = "500";
        }
        error.setCode(status);
        
        error.setMessage(e.getMessage());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String details = sw.toString();
        error.setDetails(details);
        
        String type = MediaType.APPLICATION_XML;
        List<MediaType> acceptTypes = httpHeaders.getAcceptableMediaTypes();
        if(acceptTypes != null){
        	for (MediaType acceptType : acceptTypes){
        		if (isApplicationJsonWithParametersIgnored(acceptType)) {
        			type = MediaType.APPLICATION_JSON;
        			break;
        		}
        	}
        }
        
		return Response.serverError().entity(error).type(type).build();
	}
	
	private boolean isApplicationJsonWithParametersIgnored(MediaType acceptType) {
	    return acceptType.getType().equals(MediaType.APPLICATION_JSON_TYPE.getType()) &&
	        acceptType.getSubtype().equals(MediaType.APPLICATION_JSON_TYPE.getSubtype());
	 }
	
	@XmlRootElement(name = "error")
	@XmlType(propOrder = { "code", "message", "details"})
	public static class ResponseError {

		private String code;
		
		private String message;
		
		private String details;

		@XmlElement(name = "code")
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		@XmlElement(name = "message")
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@XmlElement(name = "details")
		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}
		
	}

}
