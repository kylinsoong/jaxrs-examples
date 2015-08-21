package org.jboss.resteasy.examples.petstore.resource;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
@Api(value = "/pet")
public class PetResource {
    
    static PetData petData = new PetData();
    static ResourceUtil ru = new ResourceUtil();
    
    @GET
    @Path("/{petId}")
    @ApiOperation(value = "Find pet by ID", notes = "Returns a pet when ID < 10.  ID > 10 or nonintegers will simulate API error conditions")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"), @ApiResponse(code = 404, message = "Pet not found") })
    public Pet getPetById(@ApiParam() @PathParam("petId") String petId) throws NotFoundException {
      Pet pet = petData.getPetbyId(ru.getLong(0, 100000, 0, petId));
      if (null != pet) {
        return pet;
      } else {
        throw new NotFoundException(404, "Pet not found");
      }
    }

    @POST
    @ApiOperation(value = "Add a new pet to the store")
    @ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
    public Response addPet(@ApiParam() Pet pet) {
      petData.addPet(pet);
      return Response.ok().entity("SUCCESS").build();
    }

    @PUT
    @ApiOperation(value = "Update an existing pet")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Pet not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    public Response updatePet(@ApiParam(value = "Pet object that needs to be added to the store", required = true) Pet pet) {
      petData.addPet(pet);
      return Response.ok().entity("SUCCESS").build();
    }

    @GET
    @Path("/findByStatus")
    @ApiOperation(value = "Finds Pets by status",  notes = "Multiple status values can be provided with comma seperated strings", response = Pet.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid status value") })
    public List<Pet> findPetsByStatus(@ApiParam(value = "Status values that need to be considered for filter", required = true, defaultValue = "available", allowableValues = "available,pending,sold", allowMultiple = true) @QueryParam("status") String status) {
      return petData.findPetByStatus(status);
    }

    @GET
    @Path("/findByTags")
    @ApiOperation(value = "Finds Pets by tags", notes = "Muliple tags can be provided with comma seperated strings. Use tag1, tag2, tag3 for testing.", response = Pet.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid tag value") })
    public List<Pet> findPetsByTags(@QueryParam("tags") String tags) {
      return petData.findPetByTags(tags);
    }

}
