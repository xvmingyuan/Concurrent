package Concurrent_001.T16;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * synchronized优化
 * 同步代码快中的语句越少越好
 * 比较m1 和 m2
 * @author xmy
 * @time：2019年1月27日 下午4:20:26
 */
public class T {


	int count = 0;

	synchronized void m1() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 业务逻辑中 只有下面这句需要sync ，这是不应该给整个方法上锁
		count ++;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//采用细粒度的锁，科颜氏使用线程争用时间变短，从而提高效率
		synchronized(this) {
			count ++;
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
