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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Represents the value of a custom field. Generally the {@link #id} value will be <code>null</code> except
 * when the {@link CustomFieldItem#getType()} method returns "external_select" then both the id and value
 * properties of this object will be populated.
 * 
 * @author Randy Simon
 */
public class CustomFieldValue implements Serializable {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * For external select fields this is not <code>null</code>. However, in
	 * most cases this is just null.
	 */
	private String id;

	/**
	 * The value of the field as a String.
	 */
	private String value;

	/**
	 * This will be <code>null</code> if this is a value for an external select
	 * field.
	 * 
	 * @return the id of the external select field.
	 */
	@XmlAttribute
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the external select field.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the value of the field as a String.
	 * 
	 * @return the value
	 */
	@XmlValue
	public String getValue() {
		return value;
	}

	/**
	 * Sets the field value as a String.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value != null ? value.trim() : null;
	}
}
