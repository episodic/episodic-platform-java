/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client.response.write;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * A name/value pair needed for uploading a video or thumbnail
 * 
 * @author Randy Simon
 */
public class UploadParam {

	/**
	 * The name of the parameter.
	 */
	private String name;

	/**
	 * The value of the parameter.
	 */
	private String value;

	/**
	 * Get the name of the parameter.
	 * 
	 * @return the name
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the parameter.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the value of the parameter.
	 * 
	 * @return the value
	 */
	@XmlValue
	public String getValue() {
		return value;
	}

	/**
	 * Set the value of the parameter.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
