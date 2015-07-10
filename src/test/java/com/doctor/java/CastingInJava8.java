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
package com.doctor.java;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

/**
 * Casting In Java 8
 * 
 * 
 * Casting an instance to a type reeks of bad design. Still,
 * there are situations where there is no other choice.
 * The ability to do this has hence been part of Java since day one.
 * 
 * @see http://blog.codefx.org/java/casting-in-java-8-and-beyond/
 * 
 * @author doctor
 *
 * @time 2015年7月10日 上午8:42:00
 */
public class CastingInJava8 {

	@Test
	public void test_Static_Casting() {
		Object object = Integer.valueOf(12);
		if (object instanceof Integer) {
			Integer integer = (Integer) object;
			System.out.println(integer);
		}
	}

	@Test
	public void test_Dynamic_Casting_1() {
		Object object = Integer.valueOf(12);
		if (Integer.class.isInstance(object)) {
			Integer integer = Integer.class.cast(object);
			System.out.println(integer);
		}
	}

	@Test
	public void test_Dynamic_Casting_2() {
		Object object = Integer.valueOf(12);

		assertThat(castObject(object, Integer.class), equalTo(Integer.valueOf(12)));
	}

	@Test
	public void test_Dynamic_Casting_3() {
		Object object = Integer.valueOf(12);

		assertThat(castObject(object, String.class), equalTo(null));
	}

	public <T> T castObject(Object object, Class<T> c) {
		if (c.isInstance(object)) {
			return c.cast(object);
		}
		return null;

	}

	@Test
	public void test_Casting_Optional() {
		Optional<?> optional = Optional.ofNullable(Integer.valueOf(12));
		Optional<Integer> optional2 = optional.filter(Integer.class::isInstance)
				.map(Integer.class::cast);
		System.out.println(optional2);
	}
}
