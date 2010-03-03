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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.episodic.platform.client.response.write.CreateAssetResponse;
import com.episodic.platform.client.response.write.CreateEpisodeResponse;
import com.episodic.platform.client.response.write.UpdateEpisodeResponse;

/**
 * Makes requests to a test server. Some IDs are hardcoded so this will not work
 * for most developers without some modifications.
 * 
 * @author Randy Simon
 */
public class WriteServiceTest extends BaseServiceTest {

	private static WriteService writeService = WriteService.getInstance();

	@Before
	public void setUp() throws Exception {
		super.setUp();
		writeService.setConnection(new Connection(TEST_API_KEY,
				TEST_SECRET_KEY, TEST_HOST, TEST_PORT));
	}

	@Test
	public void testCreateAsset() throws Exception {
		CreateAssetResponse response = writeService.createAsset(TEST_SHOW_ID,
				"My New Asset " + new Date().getTime(), currentPath
						+ "/src/test/media/1-0.mp4");

		assertTrue(response.getAssetId().length() > 0);
	}

	@Test
	public void testCreateUpdateEpisode() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("upload_types", "s3");
		params.put("video_filename", "1-0.mp4");
		params.put("thumbnail_filename", "thumb.png");
		CreateEpisodeResponse response = writeService.createEpisode(
				TEST_SHOW_ID, "My New Episode " + new Date().getTime(),
				params);

		System.out.println(response.getBody());
		assertTrue(response.getEpisodeId().length() > 0);

		File video = new File(currentPath + "/src/test/media/1-0.mp4");
		writeService.uploadFileForEpisode(response.getUploadForFile(video),
				video);

		File thumbnail = new File(currentPath + "/src/test/media/thumb.png");
		writeService.uploadFileForEpisode(response.getUploadForFile(thumbnail),
				thumbnail);

		// Now update the video on the episode
		Map<String, Object> updateParams = new HashMap<String, Object>();
		updateParams.put("upload_types", "s3");
		updateParams.put("video_filename", "3-0.m4v");
		updateParams.put("off_air_date", new Date().getTime() / 1000L);
		UpdateEpisodeResponse updateResponse = writeService.updateEpisode(
				response.getEpisodeId(), updateParams);

		video = new File(currentPath + "/src/test/media/3-0.m4v");
		writeService.uploadFileForEpisode(updateResponse
				.getUploadForFile(video), video);
	}
}
