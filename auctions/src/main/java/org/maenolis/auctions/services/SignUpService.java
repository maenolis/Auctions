package org.maenolis.auctions.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maenolis.auctions.dao.User;

@Path("/SignUp")
public class SignUpService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User signUp(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		user.setPassword(User.encryptSHA256(user.getPassword()));
		
		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
		return user;
	}

}