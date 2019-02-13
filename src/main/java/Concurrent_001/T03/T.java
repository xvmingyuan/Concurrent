package Concurrent_001.T03;

/**
 * synchronized 关键字
 * 对某个对象加锁（对象锁记录在堆内存中）
 * 锁定的是什么？ 对象（无论自身（this对象），还是其他对象）
 * @author xmy
 * @time：2019年1月24日 下午4:37:17
 */
public class T {
	private int count = 10;
	public synchronized void m() {
			count -- ;
			System.out.println(Thread.currentThread().getName()+"count"+count);
	}
}