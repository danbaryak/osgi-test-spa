package org.site.contact.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("contacts")
public class ContactResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> get() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("hi", "yush");
		result.put("another", "one");
		return result;
	}
}
