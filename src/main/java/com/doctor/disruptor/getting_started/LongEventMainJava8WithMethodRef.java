package com.doctor.disruptor.getting_started;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * 由于在Java 8中方法引用也是一个lambda，因此还可以把上面的代码改成下面的代码
 * 
 * @author doctor
 *
 * @time 2015年4月28日 下午5:58:39
 */
public class LongEventMainJava8WithMethodRef {

	public static void main(String[] args) throws InterruptedException {
		final int ringBufferSize = 1024;
		final Executor executor = Executors.newFixedThreadPool(6);

		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, ringBufferSize, executor);
		disruptor.handleEventsWith(LongEventMainJava8WithMethodRef::handleEvents);

		disruptor.start();

		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		ByteBuffer buffer = ByteBuffer.allocate(8);
		for (long i = 0; true; i++) {
			buffer.putLong(0, i);
			ringBuffer.publishEvent(LongEventMainJava8WithMethodRef::translateTo, buffer);
			TimeUnit.SECONDS.sleep(1);
		}

	}

	private static void handleEvents(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println(event.getValue());
	}

	private static void translateTo(final LongEvent event, long sequence, final ByteBuffer buffer) {
		event.setValue(buffer.getLong(0));
	}
}
