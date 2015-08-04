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
package com.doctor.java.common;

import java.util.Optional;

/**
 * casting-in-java-8-and-beyond
 * 
 * @author doctor
 * 
 * @see https://dzone.com/articles/casting-in-java-8-and-beyond
 *
 * @time 2015年8月4日 上午8:52:56
 */
public class CastingInJava8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Static Casting
		Object integer = Integer.valueOf(12);
		if (integer instanceof Integer) {
			System.out.println(integer.getClass());
		}

		// Dynamic Casting
		Class<?> type = Integer.class;
		if (type.isInstance(integer)) {
			Integer cast = Integer.class.cast(integer);
			System.out.println(cast.getClass());
		}

		System.out.println(DynamicCasting(Double.valueOf(123.8D), Double.class));

		// Casting In Streams And Optionals
		// Casting the value of an Optional or the elements of a Stream is a two-step-process: First we have to filter out instances of the wrong type, then we can cast to the desired one.

		// With the methods on Class, we do this with method references. Using the example of Optional:
		Optional<Integer> optional = Optional.of(Integer.valueOf(888));
		Optional<Integer> integerOptional = optional.filter(Integer.class::isInstance).map(Integer.class::cast);
		System.out.println(integerOptional.get());
	}

	public static <T> T DynamicCasting(Object object, Class<T> type) {
		if (type.isInstance(object)) {
			T cast = type.cast(object);
			return cast;
		}
		return null;

	}
}
