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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;

import junit.framework.TestCase;

/**
 * Common functionality factored out.
 * 
 * @author Randy Simon
 */
public class BaseResponseTest extends TestCase {

	protected static Connection connection = new Connection(); 
	protected String currentPath;

	@Before
	public void setUp() throws Exception {
		currentPath = new File(".").getCanonicalPath();
	}
	
	/**
	 * Helper method to read a file into a string.
	 * 
	 * @param filePath
	 * @return
	 * @throws java.io.IOException
	 */
	protected String readFileAsString(String filePath)
			throws java.io.IOException {
		byte[] buffer = new byte[(int) new File(filePath).length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(
				filePath));
		f.read(buffer);
		return new String(buffer);
	}
}
