/*
 * Copyright (C) 2014-present  The   openSourcLibrary-2015  Authors
 *
 * https://github.com/sdcuike
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.doctor.ganymed_ssh2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 
 * @author doctor
 *
 * @time 2015年8月5日 下午2:14:16
 *
 * @see https://github.com/northern-bites/ganymed-ssh2/blob/master/examples/Basic.java
 *
 */
public class Basic {

	/**
	 * 如果出现：Password authentication fails, I get "Authentication method password
	 * not supported by the server at this stage
	 * 
	 * 请查看：ssh配置文件： /ect/ssh/sshd_config ， 找到配置： "PasswordAuthentication" 被设为 "no" .
	 * changed it to "PasswordAuthentication yes"
	 * 
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String hostname = "127.0.0.1";
		String username = "doctor";
		String passwd = "xxx";
		Connection connection = new Connection(hostname);
		connection.connect();

		boolean authenticateWithPassword = connection.authenticateWithPassword(username, passwd);
		if (!authenticateWithPassword) {
			throw new RuntimeException("authenticateWithPassword failed");
		}

		Session session = connection.openSession();
		session.execCommand("pwd  ; date ;echo hello doctor");
		InputStream streamGobbler = new StreamGobbler(session.getStdout());

		String result = IOUtils.toString(streamGobbler, StandardCharsets.UTF_8);
		System.out.println("result:" + result);
		System.out.println("ExitStatus:" + session.getExitStatus());

		session.close();
		connection.close();

	}

}
