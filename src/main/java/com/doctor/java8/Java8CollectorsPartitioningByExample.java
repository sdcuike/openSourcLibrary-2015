package com.doctor.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @see http://www.concretepage.com/java/jdk-8/java-8-collectors-partitioningby-example
 * 
 * @author doctor
 *
 * @time 2015年5月2日 下午1:23:52
 */
public class Java8CollectorsPartitioningByExample {

	public static void main(String[] args) {
		List<Student> list = Arrays.asList(new Student("Ram", 18), new Student("Shyam", 22)
				, new Student("Mohan", 19), new Student("Mahesh", 20)
				, new Student("Krishna", 21));
		Map<Boolean, List<Student>> collect = list.stream().collect(Collectors.partitioningBy((Student s) -> s.getAge() > 20));
		collect.forEach((k, v) -> System.out.println(k + ":" + v));
	}

	private static class Student {
		private String name;
		private int age;

		public Student(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}
}
