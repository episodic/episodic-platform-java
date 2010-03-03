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

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.episodic.platform.client.response.query.CustomFieldItem;
import com.episodic.platform.client.response.query.EpisodeItem;
import com.episodic.platform.client.response.query.EpisodesResponse;
import com.episodic.platform.client.response.query.PlaylistItem;
import com.episodic.platform.client.response.query.PlaylistsResponse;
import com.episodic.platform.client.response.query.ShowItem;
import com.episodic.platform.client.response.query.ShowsResponse;
import com.episodic.platform.client.response.query.EpisodeItem.EpisodePublishingStatus;

/**
 * Tests that we can parse responses returned from a Query Service request.
 * 
 * @author Randy Simon
 */
public class QueryResponseTest extends BaseResponseTest { 
	
	@Test
	public void testEpisodesResponse() throws Exception {
		String xml = readFileAsString(currentPath + "/src/test/xml/episodes-response.xml");
		
		EpisodesResponse response = (EpisodesResponse)connection.unmarshall(xml);
		
		assertEquals(14, response.getTotal());
		assertEquals(20, response.getPerPage());
	    assertEquals(1, response.getPage());
	    assertEquals(1, response.getPages());
	    
	    // Pull out a specific episode item
	    EpisodeItem episode = response.getEpisodeItemById("oo3kyjwjzcap");
	    
	    assertNotNull(episode);
	    assertEquals("Episode 2", episode.getName());
	    assertEquals(0, episode.getDescription().length());
	    assertEquals(2, episode.getTags().length);
	    assertEquals("tag1", episode.getTags()[0]);
	    assertEquals("tag2", episode.getTags()[1]);
	    
	    DateTime airDate = new DateTime(2010, 1, 6, 2, 13, 12, 0, DateTimeZone.UTC);
	    assertEquals(airDate, episode.getAirDate());
	    assertNull(episode.getOffAirDate());
	    assertEquals("00:00:28", episode.getDuration());
	    assertEquals(EpisodePublishingStatus.ON_THE_AIR, episode.getStatus());
	    
	    List<CustomFieldItem> customFields = episode.getCustomFields();
	    assertEquals(4, customFields.size());
	    
	    CustomFieldItem firstField = episode.getCustomFieldByPosition(1);
	    assertNotNull(firstField);
	    assertEquals("text", firstField.getType());
	    assertEquals(false, firstField.isRequired());
	    assertEquals("Series Name", firstField.getName());
	    assertEquals(1, firstField.getValues().size());
	    assertEquals("Game Trailers", firstField.getValues().iterator().next().getValue());
	    
	    CustomFieldItem secondField = episode.getCustomFieldByPosition(2);
	    assertNotNull(secondField);
	    assertEquals("number", secondField.getType());
	    assertEquals(true, secondField.isRequired());
	    assertEquals("Number Field", secondField.getName());
	    assertEquals(1, secondField.getValues().size());
	    assertEquals("567", secondField.getValues().iterator().next().getValue());
	    
	    CustomFieldItem thirdField = episode.getCustomFieldByPosition(3);
	    assertNotNull(thirdField);
	    assertEquals("date", thirdField.getType());
	    assertEquals(false, thirdField.isRequired());
	    assertEquals("Publish Date", thirdField.getName());
	    assertEquals(1, thirdField.getValues().size());
	    assertEquals("2010-01-29 18:51:00", thirdField.getValues().iterator().next().getValue());
	    
	    CustomFieldItem forthField = episode.getCustomFieldByPosition(4);
	    assertNotNull(forthField);
	    assertEquals("external_select", forthField.getType());
	    assertEquals(false, thirdField.isRequired());
	    assertEquals("Category", forthField.getName());
	    assertEquals(2, forthField.getValues().size());
	    assertEquals("Drama", forthField.getValues().iterator().next().getValue());
	    assertEquals("123", forthField.getValues().iterator().next().getId());
	    
	    assertEquals(1, episode.getPlaylists().size());
	    assertEquals("oo3my8ozcnb5", episode.getPlaylists().get(0).getId());
	    assertEquals(1, episode.getPlaylists().get(0).getPosition());
	    
	    assertEquals(3, episode.getThumbnails().size());
	    assertEquals("http://localhost/cdn/development/randysimon/assets/207/a38.jpg", episode.getThumbnails().get(0).getUrl());
	    
	    assertEquals(1, episode.getPlayers().size());
	    assertEquals("Default Player", episode.getPlayers().get(0).getName());
	    assertEquals(true, episode.getPlayers().get(0).isDefaultPlayer());
	    assertEquals("http://localhost/cdn/development/randysimon/5/oo3kyjwjzcap/config.xml", episode.getPlayers().get(0).getConfig());
	    assertNotNull(episode.getPlayers().get(0).getEmbedCode());
	    
	    assertEquals(1, episode.getDownloads().size());
	    assertEquals("http://localhost/cdn/development/randysimon/assets/480/a38.mp4", episode.getDownloads().get(0).getUrl());
	}
	
