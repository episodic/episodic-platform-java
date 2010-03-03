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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A derserialized playlist list returned from a playlists request.
 * 
 * @author Randy Simon
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "playlists")
public class PlaylistsResponse extends CollectionResponse {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The list of playlist items that appear in the response
	 */
	private List<PlaylistItem> playlistItems;

	/**
	 * Get the list of playlist items from the response.
	 * 
	 * @return a list of {@link PlaylistItem} objects.
	 */
	@XmlElement(name = "playlist")
	public List<PlaylistItem> getPlaylists() {
		return playlistItems;
	}

	/**
	 * Set the list of playlist items for the response.
	 * 
	 * @param playlists
	 *            a list of {@link PlaylistItem} objects.
	 */
	public void setPlaylists(List<PlaylistItem> playlists) {
		this.playlistItems = playlists;
	}

	/**
	 * Find the playlist in the list specified by ID.
	 * 
	 * @param id
	 *            the id of the playlist to find
	 * @return the playlist item or <code>null</code>
	 */
	public PlaylistItem getPlaylistItemById(String id) {
		return (PlaylistItem)getItemById(id, playlistItems);
	}
}
