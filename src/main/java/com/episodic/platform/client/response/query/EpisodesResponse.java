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
 * A derserialized Episode item returned from an episodes request.
 * 
 * @author Randy Simon
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "episodes")
public class EpisodesResponse extends CollectionResponse {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The list of episode items that appear in the response
	 */
	private List<EpisodeItem> episodeItems;

	/**
	 * Get the list of episode items from the response.
	 * 
	 * @return a list of {@link EpisodeItem} objects.
	 */
	@XmlElement(name = "episode")
	public List<EpisodeItem> getEpisodes() {
		return episodeItems;
	}

	/**
	 * Set the list of episode items for the response.
	 * 
	 * @param episodes
	 *            a list of {@link EpisodeItem} objects.
	 */
	public void setEpisodes(List<EpisodeItem> episodes) {
		this.episodeItems = episodes;
	}

	/**
	 * Find the episode in the list specified by ID.
	 * 
	 * @param id
	 *            the id of the episode to find
	 * @return the episode item or <code>null</code>
	 */
	public EpisodeItem getEpisodeItemById(String id) {
		return (EpisodeItem)getItemById(id, episodeItems);
	}
}
