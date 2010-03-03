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

import java.io.IOException;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;
import org.joda.time.DateTime;

import com.episodic.platform.client.exception.ResponseException;
import com.episodic.platform.client.response.query.EpisodesResponse;
import com.episodic.platform.client.response.query.PlaylistsResponse;
import com.episodic.platform.client.response.query.ShowsResponse;

/**
 * Class for making request to the Episodic Platform API Query service.
 * 
 * @author Randy Simon
 */
public class QueryService extends BaseService {

	/**
	 * The service name
	 */
	private static final String SERVICE_NAME = "query";

	/**
	 * Episodes query
	 */
	private static final String EPISODES_METHOD = "episodes";

	/**
	 * Modification callbacks query
	 */
	private static final String MODIFICATION_CALLBACKS_METHOD = "modification_callbacks";

	/**
	 * Playlists query
	 */
	private static final String PLAYLISTS_METHOD = "playlists";

	/**
	 * Shows query
	 */
	private static final String SHOWS_METHOD = "shows";

	/**
	 * The instance of this class
	 */
	private static QueryService instance;

	/**
	 * Private constructor to enforce singleton.
	 */
	private QueryService() {
	}

	/**
	 * Get the instance of this class.
	 * 
	 * @return the singleton instance of this class
	 */
	public static QueryService getInstance() {
		if (instance == null) {
			return new QueryService();
		}

		return instance;
	}

	/**
	 * Queries for episodes in your network. The options parameter allows you to
	 * limit your results. Acceptable options are:
	 * 
	 * <li>
	 * <code>show_id</code> - A single id or an Array of show ids. If this param is
	 * not provided then all shows in your network are queried. <code>id</code> - A
	 * single id or an Array of episode ids. If this param is not provided then
	 * all episodes in the your network or specified shows are queried.</li>
	 * 
	 * <li>
	 * <code>search_term</code> - This string is used to perform a keywords search
	 * against the title, description, tags and custom fields of an episode.
	 * Tags should be separated by commas.</li>
	 * 
	 * <li>
	 * <code>search_type</code> - The search_type parameter must be one of "tags",
	 * "name_description" or "all". The default is "all".</li>
	 * 
	 * <li>
	 * <code>tag_mode</code> - The tag_mode parameter can be "any" for an OR
	 * combination of tags, or "all" for an AND combination. The default is
	 * "any". This parameter is ignored if the search_type is not "tags".</li>
	 * 
	 * <li>
	 * <code>status</code> - The status parameter can be used to limit the list of
	 * episodes with a certain publishing status. The value must be a comma
	 * delimited list of one or more of "off_the_air", "publishing",
	 * "on_the_air", "waiting_to_air", "publish_failed"</li>
	 * 
	 * <li>
	 * <code>sort_by</code> - The sort_by parameter is optional and specifies a field
	 * to sort the results by. The value must be one of "updated_at",
	 * "created_at", "air_date" or "name". The default is "created_at".</li>
	 * 
	 * <li>
	 * <code>sort_dir</code> - The sort_dir parameter is optional and specifies the
	 * sort direction. The value must be one of "asc" or "desc". The default is
	 * "asc".</li>
	 * 
	 * <li>
	 * <code>include_views</code> - A value that must be one of "true" or "false" to
	 * indicate if total views and complete views should be included in the
	 * response. The default is "false". NOTE: Setting this to "true" may result
	 * in slower response times.</li>
	 * 
	 * <li>
	 * <code>page</code> - A value that must be an integer indicating the page number
	 * to return the results for. The default is 1.</li>
	 * 
	 * <li>
	 * <code>per_page</code> - A value that must be an integer indicating the number
	 * of items per page. The default is 20. NOTE: The smaller this value is the
	 * better your response times will be.</li>
	 * 
	 * <li>
	 * <code>embed_width</code> - An integer value in pixels that specifies the width
	 * of the player. The returned embed code width may be larger that this to
	 * account for player controls depending on the player you are using. If
	 * only the width is provided, the height is determined by maintaining the
	 * aspect ratio.</li>
	 * 
	 * <li>
	 * <code>embed_height</code> - An integer value in pixels that specifies the
	 * height of the player. The embed code height may be larger that this to
	 * account for player controls depending on the player you are using. The
	 * default height is 360.</li>
	 * <p>
	 * 
	 * @param options
	 *            A hash of optional attributes. See above.
	 * @return The parsed response.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JAXBException
	 * @throws ResponseException
	 */
	public EpisodesResponse episodes(Map<String, Object> options)
			throws ClientProtocolException, IOException, JAXBException,
			ResponseException {
		return (EpisodesResponse) connection.doGet(SERVICE_NAME,
				EPISODES_METHOD, options);
	}

