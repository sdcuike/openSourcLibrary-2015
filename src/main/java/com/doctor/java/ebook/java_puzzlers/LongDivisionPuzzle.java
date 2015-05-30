package com.doctor.java.ebook.java_puzzlers;

/**
 * Puzzle : Long Division -> 类型隐式转换及overflow
 * 
 * @author doctor
 *
 * @time 2015年5月30日 下午12:01:31
 */
public class LongDivisionPuzzle {

	public static void main(String[] args) {
		long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
		long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
		System.out.println(MICROS_PER_DAY);// 500654080 明显是错误结果
		System.out.println(MILLIS_PER_DAY);// 86400000
		System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);// 5

		MICROS_PER_DAY = 24L * 60 * 60 * 1000 * 1000;
		MILLIS_PER_DAY = 24L * 60 * 60 * 1000;
		System.out.println(MICROS_PER_DAY);// 86400000000
		System.out.println(MILLIS_PER_DAY);// 86400000
		System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);// 1000

	}

}
