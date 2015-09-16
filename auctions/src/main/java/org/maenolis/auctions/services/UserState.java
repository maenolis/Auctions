package org.maenolis.auctions.services;

import javax.servlet.http.HttpSession;

import org.maenolis.auctions.services.literals.PropertyProvider;

public class UserState {

	public static boolean isLogged(final HttpSession session) {
		if (session.getAttribute(PropertyProvider.USERID) == null
				|| session.getAttribute(PropertyProvider.USERNAME) == null) {
			return false;
		}
		return true;
	}

	public static void logoutUser() {

	}
}
