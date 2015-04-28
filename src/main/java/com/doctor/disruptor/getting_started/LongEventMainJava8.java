package com.doctor.disruptor.getting_started;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * 
 * Disruptor在自己的接口里面添加了对于Java 8 Lambda的支持。大部分Disruptor中的接口都符合Functional Interface的要求（也就是在接口中仅仅有一个方法）。所以在Disruptor中，可以广泛使用Lambda来代替自定义类。
 * 
 * @see http://ifeve.com/disruptor-getting-started/
 *      https://github.com/LMAX-Exchange/disruptor/wiki/Getting-Started
 * 
 * @author doctor
 *
 * @time 2015年4月28日 下午5:38:30
 */
public class LongEventMainJava8 {

	public static void main(String[] args) throws InterruptedException {
		final Executor executor = Executors.newFixedThreadPool(6);
		final int ringBufferSize = 1024;
		Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, ringBufferSize, executor);
		disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
			System.out.println(event.getValue());
		});
		disruptor.start();

		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		for (long i = 0; true; i++) {
			byteBuffer.putLong(0, i);
			ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), byteBuffer);
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
