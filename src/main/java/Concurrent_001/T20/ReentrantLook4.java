package Concurrent_001.T20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock 用于替代 synchronized 本例中由于m1 锁定this， 只有m1执行完毕的时候，m2才能执行
 * 
 * 使用reentrantlock 可以完成同样的功能 需要注意的是，必须必须必须要手动释放锁（重要的事说三遍） 使用sync锁定的话
 * 如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * 
 * 使用reentrantlock 可以进行“尝试锁定” trylock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 * 
 * 使用ReetrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 * 
 * @author xmy
 * @time：2019年1月28日 上午9:35:37
 */
public class ReentrantLook4 {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Thread t1 = new Thread(() -> {
			try {
				lock.lock();
				System.out.println("t1 start");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				System.out.println("t1 end");
			} catch (InterruptedException e) {
				System.out.println("interrupted!");
			} finally {
				lock.unlock();
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			try {
				System.out.println("t2 start");
				// lock.lock();
				lock.lockInterruptibly(); // 可以对interrupt（）方法做出响应
				TimeUnit.SECONDS.sleep(5);
				System.out.println("t2 end");
			} catch (InterruptedException e) {
				System.out.println("t2 interrupted");
			} finally {
				if (lock.tryLock()) {//当拿不到锁的时候 不需要释放锁
					lock.unlock();
				}
			}
		});
		t2.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.interrupt();
	}

}
