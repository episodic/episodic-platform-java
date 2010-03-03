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
 * This exception is thrown when the requested resource cannot be found.
 * 
 * @author Randy Simon
 */
public class NotFoundException extends ResponseException {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param errorResponse
	 *            the parsed error response.
	 */
	public NotFoundException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}
