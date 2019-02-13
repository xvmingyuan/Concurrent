package Concurrent_001.T19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法， add ，size 写两个线程，线程1添加10个元素到容器中，线程2实现监视元素的个数，当个数到5时，线程2给出提示并结束
 * 分析： 给list 加上volatile之后 t2能够收到通知，t2线程 while 循环浪费cpu ，如果不用死循环怎么做？
 * 
 * 解决： 使用wait 和notify， wait会释放锁，而notify不会释放锁； 注意：运用这种方法 t2需要先执行，让t2先监听
 * 问题：并不是size =5是 t2退出，而是t1结束是t2才退出
 * @author xmy
 * @time：2019年1月27日 下午4:50:52
 */
public class MyContainer3 {

	/** volatile 使线程间可见 */
	volatile List<Object> list = new ArrayList<>();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer3 myContainer3 = new MyContainer3();

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
					myContainer3.add(new Object());
					System.out.println("add" + i);
					if (myContainer3.size() == 5) {
						lock.notify();
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
