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

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import com.episodic.platform.client.response.Response;

/**
 * Base class for responses that include a collection of items that supports
 * pagination.
 * 
 * @author Randy Simon
 */
abstract public class CollectionResponse extends Response {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The current page for a paginated response.
	 */
	private long page;

	/**
	 * The total number of pages.
	 */
	private long pages;

	/**
	 * The number of items per page.
	 */
	private long perPage;

	/**
	 * The total number of items that matched the query.
	 */
	private long total;

	/**
	 * Get the current page for a paginated response.
	 * 
	 * @return the current page number.
	 */
	@XmlAttribute(required = true)
	public long getPage() {
		return page;
	}

	/**
	 * Sets the current page for a paginated response.
	 * 
	 * @param page
	 *            the current page number to set.
	 */
	public void setPage(long page) {
		this.page = page;
	}

	/**
	 * Get the total number of pages.
	 * 
	 * @return the total number of pages
	 */
	@XmlAttribute(required = true)
	public long getPages() {
		return pages;
	}

	/**
	 * Set the total number of pages.
	 * 
	 * @param pages
	 *            the total number of pages to set.
	 */
	public void setPages(long pages) {
		this.pages = pages;
	}

	/**
	 * Get the number of items per page.
	 * 
	 * @return the number of items per page.
	 */
	@XmlAttribute(name = "per_page", required = true)
	public long getPerPage() {
		return perPage;
	}

	/**
	 * Set the number of items per page.
	 * 
	 * @param perPage
	 *            the number of items per page to set.
	 */
	public void setPerPage(long perPage) {
		this.perPage = perPage;
	}

	/**
	 * Get the total number of items that matched the query.
	 * 
	 * @return the total number of items
	 */
	@XmlAttribute(required = true)
	public long getTotal() {
		return total;
	}

	/**
	 * Set the total number of items that matched the query.
	 * 
	 * @param total
	 *            the total to set.
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * Helper method used by subclasses to get a sepecific item specified by id
	 * 
	 * @param id
	 *            the id of the item to find
	 * @param items
	 *            the list of items to search
	 * @return the found item or <code>null</code>
	 */
	protected Item getItemById(String id, List<? extends Item> items) {

		Item item = null;

		if (items != null) {
			for (Iterator<? extends Item> itr = items.iterator(); itr.hasNext();) {
				Item currentItem = itr.next();
				if (currentItem.getId().equals(id)) {
					item = currentItem;
					break;
				}
			}
		}

		return item;
	}
}
