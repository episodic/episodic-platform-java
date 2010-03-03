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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Class used for marshalling to/from a String/DateTime.
 * 
 * This class was mainly taken from <a href="http://yestech.org/">YES Technology
 * Association</a> <a href="http://github.com/yestech/yesepisodic">Episodic
 * client library</a>.
 * 
 * @author Randy Simon
 * @author A.J. Wright
 */
@XmlTransient
public class DateXmlAdapter extends XmlAdapter<String, DateTime> {

	private static final DateTimeFormatter FORMAT = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss").withZone(DateTimeZone.UTC);

	@Override
	public DateTime unmarshal(String v) throws Exception {
		if (v == null || v.trim().length() == 0) {
			return null;
		}
		
		// Use UTC for parsing
		return FORMAT.parseDateTime(v);
	}

	@Override
	public String marshal(DateTime v) throws Exception {
		return v.toString(FORMAT);
	}

}
