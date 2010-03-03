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
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents the download elements in the XML response. The download item is
 * essentially a link to a downloadable version of a video.
 * 
 * @author Randy Simon
 */
public class DownloadItem implements Serializable {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The height of the download.
	 */
	private int height;

	/**
	 * The url to the download.
	 */
	private String url;

	/**
	 * The width of the download
	 */
	private int width;

	/**
	 * Get the height of the download (in pixels).
	 * 
	 * @return the height
	 */
	@XmlAttribute
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the download (in pixels).
	 * 
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get the url to the download.
	 * 
	 * @return the url
	 */
	@XmlElement
	public String getUrl() {
		return url;
	}

	/**
	 * Set the url of the download.
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get the width of the download (in pixels)
	 * 
	 * @return the width
	 */
	@XmlAttribute
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the download (in pixels).
	 * 
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
}
