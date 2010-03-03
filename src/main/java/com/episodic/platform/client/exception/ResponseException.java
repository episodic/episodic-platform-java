/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client.exception;

import com.episodic.platform.client.response.ErrorResponse;

/**
 * Wraps an Error Response from the server.
 * 
 * @author Randy Simon
 */
public class ResponseException extends EpisodicPlatformException {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The underlying parsed error response returned from Episodic.
	 */
	private ErrorResponse errorResponse;

	/**
	 * Constructor
	 * 
	 * @param errorResponse
	 *            the parsed error response.
	 */
	public ResponseException(ErrorResponse errorResponse) {
		super();
		this.errorResponse = errorResponse;
	}

	/**
	 * Get the error message from the response.
	 * 
	 * @return the error message from the Episodic response.
	 */
	@Override
	public String getMessage() {
		return errorResponse.getMessage();
	}

	/**
	 * Get details of the error response that caused this exception to be
	 * thrown.
	 * 
	 * @return the parsed error response.
	 */
	public ErrorResponse getResponse() {
		return this.errorResponse;
	}
}
