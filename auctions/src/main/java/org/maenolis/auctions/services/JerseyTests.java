package org.maenolis.auctions.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.maenolis.auctions.services.retObj.BidRetObject;
import org.maenolis.auctions.services.retObj.MessageRetObject;
import org.maenolis.auctions.services.retObj.UserRetObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;

/**
 * The Class JerseyTests.
 */
@Path("/test")
public class JerseyTests {

	@Path("/printer")
	@POST
	public void printer(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response, final BidRetObject bid)
			throws IOException {
		System.out.println(bid);
		response.sendRedirect("/auctions/#/login");
	}

	@Path("/redirectMe")
	@GET
	public void redirectMe(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) throws IOException {
		response.sendRedirect("/auctions/#/login");
	}

	/**
	 * Gets the auctions.
	 *
	 * @return the auctions
	 * @throws ParseException
	 */
	@Path("/auctions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<AuctionRetObject> getterAuctions() throws ParseException {

		List<AuctionRetObject> list = Auction.getAllAuctions();
		ListWrapper<AuctionRetObject> ret = new ListWrapper<AuctionRetObject>(
				list);
		return ret;

	}

	@Path("/auctionsXml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<AuctionRetObject> getterAuctionsXml() throws ParseException {

		List<AuctionRetObject> list = Auction.getAllAuctions();

		return list;

	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	@Path("/messages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<MessageRetObject> getterMessages() {

		List<MessageRetObject> list = User.getUserReceivedMessages(4);

		ListWrapper<MessageRetObject> ret = new ListWrapper<MessageRetObject>(
				list);
		return ret;

	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 * @throws IOException
	 */
	@Path("/users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<UserRetObject> getterUsers(
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) throws IOException {

		response.sendRedirect(PropertyProvider.REDIRECTIONURL);
		System.out.println("Continued!!!");
		if (true)
			return null;
		System.out.println("Continued 2!!!");

		List<UserRetObject> list = User.getAllUsers();

		ListWrapper<UserRetObject> ret = new ListWrapper<UserRetObject>(list);
		System.out.println("size list : " + list.size());
		System.out.println("wrapper size list : " + ret.getItems().size());
		return ret;

	}

	@Path("/from")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<MessageRetObject> getMessagesFromUser() {

		// List<MessageRetObject> list = User.getUserReceivedMessagesFrom(
		// message.getSenderId(), message.getReceiverId());

		System.out.println("/from");
		List<MessageRetObject> list = User.getUserReceivedMessagesFrom(7, 4);

		ListWrapper<MessageRetObject> ret = new ListWrapper<MessageRetObject>(
				list);
		return ret;

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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<BidRetObject> getBidsForAuction(
			final AuctionRetObject auction,
			@Context final HttpServletRequest request) {

		System.out.println("/from");
		List<BidRetObject> list = Auction.getBids(auction.getId());

		ListWrapper<BidRetObject> ret = new ListWrapper<BidRetObject>(list);
		return ret;

	}

	@Path("/updateUser")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String updateUser() {

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();

			User persistedUser = (User) session.load(User.class, 4);
			persistedUser.setFirstName("alpha!!");
			session.save(persistedUser);
			tx.commit();
		} catch (Exception e) {
			System.err.print("During transaction received error : "
					+ e.getMessage());
		} finally {
			session.close();
		}

		return "ok";
	}
}
