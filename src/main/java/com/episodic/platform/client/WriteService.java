/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.episodic.platform.client.exception.FileUploadFailedException;
import com.episodic.platform.client.exception.ResponseException;
import com.episodic.platform.client.response.write.CreateAssetResponse;
import com.episodic.platform.client.response.write.CreateEpisodeResponse;
import com.episodic.platform.client.response.write.CreatePlaylistResponse;
import com.episodic.platform.client.response.write.UpdateEpisodeResponse;
import com.episodic.platform.client.response.write.UpdatePlaylistResponse;
import com.episodic.platform.client.response.write.Upload;
import com.episodic.platform.client.response.write.UploadParam;

/**
 * Class for making request to the Episodic Platform API Write service.
 * 
 * @author Randy Simon
 */
public class WriteService extends BaseService {

	/**
	 * Logger for this class
	 */
	private static Log LOG = LogFactory.getLog(WriteService.class);

	/**
	 * The service name
	 */
	private static final String SERVICE_NAME = "write";

	/**
	 * Create asset method
	 */
	private static final String CREATE_ASSET_METHOD = "create_asset";

	/**
	 * Create episode method
	 */
	private static final String CREATE_EPISODE_METHOD = "create_episode";

	/**
	 * Create playlist method
	 */
	private static final String CREATE_PLAYLIST_METHOD = "create_playlist";

	/**
	 * Update episode method
	 */
	private static final String UPDATE_EPISODE_METHOD = "update_episode";

	/**
	 * Update playlist method
	 */
	private static final String UPDATE_PLAYLIST_METHOD = "update_playlist";

	/**
	 * The instance of this class
	 */
	private static WriteService instance;

	/**
	 * Private constructor to enforce singleton.
	 */
	private WriteService() {
	}

	/**
	 * Get the instance of this class.
	 * 
	 * @return the singleton instance of this class
	 */
	public static WriteService getInstance() {
		if (instance == null) {
			return new WriteService();
		}

		return instance;
	}

	/**
	 * The Create Asset method is used to upload a new video or image asset for
	 * use in one of your shows.
	 * <p>
	 * 
	 * NOTE: This method of uploading has been deprecated. The preferred method
	 * is to call {@link #createEpisode(String, String, Map)} or
	 * {@link #updateEpisode(String, Map)} with <code>upload_types</code>
	 * specified.
	 * <p>
	 * 
	 * You may still use this method but we will limit your usage to 5 assets a
	 * day.
	 * <p>
	 * 
	 * @param showId
	 *            The ID of the show to create the asset in.
	 * @param name
	 *            The name of the new asset. This value must be less than 255
	 *            characters.
	 * @param file
	 *            The full path to the file on the file system. This is the
	 *            image or video.
	 * @return The parsed response.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JAXBException
	 * @throws ResponseException
	 * 
	 * @deprecated
	 */
	public CreateAssetResponse createAsset(String showId, String name,
			Object file) throws ClientProtocolException, IOException,
			JAXBException, ResponseException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("show_id", showId);
		params.put("name", name);

		Map<String, Object> fileParams = new HashMap<String, Object>();
		fileParams.put("uploaded_data", file);

