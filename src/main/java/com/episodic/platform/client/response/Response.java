/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client.response;

import java.io.Serializable;

/**
 * Base class for all server responses. Generally this class is extended to
 * include the parsed data. However, this class also provides access to the
 * response code and the full body of the response.
 * 
 * @author Randy Simon
 */
public class Response implements Serializable {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The raw XML from the response
	 */
	private String body;

	/**
	 * The HTTP status code from the response.
	 */
	private int statusCode;

	/**
	 * Get the XML from the response.
	 * 
	 * @return the xml
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Set the XML from the HTTP response.
	 * 
	 * @param body
	 *            the xml to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Get the HTTP status code from the response
	 * 
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Set the HTTP status code.
	 * 
	 * @param statusCode
	 *            the code to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
