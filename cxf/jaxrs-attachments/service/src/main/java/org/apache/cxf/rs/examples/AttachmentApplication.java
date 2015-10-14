package org.apache.cxf.rs.examples;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.cxf.jaxrs.provider.json.JSONProvider;

/*
 * Class that can be used (instead of XML-based configuration) to inform the JAX-RS 
 * runtime about the resources and providers it is supposed to deploy.  See the 
 * ApplicationServer class for more information.  
 */
@ApplicationPath("/attachments")
public class AttachmentApplication extends Application {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();
        singletons.add(new XopAttachmentServiceImpl());
        singletons.add(new MultipartsServiceImpl());
        
        JSONProvider provider = new JSONProvider();
        // equivalent to provider.setIgnoreNamespaces(true);
        provider.setOutTransformElements(Collections.singletonMap("{http://ksoong.org}Book", "Book"));
        
        provider.setInTransformElements(Collections.singletonMap("Book", "{http://ksoong.org}Book"));
        
        singletons.add(provider);
        
        return singletons;
    }

}
