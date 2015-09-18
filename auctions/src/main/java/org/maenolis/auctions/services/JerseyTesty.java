package org.maenolis.auctions.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maenolis.auctions.dao.Auction;
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.retObj.AuctionRetObject;
import org.maenolis.auctions.services.retObj.MessageRetObject;
import org.maenolis.auctions.services.retObj.UserRetObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;

@Path("/test")
public class JerseyTesty {

	@Path("/auctions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<AuctionRetObject> getterAuctions() {

		List<AuctionRetObject> list = Auction.getAllAuctions();
		ListWrapper<AuctionRetObject> ret = new ListWrapper<AuctionRetObject>(
				list);
		return ret;

	}

	@Path("/messages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<MessageRetObject> getterMessages() {

		List<MessageRetObject> list = User.getUserReceivedMessages(4);

		ListWrapper<MessageRetObject> ret = new ListWrapper<MessageRetObject>(
				list);
		return ret;

	}

	@Path("/users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<UserRetObject> getterUsers() {

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
}
