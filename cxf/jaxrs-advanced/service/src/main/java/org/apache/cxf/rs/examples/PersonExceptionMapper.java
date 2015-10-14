package org.apache.cxf.rs.examples;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersonExceptionMapper implements ExceptionMapper<PersonUpdateException> {

	@Override
	public Response toResponse(PersonUpdateException exception) {
		return Response.status(400).build();
	}

}
