package org.site.login.rest;

import java.util.List;

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
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
}