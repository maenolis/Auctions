package org.maenolis.auctions.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;

@Path("/Login")
public class LoginService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void login(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response, final User user)
			throws IOException, NoSuchAlgorithmException {

		LoginRetObject loginRetObject = new LoginRetObject();
		loginRetObject.setStatus(PropertyProvider.NOK);
		User confirmedUser = User.getUser(user.getEmail());
		if (confirmedUser.getPassword().equals(
				User.encryptSHA256(user.getPassword()))) {
			User retUser = new User();
			retUser.setUsername(confirmedUser.getUsername());
			// retUser.setSessionId(request.getSession().getId());

			loginRetObject.setStatus(PropertyProvider.OK);
			loginRetObject.setUsername(confirmedUser.getUsername());
			System.out.println("retJson : " + loginRetObject);
			request.getSession().setAttribute(PropertyProvider.USERID,
					confirmedUser.getId());
			request.getSession().setAttribute(PropertyProvider.USERNAME,
					confirmedUser.getUsername());
		}

		// return loginRetObject;
	}
}
