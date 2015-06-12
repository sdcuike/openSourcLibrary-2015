package com.doctor.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Returning null from a public method is often a bad practice.
 * 
 * 不要返回null值，宁可返回空。
 * 
 * 你知道，我知道，它们知道，每个人都知道，从公有方法返回null是一个坏习惯。
 * 原因很多，比如避免出现运行时的空指针异常（NullPointerException），我们必须自己判断
 * horrible == null。
 * 为了避免返回null，java8版本以后可以选择用 {@linkplain java.util.Optional}(java8之前版本可以选择
 * Guava中的该类）
 * 
 * @author doctor
 * 
 * @see http://rdafbn.blogspot.gr/2014/09/java-8-null-no-thanks.html
 *
 * @time 2015年6月12日 上午11:40:30
 */
public class Java8NullNoThanks {

	private static final Map<String, Integer> nameToAge = new HashMap<>();
	private static final Map<String, List<String>> nameToEmails = new HashMap<>();
	static {
		nameToAge.put("doctor who", 6000);
		List<String> emails = new ArrayList<>();
		emails.add("doctor_who@aol.com");
		emails.add("doctor_who@gmail.com");

		nameToEmails.put("doctor who", emails);
	}

	public static void main(String[] args) {
		System.out.println(getAge(""));

		System.out.println(getEmails(""));

		System.out.println(getAge("doctor who"));

		System.out.println(getEmails("doctor who"));

	}

	public static Integer getAge(String name) {
		return Optional.ofNullable(nameToAge.get(name)).orElse(-1);
	}

	public static List<String> getEmails(String name) {
		return Optional.ofNullable(nameToEmails.get(name)).orElse(new ArrayList<>());
	}
}
