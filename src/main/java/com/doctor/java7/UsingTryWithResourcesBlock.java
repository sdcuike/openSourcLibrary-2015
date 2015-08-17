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
package com.doctor.java7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Using the try-with-resources block to improve exception handling code
 * 
 * @author doctor
 *
 * @time 2015年8月17日 下午9:42:34
 * 
 *       To complete our understanding of the try-with-resources technique, we need to address
 *       two other topics as follows:
 * 
 *       1. Understanding suppressed exceptions
 * 
 * @see java.lang.Throwable #addSuppressed(Throwable)
 * @see java.lang.Throwable #getSuppressed()
 * 
 *      2. Structuring issues when using the try-with-resources technique
 *      try中对象创建要合理利用，避免创建过多中间对象。比如Paths.get,我们用string，没有用url参数。
 * 
 * 
 */
public class UsingTryWithResourcesBlock {

	public static void main(String[] args) {

		final String resource = UsingTryWithResourcesBlock.class.getResource("").getFile();

		final String fileName = resource.replace("target/classes", "src/main/java") + "UsingTryWithResourcesBlock.java";

		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
