package org.maenolis.auctions.userManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.maenolis.auctions.dao.User;
import org.maenolis.auctions.services.literals.PropertyProvider;

/**
 * The Class UserState.
 */
public class UserState {

	public static boolean checkAdmin(final HttpServletRequest request,
			final HttpServletResponse response) {
		if (!isLogged(request.getSession())) {
			return false;
		}
		if (!User.getUser(
				(int) request.getSession()
						.getAttribute(PropertyProvider.USERID)).isAdmin()) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is logged.
	 *
	 * @param session
	 *            the session
	 * @return true, if is logged
	 */
	public static boolean isLogged(final HttpSession session) {
		if (session.getAttribute(PropertyProvider.USERID) == null
				|| session.getAttribute(PropertyProvider.USERNAME) == null) {
			return false;
		}
		if (!User.getUser((int) session.getAttribute(PropertyProvider.USERID))
				.isConfirmed()) {
			return false;
		}
		return true;
	}

	/**
	 * Logout user.
	 *
	 * @param request
	 *            the request
	 * @return the logout ret object
	 */
	public static void logoutUser(final HttpServletRequest request,
			final HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (isLogged(request.getSession())) {
			session.invalidate();
		}
	}

	public static int getId(final HttpSession session) {
		return (int) session.getAttribute(PropertyProvider.USERID);
	}
}
