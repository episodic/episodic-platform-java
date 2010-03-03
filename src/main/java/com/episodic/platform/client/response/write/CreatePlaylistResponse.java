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
 * This is a response from the create playlist method that includes the id of
 * the newly created playlist.
 * 
 * @author Randy Simon
 */
@XmlRootElement(name = "create_playlist_response")
public class CreatePlaylistResponse extends CreateUpdatePlaylistResponse {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
}
