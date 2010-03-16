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

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.episodic.platform.client.response.query.ShowsResponse;

/**
 * Makes requests to a test server. Some IDs are hardcoded so this will not work
 * for most developers without some modifications.
 * 
 * @author Randy Simon
 */
public class QueryServiceTest extends BaseServiceTest {

	private static QueryService queryService = QueryService.getInstance();

	@Before
	public void setUp() throws Exception {
		super.setUp();
		queryService.setConnection(new Connection(TEST_API_KEY,
				TEST_SECRET_KEY, TEST_HOST, TEST_PORT));
	}

	@Test
	public void testShows() throws Exception {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page", 1);
		params.put("per_page", 20);
		ShowsResponse response = queryService.shows(params);

		assertNotNull(response);
	}
}
