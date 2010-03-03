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

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Represents a playlist that is associated with an episode and appears in the
 * "episodes" response.
 * 
 * @author Randy Simon
 */
public class EpisodePlaylistItem extends Item {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The playlist's position
	 */
	private int position;

	/**
	 * Get the playlist's position
	 * 
	 * @return the position
	 */
	@XmlAttribute(required = true)
	public int getPosition() {
		return position;
	}

	/**
	 * Set the playlist's position.
	 * 
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

}
