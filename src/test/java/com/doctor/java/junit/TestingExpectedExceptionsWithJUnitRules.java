package com.doctor.java.junit;

import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Testing Expected Exceptions with JUnit Rules
 * 
 * @see http://www.javacodegeeks.com/2013/02/testing-expected-exceptions-with-junit-rules.html
 * 
 * @author doctor
 *
 * @time 2015年6月8日 上午9:00:19
 */
public class TestingExpectedExceptionsWithJUnitRules {
	/**
	 * Approach 1: Use the ExpectedException Rule
	 * 
	 * This is my favourite approach. The ExpectedException rule allows you to specify,
	 * within your test, what exception you are expecting and
	 * 
	 * even what the exception message is. This is shown below:
	 */

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	public void test_ExpectedException_1() {
		expected.expect(RuntimeException.class);
		expected.expectMessage(equalTo("test"));
		throw new RuntimeException("test");
	}

	/**
	 * Approach 2: Specify the exception in the @Test annotation
	 * 
	 * As shown in the code snippet below, you can specify the expected exception in the @Test annotation.
	 * The test will pass only if an exception of the specified class is thrown by the test method.
	 * Unfortunately, you can’t test the exception message with this approach.
	 */
	@Test(expected = RuntimeException.class)
	public void test_ExpectedException_2() {
		throw new RuntimeException("test");
	}

	/**
	 * Approach 3: Use a try-catch block
	 * 
	 * This is the ‘traditional’ approach which was used with old versions of JUnit,
	 * before the introduction of annotations and rules.
	 * Surround your code in a try-catch clause and test if the exception is thrown.
	 * Don’t forget to make the test fail if the exception is not thrown!
	 */
}
