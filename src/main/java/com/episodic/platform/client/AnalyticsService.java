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
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;

import com.episodic.platform.client.exception.ResponseException;
import com.episodic.platform.client.response.Response;
import com.episodic.platform.client.response.analytics.CampaignsDailyResponse;
import com.episodic.platform.client.response.analytics.EpisodeDailyResponse;
import com.episodic.platform.client.response.analytics.EpisodesSummaryResponse;

/**
 * Class for making request to the Episodic Platform API Analytics service.
 * 
 * @author Randy Simon
 */
public class AnalyticsService extends BaseService {

	/**
	 * The service name
	 */
	private static final String SERVICE_NAME = "analytics";

	/**
	 * The name of the episodes summary report method
	 */
	private static final String EPISODES_SUMMARY_REPORT_METHOD = "request_episodes_summary_report";

	/**
	 * The name of the episode daily report method
	 */
	private static final String EPISODE_DAILY_REPORT_METHOD = "request_episode_daily_report";

	/**
	 * The name of the campaigns daily report method
	 */
	private static final String CAMPAIGNS_DAILY_REPORT_METHOD = "request_campaigns_daily_report";

	/**
	 * The name of the method to get the report once you have a token.
	 */
	private static final String GET_REPORT_METHOD = "get_report";

	/**
	 * Valid date ranges
	 */
	public static enum DateRange {
		TODAY {
			public String toString() {
				return "today";
			}
		},
		LAST_SEVEN {
			public String toString() {
				return "last_seven";
			}
		},
		LAST_THIRTY {
			public String toString() {
				return "last_thirty";
			}
		},
        ALL_TIME {
            public String toString() {
                return "all_time";
            }
        }
	}

	/**
	 * Valid date grouping values
	 */
	public static enum DateGrouping {
		DAILY {
			public String toString() {
				return "daily";
			}
		},
		AGGREGATE {
			public String toString() {
				return "aggregate";
			}
		}
	}

	/**
	 * Valid report formats
	 */
	public static enum ReportFormat {
		CSV {
			public String toString() {
				return "csv";
			}
		},
		XML {
			public String toString() {
				return "xml";
			}
		}
	}

	/**
	 * The instance of this class
	 */
	private static AnalyticsService instance;

	/**
	 * Private constructor to enforce singleton.
	 */
	private AnalyticsService() {
	}

	/**
	 * Get the instance of this class.
	 * 
	 * @return the singleton instance of this class
	 */
	public static AnalyticsService getInstance() {
		if (instance == null) {
			return new AnalyticsService();
		}

		return instance;
	}

	/**
	 * Fetches a previously generated report.
	 * 
	 * @param token
	 *            This is the value returned from one of the report generation
	 *            methods.
	 * @return The contents of the report.
	 * @throws ClientProtocolException
	 * @throws ResponseException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public String getReport(String token) throws ClientProtocolException,
			ResponseException, IOException, JAXBException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("report_token", token);
		Response response = connection.doGet(SERVICE_NAME, GET_REPORT_METHOD,
				params, false);

		return response.getBody();
	}

	/**
	 * Generates a report for all episodes in a show. The report will contain
	 * information such as the total views, complete views, downloads, etc for
	 * each episode in the show.
	 * 
	 * It is important to note that this method just generates the report and
	 * returns a token that can be used to get the report by calling
	 * {@link #getReport(String)}.
	 * 
	 * @param showId
	 *            The id of the show that contains your episodes. To get a list
	 *            of your shows see {@link QueryService#shows(Map)}.
	 * @param dateRange
	 *            Specifies period of your report.
	 * @param dateGrouping
	 *            Must be one of {@link DateGrouping#DAILY} or
	 *            {@link DateGrouping#AGGREGATE} to list the data by day or just
	 *            a total for the entire period respectively.
	 * @param format
	 *            the format of the report.
	 * @return This response object includes a token that can be used to
	 *         actually get the report by calling {@link #getReport(String)}
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ResponseException
	 * @throws JAXBException
	 */
	public EpisodesSummaryResponse requestEpisodesSummaryReport(String showId,
			DateRange dateRange, DateGrouping dateGrouping, ReportFormat format)
			throws ClientProtocolException, IOException, ResponseException,
			JAXBException {

		// Construct the request params
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("show_id", showId);
		params.put("date_range", dateRange);
		params.put("date_grouping", dateGrouping);
		params.put("format", format);

		return (EpisodesSummaryResponse) connection.doGet(SERVICE_NAME,
				EPISODES_SUMMARY_REPORT_METHOD, params);
	}

	/**
	 * Generates a report for a specific episode in a show. The report will
	 * contain information such as the total views, complete views, downloads,
	 * etc for each episode in the show.
	 * 
	 * It is important to note that this method just generates the report and
	 * returns a token that can be used to get the report by calling
	 * {@link #getReport(String)}.
	 * 
	 * @param showId
	 *            The id of the show that contains your episodes. To get a list
	 *            of your shows see {@link QueryService#shows(Map)}.
	 * @param episodeId
	 *            The id of the episodes to generate the report for. To get a
	 *            list of your episodes see {@link QueryService#episodes(Map)}.
	 * @param dateRange
	 *            Specifies period of your report.
	 * @param format
	 *            the format of the report.
	 * @return This response object includes a token that can be used to
	 *         actually get the report by calling {@link #getReport(String)}.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ResponseException
	 * @throws JAXBException
	 */
	public EpisodeDailyResponse requestEpisodeDailyReport(String showId,
			String episodeId, DateRange dateRange, ReportFormat format)
			throws ClientProtocolException, IOException, ResponseException,
			JAXBException {

		// Construct the request params
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("show_id", showId);
		params.put("date_range", dateRange);
		params.put("episode_id", episodeId);
		params.put("format", format);

		return (EpisodeDailyResponse) connection.doGet(SERVICE_NAME,
				EPISODE_DAILY_REPORT_METHOD, params);
	}

	/**
	 * Generates a report for all campaigns in a specific show. The report will
	 * contain information such as the total views, complete views, downloads,
	 * click throughs, etc for each campaign in the show.
	 * 
	 * It is important to note that this method just generates the report and
	 * returns a token that can be used to get the report by calling
	 * {@link #getReport(String)}.
	 * 
	 * @param showId
	 *            The id of the show that contains your episodes. To get a list
	 *            of your shows see {@link QueryService#shows(Map)}.
	 * @param dateRange
	 *            Specifies period of your report.
	 * @param format
	 *            the format of the report.
	 * @return This response object includes a token that can be used to
	 *         actually get the report by calling {@link #getReport(String)}.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ResponseException
	 * @throws JAXBException
	 */
	public CampaignsDailyResponse requestCampaignsDailyReport(String showId,
			DateRange dateRange, ReportFormat format)
			throws ClientProtocolException, IOException, ResponseException,
			JAXBException {

		// Construct the request params
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("show_id", showId);
		params.put("date_range", dateRange);
		params.put("format", format);

		return (CampaignsDailyResponse) connection.doGet(SERVICE_NAME,
				CAMPAIGNS_DAILY_REPORT_METHOD, params);
	}
}
