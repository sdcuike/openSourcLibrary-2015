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

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

/**
 * MethodHandle
 * 
 * @author doctor
 *
 * @time: 2015年8月30日 下午10:04:37
 */
public class MethodHandle {
	@Override
	public String toString() {

		return super.toString() + "==MethodHandle";
	}

	public static void main(String[] args) throws Throwable {
		MethodHandle handle = new MethodHandle();
		MethodType methodType = MethodType.methodType(String.class);

		Lookup lookup = MethodHandles.lookup();

		java.lang.invoke.MethodHandle methodHandle = lookup.findVirtual(MethodHandle.class, "toString", methodType);
		String toString = (String) methodHandle.invokeExact(handle);
		System.out.println(toString);// com.doctor.java7.MethodHandle@355da254==MethodHandle

		// or like this:
		java.lang.invoke.MethodHandle methodHandle2 = methodHandle.bindTo(handle);
		String toString2 = (String) methodHandle2.invokeWithArguments();
		System.out.println(toString2);// com.doctor.java7.MethodHandle@355da254==MethodHandle

		// 得到当前Class的不同表示方法，最后一个最好。一般我们在静态上下文用SLF4J得到logger用。
		System.out.println(MethodHandle.class);
		System.out.println(handle.getClass());
		System.out.println(MethodHandles.lookup().lookupClass()); // like getClass()
	}

}
