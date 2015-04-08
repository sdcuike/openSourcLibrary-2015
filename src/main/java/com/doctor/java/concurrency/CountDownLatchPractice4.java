package com.doctor.java.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch – a more general wait/notify mechanism
 * 
 * CountDownLatch 模拟常用wait/notify机制．
 * 
 * As you can see, it is simpler than wait/notify, and requires less code. It also allows us to invoke the condition that ultimately releases the
 * 
 * block before we call wait(). This can mean safer code
 * 
 * @author doctor
 *
 * @time 2015年4月8日 上午8:34:53
 */
public class CountDownLatchPractice4 {

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Runnable runnable = () -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("going to CountDown...");
			countDownLatch.countDown();
		};

		new Thread(runnable).start();
		System.out.println("going to wait...");
		countDownLatch.await();
		System.out.println("Done waiting");
	}

}
