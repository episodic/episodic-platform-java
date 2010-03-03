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

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import com.episodic.platform.client.response.Response;

/**
 * Base class for a create or update episode request. The data in the response
 * for these methods is basically identical aside from the root element.
 * 
 * @author Randy Simon
 */
abstract public class CreateUpdateEpisodeResponse extends Response {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ID of the created or updated episode.
	 */
	private String episodeId;

	/**
	 * The list of pending uploads. This contains information about how to
	 * upload the video and/or thumbnail for the episode.
	 */
	private List<Upload> uploads = new ArrayList<Upload>();

	/**
	 * Gets the ID of the created or updated episode.
	 * 
	 * @return the ID
	 */
	@XmlAttribute(name = "episode_id")
	public String getEpisodeId() {
		return episodeId;
	}

	/**
	 * Sets the ID of the created or updated episode.
	 * 
	 * @param episodeId
	 *            the id to set
	 */
	public void setEpisodeId(String episodeId) {
		this.episodeId = episodeId;
	}

	/**
	 * Get the list of pending uploads. This contains information about how to
	 * upload the video and/or thumbnail for the episode.
	 * 
	 * @return the uploads
	 */
	@XmlElements( { @XmlElement(name = "upload", type = Upload.class) })
	public List<Upload> getUploads() {
		return uploads;
	}

	/**
	 * Set the list of pending uploads.
	 * 
	 * @param uploads
	 *            the uploads to set
	 */
	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}

	/**
	 * Get the pending upload params for a specified file. This method can be
	 * used on the response from
	 * {@link com.episodic.platform.client.WriteService#createEpisode(String, String, java.util.Map)}
	 * or or
	 * {@link com.episodic.platform.client.WriteService#updateEpisode(String, java.util.Map)}
	 * . The {@link Upload} object returned from this method can be passed to
	 * {@link com.episodic.platform.client.WriteService#uploadFileForEpisode(Upload, File)}
	 * .
	 * 
	 * @param filePath
	 *            the full filepath to the file to be uploaded.
	 * @return the upload params or <c>null</c> if the upload params cannot be
	 *         found.
	 */
	public Upload getUploadForFilePath(String filePath) {
		File file = new File(filePath);

		return getUploadForFile(file);
	}

	/**
	 * Get the pending upload params for a specified file. This method can be
	 * used on the response from
	 * {@link com.episodic.platform.client.WriteService#createEpisode(String, String, java.util.Map)}
	 * or or
	 * {@link com.episodic.platform.client.WriteService#updateEpisode(String, java.util.Map)}
	 * . The {@link Upload} object returned from this method can be passed to
	 * {@link com.episodic.platform.client.WriteService#uploadFileForEpisode(Upload, File)}
	 * 
	 * @param file
	 *            the file object for the file to be uploaded.
	 * @return the upload params or <c>null</c> if the upload params cannot be
	 *         found.
	 */
	public Upload getUploadForFile(File file) {
		String filename = file.getName();

		Upload upload = null;

		if (uploads != null) {
			for (Iterator<Upload> itr = uploads.iterator(); itr.hasNext();) {
				Upload currentUpload = itr.next();
				if (currentUpload.getFilename().equals(filename)) {
					upload = currentUpload;
					break;
				}
			}
		}

		return upload;
	}
}
