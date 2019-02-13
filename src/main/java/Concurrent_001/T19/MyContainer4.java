package Concurrent_001.T19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer4 {


	/** volatile 使线程间可见 */
	volatile List<Object> list = new ArrayList<>();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer4 myContainer4 = new MyContainer4();

		final Object lock = new Object();

		new Thread(() -> {
			synchronized (lock) {
				System.out.println("t2监听。。。");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t2 结束");
				lock.notify();
			}
		}, "t2").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(() -> {
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					myContainer4.add(new Object());
					System.out.println("add" + i);
					if (myContainer4.size() == 5) {
						lock.notify();
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();

	}


	
}
