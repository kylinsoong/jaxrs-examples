package org.teiid.jboss.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/customer")
@Api("/customer")
public class CustomersResource {
	
    private static final String XMLFILE = "customerList.xml";

    @GET
    @Path("/customerList")
    @Produces({ MediaType.APPLICATION_XML })
    @ApiOperation("get Customer List")
    @ApiResponses({})
	public String getCustomerList() {
    	        
        StringBuffer fileContents = new StringBuffer();

		try {            
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(XMLFILE);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
			try {
				String line = null;

				while ((line = input.readLine()) != null) {
					fileContents.append(line);
					fileContents.append(System.getProperty("line.separator"));
				}
			} finally {
                input.close();
                
            }
		} catch (IOException ex) {
			ex.printStackTrace();
		}
        

        return fileContents.toString();
    }
}
