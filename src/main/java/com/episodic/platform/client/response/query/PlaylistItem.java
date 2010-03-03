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

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.DateTime;

import com.episodic.platform.client.util.DateXmlAdapter;

/**
 * Represents a deserialized playlist from a playlists request.
 * 
 * @author Randy Simon
 */
public class PlaylistItem extends Item {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The playlist's created date..
	 */
	private DateTime createdAt;

	/**
	 * The custom fields for this playlist.
	 */
	private List<CustomFieldItem> customFields;

	/**
	 * The list of players associated with this episode.
	 */
	private List<PlayerItem> players;

	/**
	 * The list of playlists associated with this episode.
	 */
	private List<PlaylistEpisodeItem> episodes;

	/**
	 * The thumbnails for this episode.
	 */
	private List<ThumbnailItem> thumbnails;

	/**
	 * Get the playlist's created date.
	 * 
	 * @return the created date
	 */
	@XmlElement(name = "created_at")
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	public DateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * Set the playlist's created date
	 * 
	 * @param createdAt
	 *            the date to set
	 */
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the customField
	 */
	@XmlElementWrapper(name = "custom_fields")
	@XmlElements( { @XmlElement(name = "field", type = CustomFieldItem.class) })
	public List<CustomFieldItem> getCustomFields() {
		return customFields;
	}

	/**
	 * Set the custom fields for this playlist.
	 * 
	 * @param customFields
	 *            the customField to set
	 */
	public void setCustomFields(List<CustomFieldItem> customFields) {
		this.customFields = customFields;
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
	 * Get the list of episodes in this playlist.
	 * 
	 * @return the episode items
	 */
	@XmlElementWrapper(name = "episodes")
	@XmlElements( { @XmlElement(name = "episode", type = PlaylistEpisodeItem.class) })
	public List<PlaylistEpisodeItem> getEpisodes() {
		return episodes;
	}

	/**
	 * Set the list of episodes that are in this playlist.
	 * 
	 * @param episodes
	 *            the episodes to set
	 */
	public void setEpisodes(List<PlaylistEpisodeItem> episodes) {
		this.episodes = episodes;
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

	/**
	 * Find the custom field in the list specified by position.
	 * 
	 * @param position
	 *            the position of the custom field to find
	 * @return the custom field item or <code>null</code>
	 */
	public CustomFieldItem getCustomFieldByPosition(int position) {

		CustomFieldItem customField = null;

		if (customFields != null) {
			for (Iterator<CustomFieldItem> itr = customFields.iterator(); itr
					.hasNext();) {
				CustomFieldItem currentField = itr.next();
				if (currentField.getPosition() == position) {
					customField = currentField;
					break;
				}
			}
		}

		return customField;
	}
}
