package com.doctor.disruptor.getting_started;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * @see http://ifeve.com/disruptor-getting-started/
 * @see https://github.com/LMAX-Exchange/disruptor/wiki/Getting-Started
 * 
 * @author doctor
 *
 * @time 2015年4月28日
 */
public class LongEventMain {

	public static void main(String[] args) throws InterruptedException {
		// Executor that will be used to construct new threads for consumers
		final EventFactory<LongEvent> eventFactory = new LongEventFactory();

		// Specify the size of the ring buffer, must be power of 2.
		final int ringBufferSize = 1024;

		// Executor that will be used to construct new threads for consumers
		final Executor executor = Executors.newFixedThreadPool(6);

		// Construct the Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, executor);

		// Connect the handler
		disruptor.handleEventsWith(new LongEventHandler());

		// Start the Disruptor, starts all threads running
		disruptor.start();

		// Get the ring buffer from the Disruptor to be used for publishing.
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		// LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
		LongEventProducerWithTranslator longEventProducer = new LongEventProducerWithTranslator(ringBuffer);

		ByteBuffer byteBuffer = ByteBuffer.allocate(8);

		for (int i = 0; true; i++) {
			byteBuffer.putLong(0, i);
			longEventProducer.onData(byteBuffer);
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
