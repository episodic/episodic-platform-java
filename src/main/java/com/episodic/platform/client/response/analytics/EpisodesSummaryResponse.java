/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client.response.analytics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The unmarshalled response to a
 * {@link com.episodic.platform.client.AnalyticsService#requestEpisodesSummaryReport(String, com.episodic.platform.client.AnalyticsService.DateRange, com.episodic.platform.client.AnalyticsService.DateGrouping, com.episodic.platform.client.AnalyticsService.ReportFormat)}
 * call.
 * 
 * The {@link #getToken()} can be used to call
 * {@link com.episodic.platform.client.AnalyticsService#getReport(String)}.
 * 
 * @author Randy Simon
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "episodes_summary_response")
public class EpisodesSummaryResponse extends TokenResponse {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

}
