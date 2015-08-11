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
package com.doctor.java8;

import java.util.Arrays;

/**
 * @author doctor
 *
 * @time 2015年8月11日 下午9:10:13
 */
public class ParallelArrayOperations {

	public static void main(String[] args) {
		int size = 200;
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = i + 1;
		}
		System.out.println(Arrays.toString(array));
		// 上面的数组赋值，我们可以用下面的java8并行

		int[] array2 = new int[size];
		Arrays.parallelSetAll(array, i -> i + 1);
		System.out.println(Arrays.toString(array));
	}

}
