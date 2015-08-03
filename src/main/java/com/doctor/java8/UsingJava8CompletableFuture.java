/*
 * Copyright (C) 2014-present  The  openSourcLibrary-2015  Authors
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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author doctor
 * 
 *         A simple scatter-gather scenario using Java 8 CompletableFuture
 * 
 * @see https://dzone.com/articles/using-java-8-completablefuture-and-rx-java-observa
 *
 * @time 2015年8月3日 下午4:01:06
 */
public class UsingJava8CompletableFuture {

	public static void main(String[] args) throws Throwable {
		// Sequential
		List<String> list = IntStream.range(1, 20).boxed().map(UsingJava8CompletableFuture::generateTask).collect(Collectors.toList());
		System.out.println(list);

		// With CompletableFuture
		List<String> collect = IntStream.range(1, 20).boxed().map(i -> CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			return i + " -test";
		}))
				.map(f -> f.join()).collect(Collectors.toList());
		System.out.println(collect);
	}

	public static String generateTask(int i) {
		try {
			TimeUnit.MICROSECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i + "- test";
	}
}
