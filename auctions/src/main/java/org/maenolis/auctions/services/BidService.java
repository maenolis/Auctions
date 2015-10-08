package org.maenolis.auctions.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.AuctionRetObject;
import org.maenolis.auctions.services.retObj.BidRetObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;
import org.maenolis.auctions.userManagement.UserState;

/**
 * The Class BidService.
 */
@Path("/Bid")
public class BidService {

	/**
	 * New bid.
	 */
	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void newBid(final BidRetObject bidJs,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		if (!UserState.isLogged(request.getSession())) {
			return;
		}

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();
			bidJs.setBidder_id((int) request.getSession().getAttribute(
					PropertyProvider.USERID));
			Bid bid = new Bid(bidJs);
			session.save(bid);
			tx.commit();
		} catch (Exception e) {
			System.err.print("During transaction received error : "
					+ e.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * Gets the bids for auction.
	 *
	 * @param auction
	 *            the auction
	 * @param request
	 *            the request
	 * @return the bids for auction
	 */
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
