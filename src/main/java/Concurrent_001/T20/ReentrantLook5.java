package Concurrent_001.T20;
/**
 * ReentrantLock 指定为公平锁
 * @author xmy
 * @time：2019年1月28日 下午2:52:56
 */

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLook5 extends Thread{
	private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示为公平锁
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+"获得锁");
			} finally {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		ReentrantLook5 r = new ReentrantLook5();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		t1.start();
		t2.start();
		t3.start();
		
	}
}
