package Concurrent_001.T25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
/**
 * CopyOnWriteArrayList 线程安全
 * CopyOnWriteArrayList ：写时复制，读效率非常高，写效率非常低，
 * 适用：读不用加锁，写的很少，读的非常多
 * 场景：事件监听器
 * @author xmy
 * @time：2019年1月30日 下午5:43:49
 */
public class T02_CopyOnWriteList {
	public static void main(String[] args) {
		List<String> lists =
				// new ArrayList<>();
//				 new Vector<>();
				new CopyOnWriteArrayList<>();
		Random r = new Random();
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch(ths.length);

		for (int i = 0; i < ths.length; i++) {
			Runnable task = new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						lists.add("a" + r.nextInt(100000));
					}
				}
			};
			ths[i] = new Thread(task);
		}
		runAndComputeTime(ths);
		System.out.println(lists.size());
	}

	private static void runAndComputeTime(Thread[] ths) {
		// 启动线程数组
		long start = System.currentTimeMillis();
		Arrays.asList(ths).forEach(t -> t.start());
		Arrays.asList(ths).forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
