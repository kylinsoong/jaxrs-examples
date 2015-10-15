package org.apache.cxf.rs.examples;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.cxf.rs.examples.thirdparty.RestaurantService;

@ApplicationPath("/restaurant")
public class RestaurantApplication extends Application {

	@Override
    public Set<Object> getSingletons() {
        Set<Object> classes = new HashSet<Object>();
        
        classes.add(new RestaurantService());
        
        return classes;
    }
}
