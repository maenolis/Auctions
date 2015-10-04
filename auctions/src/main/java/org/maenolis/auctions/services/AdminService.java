package org.maenolis.auctions.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.maenolis.auctions.dao.Auction;
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.retObj.AuctionRetObject;
import org.maenolis.auctions.services.retObj.BooleanRetObject;
import org.maenolis.auctions.userManagement.UserState;

/**
 * The Class AdminService.
 */
@Path("/Admin")
public class AdminService {

	@GET
	@Path("/isAdmin")
	@Produces(MediaType.APPLICATION_JSON)
	public BooleanRetObject isAdmin(@Context final HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (!UserState.isLogged(session)) {
			return new BooleanRetObject(false);
		} else {
			return new BooleanRetObject(User.getUser(UserState.getId(session))
					.isAdmin());
		}

	}

	@Path("/auctions")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAll() {

		List<AuctionRetObject> list = Auction.getAllAuctions();

		GenericEntity<List<AuctionRetObject>> ret = new GenericEntity<List<AuctionRetObject>>(
				list) {
		};

		return Response.ok(ret).build();
	}
}
