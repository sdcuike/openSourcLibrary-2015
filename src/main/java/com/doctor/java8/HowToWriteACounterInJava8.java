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
package com.doctor.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * How to write a counter in one line of code?
 * 
 * @author doctor
 * 
 * @see http://www.programcreek.com/2014/01/how-to-write-a-counter-in-java-8/
 *
 * @time 2015年8月5日 上午10:11:17
 *
 */
public class HowToWriteACounterInJava8 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("hello","doctor who","doctor","who","hello","doctor who");
		Map<String, Long> map = list.stream().collect(Collectors.groupingBy(String::toString,Collectors.counting()));
		map.entrySet().forEach(entry -> {
			System.out.println("key:" + entry.getKey() + "  value:" + entry.getValue());
		});

	}

}
