package org.maenolis.auctions.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.retObj.UserRetObject;

@Path("/User")
public class UserService {

	@Path("/confirm")
	@GET
	public String confirm(final UserRetObject user) {

		return User.confirm(4);
	}
}
