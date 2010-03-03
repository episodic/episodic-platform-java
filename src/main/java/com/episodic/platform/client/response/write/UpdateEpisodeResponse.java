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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is a response from the update episode method that includes the id of the
 * modified episode as well as data about how to upload a corresponding
 * video or thumbnail.
 * 
 * @author Randy Simon
 */
@XmlRootElement(name = "update_episode_response")
public class UpdateEpisodeResponse extends CreateUpdateEpisodeResponse {
	
	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
}
