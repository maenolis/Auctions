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
import org.maenolis.auctions.dao.Message;
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.MessageRetObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;

@Path("/Message")
public class MessageService {

	@Path("/new")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void newMessage(final MessageRetObject message,
			@Context final HttpServletRequest request) {

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

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<MessageRetObject> getterMessages() {

		List<MessageRetObject> list = User.getUserReceivedMessages(4);

		ListWrapper<MessageRetObject> ret = new ListWrapper<MessageRetObject>(
				list);
		return ret;

	}

	@Path("/from")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<MessageRetObject> getMessagesFromUser(
			final MessageRetObject message) {

		List<MessageRetObject> list = User.getUserReceivedMessagesFrom(
				message.getSenderId(), message.getReceiverId());

		ListWrapper<MessageRetObject> ret = new ListWrapper<MessageRetObject>(
				list);
		return ret;

	}

}
