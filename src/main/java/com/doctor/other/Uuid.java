/*
 * Copyright (C) 2014-present  The  openSourcLibrary-2015  Authors
 *
 * https://github.com/sdcuike
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.doctor.other;

import java.util.concurrent.atomic.LongAdder;

/**
 * id生成器。 在单JVM内唯一，非可以用uuid（或uuid+这里的Uuid）
 * 
 * @author doctor
 *
 * @time 2015年7月23日 下午4:18:32
 */
public final class Uuid {
	private static final LongAdder longAdder = new LongAdder();

	private static Long getId() {
		longAdder.increment();
		return Long.valueOf(longAdder.longValue());
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(Uuid.getId());
		}
	}
}
