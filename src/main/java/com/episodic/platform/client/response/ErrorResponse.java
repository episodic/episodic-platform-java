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

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * If the server responds with an error this class is used to parse the
 * response.
 * 
 * @author Randy Simon
 */
@XmlRootElement(name = "error")
public class ErrorResponse extends Response {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The error code from the response.
	 */
	private int code;

	/**
	 * In the case that the error was caused by invalid parameters this list is
	 * populated with the list of guilty parameter names.
	 */
	private List<InvalidParameter> invalidParameters;

	/**
	 * The error message from the response.
	 */
	private String message;

	/**
	 * Gets the error code from the response.
	 * 
	 * @return the error code from the response.
	 */
	@XmlElement
	public int getCode() {
		return code;
	}

	/**
	 * Sets the error code.
	 * 
	 * @param code
	 *            the Episodic error code.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Get the list of invalid parameters that caused the error.
	 * 
	 * @return the list of parameter names.
	 */
	@XmlElementWrapper(name = "invalid_parameters")
	@XmlElements( { @XmlElement(name = "invalid_parameter", type = InvalidParameter.class) })
	public List<InvalidParameter> getInvalidParameters() {
		return invalidParameters;
	}

	/**
	 * Set the list of invalid parameters that caused the error.
	 * 
	 * @param invalidParameters
	 *            the list or parameter names.
	 */
	public void setInvalidParameters(List<InvalidParameter> invalidParameters) {
		this.invalidParameters = invalidParameters;
	}

	/**
	 * Gets the error message from the response.
	 * 
	 * @return the error message
	 */
	@XmlElement
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param message
	 *            the error message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
