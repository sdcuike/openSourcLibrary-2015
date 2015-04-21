package com.doctor.java8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Easy As Pie Local Caching
 * 
 * @see http://www.javacodegeeks.com/2014/03/java-8-friday-goodies-easy-as-pie-local-caching.html
 * 
 * @author doctor
 *
 * @time 2015年4月21日 上午8:56:06
 */
public class EasyAsPieLocalCaching {
	private static Map<Integer, Integer> cache = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		// System.out.println(fibonacci(6));
		System.out.println(fibonacci_cache(6));
	}

	private static int fibonacci(int i) {
		if (i == 0 || i == 1) {
			return 1;
		}
		System.out.println("Calculating fibonacci(" + i + ")");
		return fibonacci(i - 1) + fibonacci(i - 2);
	}

	private static int fibonacci_cache(int i) {
		if (i == 0 || i == 1) {
			return 1;
		}
		return cache.computeIfAbsent(i, v -> {
			System.out.println("Calculating fibonacci(" + i + ")");
			return fibonacci_cache(i - 1) + fibonacci_cache(i - 2);
		});
	}
}
