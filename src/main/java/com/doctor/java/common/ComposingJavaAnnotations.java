package com.doctor.java.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.stream.Stream;

/**
 * java注解组合成新的注解类型
 * 
 * @see http://www.javacodegeeks.com/2012/12/composing-java-annotations.html
 * 
 * @author doctor
 *
 * @time 2015年6月11日 下午6:32:43
 */

public class ComposingJavaAnnotations {

	public static void main(String[] args) {
		ClassPreamble classPreambles = ClassPreambleTest.class.getAnnotationsByType(ClassPreamble.class)[0];
		Author author = classPreambles.Author();
		Reviewer[] reviewers = classPreambles.Reviewer();

		System.out.println("author:" + author.firstName() + " " + author.lastName());
		System.out.println("reviewers");
		Stream.of(reviewers).forEach(t -> {
			System.out.println("reviewer:" + t.firstName() + "  " + t.lastName());
		});

	}

	@ClassPreamble(Author = @Author(firstName = "doctor", lastName = "who"),
			Reviewer = { @Reviewer(firstName = "sim", lastName = "who"),
					@Reviewer(firstName = "dssim", lastName = "who") })
	public static class ClassPreambleTest {

	}

	@Documented
	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ClassPreamble {
		Author Author();

		Reviewer[] Reviewer();

	}

	@Documented
	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Author {
		String firstName() default "";

		String lastName() default "";
	}

	@Documented
	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Reviewer {
		String firstName() default "";

		String lastName() default "";
	}
}
