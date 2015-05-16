package com.doctor.jooq.joor;

import org.joor.Reflect;

/**
 * @author doctor
 *
 * @time 2015年5月16日 下午5:51:41
 */
public class SimpleExample {

	public static void main(String[] args) {
		Reflect call = Reflect.on(String.class).create("hello doctor").call("toString");
		System.out.println(call.get());

		Person person = new Person();
		person.setName("doctor");
		person.setAge(2000);
		person.setAddress("who know");

		Object object = Reflect.on(person).get("name");
		System.out.println(object);

		System.out.println(Reflect.on(person).get("age"));

		Reflect.on(person).set("age", 66666);

		System.out.println(Reflect.on(person).get("age"));

	}

	private static class Person {
		private String name;
		private int age;
		private String address;

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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	}

}
