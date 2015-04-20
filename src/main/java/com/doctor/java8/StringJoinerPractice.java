package com.doctor.java8;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * java8 StringJoinerPractice
 * 
 * @see http://www.javacodegeeks.com/2014/03/java-can-finally-join-strings.html
 * 
 * @author doctor
 *
 * @time 2015年4月20日 下午2:09:10
 */
public class StringJoinerPractice {

	public static void main(String[] args) {
		// 1
		StringJoiner stringJoiner = new StringJoiner(",");
		stringJoiner.add("name");
		stringJoiner.add("sex");
		System.out.println(stringJoiner.toString());
		// 2
		stringJoiner = new StringJoiner(",", "[", "]");
		stringJoiner.add("name");
		stringJoiner.add("sex");
		System.out.println(stringJoiner.toString());
		// 3
		stringJoiner = new StringJoiner(",").add("name").add("sex");
		System.out.println(stringJoiner.toString());

		// 4
		String collect = Arrays.asList("name", "sex").stream().collect(Collectors.joining(","));
		System.out.println(collect);
	}

}
