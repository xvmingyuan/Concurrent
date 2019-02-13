package Concurrent_001.T01;
/**
 * synchronized 关键字
 * 对某个对象加锁（对象锁记录在堆内存中）
 * 2个线程同时申请o对象锁，当一个线程拿到o对象的锁，其他线程拿不到o对象锁
 * @author xmy
 * @time：2019年1月24日 下午4:37:17
 */
public class T {
	private int count = 10;
	private Object o = new Object();
	public void m() {
		synchronized (o) {
			count -- ;
			System.out.println(Thread.currentThread().getName()+"count"+count);
		}
	}
}
