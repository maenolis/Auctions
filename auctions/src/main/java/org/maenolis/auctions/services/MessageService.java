package org.maenolis.auctions.services;

import java.io.IOException;
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
import org.maenolis.auctions.dao.Message;
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.MessageRetObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;
import org.maenolis.auctions.userManagement.UserState;

/**
 * The Class MessageService.
 */
@Path("/Message")
public class MessageService {

	/**
	 * New message.
	 *
	 * @param message
	 *            the message
	 * @param request
	 *            the request
	 * @throws IOException
	 */
	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void newMessage(final MessageRetObject message,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) throws IOException {

		UserState.checkState(request, response);

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();

			message.setSenderId((int) request.getSession().getAttribute(
					PropertyProvider.USERID));
			session.save(new Message(message));
			tx.commit();
		} catch (Exception e) {
			System.err.print("During transaction received error : "
					+ e.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * Gets the ter messages.
	 *
	 * @return the ter messages
	 * @throws IOException
	 */
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<MessageRetObject> getterMessages(
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) throws IOException {

		UserState.checkState(request, response);

		List<MessageRetObject> list = User.getUserReceivedMessages(4);

		ListWrapper<MessageRetObject> ret = new ListWrapper<MessageRetObject>(
				list);
		return ret;

	}

	/**
	 * Gets the messages from user.
	 *
	 * @param message
	 *            the message
	 * @return the messages from user
	 * @throws IOException
	 */
	@Path("/from")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<MessageRetObject> getMessagesFromUser(
			final MessageRetObject message,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) throws IOException {

		UserState.checkState(request, response);

		List<MessageRetObject> list = User.getUserReceivedMessagesFrom(
				message.getSenderId(), message.getReceiverId());

		ListWrapper<MessageRetObject> ret = new ListWrapper<MessageRetObject>(
				list);
		return ret;

	}

}
