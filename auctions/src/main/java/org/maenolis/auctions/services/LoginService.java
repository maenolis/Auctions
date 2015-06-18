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
import javax.xml.bind.annotation.XmlSeeAlso;

import org.maenolis.auctions.dao.ConfirmedUser;
import org.maenolis.auctions.dao.User;


//@XmlSeeAlso({User.class, ConfirmedUser.class})
@Path("/Login")
public class LoginService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ConfirmedUser login(@Context HttpServletRequest request,User user) throws IOException, NoSuchAlgorithmException {
		
		User confirmedUser = User.getUser(user.getEmail());
		if (confirmedUser.getPassword().equals(User.encryptSHA256(user.getPassword()))){
			return new ConfirmedUser(confirmedUser.getUsername(), request.getSession().getId());
		}
		
		return null;
	}
}
