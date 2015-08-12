/*
 * Copyright (C) 2014-present  The   openSourcLibrary-2015  Authors
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
 *
 */
package com.doctor.java8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Date and Time API changes in Java 8
 * 
 * 详细看java包文档
 * 
 * @author doctor
 *
 * @time 2015年8月12日 下午1:50:18
 *
 * @see
 *
 */
public class DateAndTimeAPIChanges {

	public static void main(String[] args) {
		// The LocalDate class represents a date. There is no representation of a time or time-zon
		System.out.println("The LocalDate class:");
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		System.out.println(localDate.isLeapYear());
		System.out.println(localDate.getDayOfMonth());
		System.out.println(localDate.getDayOfWeek());

		// LocalTime: The LocalTime class represents a time. There is no representation of a date or time-zone.
		// LocalDateTime: The LocalDateTime class represents a date-time. There is no representation of a time-zone.

		// If you want to use the date functionality with zone information, then Lambda provide you extra 3 classes similar to above one i.e. OffsetDate, OffsetTime and OffsetDateTime.
		// Timezone offset can be represented in “+05:30″ or “Europe/Paris” formats. This is done via using another class i.e.
		// ZoneId.
		System.out.println("OffsetDateTime:");
		OffsetDateTime offsetDateTime = OffsetDateTime.now();
		System.out.println(offsetDateTime);

		offsetDateTime = OffsetDateTime.now(ZoneId.systemDefault());
		System.out.println(offsetDateTime);

		System.out.println(ZonedDateTime.now());

		// New classes to represent timestamp and duration
		System.out.println("New classes to represent timestamp and duration");
		System.out.println(Instant.now());
		System.out.println(Instant.now().plus(Duration.ofHours(1L)));

		// Duration class is a whole new concept brought first time in java language. It represents the time difference between two time stamps
		System.out.println(Duration.ofDays(1L));
		System.out.println(Duration.ofSeconds(100L));
	}

}
