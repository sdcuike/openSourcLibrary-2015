package com.doctor.java.ebook.java_closures_and_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ebook.java_closures_and_lambda Chapter1Code
 * 
 * @author doctor
 *
 * @time 2015年4月17日
 */
public class Chapter1Code {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 3, null, null, 6);
		System.out.println(cloneWithoutNulls_1(list));

		System.out.println(cloneWithoutNulls_2(list));

	}

	private static <E> List<E> cloneWithoutNulls_1(final List<E> list) {
		ArrayList<E> arrayList = new ArrayList<E>(list);
		// arrayList.removeIf(Predicate.isEqual(null));
		arrayList.removeIf(e -> e == null);
		return arrayList;
	}

	private static <E> List<E> cloneWithoutNulls_2(final List<E> list) {
		return list.parallelStream().filter(e -> e != null).collect(Collectors.toCollection(ArrayList::new));
	}
}
