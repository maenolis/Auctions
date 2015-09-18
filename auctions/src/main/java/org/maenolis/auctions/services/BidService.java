package org.maenolis.auctions.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maenolis.auctions.dao.Auction;
import org.maenolis.auctions.dao.Bid;
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.retObj.AuctionRetObject;
import org.maenolis.auctions.services.retObj.BidRetObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;

@Path("/Bid")
public class BidService {

	@Path("/new")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void newBid() {

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
			Bid bid = new Bid();
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

	@Path("/bids")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ListWrapper<BidRetObject> getBidsForAuction(
			final AuctionRetObject auction,
			@Context final HttpServletRequest request) {

		ListWrapper<BidRetObject> ret = null;
		if (auction != null) {
			List<BidRetObject> list = Auction.getBids(auction.getId());
			ret = new ListWrapper<BidRetObject>(list);
			return ret;
		} else {
			ret = new ListWrapper<BidRetObject>(new ArrayList<BidRetObject>());
		}

		return ret;

	}

}
