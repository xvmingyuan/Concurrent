package Concurrent_001.T18;
/**
 * 警示：不要以字符串常量作为锁对象
 * 在下面的例子中，m1和m2其实锁定的是同一个对象
 * 这种情况还会发生比较诡异的现象，比如你用到的了一个类库，在该类库中代码锁定了字符串“Hello”
 * 但是你读不到源码，所以你在自己的代码中也锁定了“Hello”，这时候就有可能发生非常诡异的死锁阻塞
 * @author xmy
 * @time：2019年1月27日 下午4:32:42
 */
public class T {
	String s1 = "Hello";
	String s2 = "Hello";
	void m1() {
		synchronized (s1) {
			
		}
	}
	void m2() {
		synchronized (s2) {
			
		}
	}
}
