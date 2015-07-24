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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author doctor
 *
 * @time 2015年7月24日 下午5:29:50
 */
public final class CacheManager {
	// hashmap多级索引规则：特征->属主类别-> 内存数据时间分片->内存数据列表
	private ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentLinkedQueue<Long>>>> cache = new ConcurrentHashMap<>();

	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("YYYYMMdd");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String format = LocalDateTime.now().format(timeFormatter);
		System.out.println(format);

	}

	public static class AttributeMap {
		private final LongAdder count;
		private final ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentLinkedQueue<Long>>> map;

		public AttributeMap() {
			count = new LongAdder();
			map = new ConcurrentHashMap<>();
		}

		public long getAccount() {
			return count.longValue();
		}

		public boolean put(String attribute, LocalDateTime time, Long value) {
			if (value == null) {
				return false;
			}

			return false;
		}
	}
}
