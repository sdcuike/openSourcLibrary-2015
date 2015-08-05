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
 * @author doctor
 *
 * @time 2015年8月5日 下午9:17:20
 */
public final class SSHAgent {

	private Logger log = LoggerFactory.getLogger(getClass());

	private Connection connection;
	private Session session;

	public void initSession(String hostName, String userName, String passwd) throws IOException {
		connection = new Connection(hostName);
		connection.connect();

		boolean authenticateWithPassword = connection.authenticateWithPassword(userName, passwd);
		if (!authenticateWithPassword) {
			throw new RuntimeException("authenticateWithPassword failed");
		}

		session = connection.openSession();
	}

	public String execCommand(String command) throws IOException {
		session.execCommand(command, StandardCharsets.UTF_8.toString());
		InputStream streamGobbler = new StreamGobbler(session.getStdout());

		String result = IOUtils.toString(streamGobbler, StandardCharsets.UTF_8);
		log.info("execCommand exit status :", session.getExitStatus());
		return result;
	}

	public void close() {
		connection.close();
		session.close();
	}

	public static void main(String[] args) throws IOException {
		SSHAgent sshAgent = new SSHAgent();
		sshAgent.initSession("127.0.0.1", "doctor", "****");
		String execCommand = sshAgent.execCommand("pwd ; date");
		System.out.println(execCommand);
		sshAgent.close();
	}
}
