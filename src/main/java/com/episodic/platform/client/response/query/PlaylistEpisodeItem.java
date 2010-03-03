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
 * Represents an episode that is in a playlist and appears in the "playlists"
 * response.
 * 
 * @author Randy Simon
 */
public class PlaylistEpisodeItem extends Item {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The episode's position in the playlist
	 */
	private int position;

	/**
	 * Get the episode's position in the playlist
	 * 
	 * @return the position
	 */
	@XmlAttribute(required = true)
	public int getPosition() {
		return position;
	}

	/**
	 * Set the episode's position in the playlist.
	 * 
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

}
