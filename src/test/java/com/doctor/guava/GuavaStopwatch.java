package com.doctor.guava;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Stopwatch;

/**
 * 
 * @author doctor
 * 
 * @see <a href ="http://www.javacodegeeks.com/2012/11/guava-stopwatch.html">the blog <a/>
 * 
 *
 * @time 2015年6月12日 上午10:42:26
 */
public class GuavaStopwatch {
	private Stopwatch stopWatch;

	@Before
	public void InitClass() {
		stopWatch = Stopwatch.createUnstarted();
	}

	@Test
	public void test_Stopwatch() throws InterruptedException {
		stopWatch.start();
		TimeUnit.SECONDS.sleep(2);
		stopWatch.stop();
		System.out.println(stopWatch.elapsed(TimeUnit.SECONDS));
	}
}
