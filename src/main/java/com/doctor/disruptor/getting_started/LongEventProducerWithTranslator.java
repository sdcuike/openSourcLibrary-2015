package com.doctor.disruptor.getting_started;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * Disruptor 3.0提供了lambda式的API。这样可以把一些复杂的操作放在Ring Buffer，所以在Disruptor3.0以后的版本最好使用Event Publisher或者Event Translator来发布事件。
 * 
 * @author doctor
 *
 * @time 2015年4月28日
 */
public class LongEventProducerWithTranslator {
	private final RingBuffer<LongEvent> ringBuffer;

	private static final EventTranslatorOneArg<LongEvent, ByteBuffer> eventTranslator =
			(final LongEvent event, long sequence, final ByteBuffer byteBuffer)
			-> {
				event.setValue(byteBuffer.getLong(0));
			};

	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer byteBuffer) {
		ringBuffer.publishEvent(eventTranslator, byteBuffer);
	}
}
