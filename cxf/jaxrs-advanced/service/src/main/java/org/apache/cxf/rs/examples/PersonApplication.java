package org.apache.cxf.rs.examples;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.cxf.jaxrs.ext.search.SearchContextProvider;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;

@ApplicationPath("/personservice")
public class PersonApplication extends Application {

	@Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>();
    }
	
	@Override
    public Set<Object> getSingletons() {
		
		Set<Object> classes = new HashSet<Object>();	
		
		classes.add(new PersonExceptionMapper());

        JSONProvider<?> provider = new JSONProvider<Object>();
        provider.setIgnoreNamespaces(true);
        classes.add(provider);

        classes.add(new SearchContextProvider());
        
        return classes;
	}
}
