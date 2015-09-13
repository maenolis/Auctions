package org.maenolis.auctions.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.maenolis.auctions.dao.ConfirmedUser;
import org.maenolis.auctions.dao.User;

@Path("/Login")
public class LoginService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ConfirmedUser login(@Context final HttpServletRequest request,
			final User user) throws IOException, NoSuchAlgorithmException {

		User confirmedUser = User.getUser(user.getEmail());
		if (confirmedUser.getPassword().equals(
				User.encryptSHA256(user.getPassword()))) {
			ConfirmedUser retUser = new ConfirmedUser();
			retUser.setUsername(confirmedUser.getUsername());
			retUser.setSessionId(request.getSession().getId());
			return retUser;
		}

		return null;
	}
}
