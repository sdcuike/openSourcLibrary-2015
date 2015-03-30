package com.doctor.java.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see http://howtodoinjava.com/2013/07/18/when-to-use-countdownlatch-java-concurrency-example-tutorial
 * 
 * 
 *      CountDownLatch works by having a counter initialized with number of threads, which is decremented each time a thread complete its execution.
 *      When count reaches to zero, it means all threads have completed their execution, and thread waiting on latch resume the execution.
 * 
 *      The first interaction with CountDownLatch is with main thread which is goind to wait for other threads. This main thread must call,
 *      CountDownLatch.await() method immediately after starting other threads. The execution will stop on await() method till the time, other threads
 *      complete their execution.
 * 
 *      Other N threads must have reference of latch object, because they will need to notify the CountDownLatch object that they have completed their
 *      task. This notification is done by method : CountDownLatch.countDown(); Each invocation of method decreases the initial count set in
 *      constructor, by 1. So, when all N threads have call this method, count reaches to zero, and main thread is allowed to resume its execution
 *      past await() method.
 * 
 * 
 * @author doctor
 *
 * @time 2015年3月30日 上午10:28:57
 */
public class CountdownlatchPractice3 {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		List<BaseHealthChecker> list = Arrays.asList(new NetworkHealthChecker(countDownLatch), new CacheHealthChecker(countDownLatch));

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		list.forEach(t -> executorService.execute(t));
		countDownLatch.await();

		executorService.shutdown();
		list.forEach(t -> System.out.println(t.isServiceUp()));

	}

	private static abstract class BaseHealthChecker implements Runnable {

		protected final Logger log = LoggerFactory.getLogger(getClass());

		private CountDownLatch countDownLatch;
		private String serviceName;
		private boolean isServiceUp = false;

		public BaseHealthChecker(String serviceName, CountDownLatch countDownLatch) {
			this.serviceName = serviceName;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			try {
				isServiceUp = true;
				verifyService();
				countDownLatch.countDown();

			} catch (Throwable e) {
				isServiceUp = false;
				log.error("{error:'{}'}", serviceName, e);
			} finally {
				countDownLatch.countDown();
			}

		}

		public String getServiceName() {
			return serviceName;
		}

		public boolean isServiceUp() {
			return isServiceUp;
		}

		public abstract void verifyService();
	}

	private static class NetworkHealthChecker extends BaseHealthChecker {

		public NetworkHealthChecker(CountDownLatch countDownLatch) {
			super("NetworkHealthChecke", countDownLatch);
		}

		@Override
		public void verifyService() {
			log.info("{verifyService:'{}'}", getServiceName());
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				log.error("error:'{}'", e);
			}

			log.info("done:'{}'", getServiceName());
		}

	}

	private static class CacheHealthChecker extends BaseHealthChecker {

		public CacheHealthChecker(CountDownLatch countDownLatch) {
			super("CacheHealthChecker", countDownLatch);
		}

		@Override
		public void verifyService() {
			log.info("{verifyService:'{}'}", getServiceName());

			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				log.error("error:'{}'", e);
			}
			log.info("{done:'{}'}", getServiceName());
		}

	}
}
