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
import javax.xml.bind.annotation.XmlElement;

/**
 * For an embeddable item (show, episode, playlist) this is information that can
 * be used to embed the player for that item on a web page.
 * 
 * @author Randy Simon
 */
public class PlayerItem extends Item {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The URL to the config file for this player.
	 */
	private String config;

	/**
	 * Indicates if this is the default player.
	 */
	private boolean defaultPlayer;

	/**
	 * The embed code for this player.
	 */
	private String embedCode;

	/**
	 * The height of the player embed.
	 */
	private int height;

	/**
	 * The width of the player embed
	 */
	private int width;

	/**
	 * Get the config URL for this player.
	 * 
	 * @return the config URL
	 */
	@XmlElement
	public String getConfig() {
		return config;
	}

	/**
	 * Set the config URL for this player.
	 * 
	 * @param config
	 *            the config URL to set
	 */
	public void setConfig(String config) {
		this.config = config;
	}

	/**
	 * Checks if this is the default player.
	 * 
	 * @return <code>true</code> if this is the default player.
	 */
	@XmlAttribute(name = "default")
	public boolean isDefaultPlayer() {
		return defaultPlayer;
	}

	/**
	 * Sets if this is the default player.
	 * 
	 * @param defaultPlayer
	 *            <code>true</code> if this is the default player.
	 */
	public void setDefaultPlayer(boolean defaultPlayer) {
		this.defaultPlayer = defaultPlayer;
	}

	/**
	 * Get the embed code for the player. This is the value used to embed the
	 * playlist/episode in a web page.
	 * 
	 * @return the embed code markup
	 */
	@XmlElement(name = "embed_code")
	public String getEmbedCode() {
		return embedCode;
	}

	/**
	 * Set the embed code for the player.
	 * 
	 * @param embedCode
	 *            the embed code to set
	 */
	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}

	/**
	 * Get the height of the player embed (in pixels).
	 * 
	 * @return the height
	 */
	@XmlAttribute
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the player embed (in pixels).
	 * 
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get the width of the player embed (in pixels)
	 * 
	 * @return the width
	 */
	@XmlAttribute
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the player embed (in pixels).
	 * 
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
}
