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
import org.maenolis.auctions.dao.Auction;
import org.maenolis.auctions.dao.Bid;

@Path("/NewBid")
public class NewBidService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void newBid(final Auction auction, final Bid bid) {

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();
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
