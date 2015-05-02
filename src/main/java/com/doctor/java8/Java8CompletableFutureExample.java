package com.doctor.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @see http://www.concretepage.com/java/jdk-8/java-8-completablefuture-example
 * 
 * @author doctor
 *
 * @time 2015年5月2日 下午12:46:33
 */
public class Java8CompletableFutureExample {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10, 20, 30, 40);

		List<Object> collect = list.stream()
				.map(data -> CompletableFuture.supplyAsync(() -> getNumber(data)))
				.map(completableFuture -> completableFuture.thenApplyAsync(n -> n * n))
				.map(t -> t.join())
				.collect(Collectors.toList());

		System.out.println(collect);

		//
		List<String> list2 = Arrays.asList("A", "B", "C", "D");
		list2.stream()
				.map(data -> CompletableFuture.supplyAsync(() -> Thread.currentThread().getName() + "-Processing:" + data))
				.map(completableFuture -> completableFuture.thenAccept(System.out::println))
				.map(t -> t.join())
				.count();

		//
		list2.stream()
				.map(data -> CompletableFuture.supplyAsync(() -> data + data))
				.map(completableFuture -> completableFuture.whenComplete((result, error) -> System.out.println("result:" + result + ",error:" + error)))
				.count();
	}

	private static int getNumber(int number) {
		return number * number;
	}
}
