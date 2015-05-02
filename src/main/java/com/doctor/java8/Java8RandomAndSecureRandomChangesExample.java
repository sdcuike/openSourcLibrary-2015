package com.doctor.java8;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * @see http://www.concretepage.com/java/jdk-8/java-8-random-securerandom-changes-example
 * 
 * @author doctor
 *
 * @time 2015年5月2日 上午10:38:40
 */
public class Java8RandomAndSecureRandomChangesExample {

	public static void main(String[] args) {
		List<Integer> valid_pwd_chars = new ArrayList<>();
		IntStream.rangeClosed('0', '9').forEach(valid_pwd_chars::add);
		IntStream.rangeClosed('a', 'z').forEach(valid_pwd_chars::add);
		IntStream.rangeClosed('A', 'Z').forEach(valid_pwd_chars::add);

		List<Character> passwd = new ArrayList<>();
		new Random().ints(8, 0, valid_pwd_chars.size())
				.map(valid_pwd_chars::get)
				.forEachOrdered(t -> passwd.add((char) t));

		System.out.println(passwd);
		StringJoiner stringJoiner = new StringJoiner("");
		passwd.stream().forEach(t -> stringJoiner.add(t + ""));
		System.out.println(stringJoiner.toString());

		//
		passwd.clear();
		new SecureRandom().ints(8, 0, valid_pwd_chars.size())
				.map(valid_pwd_chars::get)
				.forEach(t -> passwd.add((char) t));
		System.out.println(passwd);
		StringJoiner stringJoiner2 = new StringJoiner("");
		passwd.stream().forEach(t -> stringJoiner2.add(t + ""));
		System.out.println(stringJoiner2.toString());

	}

}
