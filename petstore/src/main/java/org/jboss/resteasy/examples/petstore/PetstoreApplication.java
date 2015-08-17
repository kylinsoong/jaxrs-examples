package org.jboss.resteasy.examples.petstore;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.examples.petstore.resource.HelloWorldResource;
import org.jboss.resteasy.examples.petstore.resource.PetResource;
import org.jboss.resteasy.examples.petstore.resource.PetStoreResource;
import org.jboss.resteasy.examples.petstore.resource.UserResource;

@ApplicationPath("rest")
public class PetstoreApplication extends Application {
    
    private Set<Object> singletons = new HashSet<Object>();
    
    
    public PetstoreApplication(){
    }
    @Override
    public Set<Class<?>> getClasses() {
        
        Set<Class<?>> resources = new HashSet<Class<?>>();
        
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        
        resources.add(HelloWorldResource.class);
        resources.add(PetResource.class);
        resources.add(PetStoreResource.class);
        resources.add(UserResource.class);
        
         return resources;
    }
    @Override
    public Set<Object> getSingletons() {
         return singletons;
    }

}
