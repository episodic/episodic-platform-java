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

import com.episodic.platform.client.response.Response;

/**
 * Base class for a create or update playlist request. The data in the response
 * for these methods is basically identical aside from the root element.
 * 
 * @author Randy Simon
 */
abstract public class CreateUpdatePlaylistResponse extends Response {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ID of the created or updated playlist.
	 */
	private String playlistId;

	/**
	 * Gets the ID of the created or updated playlist.
	 * 
	 * @return the ID
	 */
	@XmlAttribute(name = "playlist_id")
	public String getPlaylistId() {
		return playlistId;
	}

	/**
	 * Sets the ID of the created or updated playlist.
	 * 
	 * @param playlistId
	 *            the id to set
	 */
	public void setPlaylistId(String playlistId) {
		this.playlistId = playlistId;
	}
}
