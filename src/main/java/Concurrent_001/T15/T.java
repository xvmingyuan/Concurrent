package Concurrent_001.T15;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 解决同样的问题的更高效的方法，使用AtomicXXX类
 * AtomicXXX类本身方法都是原子性的，但是不能保证多个方法连续调用是原子性的
 * @author xmy
 * @time：2019年1月27日 下午4:14:46
 */

public class T {
	/* Java util concurrent atomic包中的并发integer */
	AtomicInteger count = new AtomicInteger(0);

	void m() {
		for (int i = 0; i < 30000; i++) {
			// if count.get()<1000  //此时 if 和 incrementAndGet 之间还是存在原子问题
			count.incrementAndGet(); // 替代  ++
		}
	}

	public static void main(String[] args) {
		T t = new T();
		ArrayList<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m, "thread" + i));
		}
		threads.forEach((o) -> o.start());

		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(t.count);
	}

}
