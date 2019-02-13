package Concurrent_001.T04;


/**
 * synchronized 关键字 对某个对象加锁（对象锁记录在堆内存中） 锁定的是什么？ 对象（无论自身（this），还是其他对象）
 * 由于静态属性和方法没有实例对象，所以锁定的是当前方法的class对象
 * @author xmy
 * @time：2019年1月24日 下午4:37:17
 */
public class T {
	private static int count = 10;

	public synchronized static void m() {
		count--;
		System.out.println(Thread.currentThread().getName() + "count" + count);
	}

	public static void mm() {
		synchronized (T.class) {//这里不能使用 synchronized(this) 因为是static
			count--;
		}
	}
}
