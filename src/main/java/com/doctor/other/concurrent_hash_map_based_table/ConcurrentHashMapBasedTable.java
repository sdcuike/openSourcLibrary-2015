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

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * ConcurrentHashMapBasedTable 三维表格索引实现(数据库是二维，HBase相当于变种三维）
 * 利用范型适用化此类
 * 
 * @author doctor
 *
 * @time 2015年7月27日 下午2:18:27
 */
public final class ConcurrentHashMapBasedTable<T> {
	private static final Logger log = LoggerFactory.getLogger(ConcurrentHashMapBasedTable.class);

	private int ttl = 3;

	private ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<T>>>> table = new ConcurrentHashMap<>();
	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() + 1);

	public ConcurrentHashMapBasedTable() {

	}

	public ConcurrentHashMapBasedTable(int ttl) {
		this.ttl = ttl;
	}

	/**
	 * 
	 * @param rowKey
	 * @param columnKey
	 * @param timesplice
	 *            小时分片:201572701
	 * @param value
	 * @return
	 */
	public boolean put(final String rowKey, final String columnKey, final String timesplice, final T value) {
		Preconditions.checkState(StringUtils.isNotBlank(rowKey), "rowKey is blank");
		Preconditions.checkState(StringUtils.isNotBlank(columnKey), "columnKey is blank");
		Preconditions.checkState(StringUtils.isNotBlank(timesplice), "timesplice is blank");
		Preconditions.checkNotNull(value, "value is null");

		ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<T>>> row = table.get(rowKey);
		if (row == null) {
			table.putIfAbsent(rowKey, new ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<T>>>());
		}

		row = table.get(rowKey);

		ConcurrentSkipListMap<String, ConcurrentSet<T>> column = row.get(columnKey);
		if (column == null) {
			row.putIfAbsent(columnKey, new ConcurrentSkipListMap<String, ConcurrentSet<T>>());
		}

		column = row.get(columnKey);

		ConcurrentSet<T> values = column.get(timesplice);
		if (values == null) {
			column.putIfAbsent(timesplice, new ConcurrentSet<>());
		}

		values = column.get(timesplice);
		return values.add(value);
	}

	public Long getSumForRowKey(final String rowKey) {
		if (StringUtils.isBlank(rowKey)) {
			return Long.valueOf(0L);
		}

		ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<T>>> map = table.get(rowKey);
		if (map == null) {
			return Long.valueOf(0L);
		}

		return map.values().parallelStream().flatMap(k -> k.values().parallelStream()).mapToLong(k2 -> k2.size()).sum();
	}

	public Long getSumForRowColumnKey(final String rowKey, final String columnKey) {
		if (StringUtils.isBlank(rowKey) || StringUtils.isBlank(columnKey)) {
			return Long.valueOf(0L);
		}

		ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<T>>> row = table.get(rowKey);
		if (row == null) {
			return Long.valueOf(0L);
		}

		ConcurrentSkipListMap<String, ConcurrentSet<T>> column = row.get(columnKey);
		if (column == null) {
			return Long.valueOf(0L);
		}

		return column.values().parallelStream().mapToLong(k -> k.size()).sum();
	}

	public void startExpire() {
		executorService.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				pruneCache();

			}
		}, 0L, ttl, TimeUnit.HOURS);
	}

	public void closeExpire() {
		executorService.shutdown();
	}

	private void pruneCache() {
		String expireTime = LocalDateTime.now().minusHours(ttl).format(Util.timeFormatter);
		log.info("{执行缓存失效，expireTime:{}}", expireTime);

		for (String rowKey : table.keySet()) {
			ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<T>>> rowMap = table.get(rowKey);
			for (String columnKey : rowMap.keySet()) {
				ConcurrentSkipListMap<String, ConcurrentSet<T>> columnMap = rowMap.get(columnKey);

				Iterator<String> iterator = columnMap.keySet().iterator();
				while (iterator.hasNext()) {
					String timesplices = iterator.next();
					if (timesplices.compareTo(expireTime) < 0) {
						columnMap.get(timesplices).clear();
						iterator.remove();
					} else {
						break;
					}
				}
			}
		}

	}

	@Override
	public String toString() {
		final String padding = "-----------";

		StringBuilder stringBuilder = new StringBuilder(256);
		table.forEach((row, value) -> {
			stringBuilder.append("row: ").append(row).append(", ");
			value.forEach((colum, value2) -> {
				stringBuilder.append("colum: ").append(colum).append(", ");
				value2.forEach((timesplice, value3) -> {
					stringBuilder.append("timesplice: [").append(timesplice).append("] ");
					stringBuilder.append(" values: ").append(value3).append("; ");
				});
				stringBuilder.append("\n").append(padding);
			});
			stringBuilder.delete(stringBuilder.lastIndexOf(padding), stringBuilder.length());
			stringBuilder.append("\n");
		});

		return stringBuilder.toString();
	}

	public static void main(String[] args) throws InterruptedException {
		ConcurrentHashMapBasedTable<Long> table = new ConcurrentHashMapBasedTable<>();
		// table.startExpire();

		table.put("row", "col", LocalDateTime.now().minusHours(4L).format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row1", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row1", "col2", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().plusDays(1).format(Util.timeFormatter), Uuid.getId());
		System.out.println(table);
		System.out.println(table.getSumForRowKey("row"));
		System.out.println(table.getSumForRowKey("row1"));
		System.out.println(table.getSumForRowKey("row1s"));
		System.out.println(table.getSumForRowColumnKey("row", "col"));
		System.out.println(table.getSumForRowColumnKey("row1", "col"));
		System.out.println(table.getSumForRowColumnKey("row1", "col2"));
		table.startExpire();
		TimeUnit.SECONDS.sleep(5L);
		table.closeExpire();

		System.out.println(table);
		TimeUnit.SECONDS.sleep(5L);

	}
}
