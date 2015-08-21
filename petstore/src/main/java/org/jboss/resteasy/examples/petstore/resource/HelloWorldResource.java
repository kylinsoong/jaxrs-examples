package org.jboss.resteasy.examples.petstore.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.examples.petstore.model.HelloWorld;


@Path("/helloworld")
@Api(value = "/helloworld")
public class HelloWorldResource {

    /**
     * Retrieves a JSON hello world message.
     * The {@link javax.ws.rs.Path} method annotation value is related to the one defined at the class level.
     * @return
     */
    @GET
    @Path("json")
    @Produces({ "application/json" })
    @ApiOperation(value = "get helloworld json", notes = "")
    public HelloWorld getHelloWorldJSON() {
        return new HelloWorld("HelloWorld");
    }

    /**
     * Retrieves a XML hello world message.
     * The {@link javax.ws.rs.Path} method annotation value is related to the one defined at the class level.
     * @return
     */
    @GET
    @Path("xml")
    @Produces({ "application/xml" })
    @ApiOperation(value = "get helloworld xml", notes = "")
    public String getHelloWorldXML() {
        return "<xml><result>" + "Hello World" + "</result></xml>";
    }

}