		return (CreateAssetResponse) connection.doPost(SERVICE_NAME,
				CREATE_ASSET_METHOD, params, fileParams);
	}

	/**
	 * Creates an episode in the Episodic System in the specified show. This
	 * method will return the information needed to upload the video file for
	 * this episode. If you are adding a file to the episode then the
	 * {@link #uploadFileForEpisode(Upload, File)} must be called immediately
	 * after this method. For example,
	 * <p>
	 * 
	 * <code>
	 *  Map<String, Object> options = new HashMap<String, Object>();
	 *  </code> <br>
	 * <code>
	 *  options.put("upload_types", "s3");
	 *  </code> <br>
	 * <code>
	 *  options.put("video_filename", "1-0.mp4");
	 *  </code> <br>
	 * <code>
	 * 	CreateEpisodeResponse respone = writeService.createEpisode("oz04s1q0i29t", "My Episode", options);  
	 * </code> <br>
	 * <code>
	 *  writeService.uploadFileforEpisode(response.getUplaodForFilePath("/path/to/file/1-0.mp4"), "/path/to/file/1-0.mp4");
	 * </code>
	 * <p>
	 * 
	 * The last parameter to this method is a list of optional attributes.
	 * Acceptable options are:
	 * 
	 * <li>
	 * <code>air_date</code> - Setting this date in the future will tell the
	 * Episodic Platform to publish the episode but not make it available in
	 * playlists until this date has passed. This defaults to now if not
	 * provided.</li>
	 * 
	 * <li>
	 * <code>custom_fields</code> - If you have set up custom metadata fields on
	 * the show that you are creating the episode in you can also assign values
	 * to those fields by passing in a Map of name/value pairs where the name is
	 * the name of your custom field. In the case that the field you are trying
	 * to set is a external select field then the value should be a Map mapping
	 * ids to display values.</li>
	 * 
	 * <li>
	 * <code>description</code> - A string value to be used as the description
	 * for the episode. Descriptions must be less than 255 characters</li>
	 * 
	 * <li>
	 * <code>off_air_date</code> - When this date is reached the episode will be
	 * removed from all playlists. This defaults to indefinite if not provided.</li>
	 * 
	 * <li>
	 * <code>publish</code> - This must either <code>true</code> or
	 * <code>false</code> to indicate whether the episode should be submitted
	 * for publishing. The default is <code>false</code>.</li>
	 * 
	 * <li>
	 * <code>publish_format_ids</code> - Publishing resolutions and bitrates
	 * defaults are set on the containing show but can be overridden on the
	 * episode. If you wish to override the defaults, this value should be an
	 * Array of publishing profile ids.</li>
	 * 
	 * <li>
	 * <code>tags</code> - A comma delimitted list of tags for the new episode.</li>
	 * 
	 * <li>
	 * <code>upload_types</code> - This is only used when there is a
	 * 'video_filename' and/or 'thumbnail_filename' included. The caller may
	 * pass in a single value or a comma delimited list. However, it is
	 * important to note that your network must support the one of specified
	 * upload types or the call will fail. Currently, the only valid value is
	 * 's3'.</li>
	 * 
	 * <li>
	 * <code>asset_ids</code> - The list of assets in the order they are to
	 * appear in the episode. Ingored if the upload_types and asset_filename
	 * parameters are not blank.</li>
	 * 
	 * <li>
	 * <code>thumbnail_id</code> - The id of the thumbnail to set on the
	 * episode. Ignored if the upload_types and thumbnail_filename parameters
	 * are not blank.</li>
	 * 
	 * <li>
	 * <code>video_filename</code> - If an upload_type is specified this is the
	 * name of the file that will be uploaded and made the single video for the
	 * episode.</li>
	 * 
	 * <li>
	 * <code>thumbnail_filename</code> - If an upload_type is specified this is
	 * the name of the file that will be uploaded and made the thumbnail for the
	 * episode.</li>
	 * 
	 * <li>
	 * <code>ping_url</code> - The URL the Episodic system will issue a GET
	 * request against to notify you that publishing has completed. The ping URL
	 * should accept two parameters: the episode id and a status which will be
	 * one of 'success' or 'failure'.</li>
	 * <p>
	 * 
	 * @param showId
	 *            The ID of the show to create the episode in.
	 * @param name
	 *            The name of the episode. This must be unique across your show.
	 * @param options
	 *            Optional attributes.
	 * @return An object that contains the XML response as well as some helper
	 *         methods to get the pending uploads.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ResponseException
	 * @throws JAXBException
	 */
	public CreateEpisodeResponse createEpisode(String showId, String name,
			Map<String, Object> options) throws ClientProtocolException,
			IOException, ResponseException, JAXBException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("show_id", showId);
		params.put("name", name);
		params.putAll(options);

		return (CreateEpisodeResponse) connection.doPost(SERVICE_NAME,
				CREATE_EPISODE_METHOD, params, null);
	}

	/**
	 * Creates a manual playlist in the Episodic System in the specified show.
	 * 
	 * <p>
	 * The last parameter to this method is a list of optional attributes.
	 * Acceptable options are:
	 * 
	 * <li>
	 * <code>description</code> - A string value to be used as the description
	 * for the playlist. Descriptions must be less than 255 characters.</li>
	 * 
	 * <li>
	 * <code>episode_ids</code> - An array or list of comma separated valid
	 * episode ids in the order they should appear in the playlist.</li>
	 * 
	 * <li>
	 * <code>behavior</code> - Indicates what the player should do when an
	 * episode in the playlist finishes. Valid values are '0' (display a list of
	 * other episodes in the playlist), '1' (start playing the next episode
	 * immediately) or '2' (display the list but start playing after
	 * 'auto_play_delay' seconds). The default is '0'.</li>
	 * 
	 * <li>
	 * <code>auto_play_delay</code> - If the 'behavior' value is '2' then this
	 * is the number of seconds to wait until the next episode is played. The
	 * default is 5.</li>
	 * 
	 * <li>
	 * <code>custom_fields</code> - If you have set up custom metadata fields on
	 * the show that you are creating the playlist in you can also assign values
	 * to those fields by passing in a Map of name/value pairs where the name is
	 * the name of your custom field. In the case that the field you are trying
	 * to set is a external select field then the value should be a Map mapping
	 * ids to display values.</li>
	 * 
	 * <li>
	 * <code>upload_types</code> - The caller may pass in a single value or a
	 * comma delimited list. However, it is important to note that your network
	 * must support the one of specified upload types or the call will fail.
	 * Currently, the only valid value is 's3'.</li>
	 * 
	 * <li>
	 * <code>video_filename</code> - If an upload_type is specified this is the
	 * name of the file that will be uploaded and made the single video for the
	 * episode.</li>
	 * 
	 * <li>
	 * <code>thumbnail_filename</code> - If an upload_type is specified this is
	 * the name of the file that will be uploaded and made the thumbnail for the
	 * episode.</li>
	 * <p>
	 * 
	 * @param showId
	 *            The ID of the show to create the playlist in.
	 * @param name
	 *            The name of the playlist. This must be unique across your
	 *            show.
	 * @param options
	 *            Optional attributes.
	 * @return The parsed response.
	 * @throws ClientProtocolException
	 * @throws ResponseException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public CreatePlaylistResponse createPlaylist(String showId, String name,
			Map<String, Object> options) throws ClientProtocolException,
			ResponseException, IOException, JAXBException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("show_id", showId);
		params.put("name", name);
		params.putAll(options);

		return (CreatePlaylistResponse) connection.doPost(SERVICE_NAME,
				CREATE_PLAYLIST_METHOD, params, null);
	}

	/**
	 * Updates an episode in the Episodic System. This method will return the
	 * information needed to upload the video file for this episode. If you are
	 * adding a file to the episode then the
	 * {@link #uploadFileForEpisode(Upload, File)} must be called immediately
	 * after this method. For example,
	 * <p>
	 * 
	 * <code>
	 *  Map<String, Object> options = new HashMap<String, Object>();
	 *  </code> <br>
	 * <code>
	 *  options.put("upload_types", "s3");
	 *  </code> <br>
	 * <code>
	 *  options.put("video_filename", "my_thumb.jpg");
	 *  </code> <br>
	 * <code>
	 * 	CreateEpisodeResponse respone = writeService.updateEpisode("jz54h6q0i39y", options);  
	 * </code> <br>
	 * <code>
	 *  writeService.uploadFileforEpisode(response.getUplaodForFilePath("/path/to/file/my_thumb.jpg"), "/path/to/file/my_thumb.jpg");
	 * </code>
	 * <p>
	 * 
	 * The last parameter to this method is a list of optional attributes.
	 * Acceptable options are:
	 * <li>
	 * <code>air_date</code> - Setting this date in the future will tell the
	 * Episodic Platform to publish the episode but not make it available in
	 * playlists until this date has passed. This defaults to now if not
	 * provided.</li>
	 * 
	 * <li>
	 * <code>custom_fields</code>- If you have set up custom metadata fields on
	 * the show that you are creating the episode in you can also assign values
	 * to those fields by passing in a Map of name/value pairs where the name is
	 * the name of your custom field. In the case that the field you are trying
	 * to set is a external select field then the value should be a Map mapping
	 * ids to display values.</li>
	 * 
	 * <li>
	 * <code>description</code> - A string value to be used as the description
	 * for the episode. Descriptions must be less than 255 characters.</li>
	 * 
	 * <li>
	 * <code>off_air_date</code> - When this date is reached the episode will be
	 * removed from all playlists. This defaults to indefinite if not provided.</li>
	 * 
	 * <li>
	 * <code>publish_format_ids</code> - Publishing resolutions and bitrates
	 * defaults are set on the containing show but can be overridden on the
	 * episode. If you wish to override the defaults, this value should be an
	 * Array of publishing profile ids. </code></li>
	 * 
	 * <li>
	 * <code>tags</code> - A comma delimitted list of tags for the new episode.</li>
	 * 
	 * <li>
	 * <code>upload_types</code> - This is only used when there is a
	 * 'video_filename' and/or 'thumbnail_filename' included. The caller may
	 * pass in a single value or a comma delimited list. However, it is
	 * important to note that your network must support the one of specified
	 * upload types or the call will fail. Currently, the only valid value is
	 * 's3'.</li>
	 * 
	 * <li>
	 * <code>thumbnail_id</code> - The id of the thumbnail to set on the
	 * episode. Ignored if the upload_types and thumbnail_filename parametersare
	 * not blank.</li>
	 * 
	 * <li>
	 * <code>video_filename</code> - If an upload_type is specified this is the
	 * name of the file that will be uploaded and made the single video for the
	 * episode.</li>
	 * 
	 * <li>
	 * <code>thumbnail_filename</code> - If an upload_type is specified this is
	 * the name of the file that will be uploaded and made the thumbnail for the
	 * episode.</li>
	 * <p>
	 * 
	 * @param episodeId
	 *            The ID of the episode to update.
	 * @param options
	 *            Optional attributes.
	 * @return An object that contains the XML response as well as some helper
	 *         methods to get the pending uploads.
	 * @throws ClientProtocolException
	 * @throws ResponseException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public UpdateEpisodeResponse updateEpisode(String episodeId,
			Map<String, Object> options) throws ClientProtocolException,
			ResponseException, IOException, JAXBException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", episodeId);
		params.putAll(options);

		return (UpdateEpisodeResponse) connection.doPost(SERVICE_NAME,
				UPDATE_EPISODE_METHOD, params, null);
	}

	/**
	 * Updates a manual playlist in the Episodic System with the specified ID.
	 * <p>
	 * The last parameter to this method is a list of optional attributes.
	 * Acceptable options are:
	 * 
	 * <li>
	 * <code>name</code> - The name of the playlist. his must be unique across
	 * your show.</li>
	 * 
	 * <li>
	 * <code>description</code> - A string value to be used as the description
	 * for the playlist. Descriptions must be less than 255 characters.</li>
	 * 
	 * <li>
	 * <code>episode_ids</code> - An array or list of comma separated valid
	 * episode ids in the order they should appear in the playlist.</li>
	 * 
	 * <li>
	 * <code>replace_episodes</code> - Indicates if the existing episodes should
	 * be replaced by the new ones or added to. The default is 'false'.</li>
	 * 
	 * <li>
	 * <code>behavior</code> - Indicates what the player should do when an
	 * episode in the playlist finishes. Valid values are '0' (display a list of
	 * other episodes in the playlist), '1' (start playing the next episode
	 * immediately) or '2' (display the list but start playing after
	 * 'auto_play_delay' seconds). The default is '0'.</li>
	 * 
	 * <li>
	 * <code>auto_play_delay</code> - If the 'behavior' value is '2' then this
	 * is the number of seconds to wait until the next episode is played. The
	 * default is 5.</li>
	 * 
	 * <li>
	 * <code>custom_fields</code> - If you have set up custom metadata fields on
	 * the show that you are creating the playlist in you can also assign values
	 * to those fields by passing in a Map of name/value pairs where the name is
	 * the name of your custom field. In the case that the field you are trying
	 * to set is a external select field then the value should be a Map mapping
	 * ids to display values.</li>
	 * <p>
	 * 
	 * @param playlistId
	 *            The ID of the playlist to update.
	 * @param options
	 *            Optional attributes
	 * @return The parsed response.
	 * @throws ClientProtocolException
	 * @throws ResponseException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public UpdatePlaylistResponse updatePlaylist(String playlistId,
			Map<String, Object> options) throws ClientProtocolException,
			ResponseException, IOException, JAXBException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", playlistId);
		params.putAll(options);

		return (UpdatePlaylistResponse) connection.doPost(SERVICE_NAME,
				UPDATE_PLAYLIST_METHOD, params, null);
	}

	/**
	 * Uploads the video and/or images to the Episodic System. This method
	 * requires that you first called @ #createEpisode(String, String, Map)} or
	 * {@link #updateEpisode(String, Map)} since the second parameter passed to
	 * this method is returned from one of those method calls.
	 * <p>
	 * 
	 * @param pendingUpload
	 *            This is the result of the call to
	 *            {@link CreateEpisodeResponse#getUploadForFile(File)} or
	 *            {@link UpdateEpisodeResponse#getUploadForFile(File)} on the
	 *            object returned from
	 *            {@link #createEpisode(String, String, Map)} or
	 *            {@link #updateEpisode(String, Map)}.
	 * @param file
	 *            The file to be uploaded.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws FileUploadFailedException
	 */
	public void uploadFileForEpisode(Upload pendingUpload, File file)
			throws ClientProtocolException, IOException,
			FileUploadFailedException {
		HttpClient client = new DefaultHttpClient();
		HttpPost method = new HttpPost(pendingUpload.getUrl());

		// Add the upload params to the post
		MultipartEntity multipartEntity = new MultipartEntity();
		for (Iterator<UploadParam> itr = pendingUpload.getUploadParams()
				.iterator(); itr.hasNext();) {
			UploadParam param = itr.next();
			multipartEntity.addPart(param.getName(), new StringBody(param
					.getValue(), Charset.forName("UTF-8")));
		}

		// Set the file
		multipartEntity.addPart("file", new FileBody(file));
		method.setEntity(multipartEntity);

		try {
			HttpResponse httpResponse = client.execute(method);
			StatusLine statusLine = httpResponse.getStatusLine();

			// If we get an error status, then log the error and throw an
			// exception
			if (statusLine.getStatusCode() > 399) {

				if (httpResponse.getEntity() != null) {
					LOG.error(EntityUtils.toString(httpResponse.getEntity()));
				} else {
					LOG.error("Status code = " + statusLine.getStatusCode()
							+ " returned when attempting to upload file");
				}
				throw new FileUploadFailedException(statusLine.getStatusCode(),
						statusLine.getReasonPhrase());
			}
		} finally {
			client.getConnectionManager().shutdown();
		}
	}
}
