package com.doctor.java.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The second is a completion signal that allows the driver to wait until all workers have
 * completed.
 * 
 * @author doctor
 *
 * @time 2015年3月30日 上午10:05:00
 */
public class CountdownlatchPractice2 {

	public static void main(String[] args) throws InterruptedException {
		Driver driver = new Driver(6);
		driver.doDrive();

	}

	private static class Driver {
		private static final Logger log = LoggerFactory.getLogger(PartRunnable.class);

		private int parts;
		private CountDownLatch countDownLatch;

		public Driver(int parts) {
			this.parts = parts;
			countDownLatch = new CountDownLatch(parts);
		}

		public void doDrive() throws InterruptedException {
			ExecutorService executorService = Executors.newFixedThreadPool(parts);
			for (int i = 0; i < parts; i++) {
				executorService.execute(new PartRunnable("part" + i, countDownLatch));
			}
			countDownLatch.await();
			log.info("info:'{}'", "all parts done,let's drive....");
			executorService.shutdown();
		}
	}

	private static class PartRunnable implements Runnable {
		private static final Logger log = LoggerFactory.getLogger(PartRunnable.class);

		private String partName;
		private CountDownLatch countDownLatch;

		public PartRunnable(String partName, CountDownLatch countDownLatch) {
			this.partName = partName;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			doWork();
			countDownLatch.countDown();
		}

		private void doWork() {
			log.info("{doWork:'{}'}", partName + "--" + Thread.currentThread().getName());
		}

	}

}
