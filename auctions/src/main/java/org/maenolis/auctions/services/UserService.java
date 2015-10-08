package org.maenolis.auctions.services;

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

import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.AuctionRetObject;
import org.maenolis.auctions.services.retObj.BooleanRetObject;
import org.maenolis.auctions.services.retObj.UserRetObject;
import org.maenolis.auctions.services.wrapper.ListWrapper;
import org.maenolis.auctions.userManagement.UserState;

/**
 * The Class UserService.
 */
@Path("/User")
public class UserService {

	/**
	 * Confirm.
	 *
	 * @param user
	 *            the user
	 * @return the string
	 */
	@Path("/confirm")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public UserRetObject confirm(final UserRetObject user,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {
		if (!UserState.checkAdmin(request, response)) {
			return new UserRetObject();
		}
		User.confirm(user.getId());
		return user;
	}

	/**
	 * Update user.
	 *
	 * @param user
	 *            the user
	 * @param request
	 *            the request
	 * @return the string
	 */
	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserRetObject updateUser(final UserRetObject user,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		UserRetObject ret = new UserRetObject();
		ret.setStatus(PropertyProvider.NOK);

		if (!UserState.isLogged(request.getSession())) {
			return ret;
		}

		System.out.println("updateUserereceived : " + user);
		User.updateUser(user);
		return User.transformToRetObject(User.getUser(user.getId()));
	}

	/**
	 * Gets the user.
	 *
	 * @param request
	 *            the request
	 * @return the user
	 */
	@Path("/getUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserRetObject getUser(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		if (!UserState.isLogged(request.getSession())) {
			return new UserRetObject();
		}

		return User.transformToRetObject(User.getUser((int) request
				.getSession().getAttribute(PropertyProvider.USERID)));
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	@Path("/users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper<UserRetObject> getterUsers(
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		if (!UserState.checkAdmin(request, response)) {

			return new ListWrapper<UserRetObject>();
		}

		List<UserRetObject> list = User.getAllUsers();

		ListWrapper<UserRetObject> ret = new ListWrapper<UserRetObject>(list);

		return ret;

	}

	@Path("/isLogged")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BooleanRetObject isLogged(@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {
		return new BooleanRetObject(UserState.isLogged(request.getSession()));
	}

	@Path("/owsAuction")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BooleanRetObject ownsAuction(final AuctionRetObject auctionJs,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) {

		if (!UserState.isLogged(request.getSession())) {
			return new BooleanRetObject(false);
		}
		System.out.println(auctionJs);
		return new BooleanRetObject(auctionJs.getOwnerId() == (int) request
				.getSession().getAttribute(PropertyProvider.USERID));

	}

}
