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
 * Creating a resource that can be used with
 * the try-with-resources technique
 * 
 * @author doctor
 *
 * @time 2015年8月22日 上午10:39:09
 * 
 *       To create a resource that can be used with the try-with-resources technique:
 *       1. Create a class that implements the java.lang.AutoCloseable interface.
 *       2. Override the close method.
 *       3. Implement resource-specific methods.
 */
public class CreatingAResourceThatCanBeUsedWithTheTryWithResourcesTechnique {

	public static void main(String[] args) {

		try (FirstAutoCloseableResource firstAutoCloseableResource = new FirstAutoCloseableResource();
				SecondAutoCloseableResource secondAutoCloseableResource = new SecondAutoCloseableResource();) {
			firstAutoCloseableResource.manipulateResource();
			secondAutoCloseableResource.manipulateResource();

		} catch (Exception e) {
			System.out.println(e);
			for (Throwable throwable : e.getSuppressed()) {
				System.out.println(throwable);
			}
		}

		System.out.println("end ------------");
	}

	public static class FirstAutoCloseableResource implements AutoCloseable {

		@Override
		public void close() throws Exception {
			System.out.println("FirstAutoCloseableResource close()");
			throw new UnsupportedOperationException("UnsupportedOperationException in FirstAutoCloseableResource close()");
		}

		public void manipulateResource() {
			System.out.println(" manipulateResource() in FirstAutoCloseableResource");
		}

	}

	public static class SecondAutoCloseableResource implements AutoCloseable {

		@Override
		public void close() throws Exception {
			System.out.println("SecondAutoCloseableResource close()");
			throw new UnsupportedOperationException("UnsupportedOperationException in SecondAutoCloseableResource close()");
		}

		public void manipulateResource() {
			System.out.println(" manipulateResource() in SecondAutoCloseableResource");
		}

	}
}
