package org.maenolis.auctions.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.retObj.LogoutRetObject;

@Path("/Logout")
public class LogoutService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LogoutRetObject logout(@Context final HttpServletRequest request,
			final User user) {

		System.out.println("LogoutService received : " + user);

		LogoutRetObject logoutRetObject = UserState.logoutUser(request);

		return logoutRetObject;
	}
}
