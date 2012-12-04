package com.kylin.jaxrs.library;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

@Path("/library")
public class Library {
	
	private static final Logger logger = Logger.getLogger(Library.class);
	
	@GET
	@Path("/books")
	public String getBooks() {
		
		logger.info("get all books");

		return "Change Anything";
	}

	@GET
	@Path("/book/{id}")
	public String getBook(@PathParam("id") String id) {

		logger.info("get book with id: " + id);
		
		return "Change Anything";
	}
	
	@PUT
	@Path("/book/{id}")
	public void addBook(@PathParam("id") String id, @QueryParam("name") String name) {

		logger.info("add book with id: " + id);
	}
	
	@DELETE
	@Path("/book/{id}")
	public void removeBook(@PathParam("id") String id){
		
		logger.info("remove book through id: " + id);
	}



}
