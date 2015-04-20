package com.doctor.java8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * simplifying-readwritelock-with-java-8-and-lambdas 这也是利用aop编程思想
 * 
 * @see http://www.javacodegeeks.com/2014/03/simplifying-readwritelock-with-java-8-and-lambdas.html
 * 
 * @author doctor
 *
 * @time 2015年4月20日 下午2:48:19
 */
public class SimplifyingReadwritelockWithJava8AndLambdas {

	public static void main(String[] args) {

		MyBuffer myBuffer = new MyBuffer(2);
		myBuffer.put("name");
		myBuffer.put("name1");
		myBuffer.put("name2");
		System.out.println(myBuffer.getRecent());
		System.out.println(myBuffer.getDiscardedCount());

	}

	private static class MyBuffer {
		private final int capacity;
		private final Deque<String> recent;
		private int discarded;
		private final FunctionalReadWriteLock guard;

		public MyBuffer(final int capacity) {
			this.capacity = capacity;
			recent = new ArrayDeque<String>(this.capacity);
			discarded = 0;
			guard = new FunctionalReadWriteLock();
		}

		public void put(String value) {
			guard.write(() -> {
				while (recent.size() >= capacity) {
					recent.removeFirst();
					discarded++;
				}
				recent.add(value);
			});
		}

		public List<String> getRecent() {
			return guard.read(this::defensiveCopyOfRecent);
		}

		private List<String> defensiveCopyOfRecent() {
			return recent.stream().collect(Collectors.toList());
		}

		public int getDiscardedCount() {
			return guard.read(() -> {
				return discarded;
			});
		}

		public int getTotal() {
			return guard.read(() -> {
				return discarded + recent.size();
			});
		}

		public void flush() {
			guard.write(this::unsafeFlush);
		}

		private void unsafeFlush() {
			discarded += recent.size();
			recent.clear();
		}
	}

	private static class FunctionalReadWriteLock {

		private final Lock readLock;
		private final Lock writeLock;

		public FunctionalReadWriteLock() {
			this(new ReentrantReadWriteLock());
		}

		public FunctionalReadWriteLock(final ReentrantReadWriteLock readWriteLock) {
			readLock = readWriteLock.readLock();
			writeLock = readWriteLock.writeLock();
		}

		public void read(Runnable runnable) {
			readLock.lock();
			try {
				runnable.run();
			} finally {
				readLock.unlock();
			}
		}

		public <T> T read(Supplier<T> supplier) {
			readLock.lock();
			try {
				return supplier.get();
			} finally {
				readLock.unlock();
			}
		}

		public void write(Runnable runnable) {
			writeLock.lock();
			try {
				runnable.run();
			} finally {
				writeLock.unlock();
			}
		}

		public <T> T write(Supplier<T> supplier) {
			writeLock.lock();
			try {
				return supplier.get();
			} finally {
				writeLock.unlock();
			}
		}
	}
}
