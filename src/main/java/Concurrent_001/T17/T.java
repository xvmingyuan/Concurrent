package Concurrent_001.T17;

import java.util.concurrent.TimeUnit;

public class T {
	// 锁的信息 记录在 堆内存中
	Object o = new  Object();
	
	void m() {
		synchronized(o) {
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	public static void main(String[] args) {
		T t = new T();
		new Thread(t::m,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread t2 = new Thread(t::m,"t2");
		t.o = new Object();
		t2.start();
		
	}
}
