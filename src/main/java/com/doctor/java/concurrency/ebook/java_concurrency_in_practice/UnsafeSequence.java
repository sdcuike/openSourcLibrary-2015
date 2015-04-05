package com.doctor.java.concurrency.ebook.java_concurrency_in_practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author doctor
 *
 * @time 2015年4月5日 上午8:56:37
 */
public class UnsafeSequence {
	private int value = 0;

	public int getNext() {
		return value++;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		UnsafeSequence sequence = new UnsafeSequence();
		for (int i = 0; i < 20; i++) {
			executorService.submit(() -> {
				try {
					TimeUnit.SECONDS.sleep(6);
				} catch (Exception e) {

					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " : " + sequence.getNext());

			});
		}

		executorService.shutdown();
	}

}
