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
 * Concat Streams in Java 8
 * 
 * @author doctor
 *
 * @time 2015年8月5日 上午10:53:31
 * 
 * @see http://www.programcreek.com/2014/01/concat-streams-in-java-8/
 *
 */
public class ConcatStreamsInJava8 {

	public static void main(String[] args) {
		//Merge Two Streams
		String[] strings1 = {"a","b","c"};
		String[] strings2 = {"d","e","f"};
		Stream<String> stream1 = Stream.of(strings1);
		Stream<String> stream2 = Stream.of(strings2);
		
		String[] array = Stream.concat(stream1, stream2).toArray(String[]::new);
		System.out.println(Arrays.toString(array));

		//Merge a Filtered Stream to Another
		stream1 = Stream.of(strings1);
		stream2 = Stream.of(strings2);
		String[] array2 = Stream.concat(stream1.filter(s-> s.length() > 1), stream2).toArray(String[]::new);
		System.out.println(Arrays.toString(array2));
		
		//Merger More Than 2 Streams
		stream1 = Stream.of(strings1);
		stream2 = Stream.of(strings2);
		Stream<String> stream3 = Stream.of("g","h","i");
		String[] array3 = Stream.concat(Stream.concat(stream1, stream2), stream3).toArray(String[]::new);
		System.out.println(Arrays.toString(array3));
		
		
		//Use Stream.of(...).flatMap() for Merging
		stream1 = Stream.of(strings1);
		stream2 = Stream.of(strings2);
		String[] array4 = Stream.of(stream1,stream2).flatMap(x -> x).toArray(String[]::new);
		System.out.println(Arrays.toString(array4));

	}

}
