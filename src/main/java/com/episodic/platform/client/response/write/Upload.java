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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * Contains the list of upload params used to upload a video or thumbnail for a
 * newly created or updated episode.
 * 
 * @see CreateUpdateEpisodeResponse#getUploadForFile(java.io.File)
 * @see CreateUpdateEpisodeResponse#getUploadForFilePath(String)
 * 
 * @author Randy Simon
 */
public class Upload {

	/**
	 * The name of the file to be uploaded.
	 */
	private String filename;

	/**
	 * The list of params that need to be included in the POST request.
	 */
	private List<UploadParam> uploadParams = new ArrayList<UploadParam>();

	/**
	 * The URL endpoint to POST to.
	 */
	private String url;

	/**
	 * Get the name of the file to be uploaded.
	 * 
	 * @return the filename
	 */
	@XmlAttribute
	public String getFilename() {
		return filename;
	}

	/**
	 * Set the name of the file to be uploaded.
	 * 
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Get the list of params that need to be included in the POST request.
	 * 
	 * @return the upload params
	 */
	@XmlElements( { @XmlElement(name = "param", type = UploadParam.class) })
	public List<UploadParam> getUploadParams() {
		return uploadParams;
	}

	/**
	 * Set the list of params that need to be included in the POST request.
	 * 
	 * @param uploadParams
	 *            the upload params to set
	 */
	public void setUploadParams(List<UploadParam> uploadParams) {
		this.uploadParams = uploadParams;
	}

	/**
	 * Get the URL endpoint to POST to.
	 * 
	 * @return the url
	 */
	@XmlAttribute
	public String getUrl() {
		return url;
	}

	/**
	 * Set the URL endpoint to POST to.
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
