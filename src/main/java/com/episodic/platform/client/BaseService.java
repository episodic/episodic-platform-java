/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client;

/**
 * Common service methods factored out into a base class.
 * 
 * @author Randy Simon
 */
abstract public class BaseService {

	/**
	 * The connection to use when making requests to the Episodic Platform API
	 * server.
	 */
	protected Connection connection;

	/**
	 * Set the connection to use when making requests to the Episodic Platform
	 * API server.
	 * 
	 * @param connection
	 *            the connection to set.
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
