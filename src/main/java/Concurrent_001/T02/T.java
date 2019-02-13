package Concurrent_001.T02;

/**
 * synchronized 关键字 对某个对象加锁（对象锁记录在堆内存中） 
 * 锁定的是什么？ 对象（无论自身（this），还是其他对象）
 * 
 * @author xmy
 * @time：2019年1月24日 下午4:37:17
 */
public class T {
	private int count = 10;

	public void m() {
		synchronized (this) {
			count--;
			System.out.println(Thread.currentThread().getName() + "count" + count);
		}

	}
}
