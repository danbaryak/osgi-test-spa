package org.site.login.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.site.login.api.User;
import org.site.login.api.UserService;

@Path("users")
public class UserResource {

	private volatile UserService userService;
	
	@GET
	@Path("me")
	@Produces(MediaType.APPLICATION_JSON)
	public User getCurrentUser() {
		return new User("danbar");
	}
	
}
