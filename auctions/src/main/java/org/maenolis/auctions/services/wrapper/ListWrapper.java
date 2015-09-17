package org.maenolis.auctions.services.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlSeeAlso({ AuctionRetObject.class })
@XmlRootElement
public class ListWrapper<T> {

	private List<T> items;

	public ListWrapper() {
		items = new ArrayList<T>();
	}

	public ListWrapper(final List<T> items) {
		this.items = items;
	}

	@XmlAnyElement(lax = true)
	public List<T> getItems() {
		return items;
	}

}
