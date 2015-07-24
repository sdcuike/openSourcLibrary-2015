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

/**
 * Memory Object
 * 
 * 内存数据只保存实际数据的一个id + time
 * 
 * @author doctor
 *
 * @time 2015年7月23日 下午4:05:47
 */
public class Event {
	private final long id;
	private final LocalDateTime time;

	public Event(long id, LocalDateTime time) {
		this.id = id;
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getTime() {
		return time;
	}

}
