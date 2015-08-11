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
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Grouping the Data
 * 
 * @author doctor
 *
 * @time 2015年8月12日 上午12:08:31
 */
public class GroupingTheData {

	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(new Person("doctor", "man", "address-1"), new Person("doctor who ", "man", "address-1"),
				new Person("doctor me", "woman", "address-2"));
		Map<String, List<Person>> map = persons.stream().collect(Collectors.groupingBy(Person::getSex));
		System.out.println(map);

		Map<String, Map<String, List<Person>>> map2 = persons.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getAddress)));
		System.out.println(map2);
	}

	private static class Person {
		private String name;
		private String sex;
		private String address;

		public Person(String name, String sex, String address) {
			this.name = name;
			this.sex = sex;
			this.address = address;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, sex, address);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (obj instanceof Person) {
				Person person = (Person) obj;
				return name.equals(person.name) && sex.equals(person.sex) && address.equals(person.address);
			}

			return false;
		}

		@Override
		public String toString() {

			return ToStringBuilder.reflectionToString(this);
		}
	}
}
