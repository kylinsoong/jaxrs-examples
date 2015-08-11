package org.jboss.resteasy.examples.petstore.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.examples.petstore.data.UserData;
import org.jboss.resteasy.examples.petstore.exception.NotFoundException;
import org.jboss.resteasy.examples.petstore.model.User;

@Path("/user")
@Produces({"application/json", "application/xml"})
public class UserResource {
    
    static UserData userData = new UserData();

    @POST
    public Response createUser(User user) {
      userData.addUser(user);
      return Response.ok().entity("").build();
    }

    @POST
    @Path("/createWithArray")
    public Response createUsersWithArrayInput(User[] users) {
        for (User user : users) {
            userData.addUser(user);
        }
        return Response.ok().entity("").build();
    }

    @POST
    @Path("/createWithList")
    public Response createUsersWithListInput(java.util.List<User> users) {
        for (User user : users) {
            userData.addUser(user);
        }
        return Response.ok().entity("").build();
    }

    @PUT
    @Path("/{username}")
    public Response updateUser(@PathParam("username") String username, User user) {
      userData.addUser(user);
      return Response.ok().entity("").build();
    }

    @DELETE
    @Path("/{username}")
    public Response deleteUser(@PathParam("username") String username) {
      userData.removeUser(username);
      return Response.ok().entity("").build();
    }

    @GET
    @Path("/{username}")
    public Response getUserByName(@PathParam("username") String username)throws NotFoundException {
      User user = userData.findUserByName(username);
      if (null != user) {
        return Response.ok().entity(user).build();
      } else {
        throw new NotFoundException(404, "User not found");
      }
    }

    @GET
    @Path("/login")
    public Response loginUser(@QueryParam("username") String username, @QueryParam("password") String password) {
      return Response.ok()
          .entity("logged in user session:" + System.currentTimeMillis())
          .build();
    }

    @GET
    @Path("/logout")
    public Response logoutUser() {
      return Response.ok().entity("").build();
    }

}
