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
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.AuctionRetObject;

@Path("/Auction")
public class AuctionService {

	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String newAuction(final Auction auction) {

		String ret = PropertyProvider.NOK;
		System.out.println("newAuction received : " + auction);

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();
			// session.save(auction);
			// Auction a = new Auction();
			User c = new User();
			session.save(c);
			auction.setOwner(c);
			System.out.println(auction);
			session.save(auction);
			tx.commit();
			ret = PropertyProvider.OK;
		} catch (Exception e) {
			System.err.print("During transaction received error : "
					+ e.getMessage());
		} finally {
			session.close();
		}
		return ret;
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAuction(final AuctionRetObject auction) {

		String ret = PropertyProvider.NOK;
		System.out.println("deleteAuction received : " + auction);

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();
			Auction dbAuction = Auction.getAuction(7/* auction.getId() */);
			dbAuction.setAlive(false);
			session.save(dbAuction);
			tx.commit();
			ret = PropertyProvider.OK;
		} catch (Exception e) {
			System.err.print("During transaction received error : "
					+ e.getMessage());
		} finally {
			session.close();
		}
		return ret;
	}
}
