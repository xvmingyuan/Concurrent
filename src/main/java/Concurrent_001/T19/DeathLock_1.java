package Concurrent_001.T19;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeathLock_1 {
	
	Object a = new Object();
	Object b = new Object();
	static ExecutorService service = Executors.newCachedThreadPool();
	final static CountDownLatch cdAnswer = new CountDownLatch(2);
	Thread t1 = new Thread() {
		@Override
		public void run() {
			cdAnswer.countDown();
			synchronized (a) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("get a, want b");
				synchronized(b) {
					System.out.println("get b");
				}
			}
		}
	};
	Thread t2 = new Thread() {
		@Override
		public void run() {
			cdAnswer.countDown();
			synchronized (b) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("get b, want a");
				synchronized(a) {
					System.out.println("get a");
				}
			}
		}
	};

	public static void main(String[] args) throws InterruptedException {
		DeathLock_1 lock_1 = new DeathLock_1();
		service.execute(lock_1.t1);
		service.execute(lock_1.t2);
		cdAnswer.await();
	
	}
}
