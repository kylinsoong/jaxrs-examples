package org.apache.cxf.rs.examples;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * JAX-RS MembershipService root resource
 * 
 * @author kylin
 *
 */
public class MembershipServiceImpl implements MembershipService {
	
	/**
	 * AtomicInteger, ConcurrentHashMap because MembershipService is a singleton accessed by multiple requests
	 */
	Map<Integer, Person> members = new ConcurrentHashMap<Integer, Person>();
    AtomicInteger currentId = new AtomicInteger();
    
    public MembershipServiceImpl() {
        // seed HashMap with first member
        Person p = new Person();
        p.setName("Kylin");
        p.setAge(20);
        p.setId(currentId.incrementAndGet());
        members.put(p.getId(), p);
    }

	@Override
	public Person getMemberSubresource(int id) {
		System.out.println("getMemberSubresource called - id = " + id);
		Person p = members.get(id);
		if (p == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println("person ID/Name/Age = " + p.getId() + " / " + p.getName() + " / " + p.getAge());
		return p;
	}

	@Override
	public Response addMember(Person person) {
		System.out.println("----invoking addMember, Member name is: " + person.getName());
		person.setId(currentId.incrementAndGet());
		members.put(person.getId(), person);
		// param in create() used for generating HTTP Location header so client
        // can know the new item's ID.
		return Response.created(URI.create("/members/" + person.getId())).build();
	}

	@Override
	public Response deleteMember(int id) {
		System.out.println("----invoking deleteMember for id = " + id);
		Person p = members.get(id);
		if (p == null) {
            // alternative to throwing WebApplicationException()
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		members.remove(id);
		return Response.status(Response.Status.GONE).build();
	}

	@Override
	public Collection<Person> getAllMembers() {
		return new ArrayList<Person>(members.values());
	}

}
