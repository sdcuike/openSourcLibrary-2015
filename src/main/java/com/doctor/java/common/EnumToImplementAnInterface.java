package com.doctor.java.common;

/**
 * How to use an enum to implement an interface
 * 
 * @author doctor
 * 
 * @see http://examples.javacodegeeks.com/java-basics/data-types/enum/enum-to-implement-an-interface/
 *      http://blog.pengyifan.com/how-to-extend-enum-in-java/
 *      http://www.selikoff.net/2011/06/15/java-enums-can-implement-interfaces/
 *      http://www.byteslounge.com/tutorials/java-enum-implementing-an-interface
 *      http://stackoverflow.com/questions/2709593/why-would-an-enum-implement-an-interface
 * 
 * 
 *      How to use an enum to implement an interface. Implementing an interface with an enum can be
 *      useful when we need to implement some business logic that is tightly coupled with a discriminatory property of a given object or class.
 * 
 * 
 *      Enums don't just have to represent passive sets (e.g. colours). They can represent more complex objects with functionality, and so you're then
 *      likely to want to add further functionality to these - e.g. you may have interfaces such as Printable, Reportable etc. and components that
 *      support these.
 * 
 * 
 * @time 2015年5月29日 下午4:37:38
 */
public class EnumToImplementAnInterface {

	public static void main(String[] args) {
		for (Named planet : Planets.values()) {
			System.out.println(planet.name() + "==" + planet.order());
		}
	}

	public static interface Named {
		String name();

		int order();
	}

	public static enum Planets implements Named {
		Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune;

		@Override
		public int order() {
			return ordinal() + 1;
		}

	}

}
