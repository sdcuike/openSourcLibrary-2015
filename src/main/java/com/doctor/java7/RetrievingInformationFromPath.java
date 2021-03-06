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
 *
 */
package com.doctor.java7;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Retrieving information from a Path
 * 
 * @author doctor
 *
 * @time: 2015年8月30日 上午11:06:28
 */
public class RetrievingInformationFromPath {

	public static void main(String[] args) {
		Path path = Paths.get("/usr/bin/zip");
		System.out.println("getFileName:" + path.getFileName());
		System.out.println("getNameCount:" + path.getNameCount());
		System.out.println("getParent:" + path.getParent());
		System.out.println("getRoot:" + path.getRoot());
		System.out.println(path.subpath(0, 2));

	}

}