	@Test
	public void testPlaylistsResponse() throws Exception {
		String xml = readFileAsString(currentPath + "/src/test/xml/playlists-response.xml");
		
		PlaylistsResponse response = (PlaylistsResponse)connection.unmarshall(xml);
		
		assertEquals(17, response.getTotal());
		assertEquals(20, response.getPerPage());
	    assertEquals(1, response.getPage());
	    assertEquals(1, response.getPages());
	    
	    // Pull out a specific playlist item
	    PlaylistItem playlist = response.getPlaylistItemById("ootudbh0gdfl");
	    
	    assertNotNull(playlist);
	    assertEquals("test1", playlist.getName());
	    assertEquals("", playlist.getDescription());
	    DateTime createdAt = new DateTime(2010, 1, 7, 21, 20, 37, 0, DateTimeZone.UTC);
	    assertEquals(createdAt, playlist.getCreatedAt());
	    
	    List<CustomFieldItem> customFields = playlist.getCustomFields();
	    assertEquals(3, customFields.size());
	    
	    CustomFieldItem firstField = playlist.getCustomFieldByPosition(1);
	    assertNotNull(firstField);
	    assertEquals("text", firstField.getType());
	    assertEquals(false, firstField.isRequired());
	    assertEquals("text field", firstField.getName());
	    assertEquals(0, firstField.getValues().size());
	    
	    CustomFieldItem secondField = playlist.getCustomFieldByPosition(2);
	    assertNotNull(secondField);
	    assertEquals("date", secondField.getType());
	    assertEquals(false, secondField.isRequired());
	    assertEquals("date field", secondField.getName());
	    assertEquals(1, secondField.getValues().size());
	    assertEquals("2010-02-11 22:42:00", secondField.getValues().get(0).getValue());
	    
	    CustomFieldItem thirdField = playlist.getCustomFieldByPosition(3);
	    assertNotNull(thirdField);
	    assertEquals("external_select", thirdField.getType());
	    assertEquals(false, thirdField.isRequired());
	    assertEquals("External Select Field", thirdField.getName());
	    assertEquals(0, thirdField.getValues().size());

	    assertEquals(7, playlist.getEpisodes().size());
	    assertEquals("oz049unr3jep", playlist.getEpisodes().get(0).getId());
	    assertEquals(1, playlist.getEpisodes().get(0).getPosition());
	    
	    assertEquals(0, playlist.getThumbnails().size());
	    
	    assertEquals(1, playlist.getPlayers().size());
	    assertEquals("Default Player", playlist.getPlayers().get(0).getName());
	    assertEquals(true, playlist.getPlayers().get(0).isDefaultPlayer());
	    assertEquals("http://localhost/cdn/development/randysimon/6/playlists/ootudbh0gdfl/playlist.xml", playlist.getPlayers().get(0).getConfig());
	    assertTrue(playlist.getPlayers().get(0).getEmbedCode().length() > 0);
	}
	
	@Test
	public void testShowsResponse() throws Exception {
		String xml = readFileAsString(currentPath + "/src/test/xml/shows-response.xml");
		
		ShowsResponse response = (ShowsResponse)connection.unmarshall(xml);
		
		assertEquals(2, response.getTotal());
		assertEquals(20, response.getPerPage());
	    assertEquals(1, response.getPage());
	    assertEquals(1, response.getPages());
	    
	    ShowItem show = response.getShowItemById("89675");
	    assertNotNull(show);
	    assertTrue(show.exists());
	    
	    assertEquals("My show", show.getName());
	    assertEquals("My description", show.getDescription());
	    
	    assertEquals(1, show.getPlayers().size());
	    assertEquals("Default Player", show.getPlayers().get(0).getName());
	    assertEquals(true, show.getPlayers().get(0).isDefaultPlayer());
	    assertEquals("http://localhost/cdn/development/randysimon/5/latest/config.xml", show.getPlayers().get(0).getConfig());
	    assertTrue(show.getPlayers().get(0).getEmbedCode().length() > 0);
	    
	    show = response.getShowItemById("adfadsfasdf");
	    assertFalse(show.exists());
	}
}
