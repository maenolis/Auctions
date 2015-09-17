package org.maenolis.auctions.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.retObj.UserRetObject;

@Path("/User")
public class UserService {

	@Path("/confirm")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String confirm(final UserRetObject user) {
		return User.confirm(user.getId());
	}
}
