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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

/**
 * Represents a deserialized show from a shows request.
 * 
 * @author Randy Simon
 */
public class ShowItem extends Item {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The URL to the itunes podcast. 
	 */
	private String itunesURL;

	/**
	 * The URL to the main page that hosts this show.
	 */
	private String pageURL;
	
	/**
	 * The list of players associated with this episode.
	 */
	private List<PlayerItem> players;

	/**
	 * The thumbnails for this episode.
	 */
	private List<ThumbnailItem> thumbnails;

	/**
	 * @return the itunesURL
	 */
	@XmlElement(name = "itunes_url")
	public String getItunesURL() {
		return itunesURL;
	}

	/**
	 * @param itunesURL the itunesURL to set
	 */
	public void setItunesURL(String itunesURL) {
		this.itunesURL = itunesURL;
	}

	/**
	 * @return the pageURL
	 */
	@XmlElement(name = "page_url")
	public String getPageURL() {
		return pageURL;
	}

	/**
	 * @param pageURL the pageURL to set
	 */
	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	/**
	 * Get the list of players for this playlist.
	 * 
	 * @return the players
	 */
	@XmlElementWrapper(name = "players")
	@XmlElements( { @XmlElement(name = "player", type = PlayerItem.class) })
	public List<PlayerItem> getPlayers() {
		return players;
	}

	/**
	 * Set the list of players for this playlist.
	 * 
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(List<PlayerItem> players) {
		this.players = players;
	}

	/**
	 * Get the thumbnails for this playlist.
	 * 
	 * @return the thumbnails
	 */
	@XmlElementWrapper(name = "thumbnails")
	@XmlElements( { @XmlElement(name = "thumbnail", type = ThumbnailItem.class) })
	public List<ThumbnailItem> getThumbnails() {
		return thumbnails;
	}

	/**
	 * Set the thumbnails for this playlist.
	 * 
	 * @param thumbnails
	 *            the thumbnails to set
	 */
	public void setThumbnails(List<ThumbnailItem> thumbnails) {
		this.thumbnails = thumbnails;
	}
}
