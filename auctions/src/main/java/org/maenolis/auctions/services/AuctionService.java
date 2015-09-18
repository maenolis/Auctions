package org.maenolis.auctions.services;

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
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.AuctionRetObject;
import org.maenolis.auctions.services.retObj.AuctionSearchObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;

/**
 * The Class AuctionService.
 */
@Path("/Auction")
public class AuctionService {

	/**
	 * Gets the auctions.
	 *
	 * @return the auctions
	 */
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<AuctionRetObject> getterAuctions() {

		List<AuctionRetObject> list = Auction.getAllAuctions();
		ListWrapper<AuctionRetObject> ret = new ListWrapper<AuctionRetObject>(
				list);
		return ret;

	}

	/**
	 * Search auctions.
	 *
	 * @param search
	 *            the search
	 * @return the list wrapper
	 */
	@Path("/search")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<AuctionRetObject> searchAuctions(
			final AuctionSearchObject search) {

		List<AuctionRetObject> list = Auction.searchAuctions(search);
		ListWrapper<AuctionRetObject> ret = new ListWrapper<AuctionRetObject>(
				list);
		return ret;

	}

	/**
	 * New auction.
	 *
	 * @param auction
	 *            the auction
	 * @param request
	 *            the request
	 * @return the string
	 */
	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String newAuction(final AuctionRetObject auction,
			@Context final HttpServletRequest request) {

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

			auction.setOwnerId((int) request.getSession().getAttribute(
					PropertyProvider.USERID));
			session.save(new Auction(auction));
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

	/**
	 * Update auction.
	 *
	 * @param auction
	 *            the auction
	 * @param request
	 *            the request
	 * @return the string
	 */
	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAuction(final AuctionRetObject auction,
			@Context final HttpServletRequest request) {

		String ret = PropertyProvider.NOK;
		System.out.println("updateAuction received : " + auction);

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();

			session.save(new Auction(auction));
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

	/**
	 * Delete auction.
	 *
	 * @param auction
	 *            the auction
	 * @return the string
	 */
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
			Auction dbAuction = Auction.getAuction(auction.getId());
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
