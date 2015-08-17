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
package com.doctor.java7;

/**
 * @author doctor
 * 
 *         目前，jvm对Using string literals in switch statements 不直接支持，语法糖，编译器会将switch中的string
 *         转变为相应的字节比较。
 *         可以用javap -v -l -p -c -s UsingStringLiteralsInSwitchStatements.class 命令反编译，查看。
 * 
 *         其实swith string在java8中，用的hashCode,进入分支后，还会用Method java/lang/String.equals:(Ljava/lang/Object;)
 *         去比较字符串相等性。因为hashCode的hash算法可能相同字符串hash到一个数字上。map数据结构原理相同。
 * 
 *         限制：
 *         case中的字符串必须是非NULL字符串字面值，而且case中的字符串值不能重复。
 * 
 * 
 * 
 *
 * @time 2015年8月17日 下午8:24:05
 */
public class UsingStringLiteralsInSwitchStatements {

	public static void main(String[] args) {
		final String name = "doctor who";
		switch (name) {
		case "doctor who":
			System.out.println("doctor who");
			break;

		default:
			System.out.println("others");
			break;
		}

	}

}
