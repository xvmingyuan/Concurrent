package Concurrent_001.T25;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

import com.sun.glass.ui.TouchInputSupport;

public class T01_ConcurrentMap {
	public static void main(String[] args) {
		// 线程安全 无顺序 分段锁定(锁粒度较细)
		Map<Object, Object> map = new ConcurrentHashMap<>();

		// 线程安全 跳表结构（插入速度快）
		// Map<Object, Object> map = new ConcurrentSkipListMap<>();

		// 线程不安全 无顺序
		// Map<Object, Object> map = new HashMap<>();

		// 线程安全 整个对象锁定
		// Map<Object, Object> map = new Hashtable<>();

		// 树结构(红黑树),线程不安全，有顺序
		// TreeMap 

		Random r = new Random();
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch(ths.length);
		long start = System.currentTimeMillis();
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				// 装1W个随机字符串
				for (int j = 0; j < 10000; j++) {
					map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
				}
				latch.countDown();
			});
		}
		// 启动线程数组
		Arrays.asList(ths).forEach(t -> t.start());

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println(end - start);

	}
}
