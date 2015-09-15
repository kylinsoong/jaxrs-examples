package org.jboss.resteasy.examples;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		String status = "ERROR";
        if(e instanceof NotFoundException){
                status = "404";
        } else if(e instanceof InternalServerErrorException){
                status = "500";
        }

        String message = e.getMessage();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String details = sw.toString();

        StringBuilder response = new StringBuilder("<error>");
		response.append("<code>" + status + "</code>");
		response.append("<message>" + message + "</message>");
		response.append("<details>" + details + "</details>");
		response.append("</error>");
		return Response.serverError().entity(response.toString()).type(MediaType.APPLICATION_XML).build();
	}

}
