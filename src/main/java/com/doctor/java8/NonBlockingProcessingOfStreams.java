package com.doctor.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @see http://www.javacodegeeks.com/2015/03/improving-performance-non-blocking-processing-of-streams.html
 * 
 *      http://xpadro.blogspot.com/2015/03/improving-performance-non-blocking.html
 * 
 *      http://www.importnew.com/10815.html
 * 
 * @author doctor
 *
 * @time 2015年4月17日 上午10:34:33
 */
public class NonBlockingProcessingOfStreams {

	public static void main(String[] args) {
		List<String> ids = Arrays.asList(
				"C01", "C02", "C03", "C04", "C05", "C06", "C07", "C08", "C09", "C10",
				"C11", "C12", "C13", "C14", "C15", "C16", "C17", "C18", "C19", "C20");

		ExecutorService executor = Executors.newFixedThreadPool(100);

		List<CompletableFuture<String>> futureList = ids.stream().map(id -> CompletableFuture.supplyAsync(() -> {
			return Thread.currentThread().getName() + "  :  " + id;
		}, executor)).collect(Collectors.toList());

		List<String> list = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
		executor.shutdown();
		System.out.println(list);

	}
}
