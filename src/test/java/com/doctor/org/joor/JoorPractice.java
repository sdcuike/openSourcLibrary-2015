/**
 * 
 */
package com.doctor.org.joor;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.joor.Reflect;
import org.junit.Test;

/**
 * @author doctor
 *
 * @time 2015年6月14日 下午9:26:59
 */
public class JoorPractice {

	/**
	 * @see https://github.com/jOOQ/jOOR
	 */
	@Test
	public void test_simple_example() {
		Reflect reflectForString = Reflect.on(String.class).create("Hello doctor who");
		String world = reflectForString.call("substring", 6).call("toString").get();
		assertThat(world, equalTo("doctor who"));
	}

	@Test
	public void test_proxy_abstraction() {
		String substring = Reflect.on(String.class).create("doctor").as(StringProxy.class).substring(1);
		assertThat(substring, equalTo("octor"));
	}

	interface StringProxy {
		String substring(int beginIndex);
	}

	@Test
	public void test_aop() {
		com.doctor.org.joor.Reflect.on(Person.class).create().as(Pepole.class, t -> {
			System.out.println("before....");
		}, t -> {
			System.out.println("after....");
		}, null).doWalk();
	}

	@Test
	public void test_on_string() {
		String reflect = Reflect.on(Chinese.class.getName()).create().call("getName").get();
		System.out.println(reflect);

	}

	interface Pepole {
		void doWalk();

		void doEate();
	}

	static class Person implements Pepole {
		private String name = "doctor who";
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public void doWalk() {
			System.out.println(name + " do walk");

		}

		@Override
		public void doEate() {
			System.out.println(name + " do eate");
		}

	}

	static class Chinese extends Person {
		private boolean test;

		public boolean isTest() {
			return test;
		}

		public void setTest(boolean test) {
			this.test = test;
		}

	}
}
