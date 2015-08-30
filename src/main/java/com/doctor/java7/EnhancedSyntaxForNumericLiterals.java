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
 *
 */
package com.doctor.java7;

/**
 * 
 * @author doctor
 *
 * 2015年8月30日 上午9:55:21
 */
public class EnhancedSyntaxForNumericLiterals {

	 
	public static void main(String[] args) {
		int b = 0b10;
		System.out.println(b);
		System.out.println(b & 0b11);
		
		System.out.println(157_236);

	}

}
