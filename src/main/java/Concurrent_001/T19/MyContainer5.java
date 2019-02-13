package Concurrent_001.T19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainer5 {

	/** volatile： 使线程间可见 */
	volatile List<Object> list = new ArrayList<>();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer5 myContainer5 = new MyContainer5();

		CountDownLatch d = new CountDownLatch(1);

		new Thread(() -> {
			System.out.println("t2监听。。。");
			try {
				d.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t2 结束");
		}, "t2").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				myContainer5.add(new Object());
				System.out.println("add" + i);
				if (myContainer5.size() == 5) {
					d.countDown();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();

	}

}
