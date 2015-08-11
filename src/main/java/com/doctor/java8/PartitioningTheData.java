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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author doctor
 *
 * @time 2015年8月11日 下午11:49:22
 */
public class PartitioningTheData {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 22, 55, 33);
		Map<Boolean, List<Integer>> map = list.stream().collect(Collectors.partitioningBy(t -> Integer.compare(t, 10) > 0));
		System.out.println(map.get(Boolean.TRUE));
		System.out.println(map.get(Boolean.FALSE));

		Map<Boolean, Long> map2 = list.stream().collect(Collectors.partitioningBy(t -> Integer.compare(t, 10) > 0, Collectors.counting()));
		System.out.println(map2.get(Boolean.TRUE));
		System.out.println(map2.get(Boolean.FALSE));

		Map<Boolean, Integer> map3 = list.stream().collect(Collectors.partitioningBy(t -> Integer.compare(t, 10) > 0, Collectors.summingInt((Integer t) -> t)));
		System.out.println(map3.get(Boolean.TRUE));
		System.out.println(map3.get(Boolean.FALSE));
	}

}
