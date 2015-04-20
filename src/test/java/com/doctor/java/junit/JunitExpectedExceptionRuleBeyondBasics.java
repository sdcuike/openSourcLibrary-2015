package com.doctor.java.junit;

import static org.hamcrest.core.StringStartsWith.startsWith;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * JUnit ExpectedException rule: beyond basics
 * 
 * @see http://www.javacodegeeks.com/2014/03/junit-expectedexception-rule-beyond-basics.html
 * @author doctor
 *
 * @time 2015年4月20日 下午1:46:24
 */
public class JunitExpectedExceptionRuleBeyondBasics {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test_verifiesTypeAndMessage() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("RuntimeException");

		throw new RuntimeException("RuntimeException");
	}

	private static class ExceptionsThrower {

		public static void throwRuntimeException(int i) {
			if (i <= 0) {
				throw new RuntimeException("Illegal argument: i must be > 0");
			}
			throw new RuntimeException("runtime exception occurred");
		}
	}

	@Test
	public void test_runtimeExceptionOccurs() {
		thrown.expect(RuntimeException.class);
		ExceptionsThrower.throwRuntimeException(3);
	}

	@Test
	public void test_illegalArgumentExceptionOccurs() {
		thrown.expect(RuntimeException.class);
		ExceptionsThrower.throwRuntimeException(3);
	}

	@Test
	public void test_verifiesMessageStartsWith() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage(startsWith("Illegal argument:"));
		ExceptionsThrower.throwRuntimeException(-1);
	}
}
