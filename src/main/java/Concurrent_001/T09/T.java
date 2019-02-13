package Concurrent_001.T09;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象锁
 * 也就是说 synchronized 获得的锁是可重入的
 * @author xmy
 * @time：2019年1月24日 下午5:15:06
 */
public class T {
	synchronized void m1() {
		System.out.println("m1 start");
		m2();
	}
	
	synchronized void m2() {
		System.out.println("m2 start");
	}
	public static void main(String[] args) {
		T t = new T();
		new Thread(t::m1,"t1").start();
	}
}
