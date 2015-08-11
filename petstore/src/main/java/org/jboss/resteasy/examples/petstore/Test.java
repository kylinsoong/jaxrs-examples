package org.jboss.resteasy.examples.petstore;

import org.jboss.resteasy.examples.petstore.data.PetData;
import org.jboss.resteasy.examples.petstore.model.Pet;
import org.jboss.resteasy.examples.petstore.resource.ResourceUtil;

public class Test {
    
    static PetData petData = new PetData();
    static ResourceUtil ru = new ResourceUtil();

    public static void main(String[] args) {

        Pet pet = petData.getPetbyId(ru.getLong(0, 100000, 0, "1"));
        
        System.out.println(pet);
    }

}
