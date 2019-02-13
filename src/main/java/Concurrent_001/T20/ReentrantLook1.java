package Concurrent_001.T20;

import java.util.concurrent.TimeUnit;

/**
 * reentrantlock 用于替代 synchronized
 * 本例中由于m1 锁定this， 只有m1执行完毕的时候，m2才能执行
 * @author xmy
 * @time：2019年1月28日 上午9:35:37
 */
public class ReentrantLook1 {
	synchronized void m1() {
		for (int i = 0; i < 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
	synchronized void m2() {
		System.out.println("m2..");
	}
	public static void main(String[] args) {
		ReentrantLook1 r1 = new ReentrantLook1();
		new Thread(r1::m1,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2,"t2").start();
	}
}
