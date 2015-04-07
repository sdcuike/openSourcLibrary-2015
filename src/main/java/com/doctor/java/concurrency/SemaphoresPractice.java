package com.doctor.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author doctor
 *
 * @time 2015年4月7日
 */
public class SemaphoresPractice {

	public static void main(String[] args) {
		Printer printer = new Printer(6);
		ExecutorService executorService = Executors.newFixedThreadPool(16);
		for (int i = 0; i < 40; i++) {
			executorService.submit(() -> {
				printer.printJob("test");
			});
		}
		executorService.shutdown();

	}

	private static final class Printer {
		private boolean[] freePrinters;
		private Semaphore semaphore;
		private int count;

		private Lock lock = new ReentrantLock();

		public Printer(int freePrinter) {
			this.count = freePrinter;
			freePrinters = new boolean[count];
			semaphore = new Semaphore(count, true);
			for (int i = 0; i < count; i++) {
				freePrinters[i] = true;
			}
		}

		public void printJob(Object object) {
			try {
				semaphore.acquire();
				int nextPrinter = getNextPrinter();
				System.out.println("printer:" + nextPrinter + "--" + object + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
				freePrinters[nextPrinter] = true;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release();
			}
		}

		private int getNextPrinter() {
			lock.lock();
			int free = 0;
			try {
				for (int i = 0; i < count; i++) {
					if (freePrinters[i]) {
						free = i;
						freePrinters[i] = false;
						break;
					}
				}
			} finally {
				lock.unlock();
			}

			return free;
		}
	}

}
