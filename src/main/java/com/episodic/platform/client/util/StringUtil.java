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

import java.util.Collection;
import java.util.Iterator;

/**
 * Collection of Utils related to {@link String}s.
 * 
 * @author Randy Simon
 */
public class StringUtil {

	/**
	 * Convert a collection to a delimited string.
	 * 
	 * @param collection
	 *            The collection
	 * @param delimiter
	 *            The delimiter to use
	 * 
	 * @return A string delimited by the specified delimiter.
	 */
	public static String join(Collection<Object> collection, String delimiter) {
		StringBuffer buffer = new StringBuffer();
		Iterator<Object> itr = collection.iterator();
		while (itr.hasNext()) {
			buffer.append(itr.next());
			if (itr.hasNext()) {
				buffer.append(delimiter);
			}
		}
		return buffer.toString();
	}

}
