package org.maenolis.auctions.services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.maenolis.auctions.dao.User;


@XmlSeeAlso({User.class})
@Path("/Login")
public class LoginService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User user) throws IOException {
		
		System.out.println(user.getEmail() + " " + user.getPassword());
		return user;
	}
}
