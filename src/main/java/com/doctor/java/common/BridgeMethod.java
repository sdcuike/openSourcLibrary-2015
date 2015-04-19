package com.doctor.java.common;

import java.lang.reflect.Method;

import com.google.common.base.Preconditions;

/**
 * 桥接方法
 * 
 * 《深入理解java7 核心技术与最佳实践》
 * 
 * 当一个类继承某个参数化类或实现参数化接口时，在经过类型擦除之后，可能造成所继承的方法的类型签名发生改变。
 * 
 * @author doctor
 *
 * @time 2015年4月19日 上午9:13:49
 */
public class BridgeMethod {

	/**
	 * @param args
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = Sequence.class.getDeclaredMethod("compareTo", new Class[] { Object.class });
		System.out.println(method);
		// public int com.doctor.java.common.BridgeMethod$Sequence.compareTo(java.lang.Object)
		Preconditions.checkArgument(method.isBridge());
	}

	private static class Sequence implements Comparable<Sequence> {
		private final int sequenceNumber;

		public Sequence(final int sequenceNumber) {
			this.sequenceNumber = sequenceNumber;
		}

		@Override
		public int compareTo(Sequence o) {
			// 在经过类型擦除之后，这个方法会由编译器改为public int compareTo(Object o),这个由编译器添加的方法
			// 称之为桥接方法。这个桥接方法会调用原方法（只不过参数类型强制转换是由编译器实现）
			return Integer.compare(this.sequenceNumber, o.sequenceNumber);
		}

	}

}
