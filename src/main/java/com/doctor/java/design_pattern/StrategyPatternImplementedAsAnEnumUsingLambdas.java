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
package com.doctor.java.design_pattern;

/**
 * Strategy Pattern Implemented as an Enum Using Lambdas
 * 
 * The Strategy Pattern is one of the Gang of Four design patterns published in their book: Elements of Reusable Object-Oriented Software. The intent of the strategy pattern is:
 * 
 * Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
 * 
 * @author doctor
 * 
 * @see https://dzone.com/articles/strategy-pattern-implemented-as-an-enum-using-lamb
 *
 * @time 2015年8月3日 上午9:51:57
 * 
 */
public class StrategyPatternImplementedAsAnEnumUsingLambdas {

	@FunctionalInterface
	interface OperationStrategy<T> {

		T compute(T x, T y);

	}

	static enum Operation implements OperationStrategy<Double> {
		ADD((x, y) -> x + y),
		SUBTRACT((x, y) -> x - y),
		MULTIPLY((x, y) -> x + y);

		private OperationStrategy<Double> operationStrategy;

		Operation(final OperationStrategy<Double> operationStrategy) {
			this.operationStrategy = operationStrategy;
		}

		@Override
		public Double compute(Double x, Double y) {
			return operationStrategy.compute(x, y);
		}

	}

	public static void main(String[] args) {
		System.out.println(Operation.ADD.compute(10D, 12D));
		System.out.println(Operation.SUBTRACT.compute(10D, 12D));
		System.out.println(Operation.MULTIPLY.compute(10D, 12D));

	}

}
