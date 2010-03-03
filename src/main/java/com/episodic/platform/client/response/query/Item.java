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

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Base class of common attributes for items included in a query response.
 * 
 * @author Randy Simon
 */
abstract public class Item implements Serializable {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The description of the item.
	 */
	private String description;

	/**
	 * The id of the item.
	 */
	private String id;

	/**
	 * The name of the item
	 */
	private String name;

	/**
	 * The error element will only exist if the requested item could not be
	 * found.
	 */
	@XmlElement
	private String error;

	/**
	 * Get the description of the item. This may be <c>null</c> if not specified
	 * in the response.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the item.
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the Episodic ID of this item.
	 * 
	 * @return the id
	 */
	@XmlElement
	public String getId() {
		return id;
	}

	/**
	 * Set the Episodic ID of this item
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the name of this item
	 * 
	 * @return the name
	 */
	@XmlElement
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this item
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * When requesting specific items in a query it may be the case that the
	 * item specified could not be found. In this case the XML returned looks
	 * something like the following for the item: <code>
	 * 		<id>adfadsfasdf</id>
	 * 		<error>
	 * 			<code>6</code> 
	 *          <message>Show not found</message> 
	 *      </error> 
	 * </code>
	 * 
	 * Therefore, this method allows the caller to check if the item actually
	 * exists before trying to pull out other properties that will result in an
	 * exception. You really only need to use this method when you make a query
	 * request with IDs specified.
	 * 
	 * @return <code>true</code> if the item was found and requests for other
	 *         attributes will succeed.
	 */
	public boolean exists() {
		return error == null;
	}
}
