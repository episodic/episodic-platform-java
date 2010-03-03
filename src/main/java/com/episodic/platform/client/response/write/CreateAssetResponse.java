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
import javax.xml.bind.annotation.XmlRootElement;

import com.episodic.platform.client.response.Response;

/**
 * This is a response from the create asset method that includes the id of the
 * newly created asset.
 * 
 * @author Randy Simon
 */
@XmlRootElement(name = "create_asset_response")
public class CreateAssetResponse extends Response {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ID of the newly created asset.
	 */
	private String assetId;

	/**
	 * Gets the ID of the newly created asset..
	 * 
	 * @return the ID
	 */
	@XmlAttribute(name = "asset_id")
	public String getAssetId() {
		return assetId;
	}

	/**
	 * Sets the ID of the newly created asset.
	 * 
	 * @param assetId
	 *            the id to set
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
}
