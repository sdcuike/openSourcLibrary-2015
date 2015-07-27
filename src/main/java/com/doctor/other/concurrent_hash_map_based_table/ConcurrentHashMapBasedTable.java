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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentHashMapBasedTable 三维表格索引实现(数据库是二维，HBase相当于变种三维）
 * 利用范型适用化此类
 * 
 * @author doctor
 *
 * @time 2015年7月27日 下午2:18:27
 */
public final class ConcurrentHashMapBasedTable {
	private ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<Long>>>> table = new ConcurrentHashMap<>();

	public boolean put(final String rowKey, final String columnKey, final String timesplice, final Long value) {

		ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<Long>>> row = table.get(rowKey);
		if (row == null) {
			table.putIfAbsent(rowKey, new ConcurrentHashMap<String, ConcurrentSkipListMap<String, ConcurrentSet<Long>>>());
		}

		row = table.get(rowKey);

		ConcurrentSkipListMap<String, ConcurrentSet<Long>> column = row.get(columnKey);
		if (column == null) {
			row.putIfAbsent(columnKey, new ConcurrentSkipListMap<String, ConcurrentSet<Long>>());
		}

		column = row.get(columnKey);

		ConcurrentSet<Long> values = column.get(timesplice);
		if (values == null) {
			column.putIfAbsent(timesplice, new ConcurrentSet<>());
		}

		values = column.get(timesplice);
		return values.add(value);
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

	public static void main(String[] args) {
		ConcurrentHashMapBasedTable table = new ConcurrentHashMapBasedTable();

		table.put("row", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row1", "col", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row1", "col2", LocalDateTime.now().format(Util.timeFormatter), Uuid.getId());
		table.put("row", "col", LocalDateTime.now().plusDays(1).format(Util.timeFormatter), Uuid.getId());
		System.out.println(table);
	}
}
