package Concurrent_001.T22;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * 
 * ThreadLocal 是 使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就纯在与ThreadLocal中，避免synchronized的使用
 * 
 * 每个线程都复制一个份 各自维护自己的那一份数据 互不影响
 * 每个线程可以有自己的Session，这样多线程时Session 不会冲突
 * @author xmy
 * @time：2019年1月29日 上午10:59:45
 */
public class ThreadLocal2 {
	static ThreadLocal<Person> tl = new ThreadLocal<>();

	public static void main(String[] args) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(tl.get());
		}).start();
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tl.set(new Person());
		}).start();

	}
	static class Person{
		public String name = "zhangsan";
	}
}


