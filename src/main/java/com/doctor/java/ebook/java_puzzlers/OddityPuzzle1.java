package com.doctor.java.ebook.java_puzzlers;

import com.google.common.base.Preconditions;

/**
 * Puzzle 1: Oddity
 * 
 * @author doctor
 *
 * @time 2015年5月29日 上午12:08:48
 */
public class OddityPuzzle1 {

	public static void main(String[] args) {
		Preconditions.checkArgument(isOddWrong(3));
		Preconditions.checkArgument(!isOddWrong(-3));// 负数结果错误

		Preconditions.checkArgument(Integer.compare(-3 % 2, -1) == 0);

		Preconditions.checkArgument(isOdd(3));
		Preconditions.checkArgument(isOdd(-3));

		Preconditions.checkArgument(isOdd_1(3));
		Preconditions.checkArgument(isOdd_1(-3));
	}

	/**
	 * 判断余数为1,对于负数有问题。余数为负的情况未考虑。
	 * 当然余数为0,就不用考虑正负情况。
	 * 
	 * @param i
	 * @return
	 */
	public static boolean isOddWrong(int i) {
		return i % 2 == 1;
	}

	public static boolean isOdd(int i) {
		return i % 2 != 0;
	}

	/**
	 * 位操作（性能好）
	 * 
	 * @param i
	 * @return
	 */
	public static boolean isOdd_1(int i) {
		return (i & 1) != 0;
	}

}
