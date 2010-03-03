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

import java.util.Arrays;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Class used for marshalling to/from a comma separated list and Array.
 * 
 * This class was mainly taken from <a href="http://yestech.org/">YES Technology
 * Association</a> <a href="http://github.com/yestech/yesepisodic">Episodic
 * client library</a>.
 * 
 * @author Randy Simon
 * @author A.J. Wright
 */
public class CommaSeperatedStringArrayXmlAdapter extends
		XmlAdapter<String, Object[]> {

	@Override
	public String[] unmarshal(String v) throws Exception {
		if (v != null && !"".equals(v)) {
			if (v.contains(",")) {
				return v.split(",");
			} else {
				return new String[] { v };
			}
		}
		return new String[0];
	}

	@Override
	public String marshal(Object[] v) throws Exception {
		return StringUtil.join(Arrays.asList(v), ",");
	}
}
