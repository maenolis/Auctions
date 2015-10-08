package org.maenolis.auctions.services.properties;

/**
 * The Class PropertyHelper.
 */
public class PropertyHelper {

	/**
	 * Check property.
	 *
	 * @param str1
	 *            the str1
	 * @param str2
	 *            the str2
	 * @return true, if successful
	 */
	public static boolean checkProperty(final String str1, final String str2) {
		boolean ret = true;
		if (str2 == null || str2 == "" || str2.equals(str1)) {
			ret = false;
		}
		return ret;
	}
}
