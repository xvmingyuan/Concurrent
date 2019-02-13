package Concurrent_001.T20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * reentrantlock 用于替代 synchronized
 * 本例中由于m1 锁定this， 只有m1执行完毕的时候，m2才能执行
 * 
 * 使用reentrantlock 可以完成同样的功能
 * 需要注意的是，必须必须必须要手动释放锁（重要的事说三遍）
 * 使用sync锁定的话 如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * @author xmy
 * @time：2019年1月28日 上午9:35:37
 */
public class ReentrantLook2 {
	Lock lock = new ReentrantLock();

	void m1() {
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {

				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	void m2() {
		lock.lock();
		System.out.println("m2..");
		lock.unlock();
	}

	public static void main(String[] args) {
		ReentrantLook2 r2 = new ReentrantLook2();
		new Thread(r2::m1, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r2::m2, "t2").start();
	}

}
