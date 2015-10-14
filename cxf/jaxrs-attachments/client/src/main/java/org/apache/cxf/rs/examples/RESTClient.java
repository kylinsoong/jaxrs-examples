package org.apache.cxf.rs.examples;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.mail.util.ByteArrayDataSource;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;

public class RESTClient {
	
	private static String urlStem = "http://localhost:8080/membership/members/";
	
    public static void main( String[] args ) throws Exception{
    	
    	// Post XOP with CXF JAX-RS WebClient
    	useXopAttachmentServiceWithWebClient();
    	
    	// Post XOP with CXF JAX-RS Proxy
//    	useXopAttachmentServiceWithProxy();
    	
    	// Post Book attachments in XML and JSON formats with CXF JAX-RS WebClient
    	useAttachmentServiceWithWebClient();
    	
    	// Post Book attachments in XML and JSON formats with CXF JAX-RS Proxy
//    	useAttachmentServiceWithProxy();
    	
        System.out.println( "Exit!" );
    }

    /**
     * Writes and reads the XOP attachment using a CXF JAX-RS WebClient.
     * Note that WebClient is created with the help of JAXRSClientFactoryBean.
     * JAXRSClientFactoryBean can be used when neither of the WebClient factory 
     * methods is appropriate. For example, in this case, an "mtom-enabled" 
     * property is set on the factory bean first.
     * @throws IOException 
     * 
     */
	private static void useXopAttachmentServiceWithWebClient() throws IOException {

		final String serviceURI = "http://localhost:8080/attachments/xop";
		
		JAXRSClientFactoryBean factoryBean = new JAXRSClientFactoryBean();
        factoryBean.setAddress(serviceURI);
        factoryBean.setProperties(Collections.singletonMap(org.apache.cxf.message.Message.MTOM_ENABLED, (Object)"true"));
        WebClient client = factoryBean.createWebClient();
        client.type("multipart/related").accept("multipart/related");
        
        XopBean xop = createXopBean();
        
        System.out.println("Posting a XOP attachment with a WebClient");
        XopBean xopResponse = client.post(xop, XopBean.class);
        verifyXopResponse(xop, xopResponse);
        
	}

	/**
     * Writes and reads the XOP attachment using a CXF JAX-RS Proxy
     * The proxy automatically sets the "mtom-enabled" property by checking
     * the CXF EndpointProperty set on the XopAttachment interface.
	 * @throws IOException 
     * 
     */
	private static void useXopAttachmentServiceWithProxy() throws IOException {

		final String serviceURI = "http://localhost:8080/attachments/xop";
		
		XopAttachmentService proxy = JAXRSClientFactory.create(serviceURI, XopAttachmentService.class);
		
		XopBean xop = createXopBean();
		
		System.out.println("Posting a XOP attachment with a proxy");
		
		XopBean xopResponse = proxy.echoXopAttachment(xop);
		
		verifyXopResponse(xop, xopResponse);
	}

	private static void useAttachmentServiceWithWebClient() throws Exception {

		final String serviceURI = "http://localhost:8080/attachments/multipart";
        
        JSONProvider provider = new JSONProvider();
        provider.setIgnoreNamespaces(true);
        provider.setInTransformElements(Collections.singletonMap("Book", "{http://ksoong.org}Book"));
        
        WebClient client = WebClient.create(serviceURI, Collections.singletonList(provider));
        
        client.type("multipart/mixed").accept("multipart/mixed");
        
        MultipartBody body = createMultipartBody();
        
        System.out.println();
        System.out.println("Posting Book attachments with a WebClient");
        
        MultipartBody bodyResponse = client.post(body, MultipartBody.class);
        
        verifyMultipartResponse(bodyResponse);
	}

	/**
     * Writes and reads the multipart/mixed attachments using a CXF JAX-RS Proxy
     * Note that a custom JAXB-driven JSONProvider is registered to simpify dealing 
     * with one of the parts in the JSON format: it is configured to drop namespace 
     * prefixes on the write and add a namespace to the incoming payload so that it
     * can be read into the namespace-qualified JAXB Book bean.
     * 
     * @throws Exception
     */
	private static void useAttachmentServiceWithProxy() throws Exception {
		
		final String serviceURI = "http://localhost:8080/attachments/multipart";
        
        JSONProvider provider = new JSONProvider();
        provider.setIgnoreNamespaces(true);
        provider.setInTransformElements(Collections.singletonMap("Book", "{http://ksoong.org}Book"));
        
        MultipartsService client = JAXRSClientFactory.create(serviceURI,
                MultipartsService.class,                                    
                Collections.singletonList(provider));
        
        MultipartBody body = createMultipartBody();
        
        System.out.println();
        System.out.println("Posting Book attachments with a proxy");
        
        MultipartBody bodyResponse = client.echoAttachment(body);
        
        verifyMultipartResponse(bodyResponse);
		
	}
	
	private static XopBean createXopBean() throws IOException {

		XopBean xop = new XopBean();
        xop.setName("xopName");
        
        InputStream is = RESTClient.class.getClassLoader().getResourceAsStream("java.jpg");
        byte[] data = IOUtils.readBytesFromStream(is);
        xop.setBytes(data);
        
        // Wrap java.jpg as a DataHandler
        xop.setDatahandler(new DataHandler(new ByteArrayDataSource(data, "application/octet-stream")));
        
        if (Boolean.getBoolean("java.awt.headless")) {
            System.out.println("Running headless. Ignoring an Image property.");
        } else {
            xop.setImage(getImage("java.jpg"));
        }
        
		return xop;
	}
	
	private static Image getImage(String string) throws IOException {
		return ImageIO.read(RESTClient.class.getClassLoader().getResourceAsStream("java.jpg"));
	}

	private static void verifyXopResponse(XopBean xopOriginal, XopBean xopResponse) {

		if (!Arrays.equals(xopResponse.getBytes(), xopOriginal.getBytes())) {
            throw new RuntimeException("Received XOP attachment is corrupted");
        }
        System.out.println();
        System.out.println("XOP attachment has been successfully received");
	}
	
	private static MultipartBody createMultipartBody() throws Exception  {
        List<Attachment> atts = new LinkedList<Attachment>();
        atts.add(new Attachment("book1", "application/xml", new Book("JAXB", 1L)));
        atts.add(new Attachment("book2", "application/json", new Book("JSON", 2L)));
        
        atts.add(new Attachment("image", "application/octet-stream", RESTClient.class.getClassLoader().getResourceAsStream("java.jpg")));
        
        return new MultipartBody(atts, true);  

    }
	
	private static void verifyMultipartResponse(MultipartBody bodyResponse) throws Exception {
        Book jaxbBook = bodyResponse.getAttachmentObject("book1", Book.class);
        Book jsonBook = bodyResponse.getAttachmentObject("book2", Book.class);
        
        byte[] receivedImageBytes = bodyResponse.getAttachmentObject("image", byte[].class);
        InputStream is = RESTClient.class.getClassLoader().getResourceAsStream("java.jpg");
        byte[] imageBytes = IOUtils.readBytesFromStream(is); 
        
        if ("JAXB".equals(jaxbBook.getName()) && 1L == jaxbBook.getId() && "JSON".equals(jsonBook.getName()) && 2L == jsonBook.getId() && Arrays.equals(imageBytes, receivedImageBytes)) {
            System.out.println("Book attachments have been successfully received");
        } else {
            throw new RuntimeException("Received Book attachment is corrupted");
        }
    }

	
}
