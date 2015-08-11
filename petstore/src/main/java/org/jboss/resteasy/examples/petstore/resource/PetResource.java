package org.jboss.resteasy.examples.petstore.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.examples.petstore.data.PetData;
import org.jboss.resteasy.examples.petstore.exception.NotFoundException;
import org.jboss.resteasy.examples.petstore.model.Pet;

@Path("/pet")
@Produces({"application/json", "application/xml"})
public class PetResource {
    
    static PetData petData = new PetData();
    static ResourceUtil ru = new ResourceUtil();
    
    @GET
    @Path("/{petId}")
    public Pet getPetById(@PathParam("petId") String petId) throws NotFoundException {
      Pet pet = petData.getPetbyId(ru.getLong(0, 100000, 0, petId));
      if (null != pet) {
        return pet;
      } else {
        throw new NotFoundException(404, "Pet not found");
      }
    }

    @POST
    public Response addPet(Pet pet) {
      petData.addPet(pet);
      return Response.ok().entity("SUCCESS").build();
    }

    @PUT
    public Response updatePet(Pet pet) {
      petData.addPet(pet);
      return Response.ok().entity("SUCCESS").build();
    }

    @GET
    @Path("/findByStatus")
    public Response findPetsByStatus(@QueryParam("status") String status) {
      return Response.ok(petData.findPetByStatus(status)).build();
    }

    @GET
    @Path("/findByTags")
    public Response findPetsByTags(@QueryParam("tags") String tags) {
      return Response.ok(petData.findPetByTags(tags)).build();
    }

}
