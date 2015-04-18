package com.doctor.java.ebook.java_closures_and_lambda;

import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.base.Preconditions;

/**
 * ebook.java_closures_and_lambda Chapter2Code
 * 
 * @author doctor
 *
 * @time 2015年4月17日 下午6:05:25
 */

public class Chapter2Code {

	public static void main(String[] args) {

		// Object funObject = e -> e; //error
		// Function<?, ?> function = e -> e; //范型 error
		Function<Number, Number> functionNumber = e -> e;
		Preconditions.checkArgument(functionNumber.apply(66).equals(66));

		Supplier<String> supplier = () -> "hello";
		Preconditions.checkState("hello".equals(supplier.get()));

		// Using Explicit Typing to Resolve Overloaded Method Ambiguity
		// -> String str 消除重载问题
		System.out.println(transform("hello", (String str) -> str + "----"));

		Function<Integer, String> function = i -> i.toString();
		function = function.andThen("10"::concat).andThen("1"::concat);
		Preconditions.checkArgument("1101".equals(function.apply(Integer.valueOf(1))));
	}

	private static CharSequence transform(CharSequence charSequence, Function<CharSequence, CharSequence> transformer) {
		return transformer.apply(charSequence);
	}

	private static String transform(String string, Function<String, String> transformer) {
		return transformer.apply(string);
	}
}
