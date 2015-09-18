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
import org.maenolis.auctions.dao.Bid;
import org.maenolis.auctions.dao.User;

@Path("/Bid")
public class BidService {

	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void newBid(final Bid bid) {

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();
			User c1 = new User();
			c1.setFirstName("kostasmarinamazi");
			session.save(c1);
			bid.setBidder(c1);
			session.save(bid);
			tx.commit();
		} catch (Exception e) {
			System.err.print("During transaction received error : "
					+ e.getMessage());
		} finally {
			session.close();
		}
	}

}
