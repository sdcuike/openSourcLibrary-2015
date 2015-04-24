package com.doctor.java.design_pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单例模式的几种实现--《java开发技术-在架构中体验设计模式和算法之美》
 * 
 * @author doctor
 *
 * @time 2015年4月24日 下午11:11:03
 */
public class SingletonPattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LazySingleton.getInstance();
		LazySingleton.getInstance();
		LazySingleton.getInstance();
		//
		EagerSingleton.getInstance();
		EagerSingleton.getInstance();

		//
		DoubleCheckLazySingleton.getInstance();
		DoubleCheckLazySingleton.getInstance();
		DoubleCheckLazySingleton.getInstance();

		//
		Singleton.getInstance();
		Singleton.getInstance();

		//
		EnumSingleton.instance.EnumSingletonOperation();
		EnumSingleton.instance.EnumSingletonOperation();

	}

	/**
	 * 懒汉式单例,线程安全
	 */
	public static class LazySingleton {
		private static final Logger log = LoggerFactory.getLogger(LazySingleton.class);
		// 私有静态对象，加载时候不做初始化
		private static LazySingleton singleton = null;

		// 私有构造方法，避免外部创建实例
		private LazySingleton() {
			log.info(LazySingleton.class.getName() + "单例 构造函数调用");
		}

		/**
		 * 静态工厂方法，返回此类的唯一实例 当发现实例没有初始化的时候，才初始化
		 * 
		 * @return
		 */
		public static synchronized LazySingleton getInstance() {
			if (singleton == null) {
				singleton = new LazySingleton();
			}
			return singleton;
		}
	}

	/**
	 * 懒汉式单例,线程安全，双重加锁提高访问速度
	 */
	public static class DoubleCheckLazySingleton {
		private static final Logger log = LoggerFactory.getLogger(DoubleCheckLazySingleton.class);
		// 私有静态对象，加载时候不做初始化
		private static DoubleCheckLazySingleton singleton = null;

		// 私有构造方法，避免外部创建实例
		private DoubleCheckLazySingleton() {
			log.info(DoubleCheckLazySingleton.class.getName() + "单例 构造函数调用");
		}

		/**
		 * 静态工厂方法，返回此类的唯一实例 当发现实例没有初始化的时候，才初始化
		 * 
		 * @return
		 */
		public static DoubleCheckLazySingleton getInstance() {
			if (singleton == null) {
				synchronized (DoubleCheckLazySingleton.class) {
					if (singleton == null) {
						singleton = new DoubleCheckLazySingleton();
					}
				}
			}
			return singleton;
		}
	}

	/**
	 * 饿汉式单例
	 */
	private static class EagerSingleton {
		private static final Logger log = LoggerFactory.getLogger(EagerSingleton.class);

		private static final EagerSingleton instance = new EagerSingleton();

		private EagerSingleton() {
			log.info(EagerSingleton.class.getName() + "单例 构造函数调用");

		}

		public static EagerSingleton getInstance() {
			return instance;
		}
	}

	/**
	 * 延迟加载+线程安全=内部类+静态初始化多线程默认同步锁
	 */

	public static class Singleton {
		private static final Logger log = LoggerFactory.getLogger(Singleton.class);

		private Singleton() {
			log.info(Singleton.class.getName() + "单例 构造函数调用");
		}

		public static Singleton getInstance() {
			return SingletonHolder.instance;
		}

		/**
		 * 类级的内部类，也就是静态的成员内部类，该内部类的实例与外部类的实例没有绑定关系，而且只有被调用到才会装载，而从实现 了延迟加载
		 */
		private static class SingletonHolder {
			// 静态初始化，由JVM来保证线程安全
			private static Singleton instance = new Singleton();
		}
	}

	/**
	 * 枚举实现单实例控制，提供序列化，且由jvm保证安全
	 */

	public static enum EnumSingleton {
		// 定义一个枚举元素，它只代表了EnumSingleton的一个实例。
		instance;
		/**
		 * 实例方法，单例可以有自己的操作
		 */
		public void EnumSingletonOperation() {
			// 功能处理
		}
	}
}
