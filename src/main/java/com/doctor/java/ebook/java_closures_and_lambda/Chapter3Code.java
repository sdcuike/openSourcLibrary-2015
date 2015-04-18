package com.doctor.java.ebook.java_closures_and_lambda;

import java.util.HashMap;
import java.util.Map;

/**
 * ebook.java_closures_and_lambda Chapter3Code
 * 
 * @author doctor
 *
 * @time 2015年4月18日 上午9:37:48
 */
public class Chapter3Code {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Map version of forEach 看下内部实现，是map遍历的最高效的方法
		Map<String, Integer> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		map.forEach((k, v) -> {
			System.out.println(k + ":" + v);
		});

	}

}