	/**
	 * Third-party applications can register to be notified of changes to
	 * episodes and playlists in their network by providing an Modification URL
	 * in their network settings. When an episode/playlist is created, modified
	 * or deleted the Episodic System when make a POST request to specified URL
	 * with an XML representation of the modified object.
	 * 
	 * <p>
	 * This method allows the caller to query for all callbacks registered since
	 * a specified time. Although, failed callbacks are marked as failed and
	 * retried, this method allows the caller to see a history of callbacks to
	 * perhaps reprocess them if something went wrong with their initial
	 * processing.
	 * 
	 * Acceptable filter options are:
	 * <li>
	 * <code>pending_only</code> - Set this parameter to 'true' if only unprocessed
	 * callbacks should be returned. The default is 'false'.</li>
	 * 
	 * <li>
	 * <code>page</code> - A value that must be an integer indicating the page number
	 * to return the results for. The default is 1.</li>
	 * 
	 * <li>
	 * <code>per_page</code> - A value that must be an integer indicating the number
	 * of items per page. The default is 20. NOTE: The smaller this value is the
	 * better your response times will be.</li>
	 * <p>
	 * 
	 * @param since
	 *            All callbacks registered since the provided date will be
	 *            included in the response.
	 * @param options
	 *            Optional attributes.
	 * @return The XML response from the server.
	 * @throws ClientProtocolException
	 * @throws ResponseException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public String modificationCallbacks(DateTime since,
			Map<String, Object> options) throws ClientProtocolException,
			ResponseException, IOException, JAXBException {
		return connection.doGet(SERVICE_NAME, MODIFICATION_CALLBACKS_METHOD,
				options, false).getBody();
	}

	/**
	 * Queries for playlists in your network. The options parameter allows you
	 * to limit your results. Acceptable options are:
	 * 
	 * <li>
	 * <code>show_id</code> - A single id or an Array of show ids. If this param is
	 * not provided then all shows in your network are queried.</li>
	 * 
	 * <li>
	 * <code>id</code> - A single id or an Array of playlist ids. If this param is not
	 * provided then all playlists in the your network or specified shows are
	 * queried.</li>
	 * 
	 * <li>
	 * <code>search_term</code> - This string is used to perform a keywords search
	 * against the title, description, and custom fields of a playlist.</li>
	 * 
	 * <li>
	 * <code>sort_by</code> - The sort_by parameter is optional and specifies a field
	 * to sort the results by. The value must be one of "updated_at",
	 * "created_at", "name". The default is "created_at".</li>
	 * 
	 * <li>
	 * <code>sort_dir</code> - The sort_dir parameter is optional and specifies the
	 * sort direction. The value must be one of "asc" or "desc". The default is
	 * "asc".</li>
	 * 
	 * <li>
	 * <code>page</code> - A value that must be an integer indicating the page number
	 * to return the results for. The default is 1.</li>
	 * 
	 * <li>
	 * <code>per_page</code> - A value that must be an integer indicating the number
	 * of items per page. The default is 20. NOTE: The smaller this value is the
	 * better your response times will be.</li>
	 * 
	 * <li>
	 * <code>embed_width</code> - An integer value in pixels that specifies the width
	 * of the player. The returned embed code width may be larger that this to
	 * account for player controls depending on the player you are using. If
	 * only the width is provided, the height is determined by maintaining the
	 * aspect ratio.</li>
	 * 
	 * <li>
	 * <code>embed_height</code> - An integer value in pixels that specifies the
	 * height of the player. The embed code height may be larger that this to
	 * account for player controls depending on the player you are using. The
	 * default height is 360.</li>
	 * <p>
	 * 
	 * @param options
	 *            Optional attributes.
	 * @return The parsed response.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JAXBException
	 * @throws ResponseException
	 */
	public PlaylistsResponse playlists(Map<String, Object> options)
			throws ClientProtocolException, IOException, JAXBException,
			ResponseException {
		return (PlaylistsResponse) connection.doGet(SERVICE_NAME,
				PLAYLISTS_METHOD, options);
	}

	/**
	 * Queries for shows in your network. The options parameter allows you to
	 * limit your results. Acceptable options are:
	 * 
	 * <li>
	 * <code>id</code> - A single id or an Array of episode ids. If this param is not
	 * provided then all shows in the your network or specified shows are
	 * queried.</li>
	 * 
	 * <li>
	 * <code>sort_by</code> - The sort_by parameter is optional and specifies a field
	 * to sort the results by. The value must be one of "updated_at",
	 * "created_at" or "name". The default is "created_at".</li>
	 * 
	 * <li>
	 * <code>sort_dir</code> - The sort_dir parameter is optional and specifies the
	 * sort direction. The value must be one of "asc" or "desc". The default is
	 * "asc".</li>
	 * 
	 * <li>
	 * <code>page</code> - A value that must be an integer indicating the page number
	 * to return the results for. The default is 1.</li>
	 * 
	 * <li>
	 * <code>per_page</code> - A value that must be an integer indicating the number
	 * of items per page. The default is 20. NOTE: The smaller this value is the
	 * better your response times will be.</li>
	 * <p>
	 * 
	 * @param options
	 *            Optional attributes.
	 * @return The parsed response.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JAXBException
	 * @throws ResponseException
	 */
	public ShowsResponse shows(Map<String, Object> options)
			throws ClientProtocolException, IOException, JAXBException,
			ResponseException {
		return (ShowsResponse) connection.doGet(SERVICE_NAME, SHOWS_METHOD,
				options);
	}
}
