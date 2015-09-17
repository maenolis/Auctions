package org.maenolis.auctions.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.maenolis.auctions.services.literals.PropertyProvider;
import org.maenolis.auctions.services.retObj.LogoutRetObject;

public class UserState {

	public static boolean isLogged(final HttpSession session) {
		if (session.getAttribute(PropertyProvider.USERID) == null
				|| session.getAttribute(PropertyProvider.USERNAME) == null) {
			return false;
		}
		return true;
	}

	public static LogoutRetObject logoutUser(final HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		LogoutRetObject logoutRetObject = new LogoutRetObject();

		logoutRetObject.setMessage("Nobody is logged in.");
		logoutRetObject.setStatus(PropertyProvider.NOK);
		if (session != null && isLogged(request.getSession())) {
			session.invalidate();
			logoutRetObject.setMessage("User logged out.");
			logoutRetObject.setStatus(PropertyProvider.OK);
		}

		return logoutRetObject;
	}
}
