/**
 * 
 */
package com.doctor.jooq.joou;

import org.joou.UByte;
import org.joou.UInteger;
import org.joou.Unsigned;

/**
 * @author doctor
 *
 * @time 2015年6月14日 下午8:58:56
 */
public class Joou {

	public static void main(String[] args) {
		UByte ubyte = Unsigned.ubyte((byte) 15);
		System.out.println(ubyte);

		UInteger uint = Unsigned.uint(123);
		System.out.println(uint);
		UInteger uint2 = Unsigned.uint(-1);
		System.out.println(uint2);

	}

}
