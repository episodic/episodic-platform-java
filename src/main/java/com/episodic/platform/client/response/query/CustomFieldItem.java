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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents a single custom field that can be set on an episode or playlist.
 * 
 * @author Randy Simon
 */
public class CustomFieldItem extends Item {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The custom field's position in the UI
	 */
	private int position;

	/**
	 * Indicates if the custom field is a required field.
	 */
	private boolean required;

	/**
	 * Indicates the type of the field.
	 */
	private String type;

	/**
	 * The values of the custom field
	 */
	private List<CustomFieldValue> values = new ArrayList<CustomFieldValue>();

	/**
	 * The list of values assigned to this field. Often this list will only have
	 * a single element.
	 * 
	 * @return the values of this field
	 */
	@XmlElement(name = "value")
	public List<CustomFieldValue> getValues() {
		return values;
	}

	/**
	 * Set the values of this field.
	 * 
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<CustomFieldValue> values) {
		this.values = values;
	}

	/**
	 * Get the custom field's position in the UI
	 * 
	 * @return the position
	 */
	@XmlAttribute(required = true)
	public int getPosition() {
		return position;
	}

	/**
	 * Set the custom field's position.
	 * 
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Check if the field is a required field.
	 * 
	 * @return <code>true</code if the field is required.
	 */
	@XmlAttribute(required = true)
	public boolean isRequired() {
		return required;
	}

	/**
	 * Set if the field is a required field.
	 * 
	 * @param required
	 *            <code>true</code if the field is required.
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * Get the type of the field. This will be a value like "text", "integer",
	 * etc.
	 * 
	 * @return the type
	 */
	@XmlAttribute(required = true)
	public String getType() {
		return type;
	}

	/**
	 * Set the value type of the field.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
