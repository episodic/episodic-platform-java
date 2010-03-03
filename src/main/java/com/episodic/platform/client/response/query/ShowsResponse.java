/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client.response.query;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A derserialized shows list returned from a shows request.
 * 
 * @author Randy Simon
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "shows")
public class ShowsResponse extends CollectionResponse {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The list of show items that appear in the response
	 */
	private List<ShowItem> showItems;

	/**
	 * Get the list of show items from the response.
	 * 
	 * @return a list of {@link ShowItem} objects.
	 */
	@XmlElement(name = "show")
	public List<ShowItem> getShows() {
		return showItems;
	}

	/**
	 * Set the list of show items for the response.
	 * 
	 * @param showItems
	 *            a list of {@link ShowItem} objects.
	 */
	public void setShows(List<ShowItem> showItems) {
		this.showItems = showItems;
	}

	/**
	 * Find the show in the list specified by ID.
	 * 
	 * @param id
	 *            the id of the show to find
	 * @return the show item or <code>null</code>
	 */
	public ShowItem getShowItemById(String id) {
		return (ShowItem) getItemById(id, showItems);
	}
}
