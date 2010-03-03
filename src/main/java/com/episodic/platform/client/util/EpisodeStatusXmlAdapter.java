/* 
 * Episodic Platform Java SDK
 * 
 * Copyright (c) 2010 by Episodic, Inc.
 * 
 * Licensed under the terms of The MIT License.
 * Please see the LICENSE included with this distribution for details.
 * 
 */
package com.episodic.platform.client.util;

import com.episodic.platform.client.response.query.EpisodeItem.EpisodePublishingStatus;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Class used for marshalling to/from a String/EpisodicPublishingStatus.
 * 
 * @author Randy Simon
 */
@XmlTransient
public class EpisodeStatusXmlAdapter extends XmlAdapter<String, EpisodePublishingStatus> {

	@Override
	public EpisodePublishingStatus unmarshal(String v) throws Exception {
		if (v == null || v.trim().length() == 0) {
			return null;
		}
		
		return EpisodePublishingStatus.valueOf(EpisodePublishingStatus.class, v);
	}

	@Override
	public String marshal(EpisodePublishingStatus v) throws Exception {
		return v.toString();
	}

}
