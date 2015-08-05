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
import java.util.stream.Stream;

/**
 * Convert Stream to Array in Java 8
 * 
 * @author doctor
 * 
 * @see http://www.programcreek.com/2014/01/convert-stream-to-array-in-java-8/
 *
 * @time 2015年8月5日 上午10:38:41
 *
 */
public class ConvertStreamToArrayInJava8 {

	public static void main(String[] args) {
		String[] strings = {"hello","doctor","who are you"};
		String[] array = Stream.of(strings).toArray(String[]::new);
		System.out.println(Arrays.toString(array));
		
		String[] array2 = Stream.of(strings).toArray(size -> new String[size]);
		System.out.println(Arrays.toString(array2));

	}

}
