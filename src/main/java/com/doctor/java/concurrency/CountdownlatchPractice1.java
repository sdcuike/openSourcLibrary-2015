package com.doctor.java.concurrency;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The first is a start signal that prevents any worker from proceeding until the driver is ready
 * 
 * for them to proceed;
 * 
 * 其实和操作系统中的p,v操作概念一样的，只不过这里有一对Ｐ，Ｖ操作．两个对象之间的等待＼唤醒．
 * 
 * @see CountDownLatch
 * @author doctor
 *
 * @time 2015年3月30日 上午9:20:51
 */
public class CountdownlatchPractice1 {
	
	public static void main(String[] args) throws InterruptedException {
		Driver driver = new Driver(6);
		driver.doDrive();
	}

	private static class Driver {
		private static final Logger log = LoggerFactory.getLogger(Driver.class);
		private int works;
		private CountDownLatch startSignal;
		private CountDownLatch doneSignal;

		public Driver(int works) {
			this.works = works;
			startSignal = new CountDownLatch(1);
			doneSignal = new CountDownLatch(this.works);
		}
		
		public void doDrive() throws InterruptedException{
			for(int i = 0; i < works; i++){
				new Thread(new Worker("worer"+i, startSignal, doneSignal)).start();
			}
			doSomethingElse();
			startSignal.countDown();//司机发出准备好信号，让工作者开始工作（v操作）　
			doneSignal.await(); //司机等待工作着完成工作的信号（p操作）
			log.info("{driver:'all wokers done ,let's dirve '}");
		}
		
		private void doSomethingElse(){
			log.info("{driver doSomethingElse:'{}'}","..... driver ready");
		}
	}

	private static class Worker implements Runnable {
		private static final Logger log = LoggerFactory.getLogger(Worker.class);
		
		private String name;
		private CountDownLatch startSignal;
		private CountDownLatch doneSignal;

		public Worker(String name, CountDownLatch startSignal, CountDownLatch doneSignal) {
			this.name = name;
			this.startSignal = startSignal;
			this.doneSignal = doneSignal;
		}

		@Override
		public void run() {
			try {
				startSignal.await();//每个工作者开始工作时都要等待司机发出准备好信号(p操作）
				doWork();
				doneSignal.countDown();//每个工作者工作完成后，都要发出信号，工作完成(v操作）
			} catch (InterruptedException e) {
				log.error("{error:'{} '}", name,e);
			}

		}

		private void doWork() {
			log.info("{run:'{}'}",name + "---" + Thread.currentThread().getName());
		}

	}
}
