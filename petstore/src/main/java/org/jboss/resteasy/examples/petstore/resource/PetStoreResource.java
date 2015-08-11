package org.jboss.resteasy.examples.petstore.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.examples.petstore.data.StoreData;
import org.jboss.resteasy.examples.petstore.exception.NotFoundException;
import org.jboss.resteasy.examples.petstore.model.Order;

@Path("/store")
@Produces({"application/json", "application/xml"})
public class PetStoreResource {
    
    static StoreData storeData = new StoreData();
    static ResourceUtil ru = new ResourceUtil();
    
    @GET
    @Path("/order/{orderId}")
    public Response getOrderById( @PathParam("orderId") Long orderId) throws NotFoundException {
      Order order = storeData.findOrderById(orderId);
      if (null != order) {
        return Response.ok().entity(order).build();
      } else {
        throw new NotFoundException(404, "Order not found");
      }
    }

    @POST
    @Path("/order")
    public Response placeOrder(Order order) {
      storeData.placeOrder(order);
      return Response.ok().entity("").build();
    }

    @DELETE
    @Path("/order/{orderId}")
    public Response deleteOrder(@PathParam("orderId") String orderId) {
      storeData.deleteOrder(ru.getLong(0, 10000, 0, orderId));
      return Response.ok().entity("").build();
    }

}
