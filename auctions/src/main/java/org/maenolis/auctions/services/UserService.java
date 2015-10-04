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
import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;
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
	public UserRetObject confirm(final UserRetObject user) {
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
	public String updateUser(final UserRetObject user,
			@Context final HttpServletRequest request) {

		String ret = PropertyProvider.NOK;
		System.out.println("updateUserereceived : " + user);

		Session session = null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			Transaction tx;
			tx = session.beginTransaction();

			session.save(new User(user));
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
	 * Gets the user.
	 *
	 * @param request
	 *            the request
	 * @return the user
	 */
	@Path("/getUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserRetObject getUser(@Context final HttpServletRequest request) {

		if (request.getSession().getAttribute(PropertyProvider.USERID) == null) {
			UserRetObject ret = new UserRetObject();
			ret.setStatus(PropertyProvider.NOK);
			return ret;
		}

		return User.transformToRetObject(User.getUser((int) request
				.getSession().getAttribute(PropertyProvider.USERID)));
	}

	// TODO: remove!!
	@Path("/isLogged")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BooleanRetObject isLogged(@Context final HttpServletRequest request) {
		if (request.getSession().getAttribute(PropertyProvider.USERID) == null) {
			return new BooleanRetObject(false);
		}
		return new BooleanRetObject(true);
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

		UserState.checkState(request, response);
		// TODO: Admin check!!

		List<UserRetObject> list = User.getAllUsers();

		ListWrapper<UserRetObject> ret = new ListWrapper<UserRetObject>(list);

		return ret;

	}
}
