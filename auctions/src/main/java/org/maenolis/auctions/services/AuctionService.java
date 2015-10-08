package org.maenolis.auctions.services;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.AuctionRetObject;
import org.maenolis.auctions.services.retObj.AuctionSearchObject;
import org.maenolis.auctions.services.retObj.BooleanRetObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;
import org.maenolis.auctions.userManagement.UserState;

/**
 * The Class AuctionService.
 */
@Path("/Auction")
public class AuctionService {

	/**
	 * Gets the auctions.
	 *
	 * @return the auctions
	 * @throws ParseException
	 */
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<AuctionRetObject> getterAuctions() throws ParseException {

		List<AuctionRetObject> list = Auction.getAllAuctions();
		ListWrapper<AuctionRetObject> ret = new ListWrapper<AuctionRetObject>(
				list);
		return ret;

	}

	@Path("/getAuction")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AuctionRetObject getAuction(final AuctionRetObject auctionJs,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		return Auction.transformToRetObject(Auction.getAuctionWithHistory(
				auctionJs.getId(), request.getSession()));

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
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		if (!UserState.isLogged(request.getSession())) {
			return PropertyProvider.NOK;
		}

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
	public AuctionRetObject updateAuction(final AuctionRetObject auction,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		AuctionRetObject ret = new AuctionRetObject();
		ret.setStatus(PropertyProvider.NOK);
		if (!UserState.isLogged(request.getSession())) {
			return ret;
		}

		System.out.println("updateAuctionRereceived : " + auction);
		Auction.updateAuction(auction);
		return Auction
				.transformToRetObject(Auction.getAuction(auction.getId()));
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BooleanRetObject deleteAuction(final AuctionRetObject auction,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		if (!UserState.isLogged(request.getSession())) {
			return new BooleanRetObject(false);
		}

		System.out.println("deleteAuctionRereceived : " + auction);
		return new BooleanRetObject(Auction.deleteAuction(auction));
	}

	@Path("/myAuctions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<AuctionRetObject> myAuctions(
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		if (!UserState.isLogged(request.getSession())) {
			return null;
		}

		List<AuctionRetObject> list = User.getUserAuctionsRetObj((int) request
				.getSession().getAttribute(PropertyProvider.USERID));
		ListWrapper<AuctionRetObject> ret = new ListWrapper<AuctionRetObject>(
				list);
		return ret;
	}
}
