package com.doctor.java8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 为什么用线程的UncaughtExceptionHandler处理异常 -> Java: Handling a RuntimeException in a Runnable
 * 
 * 为什么用线程的UncaughtExceptionHandler处理异常。
 * 
 * @see http://www.javacodegeeks.com/2014/02/java-handling-a-runtimeexception-in-a-runnable.html
 * 
 *      ** 2和3方法采用3的原因如下：@see http://make-aitee-work.blogspot.de/2013/12/a-executor-is-not-thread-or-correct.html
 * 
 *      But this approach has two major drawbacks: First, it would also catch the InterruptedException which may be used to control the thread under
 *      normal conditions. And second, the responsibility of error handling is moved to each Runnable implementation. You are going to implement a lot
 *      of Runnables and it's easy to forget something. Executor error handling calls for a generic solution.
 * 
 *      Java 1.5 adds a method to register exception handlers for exactly this purpose: Thread.setUncaughtExceptionHandler(). Exceptions which are not
 *      handled by the run() method of the Thread implementation, will be forwarded to this handler. Let's modify the above example to use a exception
 *      handler:
 * @author doctor
 *
 * @time 2015年4月22日 上午9:07:35
 */
public class HandlingRuntimeExceptionInRunnable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

		// 1. 这种会导致发生异常的线程挂起，任务不能继续运行。卡住了。如果不调用get（）获得结果，会退出。
		// executorService.scheduleAtFixedRate(() -> {
		// System.out.println(Thread.currentThread().getName());
		//
		// throw new RuntimeException("模拟异常");
		//
		// }, 0, 1, TimeUnit.SECONDS).get();

		// ------------------------------------------------------------------
		// 2.这种异常任务不会挂起，调度程序会继续运行。这也是博文作者思路
		// executorService.scheduleAtFixedRate(() -> {
		// System.out.println(Thread.currentThread().getName());
		// try {
		// throw new RuntimeException("模拟异常");
		// } catch (RuntimeException e) {
		// e.printStackTrace();
		// }
		//
		// }, 0, 1, TimeUnit.SECONDS).get();

		// ------------------------------------------------------------------

		// 3.这是作者提到Java Concurrency in Practice书中的写法
		executorService.scheduleAtFixedRate(() -> {
			System.out.println(Thread.currentThread().getName());
			try {
				throw new RuntimeException("模拟异常");
			} catch (RuntimeException e) {
				Thread t = Thread.currentThread();
				t.getUncaughtExceptionHandler().uncaughtException(t, e);
			}

		}, 0, 1, TimeUnit.SECONDS).get();

		System.out.println("exit");
		executorService.shutdown();

	}
}
