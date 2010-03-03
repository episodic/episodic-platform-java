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
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;

import com.episodic.platform.client.exception.APIAccessDisabledException;
import com.episodic.platform.client.exception.InvalidAPIKeyException;
import com.episodic.platform.client.exception.InvalidParametersException;
import com.episodic.platform.client.exception.MissingRequiredParameterException;
import com.episodic.platform.client.exception.NotFoundException;
import com.episodic.platform.client.exception.RequestExpiredException;
import com.episodic.platform.client.exception.ResponseException;
import com.episodic.platform.client.response.ErrorResponse;
import com.episodic.platform.client.response.Response;
import com.episodic.platform.client.response.analytics.CampaignsDailyResponse;
import com.episodic.platform.client.response.analytics.EpisodeDailyResponse;
import com.episodic.platform.client.response.analytics.EpisodesSummaryResponse;
import com.episodic.platform.client.response.query.EpisodesResponse;
import com.episodic.platform.client.response.query.PlaylistsResponse;
import com.episodic.platform.client.response.query.ShowsResponse;
import com.episodic.platform.client.response.write.CreateAssetResponse;
import com.episodic.platform.client.response.write.CreateEpisodeResponse;
import com.episodic.platform.client.response.write.UpdateEpisodeResponse;
import com.episodic.platform.client.util.StringUtil;

/**
 * Class used to make the actual requests to the Episodic Platform API. This
 * class holds user credential information.
 * 
 * @author Randy Simon
 */
public class Connection {

	/**
	 * Logger for this class
	 */
	private static Log LOG = LogFactory.getLog(BaseService.class);

	/**
	 * The XML parsing context.
	 */
	private static JAXBContext ctx;

