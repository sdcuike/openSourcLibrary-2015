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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 模拟交互式终端
 * 
 * @author doctor
 *
 * @time 2015年8月6日 上午10:32:35
 *
 * @see http://www.programcreek.com/java-api-examples/index.php?api=ch.ethz.ssh2.SCPClient
 *
 */
public final class SSHAgent2 {
	private Logger log = LoggerFactory.getLogger(getClass());
	private Connection connection;
	private Session session;
	private BufferedReader stdout;
	private PrintWriter printWriter;
	private BufferedReader stderr;
	private ExecutorService service = Executors.newFixedThreadPool(3);
	private Scanner scanner = new Scanner(System.in);

	public void initSession(String hostName, String userName, String passwd) throws IOException {
		connection = new Connection(hostName);
		connection.connect();

		boolean authenticateWithPassword = connection.authenticateWithPassword(userName, passwd);
		if (!authenticateWithPassword) {
			throw new RuntimeException("Authentication failed. Please check hostName, userName and passwd");
		}
		session = connection.openSession();
		session.requestDumbPTY();
		session.startShell();
		stdout = new BufferedReader(new InputStreamReader(new StreamGobbler(session.getStdout()), StandardCharsets.UTF_8));
		stderr = new BufferedReader(new InputStreamReader(new StreamGobbler(session.getStderr()), StandardCharsets.UTF_8));
		printWriter = new PrintWriter(session.getStdin());
	}

	public void execCommand() throws IOException {
		service.submit(new Runnable() {

			@Override
			public void run() {
				String line;
				try {
					while ((line = stdout.readLine()) != null) {
						System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		service.submit(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					System.out.print("input:");
					String nextLine = scanner.nextLine();
					printWriter.write(nextLine + "\r\n");
					printWriter.flush();
				}

			}
		});

	}

	public void close() {
		IOUtils.closeQuietly(stdout);
		IOUtils.closeQuietly(stderr);
		IOUtils.closeQuietly(printWriter);
		IOUtils.closeQuietly(scanner);
		session.close();
		connection.close();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SSHAgent2 sshAgent = new SSHAgent2();
		sshAgent.initSession("127.0.0.1", "xx", "xx");

		sshAgent.execCommand();

		// sshAgent.close();

	}

}
