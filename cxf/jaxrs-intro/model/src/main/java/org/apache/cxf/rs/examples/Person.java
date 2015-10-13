package org.apache.cxf.rs.examples;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")
public class Person {
	
	private int id;
    private String name;
    private int age = -1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @GET
    public Person getState() {
        return this;
    }

    @PUT
    public void updateSelf(Person newData) {
        this.name = newData.name;
        this.age = newData.age;
    }

    @Path("name")
    public String getName() {
        return name;
    }

    @PUT
    @Consumes("text/plain")
    @Path("name")
    public void setName(String name) {
        this.name = name;
    }

    @Path("age")
    public int getAge() {
        return age;
    }

    @PUT
    @Consumes("text/plain")
    @Path("age")
    public void setAge(int age) {
        this.age = age;
    }

}