	/**
	 * Static block to define the unmarshalling classes.
	 */
	static {
		try {
			ctx = JAXBContext.newInstance(CreateAssetResponse.class,
					CreateEpisodeResponse.class, ErrorResponse.class,
					CampaignsDailyResponse.class, EpisodeDailyResponse.class,
					EpisodesResponse.class, EpisodesSummaryResponse.class,
					PlaylistsResponse.class, ShowsResponse.class,
					UpdateEpisodeResponse.class);
		} catch (JAXBException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * Most requests require an expires param.
	 */
	private static final String EXPIRES_PARAM = "expires";

	/**
	 * The API key param.
	 */
	private static final String KEY_PARAM = "key";

	/**
	 * The signature param.
	 */
	private static final String SIGNATURE_PARAM = "signature";

	/**
	 * The schema to use.
	 */
	private static final String SCHEME = "http";

	/**
	 * The default API host.
	 */
	private static final String HOST = "api.episodic.com";

	/**
	 * The default API port.
	 */
	private static final int PORT = 80;

	/**
	 * The current API version supported by this client library.
	 */
	private static final String API_VERSION = "v2";

	/**
	 * The base URL path.
	 */
	private static final String BASE = "/api/";

	/**
	 * The user's API key for this connection.
	 */
	private String episodicAPIKey;

	/**
	 * The user's secret key for this connection. This attribute is not sent as
	 * part of the request.
	 */
	private String episodicSecretKey;

	/**
	 * If <c>null</c> then {@link #HOST} is used.
	 */
	private String apiHost;

	/**
	 * If <c>null</c> then {@link #PORT} is used.
	 */
	private int apiPort = 0;

	/**
	 * Default no-arg constructor used for testing.
	 */
	protected Connection() {
	}

	/**
	 * Constructor
	 * 
	 * @param episodicAPIKey
	 *            the user's API key to use as part of the request.
	 * @param episodicSecretKey
	 *            the user's secret key to use to sign the request.
	 */
	public Connection(String episodicAPIKey, String episodicSecretKey) {
		this.episodicAPIKey = episodicAPIKey;
		this.episodicSecretKey = episodicSecretKey;
	}

	/**
	 * Constructor.
	 * 
	 * @param episodicAPIKey
	 *            the user's API key to use as part of the request.
	 * @param episodicSecretKey
	 *            the user's secret key to use to sign the request.
	 * @param apiHost
	 *            the overridden API host to use
	 * @param apiPort
	 *            the overridden API port to use
	 */
	public Connection(String episodicAPIKey, String episodicSecretKey,
			String apiHost, int apiPort) {
		this(episodicAPIKey, episodicSecretKey);
		this.apiHost = apiHost;
		this.apiPort = apiPort;
	}

	/**
	 * @see #doGet(String, String, Map, boolean)
	 */
	public Response doGet(String apiName, String methodName,
			Map<String, Object> params) throws ClientProtocolException,
			IOException, ResponseException, JAXBException {
		return doGet(apiName, methodName, params, true);
	}

	/**
	 * Makes a GET request to the Episodic Platform API server.
	 * 
	 * @param apiName
	 *            the name of the API service to use.
	 * @param methodName
	 *            the name of the API method to invoke.
	 * @param params
	 *            the list of params to include in the query string
	 * @param parseResponse
	 *            <c>true</c> if the response should be parsed as XML.
	 * @return a response object that includes the deserialized response data.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ResponseException
	 * @throws JAXBException
	 */
	public Response doGet(String apiName, String methodName,
			Map<String, Object> params, boolean parseResponse)
			throws ClientProtocolException, IOException, ResponseException,
			JAXBException {

		HttpClient client = new DefaultHttpClient();

		// Convert the params to a string
		Map<String, String> requestParams = convertParamsForRequest(params);

		// Apply params like key and expires
		appendCommonParams(requestParams);

		// Build the query params list
		List<NameValuePair> queryParams = new ArrayList<NameValuePair>(
				requestParams.size());
		Iterator<Map.Entry<String, String>> itr = requestParams.entrySet()
				.iterator();
		while (itr.hasNext()) {
			Map.Entry<String, String> pair = itr.next();
			queryParams.add(new BasicNameValuePair(pair.getKey(), pair
					.getValue()));
		}

		HttpGet method = new HttpGet(constructURI(apiName, methodName,
				queryParams));

		Response response = null;
		try {
			HttpResponse httpResponse = client.execute(method);

			if (parseResponse) {
				response = processAndUnmarshallResponse(httpResponse);
			} else {
				// Just set the status and body if we are not parsing the
				// response.
				response = new Response();
				response
						.setBody(EntityUtils.toString(httpResponse.getEntity()));
				StatusLine statusLine = httpResponse.getStatusLine();
				response.setStatusCode(statusLine.getStatusCode());
			}
		} finally {
			client.getConnectionManager().shutdown();
		}

		return response;
	}

	/**
	 * Makes a GET request to the Episodic Platform API server.
	 * 
	 * @param apiName
	 *            the name of the API service to use.
	 * @param methodName
	 *            the name of the API method to invoke.
	 * @param params
	 *            the list of params to include in the POST
	 * @param fileParams
	 *            the list of files to include in the POST
	 * @return a response object that includes the deserialized response data.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ResponseException
	 * @throws JAXBException
	 */
	public Response doPost(String apiName, String methodName,
			Map<String, Object> params, Map<String, Object> fileParams)
			throws ClientProtocolException, IOException, ResponseException,
			JAXBException {

		HttpClient client = new DefaultHttpClient();

		// Convert the params to a string
		Map<String, String> requestParams = convertParamsForRequest(params);

		// Apply params like key and expires
		appendCommonParams(requestParams);

		HttpPost method = new HttpPost(constructURI(apiName, methodName, null));

		// Add the string and file params to the call.
		MultipartEntity multipartEntity = new MultipartEntity();
		for (Map.Entry<String, String> entry : requestParams.entrySet()) {
			multipartEntity.addPart(entry.getKey(), new StringBody(entry
					.getValue()));
		}
		if (fileParams != null) {
			for (Map.Entry<String, Object> entry : fileParams.entrySet()) {
				File file = (entry.getValue() instanceof File) ? (File) entry
						.getValue() : new File(entry.getValue().toString());
				multipartEntity.addPart(entry.getKey(), new FileBody(file));
			}
		}
		method.setEntity(multipartEntity);

		Response response = null;
		try {
			HttpResponse httpResponse = client.execute(method);
			response = processAndUnmarshallResponse(httpResponse);
		} finally {
			client.getConnectionManager().shutdown();
		}

		return response;
	}

	/**
	 * Helper method to construct the URI for the request.
	 * 
	 * @param apiName
	 *            the name of the API service to use.
	 * @param methodName
	 *            the name of the API method to invoke.
	 * @param queryParams
	 *            the list of params to include in the query string
	 * @return the constructed URI
	 */
	private URI constructURI(String apiName, String methodName,
			List<NameValuePair> queryParams) {

		// Check for default overrides in the connection
		String host = apiHost == null ? HOST : apiHost;
		int port = apiPort == 0 ? PORT : apiPort;

		String queryString = queryParams != null ? URLEncodedUtils.format(
				queryParams, "UTF-8") : null;

		try {
			return URIUtils.createURI(SCHEME, host, port, BASE + API_VERSION
					+ "/" + apiName + "/" + methodName, queryString, null);
		} catch (URISyntaxException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * All API requests must include a signature parameter. The signature is
	 * generated performing the following steps.
	 * <ol>
	 * <li>Concatenate all query parameters except the API Key in the format
	 * "name=value" in alphabetical order by parameter name. There should not be
	 * an ampersand or any separator between "name=value" pairs.
	 * <li>Append the string from step 1 to the Secret Key so that you have
	 * [Secret Key][String from step 1] (ex.
	 * 77c062e551279b0a0b8bc69f9709f33bexpires=1229046347show_id=13).
	 * <li>Generate the SHA-256 hash value for the string resulting from steps 1
	 * and 2.
	 * <ol>
	 * 
	 * @param params
	 *            A map of the parameters to be sent to.
	 * 
	 * @return The signature needed for an episodic request.
	 */
	private String generateSignature(Map<String, String> params) {
		StringBuilder builder = new StringBuilder(this.episodicSecretKey);

		Set<String> keys = new TreeSet<String>(params.keySet());
		for (String key : keys) {
			builder.append(key).append('=').append(params.get(key));
		}

		return DigestUtils.sha256Hex(builder.toString());
	}

	/**
	 * Apply the common Episodic params such as expires, signature and key.
	 * 
	 * @param params
	 *            The params to update.
	 */
	private void appendCommonParams(Map<String, String> params) {

		// Add an expires value if it has not been added already
		if (params.get(EXPIRES_PARAM) == null) {
			params.put(EXPIRES_PARAM, String
					.valueOf((new Date().getTime() / 1000L) + 30));
		}

		// Sign the request
		params.put(SIGNATURE_PARAM, generateSignature(params));

		// Add our key
		params.put(KEY_PARAM, this.episodicAPIKey);
	}

	/**
	 * Converts all parameters to a form for a request. This includes converting
	 * arrays to comma delimited strings, Times to integers and Hashes to a form
	 * depending on its level in the passed in params.
	 * 
	 * @param params
	 *            the list of parameters to convert.
	 * @return a single level Map of string key/value pairs.
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> convertParamsForRequest(
			Map<String, Object> params) {
		Map<String, String> result = new HashMap<String, String>(
				params.size() * 4);

		Iterator<Map.Entry<String, Object>> itr = params.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, Object> pair = itr.next();

			if (pair.getValue() instanceof Collection<?>) {
				// Convert the array to a comma delimited string.
				result.put(pair.getKey(), StringUtil.join(
						(Collection<Object>) pair.getValue(), ","));
			} else if (pair.getValue() instanceof Date) {
				// Convert the date to seconds
				result.put(pair.getKey(), String.valueOf(((Date) pair
						.getValue()).getTime() / 1000L));
			} else if (pair.getValue() instanceof DateTime) {
				// Convert the date time to seconds
				result.put(pair.getKey(), String.valueOf(((DateTime) pair
						.getValue()).getMillis() / 1000L));
			} else if (pair.getValue() instanceof Map<?, ?>) {
				// This is only the case for custom fields
				Iterator<Map.Entry<String, Object>> subItr = ((Map<String, Object>) pair
						.getValue()).entrySet().iterator();
				while (subItr.hasNext()) {
					Map.Entry<String, Object> subPair = itr.next();
					if (subPair.getValue() instanceof Date) {
						// Convert the date to seconds.
						result.put(
								pair.getKey() + "[" + subPair.getKey() + "]",
								String.valueOf(((Date) subPair.getValue())
										.getTime() / 1000L));
					} else if (subPair.getValue() instanceof DateTime) {
						// Convert the date time to seconds
						result.put(
								pair.getKey() + "[" + subPair.getKey() + "]",
								String.valueOf(((DateTime) subPair.getValue())
										.getMillis() / 1000L));
					} else if (subPair.getValue() instanceof Map<?, ?>) {
						// Put the hash in the external select field form
						StringBuffer value = new StringBuffer();
						Iterator<Map.Entry<String, Object>> keyValueItr = ((Map<String, Object>) subPair
								.getValue()).entrySet().iterator();
						while (keyValueItr.hasNext()) {
							Map.Entry<String, Object> keyValue = itr.next();
							value.append(keyValue.getKey() + "|"
									+ keyValue.getValue().toString() + ";");
						}
						result.put(
								pair.getKey() + "[" + subPair.getKey() + "]",
								value.toString());
					} else {
						result.put(
								pair.getKey() + "[" + subPair.getKey() + "]",
								subPair.getValue().toString());
					}
				}
			} else {
				result.put(pair.getKey(), pair.getValue().toString());
			}
		}

		return result;
	}

	/**
	 * Unmarshalls the input stream into one of the response objects.
	 * 
	 * @param httpResponse
	 *            the http response
	 * @return The unmarshalled response object.
	 * @throws javax.xml.bind.JAXBException
	 *             Thrown if there are issues with the xml stream passed in.
	 */
	protected Response unmarshall(HttpResponse httpResponse)
			throws JAXBException, IOException {
		String xml = EntityUtils.toString(httpResponse.getEntity());
		return unmarshall(xml);
	}

	/**
	 * Unmarshalls the XML into one of the response objects.
	 * 
	 * @param responseXML
	 *            The XML from the response
	 * @return The unmarshalled response object.
	 * @throws javax.xml.bind.JAXBException
	 *             Thrown if there are issues with the xml stream passed in.
	 */
	protected Response unmarshall(String responseXML) throws JAXBException {
		Unmarshaller unmarshaller = ctx.createUnmarshaller();
		Response response = (Response) unmarshaller.unmarshal(new StringReader(
				responseXML));
		response.setBody(responseXML);
		return response;
	}

	/**
	 * Unmarshall the HTTP response and process it by throwing an exception if
	 * the unmarshalled response is a {@link ErrorResponse} object.
	 * 
	 * @param httpResponse
	 *            the HTTP response returned from the POST or GET request.
	 * @return the parsed response
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ResponseException
	 */
	protected Response processAndUnmarshallResponse(HttpResponse httpResponse)
			throws JAXBException, IOException, ResponseException {
		Response response = unmarshall(httpResponse);

		proccessResponse(response);

		return response;
	}

	/**
	 * Unmarshall the HTTP response and process it by throwing an exception if
	 * the unmarshalled response is a {@link ErrorResponse} object.
	 * 
	 * @param responseXML
	 *            the HTTP response body.
	 * @return the parsed response
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ResponseException
	 */
	protected Response processAndUnmarshallResponse(String responseXML)
			throws JAXBException, IOException, ResponseException {
		Response response = unmarshall(responseXML);

		proccessResponse(response);

		return response;
	}

	/**
	 * Process the response by checking if it is actually an
	 * {@link ErrorResponse} and instead throwing the appropriate exception.
	 * 
	 * @param response
	 *            the parsed response.
	 * 
	 * @throws InvalidAPIKeyException
	 * @throws MissingRequiredParameterException
	 * @throws InvalidParametersException
	 * @throws RequestExpiredException
	 * @throws NotFoundException
	 * @throws APIAccessDisabledException
	 * @throws ResponseException
	 */
	private void proccessResponse(Response response)
			throws InvalidAPIKeyException, MissingRequiredParameterException,
			InvalidParametersException, RequestExpiredException,
			NotFoundException, APIAccessDisabledException, ResponseException {

		// If this is an error response, throw the appropriate exception
		if (response instanceof ErrorResponse) {
			ErrorResponse errorResponse = (ErrorResponse) response;

			switch (errorResponse.getCode()) {
			case 1:
				throw new InvalidAPIKeyException(errorResponse);
			case 3:
				throw new MissingRequiredParameterException(errorResponse);
			case 4:
				throw new InvalidParametersException(errorResponse);
			case 5:
				throw new RequestExpiredException(errorResponse);
			case 6:
				throw new NotFoundException(errorResponse);
			case 7:
				throw new APIAccessDisabledException(errorResponse);
			default:
				throw new ResponseException(errorResponse);
			}
		}
	}
}
