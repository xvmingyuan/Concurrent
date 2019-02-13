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
 * @author xmy
 * @time：2019年1月28日 上午9:35:37
 */
public class ReentrantLook3 {
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

	/**
	 * 使用trylock 进行尝试锁定，不管锁定与否，方法都讲继续进行 可以根据trylock的返回值判断是否锁定
	 * 也可以指定trylock的时间，由于trylock(time)抛出异常，所以要注意unlock的处理，必须放到finally中
	 */
	void m2() {
		boolean locked = false;
		try {
			locked = lock.tryLock(5, TimeUnit.SECONDS);
			System.out.println("m2.." + locked);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (locked) {
				lock.unlock();
			}
		}

	}

	public static void main(String[] args) {
		ReentrantLook3 r3 = new ReentrantLook3();
		new Thread(r3::m1, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r3::m2, "t2").start();
	}

}
