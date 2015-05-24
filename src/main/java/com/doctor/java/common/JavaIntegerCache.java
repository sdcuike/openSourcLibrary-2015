package com.doctor.java.common;

import com.google.common.base.Preconditions;

/**
 * Java Integer Cache
 * 
 * jdk5版本之后运行
 * 
 * 为了高效利用内存及提供性能，jdk5引入了整数类型对象缓存。-127到127之间的整数被缓存，
 * 但是这个缓存是出现在自动装箱操作过程，也就是说非装箱操作，不会带来缓存现象，
 * 如通过构造函数实例化整数。
 * 装箱操作也等同于调用Integer.valueOf（。。）方法。具体可以参考类自带文档。
 * 
 * @see http://javapapers.com/java/java-integer-cache/
 * 
 * @author doctor
 *
 * @time 2015年5月24日 下午3:34:02
 */
public class JavaIntegerCache {

	public static void main(String[] args) {
		Integer integer1 = 6;
		Integer integer2 = 6;
		Preconditions.checkArgument(integer1 == integer2);

		Integer integer3 = 300;
		Integer integer4 = 300;
		Preconditions.checkArgument(integer3 != integer4);
	}

}
