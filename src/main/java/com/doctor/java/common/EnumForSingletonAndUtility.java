package com.doctor.java.common;

/**
 * Enum for Singleton and Utility class
 * 
 * @see http://examples.javacodegeeks.com/java-basics/data-types/enum/enum-for-singleton-and-utility-class/
 * 
 * @author doctor
 *
 * @time 2015年5月29日 下午4:27:22
 */
public class EnumForSingletonAndUtility {

	public static void main(String[] args) {
		Singleton.INSTANCE.method();
		Utility.method();
	}

	public static enum Singleton {
		INSTANCE;
		public void method() {
			System.out.println("test Singleton ");
		}
	}

	public static enum Utility {
		;// no instances
		public static void method() {
			System.out.println("test Utility ");
		}
	}
}
