/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client.response.analytics;

import javax.xml.bind.annotation.XmlElement;

import com.episodic.platform.client.response.Response;

/**
 * This is a response from the analytics service that includes a token.
 * 
 * @author Randy Simon
 */
abstract public class TokenResponse extends Response {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The analytics report token from the response.
	 */
	private String token;

	/**
	 * Gets the token from the response.
	 * 
	 * @return the token
	 */
	@XmlElement(name = "report_token")
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 * 
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
