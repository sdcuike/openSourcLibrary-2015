package com.doctor.java.ebook.java_puzzlers;

import java.math.BigDecimal;

/**
 * Puzzle : Time for a Change
 * 
 * Tom去停车场停一车，停车费为1.10美元，钱包里共有2美元。如果支付这笔费用，他得到多少找回的零钱。
 * 
 * 总结：要获得精确的结果，就不用考虑float和double类型的运算。对于金钱的计算，我们要用int、long或者BigDecimal。
 * 一般我们选择BigDecimal。
 * 
 * @author doctor
 *
 * @time 2015年5月30日 上午11:42:13
 */
public class TimeForAChangePuzzle {

	public static void main(String[] args) {
		System.out.println(2.00 - 1.10);// 0.8999999999999999
		System.out.println(2.00D - 1.10D);// 0.8999999999999999
		System.out.println(2.00F - 1.10F);// 0.9

		// 选择long或者int
		System.out.println(200L - 110L);// 90
		System.out.println(BigDecimal.valueOf(2.00).subtract(BigDecimal.valueOf(1.10)));// 0.9

	}

}
