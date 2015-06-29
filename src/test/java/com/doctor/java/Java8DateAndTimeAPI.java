/*
 * Copyright (C) 2014- now() The  openSourcLibrary-2015  Authors
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
package com.doctor.java;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * @author doctor
 *
 * @time 2015年6月29日 下午9:18:10
 */
public class Java8DateAndTimeAPI {

	@Test
	public void test_Legacy_Interoperability() {
		// Legacy -> New -> Legacy
		Calendar calendar = Calendar.getInstance();
		Instant instant = calendar.toInstant();
		Date date = Date.from(instant);
		System.out.println(date);

		// New -> Legacy -> New
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
		Calendar calendar2 = GregorianCalendar.from(zdt);
		System.out.println(calendar2);

	}

}
