package com.doctor.java.common;

/**
 * Each Enum Instance a different sub-class
 * 
 * 
 * @see http://examples.javacodegeeks.com/java-basics/data-types/enum/each-enum-instance-a-different-sub-class/
 * 
 * @author doctor
 *
 * @time 2015年5月29日 下午5:27:16
 */
public class EachEnumInstanceADifferentSubCass {

	public static void main(String[] args) {
		System.out.println(Operation.PLUS.eval(10.5, 10.5));
		System.out.println(Operation.MINUS.eval(10.5, 8.5));
	}

	public static enum Operation {
		PLUS {
			@Override
			double eval(double x, double y) {
				return x + y;
			}
		},

		MINUS {
			@Override
			double eval(double x, double y) {
				return x - y;
			}
		};
		abstract double eval(double x, double y);
	}
}
