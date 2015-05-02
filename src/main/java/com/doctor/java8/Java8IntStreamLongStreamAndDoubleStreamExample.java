package com.doctor.java8;

import java.util.stream.IntStream;

/**
 * @see http://www.concretepage.com/java/jdk-8/java-8-intstream-longstream-doublestream-example
 * 
 * @author doctor
 *
 * @time 2015年5月2日 上午10:11:41
 */
public class Java8IntStreamLongStreamAndDoubleStreamExample {

	public static void main(String[] args) {
		System.out.println("--Using IntStream.rangeClosed--");
		int[] array = IntStream.rangeClosed(1, 10).toArray();
		IntStream.of(array).forEach(System.out::print);

		System.out.println();
		array = IntStream.range(1, 10).toArray();
		IntStream.of(array).forEach(System.out::print);
	}

}
