package org.maenolis.auctions.services.retObj;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class BooleanRetObject.
 */
@XmlRootElement
public class BooleanRetObject {

	/** The flag. */
	private boolean flag;

	/**
	 * Instantiates a new boolean ret object.
	 *
	 * @param flag
	 *            the flag
	 */
	public BooleanRetObject(final boolean flag) {
		super();
		this.flag = flag;
	}

	/**
	 * Instantiates a new boolean ret object.
	 */
	public BooleanRetObject() {
		super();
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(final boolean flag) {
		this.flag = flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BooleanRetObject [flag=");
		builder.append(flag);
		builder.append("]");
		return builder.toString();
	}

}
