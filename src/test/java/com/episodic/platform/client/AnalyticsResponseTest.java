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

import org.junit.Test;

import com.episodic.platform.client.response.analytics.EpisodesSummaryResponse;

/**
 * Tests that we can parse responses returned from an Analytics Service request.
 * 
 * @author Randy Simon
 */
public class AnalyticsResponseTest extends BaseResponseTest { 
	
	@Test
	public void testEpisodesSummaryResponse() throws Exception {
		String xml = readFileAsString(currentPath + "/src/test/xml/episodes-summary-response.xml");
		
		EpisodesSummaryResponse response = (EpisodesSummaryResponse)connection.unmarshall(xml);
		
		assertEquals("cc620906d7c44940efe458ca645083e0", response.getToken());
	}
}
