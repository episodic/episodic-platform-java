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

/**
 * This exception when a file cannot be uploaded to the location that was
 * specified in the response of a
 * {@link com.episodic.platform.client.WriteService#createEpisode(String, String, java.util.Map)}
 * or
 * {@link com.episodic.platform.client.WriteService#updateEpisode(String, java.util.Map)}
 * call.
 * 
 * @author Randy Simon
 */
public class FileUploadFailedException extends Exception {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The HTTP status code returned.
	 */
	private int statusCode;

	/**
	 * Constructor
	 * 
	 * @param code
	 *            the status code returned
	 * @param message
	 *            the message in the response.
	 */
	public FileUploadFailedException(int code, String message) {
		super(message);
		statusCode = code;
	}

	/**
	 * Get the status code returned from the HTTP response.
	 * 
	 * @return the HTTP status code
	 */
	public int getStatusCode() {
		return statusCode;
	}
}
