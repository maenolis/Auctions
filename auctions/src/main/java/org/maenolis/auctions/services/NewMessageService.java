package org.maenolis.auctions.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maenolis.auctions.dao.Message;
import org.maenolis.auctions.dao.User;

@Path("/NewMessage")
public class NewMessageService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void newMessage(final Message message) {

		System.out.println(">>> newMessage received: " + message);
		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();
			User c1 = new User();
			c1.setFirstName("kostas");
			session.save(c1);

			User c2 = new User();
			c2.setFirstName("matina");
			session.save(c2);

			message.setReceiver(c2);
			message.setSender(c1);
			System.out.println(">>> Message: " + message);
			session.save(message);
			tx.commit();
		} catch (Exception e) {
			System.err.print("During transaction received error : "
					+ e.getMessage());
		} finally {
			session.close();
		}
	}

}
