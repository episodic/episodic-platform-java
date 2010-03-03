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
 * This exception is thrown when one or more of the required parameters is
 * missing.
 * 
 * To determine which parameter(s) caused the problem you can call
 * {@link #getMessage()}.
 * 
 * @author Randy Simon
 */
public class MissingRequiredParameterException extends ResponseException {

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
	public MissingRequiredParameterException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}
