package org.maenolis.auctions.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class JerseyTesty {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getter() {
		return "TESTY!!!!";
	}
}
