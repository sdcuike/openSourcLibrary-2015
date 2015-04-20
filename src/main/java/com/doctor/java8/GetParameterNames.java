package com.doctor.java8;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

/**
 * How can get parameter names in java8
 * 
 * @see http://www.javacodegeeks.com/2014/05/java-8-features-tutorial.html
 * 
 *      1.详见maven-compiler-plugin配置<compilerArgument>-parameters</compilerArgument>
 * 
 *      2.eclipse设置java-compiler->打勾最后一项Store information about method parameters
 * @author doctor
 *
 * @time 2015年4月20日 下午6:20:32
 */
public class GetParameterNames {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = GetParameterNames.class.getDeclaredMethod("main", new Class[] { String[].class });
		Stream.of(method.getParameters()).forEach(System.out::println);
		// java.lang.String[] args

		Method method2 = Simple.class.getDeclaredMethod("print", new Class[] { String.class });
		Parameter parameter = method2.getParameters()[0];
		System.out.println(parameter);
		// java.lang.String name

		System.out.println(parameter.getName());
	}

	private static class Simple {
		public static void print(String name) {

		}
	}
}
