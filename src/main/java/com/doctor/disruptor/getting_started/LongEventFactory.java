package com.doctor.disruptor.getting_started;
import com.lmax.disruptor.EventFactory;

public final class LongEventFactory implements EventFactory {

	@Override
	public Object newInstance() {
		return new LongEvent();
	}

}
