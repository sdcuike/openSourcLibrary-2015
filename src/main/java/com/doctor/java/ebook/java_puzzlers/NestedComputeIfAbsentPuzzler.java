package com.doctor.java.ebook.java_puzzlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Puzzler: nested computeIfAbsent
 * 
 * The Java 8 libraries have a new method on map, computeIfAbsent.
 * 
 * This is very useful way to turn your Map into a cache of objects associated with a key.
 * 
 * However, there is a combination you might not have considered;
 * 
 * what happens if you call computeIfAbsent inside itself.
 * 
 * @see http://www.javacodegeeks.com/2015/05/puzzler-nested-computeifabsent.html
 * 
 * @author doctor
 *
 * @time 2015年5月29日 上午9:24:14
 */
public class NestedComputeIfAbsentPuzzler {

	public static void main(String[] args) {
		List<Map<String, Integer>> list = Arrays.asList(new HashMap<>(), new WeakHashMap<>(), new TreeMap<>(),
				new IdentityHashMap<>(), new Hashtable<>(), new LinkedHashMap<>(), new ConcurrentSkipListMap<>()
				);

		list.forEach(System.out::println);
		System.out.println("///");

		for (Map<String, Integer> map : list) {
			map.computeIfAbsent("1", key -> {
				map.computeIfAbsent("1", key2 -> 1);
				return 2;
			});
		}

		list.forEach(t -> System.out.println(t.getClass().getSimpleName() + t));
		System.out.println("///");

		// , new ConcurrentHashMap<>()加入这个，会导致程序退不出。
		// ConcurrentHashMap never returns. It’s locking doesn’t appear to be re-entrant.

		// Conclusion

		// You need to be careful if you nest calls to a map from inside a lambda,
		// or avoid doing so at all. If you have to do this,
		// ConcurrentSkipListMap appears to behave the best
	}

}
