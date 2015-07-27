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
package com.doctor.other.concurrent_hash_map_based_table;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author doctor
 *
 * @time 2015年7月23日 下午2:39:31
 */
public class Day20150723 {
	// [规则特征->[属主类别-> 内存数据时间分片] ]
	private ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedDeque<Integer>>> cache = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		LongAdder longAdder = new LongAdder();
		longAdder.add(Long.MAX_VALUE);
		System.out.println(longAdder.longValue());
	}

}
