package org.apache.cxf.rs.examples;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.search.client.SearchConditionBuilder;

public class RESTClient {
	
	
    public static void main( String[] args ) throws IOException{
    	
    	//uses CXF JAX-RS WebClient
    	usePersonService();
    	
    	// uses a basic proxy
    	useSimpleProxy();
    	
    	// uses CXF JAX-RS WebClient to do the advanced search
    	useSearchService();
        
        System.exit(0);
    }

    /**
     * PersonService provides information about all the persons it knows about, about individual persons and
     * their relatives : - ancestors - parents, grandparents, etc - descendants - children, etc - partners
     * Additionally it can help with adding the information about new children to existing persons and update
     * the age of the current Person
     * @throws IOException 
     */
	private static void usePersonService() throws IOException {

		System.out.println("Using a Web Client...");
		
		// A single web client will be used to retrieve all the information
		final String personServiceURI = "http://localhost:8080/personservice/main";
        WebClient wc = WebClient.create(personServiceURI);
        
        // Get the list of all persons
        System.out.println("Getting the XML collection of all persons in a type-safe way...");
        wc.accept(MediaType.APPLICATION_XML);
        List<Person> persons = getPersons(wc);
        
        // Get individual persons using JSON
        System.out.println("Getting individual persons using JSON...");
        wc.reset().accept(MediaType.APPLICATION_JSON);
        for (Person person : persons){
        	wc.path(person.getId());
        	// Read the stream
            InputStream is = wc.get(InputStream.class);
            System.out.println(IOUtils.toString(is));
            wc.back(false);
        }
        
        System.out.println("Getting info about Fred...");
        wc.reset().accept(MediaType.APPLICATION_XML);
        wc.path("4");
        getPerson(wc);
        
        System.out.println("Fred and Catherine now have a child, adding a child info to PersonService");
        Person child = new Person("Harry", 1);
        Response response = wc.reset().path("4").path("children").post(child);
        if (response.getStatus() != 201) {
            throw new RuntimeException("No child resource has been created");
        }
        String location = response.getMetadata().getFirst("Location").toString();
        getPerson(WebClient.create(location));
        
        System.out.println("Fred has become 40, updating his age");
        wc.back(false);
        wc.path("age").type("text/plain");
        Response rc = wc.put(20);
        if (rc.getStatus() != 400) {
            throw new RuntimeException("Fred has become older, not younger");
        }
        
        rc = wc.put(40);
        if (rc.getStatus() != 204) {
            throw new RuntimeException("Impossible to update Fred's age");
        }
        
        System.out.println("Getting up to date info about Fred");
        wc.back(false);
        getPerson(wc);
        
        System.out.println("do a basic search");
        wc.reset().path("find").accept(MediaType.APPLICATION_XML);
        wc.query("name", "Fred", "Lorraine");
        printPersonCollection(wc.get(PersonCollection.class));
        
        System.out.println("Using PATCH...");
        WebClient.getConfig(wc).getRequestContext().put("use.async.http.conduit", true);
        String patch = wc.reset().invoke("PATCH", "", String.class);
        System.out.println("Patch: " + patch);
        
        System.out.println();
	}

	private static void useSimpleProxy() {
				
		System.out.println("Using a simple JAX-RS proxy to get all the persons...");
		
		String webAppAddress = "http://localhost:8080/personservice";
        PersonService proxy = JAXRSClientFactory.create(webAppAddress, PersonService.class);
        
        Response resp = proxy.getPersons(0, -1);
        if (resp.getStatus() == 200) {
        	PersonCollection personColl = resp.readEntity(PersonCollection.class);
        	List<Person> persons = personColl.getList();
        	for (Iterator<Person> it = persons.iterator(); it.hasNext();) {
                Person person = it.next();
                System.out.println("ID " + person.getId() + " : " + person.getName() + ", age : " + person.getAge());
            }
        }
        
        System.out.println("Using PATCH...");
        WebClient.getConfig(proxy).getRequestContext().put("use.async.http.conduit", true);
        String patch = proxy.patch();
        System.out.println("Patch: " + patch);
        
        System.out.println();
	}

	private static void useSearchService() {

		System.out.println("Searching...");
		
		WebClient wc = WebClient.create("http://localhost:8080/personservice/search");
        WebClient.getConfig(wc).getHttpConduit().getClient().setReceiveTimeout(10000000L);
        wc.accept(MediaType.APPLICATION_XML);
        
        // Moves to "/personservice/search"
        wc.path("person");
        
        SearchConditionBuilder builder = SearchConditionBuilder.instance();
        
        System.out.println("Find people with the name Fred or Lorraine:");
        String query = builder.is("name").equalTo("Fred").or()
                .is("name").equalTo("Lorraine")
                .query();
        findPersons(wc, query);
        
        System.out.println("Find all people who are no more than 30 years old");
        query = builder.is("age").lessOrEqualTo(30)
        		.query();
        findPersons(wc, query);
        
        //Moves to "/personservice/personinfo"
        wc.reset().accept(MediaType.APPLICATION_XML);
        wc.path("personinfo");
        
        System.out.println("Find all people younger than 40 using JPA2 Tuples");
        query = builder.is("age").lessThan(40).query();
        
        wc.path(query);
        
        Collection<? extends PersonInfo> personInfos = wc.getCollection(PersonInfo.class);
        for (PersonInfo pi : personInfos) {
        	System.out.println("ID : " + pi.getId());
        }
        
        System.out.println();
	}
	
	private static void findPersons(WebClient wc, String searchExpression) {
    	wc.resetQuery();
    	wc.query("_s", searchExpression);
    	PersonCollection persons = wc.get(PersonCollection.class);

    	printPersonCollection(persons);
    }
	
	private static void printPersonCollection(PersonCollection persons) {
    	for (Person person : persons.getList()) {
            System.out.println("Found : " + person.getName());
        }
    }
	
	private static Person getPerson(WebClient wc) {
        Person person = wc.get(Person.class);
        System.out.println("ID " + person.getId() + " : " + person.getName() + ", age : " + person.getAge());
        return person;
    }
	
	private static List<Person> getPersons(WebClient wc) {
		PersonCollection collection = wc.get(PersonCollection.class);
		List<Person> persons = collection.getList();
		System.out.println("Size: " + persons.size());
		for (Person person : persons) {
            System.out.println("ID " + person.getId() + " : " + person.getName() + ", age : " + person.getAge());
        }
        return persons;
	}


	
}
