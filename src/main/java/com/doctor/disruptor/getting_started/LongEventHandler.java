package com.doctor.disruptor.getting_started;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent arg0, long arg1, boolean arg2) throws Exception {
		System.out.println(Thread.currentThread().getName() + " : " + arg0.getValue());

	}

}
