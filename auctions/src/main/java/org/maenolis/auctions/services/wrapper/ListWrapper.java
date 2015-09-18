package org.maenolis.auctions.services.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ListWrapper.
 *
 * @param <T>
 *            the generic type
 */
@XmlRootElement
public class ListWrapper<T> {

	/** The items. */
	private List<T> items;

	/**
	 * Instantiates a new list wrapper.
	 */
	public ListWrapper() {
		items = new ArrayList<T>();
	}

	/**
	 * Instantiates a new list wrapper.
	 *
	 * @param items
	 *            the items
	 */
	public ListWrapper(final List<T> items) {
		this.items = items;
	}

	@XmlAnyElement(lax = true)
	public List<T> getItems() {
		return items;
	}

}
