package org.maenolis.auctions.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.maenolis.auctions.dao.Auction;
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.AdminAuctionRetObject;
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
	public BooleanRetObject isAdmin(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		if (!UserState.isLogged(request.getSession())) {
			return new BooleanRetObject(false);
		}

		return new BooleanRetObject(User.getUser(
				UserState.getId(request.getSession())).isAdmin());

	}

	@Path("/auctions")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAll(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) throws IOException {

		if (!UserState.checkAdmin(request, response)) {
			response.sendRedirect(PropertyProvider.REDIRECTIONURL);
			return null;
		}

		List<AdminAuctionRetObject> list = Auction.transformForAdminList();

		GenericEntity<List<AdminAuctionRetObject>> ret = new GenericEntity<List<AdminAuctionRetObject>>(
				list) {
		};

		return Response.ok(ret).build();
	}
}
