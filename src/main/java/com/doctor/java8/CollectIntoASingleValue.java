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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author doctor
 *
 * @time 2015年8月11日 下午10:06:16
 */
public class CollectIntoASingleValue {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 22, 55, 33);
		Optional<Integer> max = list.stream().collect(Collectors.maxBy(Integer::compare));

		max.ifPresent(System.out::println);

		Optional<Integer> min = list.stream().collect(Collectors.minBy(Integer::compare));
		min.ifPresent(System.out::println);

		Double average = list.stream().collect(Collectors.averagingInt(t -> t));
		System.out.println(average);

	}

}
