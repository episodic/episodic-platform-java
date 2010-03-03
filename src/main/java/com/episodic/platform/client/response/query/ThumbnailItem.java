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
 * Thumbnail data for a thumbnail associated with an item (show, episode,
 * playlist).
 * 
 * @author Randy Simon
 */
public class ThumbnailItem implements Serializable {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The height of the thumbnail.
	 */
	private int height;

	/**
	 * The url to the thumbnail.
	 */
	private String url;

	/**
	 * The width of the thumbnail
	 */
	private int width;

	/**
	 * Get the height of the thumbnail (in pixels).
	 * 
	 * @return the height
	 */
	@XmlAttribute
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the thumbnail (in pixels).
	 * 
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get the url to the thumbnail.
	 * 
	 * @return the url
	 */
	@XmlValue
	public String getUrl() {
		return url;
	}

	/**
	 * Set the url of the thumbnail.
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get the width of the thumbnail (in pixels)
	 * 
	 * @return the width
	 */
	@XmlAttribute
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the thumbnail (in pixels).
	 * 
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
}
