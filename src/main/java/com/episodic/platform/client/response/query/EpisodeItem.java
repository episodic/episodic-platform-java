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

import com.episodic.platform.client.util.CommaSeperatedStringArrayXmlAdapter;
import com.episodic.platform.client.util.DateXmlAdapter;
import com.episodic.platform.client.util.EpisodeStatusXmlAdapter;

/**
 * Represents a deserialized episode from an episodes request.
 * 
 * @author Randy Simon
 */
public class EpisodeItem extends Item {

	/**
	 * Possible episode publishing states
	 */
	public static enum EpisodePublishingStatus {
		OFF_THE_AIR {
			public String toString() {
				return "off_the_air";
			}
		},
		ON_THE_AIR {
			public String toString() {
				return "on_the_air";
			}
		},
		PUBLISHING {
			public String toString() {
				return "publishing";
			}
		},
		PUBLISH_FAILED {
			public String toString() {
				return "publish_failed";
			}
		},
		WAITING_TO_AIR {
			public String toString() {
				return "waiting_to_air";
			}
		};

		/**
		 * Convert to a String.
		 * 
		 * @param enumType
		 * @param name
		 *            the status string value
		 * @return the corresponding status enum value.
		 */
		public static EpisodePublishingStatus valueOf(
				Class<EpisodePublishingStatus> enumType, String name) {

			if (name.equals(OFF_THE_AIR.toString())) {
				return OFF_THE_AIR;
			} else if (name.equals(ON_THE_AIR.toString())) {
				return ON_THE_AIR;
			} else if (name.equals(PUBLISHING.toString())) {
				return PUBLISHING;
			} else if (name.equals(PUBLISH_FAILED.toString())) {
				return PUBLISH_FAILED;
			} else if (name.equals(WAITING_TO_AIR.toString())) {
				return WAITING_TO_AIR;
			}

			throw new IllegalArgumentException();

		}
	}

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The episode's air date.
	 */
	private DateTime airDate;

	/**
	 * The custom fields for this episode.
	 */
	private List<CustomFieldItem> customFields;

	/**
	 * The list of downloads for this episode.
	 */
	private List<DownloadItem> downloads;

	/**
	 * The duration of the episode as a String. This is in the form "hh:mm:ss".
	 */
	private String duration;

	/**
	 * The episode's off air date
	 */
	private DateTime offAirDate;

	/**
	 * The list of players associated with this episode.
	 */
	private List<PlayerItem> players;

	/**
	 * The list of playlists associated with this episode.
	 */
	private List<EpisodePlaylistItem> playlists;

	/**
	 * The episode's publishing status as a string like "on_the_air" or
	 * "publishing"
	 */
	private EpisodePublishingStatus status;

	/**
	 * The list of tags associated with the episode.
	 */
	private String[] tags;

	/**
	 * The thumbnails for this episode.
	 */
	private List<ThumbnailItem> thumbnails;

	/**
	 * Get the episode's air date. This may be <code>null</code>.
	 * 
	 * @return the air date
	 */
	@XmlElement(name = "air_date")
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	public DateTime getAirDate() {
		return airDate;
	}

	/**
	 * Set the episode's air date
	 * 
	 * @param airDate
	 *            the date to set
	 */
	public void setAirDate(DateTime airDate) {
		this.airDate = airDate;
	}

	/**
	 * The list of custom fields set on this episode.
	 * 
	 * @return the customField
	 */
	@XmlElementWrapper(name = "custom_fields")
	@XmlElements( { @XmlElement(name = "field", type = CustomFieldItem.class) })
	public List<CustomFieldItem> getCustomFields() {
		return customFields;
	}

	/**
	 * Set the custom fields set on this episode.
	 * 
	 * @param customFields
	 *            the fields to set
	 */
	public void setCustomFields(List<CustomFieldItem> customFields) {
		this.customFields = customFields;
	}

	/**
	 * Get the list of downloads for this episode.
	 * 
	 * @return the download items
	 */
	@XmlElementWrapper(name = "downloads")
	@XmlElements( { @XmlElement(name = "download", type = DownloadItem.class) })
	public List<DownloadItem> getDownloads() {
		return downloads;
	}

	/**
	 * Set the downloads for this episode.
	 * 
	 * @param downloads
	 *            the download items to set
	 */
	public void setDownloads(List<DownloadItem> downloads) {
		this.downloads = downloads;
	}

	/**
	 * Get the duration of the episode as a String. This is in the form
	 * "hh:mm:ss".
	 * 
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Set the duration as a string like "hh:mm:ss".
	 * 
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Get the episode's off air date. This may be <code>null</code>.
	 * 
	 * @return the off air date
	 */
	@XmlElement(name = "off_air_date")
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	public DateTime getOffAirDate() {
		return offAirDate;
	}

	/**
	 * Set the episode's off air date
	 * 
	 * @param offAirDate
	 *            the date to set
	 */
	public void setOffAirDate(DateTime offAirDate) {
		this.offAirDate = offAirDate;
	}

	/**
	 * Get the list of players for this episode.
	 * 
	 * @return the players
	 */
	@XmlElementWrapper(name = "players")
	@XmlElements( { @XmlElement(name = "player", type = PlayerItem.class) })
	public List<PlayerItem> getPlayers() {
		return players;
	}

	/**
	 * Set the list of players for this episode.
	 * 
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(List<PlayerItem> players) {
		this.players = players;
	}

	/**
	 * Get the list of playlists that are associated with this episode.
	 * 
	 * @return the playlist items
	 */
	@XmlElementWrapper(name = "playlists")
	@XmlElements( { @XmlElement(name = "playlist", type = EpisodePlaylistItem.class) })
	public List<EpisodePlaylistItem> getPlaylists() {
		return playlists;
	}

	/**
	 * Set the list of playlists that are associated with this episode.
	 * 
	 * @param playlists
	 *            the playlists to set
	 */
	public void setPlaylists(List<EpisodePlaylistItem> playlists) {
		this.playlists = playlists;
	}

	/**
	 * Get the current publishing status of the episode.
	 * 
	 * @return the status
	 */
	@XmlElement
	@XmlJavaTypeAdapter(EpisodeStatusXmlAdapter.class)
	public EpisodePublishingStatus getStatus() {
		return status;
	}

	/**
	 * Set the publishing status of the episode.
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EpisodePublishingStatus status) {
		this.status = status;
	}

	/**
	 * Get the list of tags on this episode.
	 * 
	 * @return the tags
	 */
	@XmlElement
	@XmlJavaTypeAdapter(CommaSeperatedStringArrayXmlAdapter.class)
	public String[] getTags() {
		return tags;
	}

	/**
	 * Set the list of tags on this episode.
	 * 
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}

	/**
	 * Get the thumbnails for this episode.
	 * 
	 * @return the thumbnails
	 */
	@XmlElementWrapper(name = "thumbnails")
	@XmlElements( { @XmlElement(name = "thumbnail", type = ThumbnailItem.class) })
	public List<ThumbnailItem> getThumbnails() {
		return thumbnails;
	}

	/**
	 * Set the thumbnails for this episode.
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
