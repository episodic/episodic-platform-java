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

import junit.framework.TestCase;

/**
 * Common code for testing services. Some IDs are hardcoded so this will not work
 * for most developers without some modifications.
 * 
 * @author Randy Simon
 */
public class BaseServiceTest extends TestCase {

	public static final String TEST_API_KEY = "5994af59e8303733d1e10599d12e97c2";
	public static final String TEST_SECRET_KEY = "73efd913735636437d26e92782d11122";
	public static final String TEST_HOST = "localhost";
	public static final int TEST_PORT = 3000;
	public static final String TEST_SHOW_ID = "p7kpeeqkh2pt";

	protected String currentPath;

	public void setUp() throws Exception {
		currentPath = new File(".").getCanonicalPath();
	}
}
