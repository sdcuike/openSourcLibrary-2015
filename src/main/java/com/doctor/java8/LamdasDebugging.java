package com.doctor.java8;

import java.util.Arrays;
import java.util.List;

/**
 * 利用Stream<T> peek(Consumer<? super T> action) 调试java8 函数式
 * 
 * @author doctor
 *
 * @time 2015年5月4日 下午9:39:15
 */
public class LamdasDebugging {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("one", "to", "three", "five doc");
		long count = list.stream().peek(System.out::println)
				.filter(w -> w.length() > 3)
				.peek(System.out::println)
				.map(String::toUpperCase)
				.count();
		System.out.println(count);

	}

}
