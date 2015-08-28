package org.jboss.resteasy.examples.petstore.resource;

import io.swagger.annotations.ApiResponses;

import org.jboss.resteasy.examples.petstore.model.Pet;

public class TmpResource {
    
    @ApiResponses(value = {})
    public Pet getPetById() {
        return new Pet();
    }

}
