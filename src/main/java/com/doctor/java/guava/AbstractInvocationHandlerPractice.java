package com.doctor.java.guava;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.reflect.AbstractInvocationHandler;

/**
 * Proxies done right with Guava’s AbstractInvocationHandler
 * 
 * @see http://www.javacodegeeks.com/2013/12/proxies-done-right-with-guavas-abstractinvocationhandler.html
 * 
 * @author doctor
 *
 * @time 2015年4月24日 上午8:59:58
 */
public class AbstractInvocationHandlerPractice {

	public static void main(String[] args) {
		MailServer mailServer = new RealMailServer();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		MailServer wrap = AsyncProxy.wrap(mailServer, executorService);
		wrap.send("hello");
		System.out.println(wrap.hashCode()); // object的方法不会代理
		executorService.shutdown();
	}

	public static interface MailServer {
		void send(String message);

		int unreadCount();
	}

	public static class RealMailServer implements MailServer {

		@Override
		public void send(String message) {
			System.out.println(RealMailServer.class.getName() + " send " + message + "--" + Thread.currentThread().getName());

		}

		@Override
		public int unreadCount() {
			return 0;
		}

	}

	public static class AsyncProxy {
		public static <T> T wrap(final T underlying, final ExecutorService executorService) {
			return (T) Proxy.newProxyInstance(underlying.getClass().getClassLoader(),
					underlying.getClass().getInterfaces(),
					new AsyncHandler<T>(underlying, executorService));
		}
	}

	public static class AsyncHandler<T> extends AbstractInvocationHandler {
		private final T underlying;
		private final ExecutorService executorService;

		public AsyncHandler(final T underlying, final ExecutorService executorService) {
			this.underlying = underlying;
			this.executorService = executorService;
		}

		@Override
		protected Object handleInvocation(final Object proxy, final Method method, final Object[] args) throws Throwable {

			Future<Object> future = executorService.submit(() -> {
				return method.invoke(underlying, args);
			});

			Object handleResult = handleResult(method, future);

			return handleResult;
		}

		private Object handleResult(Method method, Future<Object> future) throws InterruptedException, ExecutionException {
			if (method.getReturnType() == void.class) {
				return null;
			}
			return future.get();
		}

	}
}
