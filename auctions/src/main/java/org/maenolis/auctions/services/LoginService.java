package org.maenolis.auctions.services;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/Login")
public class LoginService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(201).entity("allrighty").build();
	}
}
