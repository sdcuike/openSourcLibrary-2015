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
package com.doctor.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author doctor
 *
 * @time 2015年8月11日 下午8:33:00
 */
public class ParallelStreamOperations {

	public static void main(String[] args) {
		Map<String, List<String>> map = new HashMap<>();
		map.putIfAbsent("1", new ArrayList<>());
		List<String> list = map.get("1");
		list.addAll(Arrays.asList("who", "doctor", "name"));

		map.putIfAbsent("11", new ArrayList<>());
		list = map.get("11");
		list.addAll(Arrays.asList("who1", "doctor who ", "who name"));

		int sum = map.entrySet().parallelStream().peek(entry -> {
			System.out.println(Thread.currentThread().getName() + " :" + entry);

		}).mapToInt(entry -> entry.getValue().size()).sum();
		System.out.println(sum);

		// int
		System.out.println("IntStream----------");
		IntStream.rangeClosed(0, 100).parallel().peek(t -> {
			System.out.println(Thread.currentThread().getName() + ":" + t);
		}).mapToObj(t -> t).collect(Collectors.groupingByConcurrent(t -> t, Collectors.summingInt(t -> 1)));
	}

}
