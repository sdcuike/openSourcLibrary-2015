package com.doctor.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @see http://www.concretepage.com/java/jdk-8/java-8-collectors-groupingby-example
 * 
 * @author doctor
 *
 * @time 2015年5月2日 下午1:35:34
 */
public class Java8CollectorsGroupingByExample {

	public static void main(String[] args) {
		List<Student> list = Arrays.asList(new Student("Ram", "A", 20), new Student("Shyam", "B", 22)
				, new Student("Mohan", "A", 22), new Student("Mahesh", "C", 20)
				, new Student("Krishna", "B", 21));

		Map<String, List<Student>> map = list.stream().collect(Collectors.groupingBy((Student s) -> s.getClassName()));
		map.forEach((k, v) -> System.out.println(k + ":" + v));
	}

	private static class Student {
		private String name;
		private int age;
		private String className;

		public Student(String name, String className, int age) {
			this.name = name;
			this.age = age;
			this.className = className;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public String getClassName() {
			return className;
		}

		@Override
		public String toString() {
			return new ToStringBuilder(this).append("name", name)
					.append("age", age).append("className", className).toString();
		}
	}

}
