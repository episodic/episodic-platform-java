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

import com.episodic.platform.client.exception.InvalidParametersException;
import com.episodic.platform.client.response.write.CreateEpisodeResponse;

/**
 * Tests that we can parse responses returned from a Write Service request.
 * 
 * @author Randy Simon
 */
public class WriteResponseTest extends BaseResponseTest {

	@Test
	public void testCreateEpisodeResponse() throws Exception {
		String xml = readFileAsString(currentPath
				+ "/src/test/xml/create-episode-response.xml");

		CreateEpisodeResponse response = (CreateEpisodeResponse) connection
				.unmarshall(xml);
		assertFalse(response.getEpisodeId().isEmpty());
		assertTrue(response.getUploads().isEmpty());
	}

	@Test
	public void testCreateEpisodeS3Response() throws Exception {
		String xml = readFileAsString(currentPath
				+ "/src/test/xml/create-episode-response-s3.xml");

		CreateEpisodeResponse response = (CreateEpisodeResponse) connection
				.unmarshall(xml);
		assertFalse(response.getEpisodeId().isEmpty());
		assertEquals(1, response.getUploads().size());

		assertEquals("p3h7kldgjg1t", response.getEpisodeId());
		assertEquals("1-0.mp4", response.getUploads().get(0).getFilename());
		assertEquals("http://randy.dev.assets.episodic.com.s3.amazonaws.com/",
				response.getUploads().get(0).getUrl());
		assertEquals(5, response.getUploads().get(0).getUploadParams().size());
	}

	@Test
	public void testInvalidParamSingleResponse() throws Exception {
		String xml = readFileAsString(currentPath
				+ "/src/test/xml/invalid-param-response-single.xml");

		try {
			connection.processAndUnmarshallResponse(xml);
		} catch (InvalidParametersException e) {
			assertEquals(1, e.getResponse().getInvalidParameters().size());
			assertEquals("extensible_metadata_field_values_date_value", e
					.getResponse().getInvalidParameters().get(0).getName());
		}
	}

	@Test
	public void testInvalidParamMultipleResponse() throws Exception {
		String xml = readFileAsString(currentPath
				+ "/src/test/xml/invalid-param-response-multiple.xml");

		try {
			connection.processAndUnmarshallResponse(xml);
		} catch (InvalidParametersException e) {
			assertEquals(2, e.getResponse().getInvalidParameters().size());
			assertEquals("extensible_metadata_field_values_date_value", e
					.getResponse().getInvalidParameters().get(0).getName());
			assertEquals("name", e.getResponse().getInvalidParameters().get(1)
					.getName());
		}
	}
}
