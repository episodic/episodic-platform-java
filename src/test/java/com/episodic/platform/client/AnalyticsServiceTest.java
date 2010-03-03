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

import org.junit.Before;
import org.junit.Test;

import com.episodic.platform.client.AnalyticsService.DateGrouping;
import com.episodic.platform.client.AnalyticsService.DateRange;
import com.episodic.platform.client.AnalyticsService.ReportFormat;
import com.episodic.platform.client.response.analytics.EpisodesSummaryResponse;

/**
 * Makes requests to a test server. Some IDs are hardcoded so this will not work
 * for most developers without some modifications.
 * 
 * @author Randy Simon
 */
public class AnalyticsServiceTest extends BaseServiceTest {

	private static AnalyticsService analyticsService = AnalyticsService
			.getInstance();

	@Before
	public void setUp() throws Exception {
		super.setUp();
		analyticsService.setConnection(new Connection(TEST_API_KEY,
				TEST_SECRET_KEY, TEST_HOST, TEST_PORT));
	}

	@Test
	public void testGetReport() throws Exception {
		EpisodesSummaryResponse reportResponse = analyticsService
				.requestEpisodesSummaryReport(TEST_SHOW_ID,
						DateRange.LAST_SEVEN, DateGrouping.DAILY,
						ReportFormat.CSV);
		assertFalse(reportResponse.getToken().isEmpty());

		String report = analyticsService.getReport(reportResponse.getToken());
		assertFalse(report.isEmpty());
	}
}
