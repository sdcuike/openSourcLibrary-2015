package com.doctor.java.common;

import java.util.HashMap;

/**
 * how-hashmap-works-in-java.
 * 
 * 关键是学习一下如何用debug视图中的变量视图，认识内部数据结构
 * 
 * @see http://www.javacodegeeks.com/2014/03/how-hashmap-works-in-java.html
 * 
 *      Key points to Remeber:
 * 
 *      1. HashMap has a inner class called Entry which stores key-value pairs.
 *      2. Above Entry object is stored in Entry[ ](Array) called table
 *      3. An index of table is logically known as bucket and it stores first element of linkedlist
 *      4.Key object’s hashcode() is used to find bucket of that Entry object.
 *      5.If two key object ‘s have same hashcode , they will go in same bucket of table array.
 *      6. Key object ‘s equals() method is used to ensure uniqueness of key object.
 *      7. Value object ‘s equals() and hashcode() method is not used at all
 * @author doctor
 *
 * @time 2015年4月20日 下午2:33:31
 */
public class HowHashmapWorks {

	public static void main(String[] args) {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("name", "doctor");
		hashMap.put("name", "doctor who");
		hashMap.put("age", "2000");
		System.out.println(hashMap);

	}

}
