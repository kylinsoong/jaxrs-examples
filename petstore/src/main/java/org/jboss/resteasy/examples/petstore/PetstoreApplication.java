package org.jboss.resteasy.examples.petstore;

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
    
    private Set<Class<?>> empty = new HashSet<Class<?>>();
    
    public PetstoreApplication(){
         singletons.add(new HelloWorldResource());
         singletons.add(new PetResource());
         singletons.add(new PetStoreResource());
         singletons.add(new UserResource());
    }
    @Override
    public Set<Class<?>> getClasses() {
         return empty;
    }
    @Override
    public Set<Object> getSingletons() {
         return singletons;
    }

}
