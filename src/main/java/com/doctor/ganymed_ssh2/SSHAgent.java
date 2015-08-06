/*
 * Copyright (C) 2014- now() The  openSourcLibrary-2015  Authors
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
 */
package com.doctor.ganymed_ssh2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 1.确保所连接linux机器安装ssh，并且服务打开;
 * 2.密码登陆，需配置文件：
 * ssh配置文件： /ect/ssh/sshd_config
 * 配置项：PasswordAuthentication yes
 * 
 * 验证登陆成功否：ssh 127.0.0.1（/other）
 * 
 * @see http://www.ganymed.ethz.ch/ssh2/FAQ.html
 *      http://www.programcreek.com/java-api-examples/index.php?api=ch.ethz.ssh2.StreamGobbler
 *      http://www.javawebdevelop.com/3240343/
 * 
 * @author doctor
 *
 * @time 2015年8月5日 下午9:17:20
 */
public final class SSHAgent {

	private Logger log = LoggerFactory.getLogger(getClass());

	private Connection connection;

	public void initSession(String hostName, String userName, String passwd) throws IOException {
		connection = new Connection(hostName);
		connection.connect();

		boolean authenticateWithPassword = connection.authenticateWithPassword(userName, passwd);
		if (!authenticateWithPassword) {
			throw new RuntimeException("Authentication failed. Please check hostName, userName and passwd");
		}
	}

	/**
	 * Why can't I execute several commands in one single session?
	 * 
	 * If you use Session.execCommand(), then you indeed can only execute only one command per session. This is not a restriction of the library, but rather an enforcement by the underlying SSH-2 protocol (a Session object models the underlying SSH-2 session).
	 * 
	 * There are several solutions:
	 * 
	 * Simple: Execute several commands in one batch, e.g., something like Session.execCommand("echo Hello && echo again").
	 * Simple: The intended way: simply open a new session for each command - once you have opened a connection, you can ask for as many sessions as you want, they are only a "virtual" construct.
	 * Advanced: Don't use Session.execCommand(), but rather aquire a shell with Session.startShell().
	 * 
	 * @param command
	 * @return
	 * @throws IOException
	 */

	public String execCommand(String command) throws IOException {
		Session session = connection.openSession();
		session.execCommand(command, StandardCharsets.UTF_8.toString());
		InputStream streamGobbler = new StreamGobbler(session.getStdout());

		String result = IOUtils.toString(streamGobbler, StandardCharsets.UTF_8);
		log.info("execCommand exit status :{}", session.getExitStatus());
		IOUtils.closeQuietly(streamGobbler);
		session.close();
		return result;
	}

	public void close() {
		connection.close();
	}

	public static void main(String[] args) throws IOException {
		SSHAgent sshAgent = new SSHAgent();
		sshAgent.initSession("127.0.0.1", "xxx", "xxx");
		String execCommand = sshAgent.execCommand("pwd ; date");
		System.out.println("pwd ; date:" + execCommand);
		String execCommand2 = sshAgent.execCommand("who  ");
		System.out.println("who  :" + execCommand2);

		sshAgent.close();
	}
}
