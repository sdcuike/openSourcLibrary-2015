package com.doctor.java.Java_puzzle;

import java.util.Locale;

/**
 * Java’s toLowerCase() has got a surprise for you!
 * 
 * 时区相关性
 * 
 * @see http://javapapers.com/core-java/javas-tolowercase-has-got-a-surprise-for-you/
 * 
 * @author doctor
 *
 * @time 2015年5月23日 下午10:16:47
 */
public class JavaToLowerCaseHasGotASurpriseForYou {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("lt"));
		String str = "\u00cc";
		System.out.println("Before case conversion is " + str + " and length is " + str.length());
		// Before case conversion is Ì and length is 1

		String lowerCaseStr = str.toLowerCase();
		System.out.println("Lower case is " + lowerCaseStr + " and length is " + lowerCaseStr.length());
		// Lower case is i̇̀ and length is 3

		// Locale.setDefault(Locale.ENGLISH);
		lowerCaseStr = str.toLowerCase(Locale.ENGLISH);
		System.out.println("Lower case is " + lowerCaseStr + " and length is " + lowerCaseStr.length());
		// Lower case is ì and length is 1

	}

}
